package work.twgj.testingdemo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author weijie.zhu
 * @date 2023/9/21 14:43
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "localDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.local")
    public DataSource localDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
