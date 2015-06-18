package yougou.fssj.service;

import java.util.List;

import junit.framework.Assert;

import org.databene.benerator.anno.Source;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import yougou.fssj.model.City;
import yougou.fssj.service.CityService;

public class CityServiceTest extends BaseTest {
	@Autowired
	private CityService cityService;

	protected String getInitDataFile() {
		return "yougou/fssj/service/CityServiceTest_Init.xml";
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceTest_Insert.csv")
	public void testCreateCity(String name, String description, boolean exist) throws Exception {
		City city = new City();
		city.setName(name);
		city.setDescription(description);
		cityService.save(city);

		String id = city.getId();
		Assert.assertEquals(cityService.get(id) != null, exist);
	}

	@Test
	public void testListCity() throws Exception {
		List<City> cityList = cityService.find(0);
		Assert.assertTrue(cityList.size() == 1);
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceTest_Get.csv")
	public void testGetCity(String id, boolean exist) throws Exception {
		City city = cityService.get(id);
		Assert.assertEquals(city != null, exist);
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceTest_Update.csv")
	public void testUpdateCity(String id, String name, String description, String newName) throws Exception {
		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setDescription(description);
		cityService.save(city);

		City city3 = cityService.get(id);
		Assert.assertNotNull(city3);
		Assert.assertEquals(city3.getName(), newName);
	}

	@Test
	@Source(uri = "yougou/fssj/service/CityServiceTest_Delete.csv")
	public void testDelectCity(String id, String exist) throws Exception {
		cityService.delete(id);
		City city = cityService.get(id);
		Assert.assertEquals(city, exist);
	}
}
