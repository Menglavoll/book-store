package com.mengla.ticketsale.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 解决MyBatis返回HashMap时，某个字段值为null时，不会保存key
        org.apache.ibatis.session.Configuration configuration =
                new org.apache.ibatis.session.Configuration();
        configuration.setCallSettersOnNulls(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});
        sqlSessionFactoryBean.setMapperLocations(
                resolver.getResources("classpath:/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

//    //配置mybatis的分页插件pageHelper
//    @Bean
//    public PageHelper pageHelper(){
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum","true");
//        properties.setProperty("rowBoundsWithCount","true");
//        properties.setProperty("reasonable","true");
//        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }
}
