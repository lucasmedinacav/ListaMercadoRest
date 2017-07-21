package com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.SpringSessionContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.api.persistence")
public class SqlInitialization {
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(
				"jdbc:postgresql://ec2-107-20-250-195.compute-1.amazonaws.com:5432/d12mq0309tqpkt?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
		dataSource.setUsername("ramvgksxzxrimu");
		dataSource.setPassword("f538d1c28a42d5f08925865d503fa5b3372800c6c0c2d9b57ef4d7afeeb7f3a1");
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("com.api.representations");
		entityManagerFactoryBean.setJpaProperties(buildHibernateProperties());
		entityManagerFactoryBean.setJpaProperties(new Properties() {
			
			{
				put("hibernate.current_session_context_class", SpringSessionContext.class.getName());
			}
		});
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {
			
			{
				setDatabase(Database.POSTGRESQL);
			}
		});
		return entityManagerFactoryBean;
	}
	
	protected Properties buildHibernateProperties() {
		Properties hibernateProperties = new Properties();
		
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.use_sql_comments", "false");
		hibernateProperties.setProperty("hibernate.format_sql", "false");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.generate_statistics", "false");
		hibernateProperties.setProperty("javax.persistence.validation.mode", "none");
		hibernateProperties.setProperty("org.hibernate.envers.store_data_at_delete", "true");
		hibernateProperties.setProperty("org.hibernate.envers.global_with_modified_flag", "true");
		
		return hibernateProperties;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(transactionManager());
	}
}