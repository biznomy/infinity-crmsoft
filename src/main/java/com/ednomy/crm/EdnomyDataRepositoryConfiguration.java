package com.ednomy.crm;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.support.incrementer.AbstractSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ednomy.crm.util.ApplicationProperties;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EntityScan
@EnableAutoConfiguration
public class EdnomyDataRepositoryConfiguration {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	ApplicationProperties applicationProperties;

	private static final String ID_SEQUENCE = "hibernate_sequence";
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl jmsi = new JavaMailSenderImpl();
		jmsi.setHost(applicationProperties.getEmailHost());
		jmsi.setPort(applicationProperties.getEmailPort());
		jmsi.setUsername(applicationProperties.getEmailUserName());
		jmsi.setPassword(applicationProperties.getEmailPassword());
		return jmsi;
	}

	@Bean(name = "hibernateSequence")
	public AbstractSequenceMaxValueIncrementer picklistSequenceIncrementer() {
		AbstractSequenceMaxValueIncrementer incrementer = new OracleSequenceMaxValueIncrementer();

		incrementer.setIncrementerName(ID_SEQUENCE);
		incrementer.setDataSource(dataSource);
		return incrementer;
	}

	

}
