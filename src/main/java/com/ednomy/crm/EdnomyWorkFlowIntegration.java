package com.ednomy.crm;

import java.util.Properties;

import javax.sql.DataSource;

import org.jdbcdslog.ConnectionPoolDataSourceProxy;
import org.jdbcdslog.JDBCDSLogException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableAutoConfiguration
@ImportResource(value = { "classpath:root-context.xml",
		"classpath:dispatcherServlet/servlet-context.xml" })
public class EdnomyWorkFlowIntegration {

	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_CONNECTION = "jdbc:mysql://localhost/crmsoft?createDatabaseIfNotExist=true&amp;amp;useUnicode=true&amp;amp;characterEncoding=utf-8&amp;amp;autoReconnect=true";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "pass";

	@Bean
	public DataSource getDataSource() {
		ConnectionPoolDataSourceProxy proxiedDataSource = null;
		try {
			proxiedDataSource = new ConnectionPoolDataSourceProxy();
		} catch (final JDBCDSLogException jdbcdsLogException) {
		}
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		Properties prop = new Properties();
        prop.setProperty("hibernate.hbm2ddl.auto", "create");
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(JDBC_CONNECTION);
		dataSource.setConnectionProperties(prop);
		dataSource.setUsername(USER_NAME);
		dataSource.setPassword(PASSWORD);
		

		proxiedDataSource.setTargetDSDirect(dataSource);
		return proxiedDataSource;
	}
}
