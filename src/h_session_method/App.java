package h_session_method;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()
			.configure().addClass(User.class).buildSessionFactory();

	@Test
	public void testSave() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			User user = new User();
			user.setName("test");
			session.save(user);

			tx.commit(); // 必须提交事务才能持久化成功
			user.setName("newName");
			System.out.println(user.getName());
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);

		} finally {
			session.close();
		}
	}

	@Test
	public void testUpdate() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			User user = (User) session.get(User.class, 1);

			// session.clear();
			session.evict(user);
			user.setName("newName3");

			session.update(user);

			tx.commit(); // 必须提交事务才能持久化成功
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);

		} finally {
			session.close();
		}
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// --------------------------------------------

		User user = new User();
		user.setId(1); // 自己生成一个游离状态对象
		user.setName("newName3");

		session.saveOrUpdate(user);

		// --------------------------------------------
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void testGet() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			User user = (User) session.get(User.class, 5); // 持久化
			session.flush();
			System.out.println(user.getClass());

			tx.commit(); // 必须提交事务才能持久化成功
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);

		} finally {
			session.close();
		}
	}

	@Test
	public void testLoad() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			User user = (User) session.load(User.class, 5);
			System.out.println(user);
			System.out.println(user.getClass());

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	@Test
	public void testDelete() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			User user = new User();
			user.setId(4);

			session.delete(user);
			session.flush();

			System.out.println("---");

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	// 操作大量数据，要防止Session中对象过多而内存溢出
		@Test
		public void testBatchSave() throws Exception {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			// --------------------------------------------

			for (int i = 0; i < 30; i++) {
				User user = new User();
				user.setName("测试");
				session.save(user);

//				if (i % 10 == 0) {
//					session.flush(); // 先刷出
//					session.clear(); // 再清空
//				}
			}

			// --------------------------------------------
			session.getTransaction().commit();
			session.close();
		}
}
