package com.ednomy.crm;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.HsqlSequenceMaxValueIncrementer;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EntityScan()
@EnableAutoConfiguration
@ImportResource(value = {"classpath:root-context.xml",
		"classpath:dispatcherServlet/servlet-context.xml" })
public class InfrastructureConfiguration {

	@Autowired
	private DataSource dataSource;

	private static final String ID_SEQUENCE = "hibernate_sequence";

	@Bean(name = "yourSequence")
	public AbstractSequenceMaxValueIncrementer picklistSequenceIncrementer() {
		AbstractSequenceMaxValueIncrementer incrementer = new HsqlSequenceMaxValueIncrementer();
		incrementer.setIncrementerName(ID_SEQUENCE);
		incrementer.setDataSource(dataSource);
		return incrementer;
	}

	
}
