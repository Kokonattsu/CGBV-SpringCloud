package cn.tedu.account;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSourceProxy(){
        return new HikariDataSource();
    }


    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSource(DataSource dataSourceProxy){
        return new DataSourceProxy(dataSourceProxy);
    }
}
