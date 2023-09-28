package work.twgj.mybatisplusdemo.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author weijie.zhu
 * @date 2023/9/21 11:32
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig {

    @Bean(name = "localSqlSessionFactory")
    public SqlSessionFactory wxuserSqlSessionFactory(@Qualifier("localDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "localMapperScannerConfigurer")
    public MapperScannerConfigurer wxuserMapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        // 指定数据源的SqlSessionFactory
        scannerConfigurer.setSqlSessionFactoryBeanName("localSqlSessionFactory");
        // 指定Mapper接口包路径
        scannerConfigurer.setBasePackage("work.twgj.mybatisplusdemo.mapper");
        return scannerConfigurer;
    }
}
