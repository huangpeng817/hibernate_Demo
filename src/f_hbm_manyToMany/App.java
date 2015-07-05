package f_hbm_manyToMany;

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
			.addClass(Teacher.class)
			.addClass(Student.class)
			.buildSessionFactory();

	@Test
	public void testSave() {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Teacher teacher1 = new Teacher();
			teacher1.setName("刘老师");
			Teacher teacher2 = new Teacher();
			teacher2.setName("肖老师");
			
			Student student1 = new Student();
			student1.setName("程同学");
			Student student2 = new Student();
			student2.setName("徐同学");
			
			Set<Student> students = new HashSet<Student>();
			students.add(student1);
			students.add(student2);
			teacher1.setStudents(students);
			teacher2.setStudents(students);
			
			Set<Teacher> teachers = new HashSet<Teacher>();
			teachers.add(teacher1);
			teachers.add(teacher2);
			student1.setTeachers(teachers);
			student2.setTeachers(teachers);
			
			session.save(teacher1);
			session.save(teacher2);
			session.save(student1);
			session.save(student2);

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
			
			Teacher teacher = (Teacher) session.get(Teacher.class, 3);
			System.out.println(teacher);
			System.out.println(teacher.getStudents());
			
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
			
//			Teacher teacher = (Teacher) session.get(Teacher.class, 3);
//			teacher.getStudents().clear();
			
			Student student = (Student) session.get(Student.class, 3);
			student.getTeachers().clear();
			
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
			
//			Teacher teacher = (Teacher) session.get(Teacher.class, 3);
//			session.delete(teacher);
			
			Student student = (Student) session.get(Student.class, 4);
			session.delete(student);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}
}
