package e_hbm_collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
			user.setName("流氓");
			Set<String> addressSet = new TreeSet<String>();
			addressSet.add("2成都市高新西区");
			addressSet.add("1北京市天安门");

			user.setAddressSet(addressSet);
			
			List<String> addressList = new ArrayList<String>();
			addressList.add("内江");
			addressList.add("资阳");
			user.setAddressList(addressList);
			
			
			String[] addressArray = new String[] {"春熙路","西源大道"};
			user.setAddressArray(addressArray);
			
			
			Map<String, String> addressMap = new HashMap<String, String>();
			addressMap.put("学校", "UESTC");
			addressMap.put("老家", "资中高楼");
			user.setAddressMap(addressMap);
			
			
			List<String> addressBag = new ArrayList<String>();
			addressBag.add("山东蓝翔");
			addressBag.add("上海城隍庙");
			user.setAddressBag(addressBag);

			session.save(user);
			tx.commit(); // 必须提交事务才能持久化成功
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);

		} finally {
			session.close();
		}
	}

	@Test
	public void testGet() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, 9);
			System.out.println(user);
			tx.commit(); // 必须提交事务才能持久化成功
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);

		} finally {
			session.close();
		}
	}
}
