package yougou.fssj.web;

import javax.sql.DataSource;

import org.apache.struts2.StrutsSpringTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.TestContextManager;

public abstract class BaseActionTest extends StrutsSpringTestCase {
	private TestContextManager testContextManager;
	private IDatabaseConnection conn;

	@Autowired
	private DataSource dataSource;

	protected String getSchema() {
		return "PUBLIC";
	}

	protected String[] getContextLocations() {
		String[] str = { "classpath:spring/spring-junit.xml" };
		return str;
	}

	@Before
	public void init() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);

		conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource), getSchema());
		String id = "http://www.dbunit.org/properties/datatypeFactory";
		DatabaseConfig config = conn.getConfig();
		config.setProperty(id, new org.dbunit.ext.hsqldb.HsqldbDataTypeFactory());

		if (getInitDataFile() != null) {
			IDataSet dataSet = new XmlDataSet(new ClassPathResource(getInitDataFile()).getInputStream());
			DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);
		}
	}

	@After
	public void shutdown() throws Exception {
		if (getInitDataFile() != null) {
			IDataSet dataSet = new XmlDataSet(new ClassPathResource(getInitDataFile()).getInputStream());
			DatabaseOperation.DELETE_ALL.execute(conn, dataSet);
		}
	}

	protected abstract String getInitDataFile();
}