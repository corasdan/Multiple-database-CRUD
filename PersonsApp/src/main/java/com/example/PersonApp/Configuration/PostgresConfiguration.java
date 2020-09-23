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
@EnableJpaRepositories(basePackages = "com.example.PersonApp.Repository.Postgres",
        entityManagerFactoryRef = "postgresEntityManager", transactionManagerRef = "postgresTransactionManager")
public class PostgresConfiguration {
    @Autowired
    private Environment env;

    public PostgresConfiguration() {
        super();
    }

    //

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan("com.example.PersonApp.Model");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.persistent.jpa.properties.hibernate.dialect"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource postgresDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.pg.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.pg.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.pg.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.pg.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return transactionManager;
    }

}