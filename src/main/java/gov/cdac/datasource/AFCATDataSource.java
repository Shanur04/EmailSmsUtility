package gov.cdac.datasource;

import java.util.Properties;

import jakarta.persistence.EntityManagerFactory;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * @author shanurj
 *
 */
@Configuration
@ConfigurationProperties("spring.datasource.afcat")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "afcatEntityManagerFactory", transactionManagerRef = "afcatTransactionManager", basePackages = "gov.cdac.afcatRepository")
public class AFCATDataSource extends HikariConfig {

    /**
     * Defines HikariDataSource bean
     * 
     * @return
     */
    @Bean(name = "afcatDataSource")
    public HikariDataSource afcatDataSource() {
	return new HikariDataSource(this);
    }

    /**
     * Defines EntityManagerFactory Bean
     * 
     * @param dataSource
     * @return
     */
    @Bean(name = "afcatEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	    @Qualifier("afcatDataSource") final HikariDataSource dataSource) {
	return new LocalContainerEntityManagerFactoryBean() {
	    {
		setDataSource(dataSource);
		setPersistenceProviderClass(HibernatePersistenceProvider.class);
		setPersistenceUnitName("afcatUnit");
		setPackagesToScan("gov.cdac.afcatPojo");
		setJpaProperties(new Properties() {
		    {
			put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			put("show-sql", "true");
		    }
		});
	    }
	};
    }

    /**
     * Defines Transaction manager Bean
     * 
     * @param customerEntityManagerFactory
     * @return
     */
    @Bean(name = "afcatTransactionManager")
    public PlatformTransactionManager casbTransactionManager(
	    @Qualifier("afcatEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
	return new JpaTransactionManager(customerEntityManagerFactory);
    }
}
