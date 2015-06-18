package yougou.fssj.service;

import java.util.List;

import yougou.fssj.dao.CityDao;
import yougou.fssj.model.City;

public class CityServiceImpl implements CityService {
	private CityDao cityDao;

	public void setCityDao(CityDao cityDba) {
		this.cityDao = cityDba;
	}

	public int delete(String id) throws Exception {
		return cityDao.delete(id);
	}

	public List<City> find(int startNumber) throws Exception {
		List<City> al = cityDao.search(startNumber);
		return al;
	}

	public City get(String id) throws Exception {
		return cityDao.get(id);
	}

	public int save(City city) throws Exception {
		int ret = 0;
		if (city.getId() == null) {
			long id = Math.round(Math.random() * 1000000);
			city.setId(Long.toString(id));
			ret = cityDao.add(city);
		} else
			ret = cityDao.save(city);
		return ret;
	}

	public CityDao getCityDao() {
		return cityDao;
	}

}
