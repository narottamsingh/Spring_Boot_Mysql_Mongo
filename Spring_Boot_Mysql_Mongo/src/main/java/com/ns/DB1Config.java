package com.ns;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.ns.model.Datasource1Properties;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages="com.ns.db1.Repository", entityManagerFactoryRef = "db1EntityManager", transactionManagerRef = "transactionManager")
@EnableConfigurationProperties(Datasource1Properties.class)
public class DB1Config {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	private Datasource1Properties datasource1Properties;

	@Bean(name = "db1DataSource",destroyMethod = "close")
	public DataSource customerDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(datasource1Properties.getDriverClass());
		dataSource.setUsername(datasource1Properties.getUserName());
		dataSource.setPassword(datasource1Properties.getPassword());
		dataSource.setUrl(datasource1Properties.getUrl());
		dataSource.setMaxActive(datasource1Properties.getMaxActive());
		dataSource.setMaxIdle(datasource1Properties.getMaxIdle());
		dataSource.setInitialSize(datasource1Properties.getConnectionSize());
		dataSource.setValidationQuery(datasource1Properties.getValidationQuery());
		dataSource.setTestOnBorrow(true);
		return dataSource;
	}

	@Bean(name = "db1EntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean customerEntityManager() throws Throwable {

		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.show_sql", true);
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(customerDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.ns.db1.entity");
		entityManager.setPersistenceUnitName("db1PersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
