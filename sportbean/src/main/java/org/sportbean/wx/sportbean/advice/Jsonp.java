package org.sportbean.wx.sportbean.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * https://docs.spring.io/spring/docs/4.3.x/spring-framework-reference/html/cors.html
 * 
 * 跨域官网 该方法已经被取消了，使用
 * 
 * 中文翻译https://www.cnblogs.com/mmzs/p/9167743.html
 */
@ControllerAdvice(basePackages = "org.sportbean.wx.sportbean.controller")
public class Jsonp extends AbstractJsonpResponseBodyAdvice {
    public Jsonp() {
        // 构造函数
        super("callback", "jsonp");
    }
}