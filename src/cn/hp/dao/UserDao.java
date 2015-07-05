package cn.hp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.hp.domain.User;

public class UserDao {

	public void save(User user) {
		Session session = HibernateUtils.openSession();
//		Transaction tx = null;
		try {
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception e) {
//			tx.rollback();
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
	
	public User get(int id) {
		Session session = HibernateUtils.openSession();
		Transaction tx = session.beginTransaction();
		try {
			User user = (User) session.get(User.class, id);
			tx.commit();
			return user;
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
}
