package yougou.fssj.web;

import yougou.fssj.model.City;
import yougou.fssj.service.CityService;

import com.opensymphony.xwork2.ActionSupport;

public class CityEditAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 377548466801007022L;
	private CityService cityService;
	private City city;
	private String id;

	public String get() throws Exception {
		if (id == null || id.compareTo("") == 0)
			city = new City();
		else {
			city = cityService.get(id);
			if (city == null)
				city = new City();
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		if (city.getId() != null && city.getId().compareTo("") == 0)
			city.setId(null);
		if (city.getDescription() != null && city.getDescription().compareTo("") == 0)
			city.setDescription(null);
		cityService.save(city);
		return "list";
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}