package org.sportbean.wx.sportbean.filter;

import javax.servlet.*;
import java.io.IOException;

public class ValidateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    // 处理过滤器地方,用来显示过滤规则
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("MyFilter process...");
        chain.doFilter(request, response);
        //  HttpServletRequest hreq = (HttpServletRequest) request;
        //   HttpServletResponse hres = (HttpServletResponse) response;
        //   HttpSession session = hreq.getSession();
        //   if (session != null && session.getAttribute("USERINFO") != null)
        //   {
        //    log.info("session is have");
        //    chain.doFilter(request, response);
        //   }
        //   else
        //   {
        //    log.info("session havan't");
        //    hres.sendRedirect(urlwrong);
        //   }
        // ---------------------
        // 作者：fenglingcompany
        // 来源：CSDN
        // 原文：https://blog.csdn.net/fenglingcompany/article/details/4183722
        // 版权声明：本文为博主原创文章，转载请附上博文链接！
    }

    @Override
    public void destroy() {

    }
}
