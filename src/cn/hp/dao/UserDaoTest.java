package cn.hp.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.junit.Test;

import cn.hp.domain.User;

public class UserDaoTest {

	UserDao userDao = new UserDao();
	
	@Test
	public void testSave() throws IOException {
		User user = new User();
		user.setName("丽斯");
		user.setBirthday(new Date());
		user.setDesc("一段文字说明");
		
		InputStream in = new FileInputStream("D:\\1.png");
		byte[] photo = new byte[in.available()];
		in.read(photo);
		in.close();
		user.setPhoto(photo);
		userDao.save(user);
	}

	@Test
	public void testGet() throws IOException {
		
		User user = userDao.get(3);  // id=3才有photo数据
		
		OutputStream out = new FileOutputStream("D:\\copy.png");
		out.write(user.getPhoto());
		out.close();
		System.out.println(user);
	}

}
