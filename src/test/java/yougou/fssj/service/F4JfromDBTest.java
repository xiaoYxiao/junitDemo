package yougou.fssj.service;

import org.databene.benerator.anno.Database;
import org.databene.benerator.anno.Source;
import org.databene.feed4junit.Feeder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Feeder.class)
public class F4JfromDBTest {

	@Test
	@Database(id = "count", url = "jdbc:mysql://localhost:3306/test?useUnicode=true", 
			  driver = "com.mysql.jdbc.Driver", 
			  user = "root", password = "yougou")
	@Source(id = "count", selector = "select count(*) from user")
	public void testGetCity(int count) {
		Assert.assertTrue(count>0);

	}
}
