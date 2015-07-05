package cn.hp.dao;

import java.util.List;

import cn.hp.domain.News;

/*
 * 封装查询结果对象，需要知道总记录数
 * 方法想返回多个值，就需要封装成对象
 */
public class QueryResult {

	private int count;
	private List<News> list;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<News> getList() {
		return list;
	}

	public void setList(List<News> list) {
		this.list = list;
	}

	public QueryResult(int count, List<News> list) {
		super();
		this.count = count;
		this.list = list;
	}
	
	
}
