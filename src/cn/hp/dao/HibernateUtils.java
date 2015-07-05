package cn.hp.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import cn.hp.domain.User;

/*
 * 封装Hibernate的基本操作工具类
 */
public class HibernateUtils {

	// 工厂只能有一个
	private static SessionFactory sessionFactory;

	/*
	 * 静态快，只需要执行一次
	 */
	static {
		Configuration config = new Configuration().configure();// 通过加载hibernate.cfg.xml文件得到Configuration对象
				/* 相当于hibernate.cfg.xml文件中加载<mapping resource="cn/hp/domain/User.hbm.xml"/>
				hibernate.cfg.xml中的<mapping resource="cn/hp/domain/User.hbm.xml"/>可以注释掉  */
//				.addClass(User.class); 
		sessionFactory = config.buildSessionFactory(); // 构建会话工厂
	}

//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}

	/*
	 * 开启会话
	 */
	public static  Session openSession() {
		return sessionFactory.openSession();
	}

}
