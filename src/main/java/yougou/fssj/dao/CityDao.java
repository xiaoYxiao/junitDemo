package yougou.fssj.dao;

import java.sql.SQLException;
import java.util.List;

import yougou.fssj.model.City;

public interface CityDao {
	public int add(City city) throws SQLException;

	public int delete(String id) throws SQLException;

	public int save(City city) throws SQLException;

	public City get(String id) throws SQLException;

	public List<City> search(int startNumber) throws SQLException;
}
