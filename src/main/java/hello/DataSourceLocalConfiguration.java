package hello;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by david on 11/26/2015.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "hello",
        entityManagerFactoryRef = "internalEntityManagerFactory",
        transactionManagerRef = "internalTransactionManager")
public class DataSourceLocalConfiguration {

    public static final String INTERNAL = "local";

    @Bean(name = "internalDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource-local")
    public DataSource internalDataSource() {
        //return DataSourceBuilder.create().build();
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
                .addScript("schema-hsqldb.sql")
                .addScript("data-hsqldb.sql")
                .build();
        return db;
    }

    @Bean(name = "internalEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean internalEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(internalDataSource())
                .packages("hello")
                .persistenceUnit(INTERNAL)
                .build();
    }

    @Bean(name = "internalTransactionManager")
    @Primary
    public PlatformTransactionManager internalTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(internalDataSource());
        jpaTransactionManager.setPersistenceUnitName(INTERNAL);
        return jpaTransactionManager;
    }
}