package cn.hp.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.hp.domain.News;

/*
 * 使用JUnit测试增删改查方法
 */
public class NewsDaoTest {

	private NewsDao newsDao = new NewsDao();
	
	@Test
	public void testSave_1() {
		News news = new News();
		news.setTitle("标题1");
		news.setContent("内容1");
		newsDao.save(news);
	}

	@Test
	public void testSave_25() {
		for (int i = 1; i <= 25; i++) {
			News news = new News();
			news.setTitle("标题" + i);
			news.setContent("内容" + i);
			newsDao.save(news);
		}
	}

	@Test
	public void testUpdate() {
		News news = newsDao.findById(3);
		news.setTitle("更新标题");
		news.setContent("更新标题");
		newsDao.update(news);
	}

	@Test
	public void testDelete() {
		
		newsDao.delete(5);
	}

	@Test
	public void testFindById() {
		newsDao.findById(5);
	}

	@Test
	public void testFindAll() {
		List<News> newsList = newsDao.findAll();
		for (News news : newsList) {
			System.out.println(news);
		}
	}

	@Test
	public void testFindAllIntInt() {
//		QueryResult qr = newsDao.findAll(0, 10);
//		QueryResult qr = newsDao.findAll(10, 10);
		QueryResult qr = newsDao.findAll(20, 10);
		System.out.println("总记录数："+qr.getCount());
		List<News> newsList = qr.getList();
		for (News news : newsList) {
			System.out.println(news);
		}
	}

}
