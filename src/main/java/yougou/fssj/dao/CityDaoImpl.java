package yougou.fssj.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import yougou.fssj.model.City;

public class CityDaoImpl implements CityDao {
	private SqlMapClientTemplate template;
	private static final String nameSpace = "yougou.fssj.model.City.";

	public int add(City city) throws SQLException {
		template.insert(nameSpace + "insert", city);
		return 1;
	}

	public int save(City city) throws SQLException {
		return template.update(nameSpace + "update", city);
	}

	public int delete(String id) throws SQLException {
		return template.delete(nameSpace + "delete", id);
	}

	public City get(String id) throws SQLException {
		return (City) template.queryForObject(nameSpace + "get", id);
	}

	public List<City> search(int startNumber) throws SQLException {
		List<City> list = template.queryForList(nameSpace + "search");
		return list;
	}

	public void setTemplate(SqlMapClientTemplate template) {
		this.template = template;
	}

	public SqlMapClientTemplate getTemplate() {
		return template;
	}
	
}
