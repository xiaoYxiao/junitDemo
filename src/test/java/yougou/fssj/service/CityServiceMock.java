package yougou.fssj.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.databene.benerator.anno.Source;
import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import yougou.fssj.dao.CityDao;
import yougou.fssj.model.City;
import yougou.fssj.service.CityService;

public class CityServiceMock extends BaseTest {
	private CityDao cityDao;
	@Autowired
	private CityService cityService;

	protected String getInitDataFile() {
		return null;
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceMock_Insert.csv")
	public void testCreateCity(String name, String description, int exist) throws Exception {
		City city = new City();
		city.setName(name);
		city.setDescription(description);

		cityDao = EasyMock.createMock(CityDao.class);
		EasyMock.expect(cityDao.add(city)).andReturn(1);

		EasyMock.replay(cityDao);

		cityService.setCityDao(cityDao);
		int ret = cityService.save(city);

		Assert.assertEquals(ret, exist);
		EasyMock.verify(cityDao);
	}

	@Test
	public void testListCity() throws Exception {
		City city = new City();
		List<City> list = new ArrayList<City>();
		list.add(city);

		cityDao = EasyMock.createMock(CityDao.class);
		EasyMock.expect(cityDao.search(0)).andReturn(list);

		EasyMock.replay(cityDao);

		cityService.setCityDao(cityDao);
		List<City> cityList = cityService.find(0);

		Assert.assertTrue(cityList.size() == 1);
		EasyMock.verify(cityDao);
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceMock_Get.csv")
	public void testGetCity(String id, boolean exist) throws Exception {
		City city = new City();
		city.setId(id);

		cityDao = EasyMock.createMock(CityDao.class);
		EasyMock.expect(cityDao.get(id)).andReturn(city);

		EasyMock.replay(cityDao);

		cityService.setCityDao(cityDao);
		City theCity = cityService.get(id);

		Assert.assertEquals(theCity != null, exist);
		EasyMock.verify(cityDao);
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceMock_Update.csv")
	public void testUpdateCity(String id, String name, String description, int exist) throws Exception {
		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setDescription(description);

		cityDao = EasyMock.createMock(CityDao.class);
		EasyMock.expect(cityDao.save(city)).andReturn(1);

		EasyMock.replay(cityDao);

		cityService.setCityDao(cityDao);
		int ret = cityService.save(city);

		Assert.assertEquals(ret, exist);
		EasyMock.verify(cityDao);
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceMock_Delete.csv")
	public void testDelectCity(String id, int exist) throws Exception {
		cityDao = EasyMock.createMock(CityDao.class);
		EasyMock.expect(cityDao.delete(id)).andReturn(1);

		EasyMock.replay(cityDao);

		cityService.setCityDao(cityDao);
		int ret = cityService.delete(id);

		Assert.assertEquals(ret, exist);
		EasyMock.verify(cityDao);
	}
}
