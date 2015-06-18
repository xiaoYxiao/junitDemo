package yougou.fssj.service;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import yougou.fssj.dao.CityDao;
import yougou.fssj.model.City;

public class CityDaoTest extends BaseTest {
	@Autowired
	private CityDao cityDao;

	protected String getInitDataFile() {
		return "yougou/fssj/service/CityDaoTest_Init.xml";
	}

	@Test
	public void testInsertCity() throws SQLException {
		String id = "125";
		String name = "深圳";
		String description = "深圳是一个美好的地方";
		boolean exist = true;

		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setDescription(description);
		cityDao.add(city);

		Assert.assertEquals(cityDao.get(id) != null, exist);
	}

	@Test
	public void testSearchCity() throws SQLException {
		List<City> list = cityDao.search(0);
		Assert.assertTrue(list.size() == 1);
	}

	@Test
	public void testGetCity() throws SQLException {
		String id = "123";
		boolean exist = true;

		City city = cityDao.get(id);
		Assert.assertEquals(city != null, exist);
	}

	@Test
	public void testUpdateCity() throws SQLException {
		String id = "123";
		String name = "深圳";
		String description = "欢迎来到深圳";
		String newName = "深圳";

		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setDescription(description);
		cityDao.save(city);

		City city3 = cityDao.get(id);
		Assert.assertEquals(city3.getName(), newName);
	}

	@Ignore
	public void testDelectCity() throws SQLException {
		String id = "123";
		City exist = null;
		cityDao.delete(id);
		City city = cityDao.get(id);
		Assert.assertEquals(city, exist);
	}
}
