package com.ns;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.ns.model.Datasource2Properties;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages = {"com.ns.db2.repository"}, entityManagerFactoryRef = "db2EntityManager", transactionManagerRef = "transactionManager")
@EnableConfigurationProperties(Datasource2Properties.class)
public class DB2Config {

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Autowired
	private Datasource2Properties datasource2Properties;

	@Primary
	@Bean(name = "db2DataSource", destroyMethod = "close")
	public DataSource spfsDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(datasource2Properties.getDriverClass());
		dataSource.setUsername(datasource2Properties.getUserName());
		dataSource.setPassword(datasource2Properties.getPassword());
		dataSource.setUrl(datasource2Properties.getUrl());
		dataSource.setMaxActive(datasource2Properties.getMaxActive());
		dataSource.setMaxIdle(datasource2Properties.getMaxIdle());
		dataSource.setInitialSize(datasource2Properties.getConnectionSize());
		dataSource.setValidationQuery(datasource2Properties.getValidationQuery());
		dataSource.setTestOnBorrow(true);
		return dataSource;
	}


	@Bean(name = "db2EntityManager")
	public LocalContainerEntityManagerFactoryBean spfsEntityManager() throws Throwable {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.show_sql", true);
		properties.put("pinGlobalTxToPhysicalConnection", true);
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(spfsDataSource());
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		String[] entityPackage = { "com.ns.db2.entity"};
		entityManager.setPackagesToScan(entityPackage);
		entityManager.setPersistenceUnitName("db2PersistenceUnit");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
