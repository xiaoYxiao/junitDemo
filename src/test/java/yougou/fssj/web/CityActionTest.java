package yougou.fssj.web;

import org.junit.Test;

import yougou.fssj.model.City;

import com.opensymphony.xwork2.ActionProxy;

public class CityActionTest extends BaseActionTest {

	protected String getInitDataFile() {
		return "yougou/fssj/web/CityActionTest_Init.xml";
	}

	// 测试列表方法
	@Test
	public void testList() throws Exception {
		// 获取action
		ActionProxy proxy = getActionProxy("/City!list.do");
		CityAction action = (CityAction) proxy.getAction();

		// 检查action中函数返回值是否正确
		String result = proxy.execute();

		assertTrue(action.getFieldErrors().size() == 0);
		assertEquals("success", result);
	}

	// 创建信息
	@Test
	public void testCreate() throws Exception {
		String name = "name";
		String description = "description";
		String page = "list";

		// 从struts2的配置文件中取得的实例
		ActionProxy proxy = getActionProxy("/CityEdit!save.do");
		CityEditAction action = (CityEditAction) proxy.getAction();

		City city = new City();
		city.setName(name);
		city.setDescription(description);
		action.setCity(city);

		String result = proxy.execute();
		assertTrue(action.getFieldErrors().size() == 0);
		assertEquals(page, result);
	}

	// 保存信息
	@Test
	public void testSave() throws Exception {
		String id = "123";
		String name = "name";
		String description = "description";
		String page = "list";

		// 从struts2的配置文件中取得的实例
		ActionProxy proxy = getActionProxy("/CityEdit!save.do");
		CityEditAction action = (CityEditAction) proxy.getAction();

		City city = new City();
		city.setId(id);
		city.setName(name);
		city.setDescription(description);
		action.setCity(city);

		String result = proxy.execute();
		assertTrue(action.getFieldErrors().size() == 0);
		assertEquals(page, result);
	}

	// 查看信息
	@Test
	public void testView() throws Exception {
		String id = "123";
		String page = "success";

		// 从struts2的配置文件中取得的实例
		ActionProxy proxy = getActionProxy("/CityEdit!get.do");
		CityEditAction action = (CityEditAction) proxy.getAction();

		action.setId(id);

		String result = proxy.execute();
		assertTrue(action.getFieldErrors().size() == 0);
		assertEquals(page, result);
	}

	// 删除信息
	@Test
	public void testRemove() throws Exception {
		String id = "123";
		String page = "success";

		// 从struts2的配置文件中取得的实例
		ActionProxy proxy = getActionProxy("/City!delete.do");
		CityAction action = (CityAction) proxy.getAction();

		action.setCityIds(new String[] { id });

		String result = proxy.execute();
		assertTrue(action.getFieldErrors().size() == 0);
		assertEquals(page, result);
	}
}
