package org.sportbean.wx.sportbean.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sportbean.wx.sportbean.filter.ValidateFilter;
// import com.atguigu.springboot.listener.MyListener;
// import com.atguigu.springboot.servlet.MyServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {
    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    // 注册三大组件
    // @Bean
    // public ServletRegistrationBean myServlet() {
    // ServletRegistrationBean registrationBean = new ServletRegistrationBean(new
    // MyServlet(), "/myServlet");
    // registrationBean.setLoadOnStartup(1);
    // return registrationBean;
    // }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ValidateFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/login/*", "/myServlet/*", "/wxuser/*", "/sms/*"));
        if (logger.isDebugEnabled()) {
            logger.debug("build a filter");
        }
        return registrationBean;
    }

    // @Bean
    // public ServletListenerRegistrationBean myListener(){
    // ServletListenerRegistrationBean<MyListener> registrationBean = new
    // ServletListenerRegistrationBean<>(new MyListener());
    // return registrationBean;
    // }

    // //配置嵌入式的Servlet容器
    // @Bean
    // public EmbeddedServletContainerCustomizer
    // embeddedServletContainerCustomizer(){
    // return new EmbeddedServletContainerCustomizer() {

    // //定制嵌入式的Servlet容器相关的规则
    // @Override
    // public void customize(ConfigurableEmbeddedServletContainer container) {
    // container.setPort(8083);
    // }
    // };
    // }

}
