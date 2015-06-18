package yougou.fssj.service;

import javax.sql.DataSource;

import org.databene.feed4junit.Feeder;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

@RunWith(Feeder.class)
@ContextConfiguration(locations = { "classpath:spring/spring-junit.xml" })
public abstract class BaseTest {
	private TestContextManager testContextManager;

	@Autowired
	private DataSource dataSource;

	protected String getSchema() {
		return null;
	}

	@Before
	public void init() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);

		IDatabaseConnection conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource), getSchema());
		String id = "http://www.dbunit.org/properties/datatypeFactory";
		DatabaseConfig config = conn.getConfig();
		config.setProperty(id, new org.dbunit.ext.hsqldb.HsqldbDataTypeFactory());

		if (getInitDataFile() != null) {
			IDataSet dataSet = new XmlDataSet(new ClassPathResource(getInitDataFile()).getInputStream());

			DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);
			//System.out.println("Initial data file inserted: "+getInitDataFile()+" "+dataSet);
		}
	}

	@After
	public void shutdown() throws Exception {
		if (getInitDataFile() != null) {
			IDatabaseConnection conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource), getSchema());
			IDataSet dataSet = new XmlDataSet(new ClassPathResource(getInitDataFile()).getInputStream());
			DatabaseOperation.DELETE_ALL.execute(conn, dataSet);
			//System.out.println("Initial data file removed: "+getInitDataFile()+" "+dataSet);
		}
	}

	protected abstract String getInitDataFile();
}
