package cn.hp.domain;

public class News {

	private int id;
	private String title;
	private String content;
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
