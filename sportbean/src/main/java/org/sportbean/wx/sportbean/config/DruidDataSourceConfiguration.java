package org.sportbean.wx.sportbean.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// @EnableTransactionManagement
@MapperScan("org.sportbean.wx.sportbean.mapper")
public class DruidDataSourceConfiguration {
    // @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "dataSource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    // druid 监控
    @Bean
    public ServletRegistrationBean statVIewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", ""); // 默认全部运行
        initParams.put("deny", "192.168.15.21"); // 拒绝访问

        bean.setInitParameters(initParams);
        return bean;
    }

    // // 2.生产一个beannfilter
    // @Bean
    // public FilterRegistrationBean webViewfilter() {
    // FilterRegistrationBean bean = new FilterRegistrationBean();
    // bean.setFilter(new WebStatFilter());
    // Map<String, String> initParams = new HashMap<>();
    // bean.setInitParameters(initParams);
    // // System.out.println("创建一个规则");
    // bean.setUrlPatterns(Arrays.asList("/*"));
    // return bean;
    // }
}