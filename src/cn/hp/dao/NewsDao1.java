package cn.hp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.hp.domain.News;

public class NewsDao1 {

	public static void main(String[] args) {
		/*
		 * 非封装方式使用hibernate
		 */
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		News n = new News();
		n.setTitle("天气预报");
		n.setContent("今天天气真的不错！");
		session.save(n);
		tx.commit();
		session.close();
		sf.close();
		System.out.println("add Successfully!");
	}
}
