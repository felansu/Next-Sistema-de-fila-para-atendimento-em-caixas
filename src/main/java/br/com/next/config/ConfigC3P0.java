package br.com.next.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class ConfigC3P0 {
    @Value("${c3p0.driverClass}")
    private String driverClass;

    @Value("${c3p0.url}")
    private String jdbcUrl;

    @Value("${c3p0.username}")
    private String jdbcUsername;

    @Value("${c3p0.password}")
    private String jdbcPassword;

    @Value("${c3p0.idleMaxAgeInMinutes}")
    private Integer idleMaxAgeInMinutes;

    @Value("${c3p0.idleConnectionTestPeriodInMinutes}")
    private Integer idleConnectionTestPeriodInMinutes;

    @Value("${c3p0.maxStatementsPerConnection}")
    private Integer maxStatementsPerConnection;

    @Value("${c3p0.minPoolSize}")
    private Integer minPoolSize;

    @Value("${c3p0.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${c3p0.maxStatements}")
    private Integer maxStatements;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setMaxIdleTime(idleMaxAgeInMinutes);
        dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriodInMinutes);
        dataSource.setMaxStatementsPerConnection(maxStatementsPerConnection);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMaxStatements(maxStatements);
        return dataSource;
    }
}