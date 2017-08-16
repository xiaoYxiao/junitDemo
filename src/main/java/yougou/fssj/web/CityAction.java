package yougou.fssj.web;

import java.util.List;

import yougou.fssj.model.City;
import yougou.fssj.service.CityService;

import com.opensymphony.xwork2.ActionSupport;

public class CityAction extends ActionSupport {
	private CityService cityService;
	private List<City> cityList;
	private String[] cityIds;

	/**
	 * 查询list列表
	 */
	private static final long serialVersionUID = 6043973040399939034L;

	public String list() throws Exception {
		cityList = cityService.find(0);
		return "success";
	}
	/**
	 * 删除多余数据
	 */
	public String delete() throws Exception {
		if (cityIds != null) {
			for (String id : cityIds) {
				cityService.delete(id);
			}
		}

		list();
		return "success";
	}
	
	public String get() throws Exception {
       City city = cityService.get(cityIds[0]);
        return "success";
    }

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public String[] getCityIds() {
		return cityIds;
	}

	public void setCityIds(String[] cityIds) {
		this.cityIds = cityIds;
	}

}
