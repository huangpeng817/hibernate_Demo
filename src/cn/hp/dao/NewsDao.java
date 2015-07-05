package cn.hp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.hp.domain.News;

/*
 * 封装对象的增删改查方法
 */
public class NewsDao {

	/*
	 * 插入一条记录（对象）
	 */
	public void save(News news) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(news);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	/*
	 * 修改对象
	 */
	public void update(News news) {
		Session session = HibernateUtils.openSession();
		Transaction tx =  null;
		try {
			tx = session.beginTransaction();
			session.update(news);
			tx.commit();
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	/*
	 * 根据id删除一个对象（首先通过id获取对象，再删除对象）
	 */
	public void delete(int id) {
		Session session = HibernateUtils.openSession();
		Transaction tx =  null;
		try {
			tx = session.beginTransaction();
			News news = (News) session.get(News.class, id);
			session.delete(news);
			tx.commit();
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}
	
	/*
	 * 通过id查询一个对象
	 */
	public News findById(int id) {
		Session session = HibernateUtils.openSession();
		Transaction tx =  null;
		try {
			tx = session.beginTransaction();
			News news = (News) session.get(News.class, id);
			tx.commit();
			return news;
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	/*
	 * 查询所有对象集合
	 */
	public List<News> findAll() {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			/*
			 * 方式一：使用HQL语句进行查询
			 */
//			List<News> newsList = session.createQuery("FROM News").list();
			/*
			 * 方式二：构建查询条件进行查询（可以添加限制条件和排序条件）
			 */
			Criteria criteria =  session.createCriteria(News.class);
//			criteria.add(Restrictions.eq("id", 5)); // id = 5
//			criteria.add(Restrictions.ge("id", 5)); // id >= 5
//			criteria.add(Restrictions.lt("id", 5)); // id < 5
			criteria.add(Restrictions.between("id", 3, 12)); // id < 5
			criteria.addOrder(Order.desc("id")); // 以id属性降序
			
			List<News> newsList = criteria.list();
			tx.commit();
			return newsList;
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	/*
	 * 分页查询对象集合
	 */
	public QueryResult findAll(int firstResult, int maxResults) {
		Session session = HibernateUtils.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<News> newsList = session.createQuery("FROM News")
					.setFirstResult(firstResult)
					.setMaxResults(maxResults)
					.list();
			// 返回的记录条数是Long型，并且为唯一值
			Long count = (Long) session.createQuery("SELECT COUNT(*) FROM News")
					.uniqueResult();
			tx.commit();
			return new QueryResult(count.intValue(), newsList);
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
