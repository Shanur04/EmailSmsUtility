package gov.cdac.emailservice.datasource;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

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
@ConfigurationProperties("spring.datasource.icg.officer")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "icgOfficerEntityManagerFactory", transactionManagerRef = "icgOfficerTransactionManager", basePackages = "gov.cdac.emailservice.icg.officer.repository")
public class ICGOfficerDataSource extends HikariConfig {
	 /**
     * Defines HikariDataSource bean
     * 
     * @return
     */
    @Bean(name = "icgOfficerDataSource")
    public HikariDataSource icgOfficerDataSource() {
	return new HikariDataSource(this);
    }
    
    
    /**
     * Defines EntityManagerFactory Bean
     * 
     * @param dataSource
     * @return
     */
    @Bean(name = "icgOfficerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	    @Qualifier("icgOfficerDataSource") final HikariDataSource dataSource) {
	return new LocalContainerEntityManagerFactoryBean() {
	    {
		setDataSource(dataSource);
		setPersistenceProviderClass(HibernatePersistenceProvider.class);
		setPersistenceUnitName("icgOfficerUnit");
		setPackagesToScan("gov.cdac.emailservice.icg.officer.pojo");
		setJpaProperties(new Properties() {
		    {
			put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
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
    @Bean(name = "icgOfficerTransactionManager")
    public PlatformTransactionManager casbTransactionManager(
	    @Qualifier("icgOfficerEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
	return new JpaTransactionManager(customerEntityManagerFactory);
    }
}

