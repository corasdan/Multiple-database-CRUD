package com.example.PersonApp.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


@Configuration
@EnableJpaRepositories(basePackages = "com.example.PersonApp.Repository.Maria",
        entityManagerFactoryRef = "mariadbEntityManager", transactionManagerRef = "mariadbTransactionManager")
public class MariaDbConfiguration {
    @Autowired
    private Environment env;

    public MariaDbConfiguration() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mariadbEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mariadbDataSource());
        em.setPackagesToScan("com.example.PersonApp.Model");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource mariadbDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.mdb.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.mdb.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.mdb.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.mdb.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager mariadbTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mariadbEntityManager().getObject());
        return transactionManager;
    }

}