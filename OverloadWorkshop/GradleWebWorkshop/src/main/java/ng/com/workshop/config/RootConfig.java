package ng.com.workshop.config;

import java.util.Collections;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = { "ng.com.workshop" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "ng.com.workshop.business.data.jpa" })
public class RootConfig {

    @Value("${jdbc.driver.class.name}")
    String driverClassName;

    @Value("${jdbc.username}")
    String username;

    @Value("${jdbc.password}")
    String password;

    @Value("${jdbc.init.pool.size}")
    Integer poolSize;

    @Value("${jdbc.max.pool.size}")
    Integer maxActive;

    @Value("${jdbc.driver.url}")
    String dbUrl;

    @Value("${jpa.dialect}")
    String jpaDialect;

    @Value("${jpa.root.package}")
    String entityRootPackage;

    @Value("${jpa.database.mode:update}")
    private String databaseMode;

    @Value("${jpa.showsql:false")
    private String showSql;


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Bean
    @Profile("development")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }


    @Bean
    @Profile("development")
    public LocalEntityManagerFactoryBean devEntityManagerFactoryBean() {
        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("worktoolPU");
        return emf;
    }


    @Bean
    public BasicDataSource basicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(poolSize);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }


    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter adapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan(entityRootPackage);
        emf.setPersistenceUnitName("workshop-pu");
        emf.setJpaPropertyMap(Collections.singletonMap("hibernate.hbm2ddl.auto", databaseMode));
        return emf;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setDatabasePlatform(jpaDialect);
        adapter.setGenerateDdl(false);
        adapter.setShowSql(new Boolean(showSql));
        return adapter;
    }


    @Bean
    @Profile("production")
    public JndiObjectFactoryBean dataSource() {
        JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
        jndi.setJndiName("jdbc/WorkshopDB");
        jndi.setResourceRef(true);
        jndi.setProxyInterface(DataSource.class);
        return jndi;
    }


    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager() {
        return new JpaTransactionManager();
    }
}
