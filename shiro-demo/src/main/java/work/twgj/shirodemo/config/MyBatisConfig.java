package work.twgj.shirodemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
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
    public SqlSessionFactory localSqlSessionFactory(@Qualifier("localDataSource") DataSource dataSource,
                                                    @Qualifier("localMybatisPlusInterceptor")MybatisPlusInterceptor interceptor) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        // 配置分页拦截器之后，需要在sqlSessionFactory将拦截器以MybatisConfiguration的方式set到SqlSessionFactory中
        // 否则调用分页接口，会发现分页一直不生效
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.addInterceptor(interceptor);
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "localMapperScannerConfigurer")
    public MapperScannerConfigurer localMapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        // 指定数据源的SqlSessionFactory
        scannerConfigurer.setSqlSessionFactoryBeanName("localSqlSessionFactory");
        // 指定Mapper接口包路径
        scannerConfigurer.setBasePackage("work.twgj.shirodemo.mapper");
        return scannerConfigurer;
    }

    /**
     * 分页配置
     * */
    @Bean(name = "localMybatisPlusInterceptor")
    public MybatisPlusInterceptor localMybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加：分页插件
        // 参数：new PaginationInnerInterceptor(DbType.MYSQL)，是专门为mysql定制实现的内部的分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 添加乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
