package yougou.fssj.service;

import java.util.List;

import yougou.fssj.dao.CityDao;
import yougou.fssj.model.City;

public interface CityService {
	public int delete(String id) throws Exception;

	public int save(City city) throws Exception;

	public City get(String id) throws Exception;

	public List<City> find(int startNumber) throws Exception;

	public void setCityDao(CityDao cityDao);
}
