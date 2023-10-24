package work.twgj.swagger3demo.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author weijie.zhu
 * @date 2023/9/21 11:32
 */
@Configuration
public class MyBatisConfig {

    @Bean(name = "localDataSourceTransactionManager")
    public DataSourceTransactionManager localDataSourceTransactionManager(@Qualifier("localDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "localSqlSessionFactory")
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("localDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "localMapperScannerConfigurer")
    public MapperScannerConfigurer localMapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        // 指定数据源的SqlSessionFactory
        scannerConfigurer.setSqlSessionFactoryBeanName("localSqlSessionFactory");
        // 指定Mapper接口包路径
        scannerConfigurer.setBasePackage("work.twgj.swagger3demo.mapper");
        return scannerConfigurer;
    }
}
