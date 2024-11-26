package gov.cdac.datasource;
/**
 * 
 * @author shanurj
 *
 */

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

@Configuration
@ConfigurationProperties("spring.datasource.icg")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "icgEntityManagerFactory", transactionManagerRef = "icgTransactionManager", basePackages = "gov.cdac.icgRepositories")
public class ICGDataSource extends HikariConfig {

    /**
     * Defines HikariDataSource bean
     * 
     * @return
     */
    @Bean(name = "icgDataSource")
    public HikariDataSource icgDataSource() {
	return new HikariDataSource(this);
    }

    /**
     * Defines EntityManagerFactory Bean
     * 
     * @param dataSource
     * @return
     */
    @Bean(name = "icgEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	    @Qualifier("icgDataSource") final HikariDataSource dataSource) {
	return new LocalContainerEntityManagerFactoryBean() {
	    {
		setDataSource(dataSource);
		setPersistenceProviderClass(HibernatePersistenceProvider.class);
		setPersistenceUnitName("icgUnit");
		setPackagesToScan("gov.cdac.icgPojo");
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
    @Bean(name = "icgTransactionManager")
    public PlatformTransactionManager icgTransactionManager(
	    @Qualifier("icgEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
	return new JpaTransactionManager(customerEntityManagerFactory);
    }
}
