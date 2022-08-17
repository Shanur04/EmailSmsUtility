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
@ConfigurationProperties("spring.datasource.casb")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "casbEntityManagerFactory", transactionManagerRef = "casbTransactionManager", basePackages = "gov.cdac.emailservice.casb.repository")
public class CASBDataSource extends HikariConfig {

    /**
     * Defines HikariDataSource bean
     * 
     * @return
     */
    @Bean(name = "casbDataSource")
    public HikariDataSource casbDataSource() {
	return new HikariDataSource(this);
    }

    /**
     * Defines EntityManagerFactory Bean
     * 
     * @param dataSource
     * @return
     */
    @Bean(name = "casbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	    @Qualifier("casbDataSource") final HikariDataSource dataSource) {
	return new LocalContainerEntityManagerFactoryBean() {
	    {
		setDataSource(dataSource);
		setPersistenceProviderClass(HibernatePersistenceProvider.class);
		setPersistenceUnitName("casbUnit");
		setPackagesToScan("gov.cdac.emailservice.casb.pojo");
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
    @Bean(name = "casbTransactionManager")
    public PlatformTransactionManager casbTransactionManager(
	    @Qualifier("casbEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
	return new JpaTransactionManager(customerEntityManagerFactory);
    }
}
