package f_hbm_oneToMany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	private static SessionFactory sessionFactory = new Configuration()
			.configure()
			.addClass(Employee.class)
			.addClass(Department.class)
			.buildSessionFactory();

	@Test
	public void testSave() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Department department = new Department();
			department.setName("研发部");
			
			Employee employee1 = new Employee();
			employee1.setName("张三");
			Employee employee2 = new Employee();
			employee2.setName("李四");
			
			Set<Employee> employees = new HashSet<Employee>();
			employees.add(employee1);
			employees.add(employee2);
			
			department.setEmployees(employees);
			
//			employee1.setDepartment(department);
//			employee2.setDepartment(department);
			
			session.save(department);
//			session.save(employee1);
//			session.save(employee2);

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
			
			Employee employee = (Employee) session.get(Employee.class, 9);
			System.out.println(employee);
			System.out.println(employee.getDepartment());
			
			tx.commit(); // 必须提交事务才能持久化成功
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);

		} finally {
			session.close();
		}
	}
	
	@Test
	public void testRemoveRelation() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
//			Department department = (Department) session.get(Department.class, 3);
//			department.getEmployees().clear();
////			department.setEmployees(null);
			Employee employee = (Employee) session.get(Employee.class, 13);
			employee.setDepartment(null);
			
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
			
			Department department = (Department) session.get(Department.class, 3);
			session.delete(department);
			
//			Employee employee = (Employee) session.get(Employee.class, 10);
//			session.delete(employee);
//			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
}
