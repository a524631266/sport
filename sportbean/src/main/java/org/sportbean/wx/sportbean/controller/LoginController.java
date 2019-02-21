package org.sportbean.wx.sportbean.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sportbean.wx.sportbean.pojo.AdminPojo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class LoginController {
    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "")
    public Boolean login(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password, HttpSession httpSession,
            HttpServletResponse httpServletResponse, HttpServletRequest request) {
        // 用户在登录之后有效
        // 获取session并接触业务
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        String sessionid = session.getId();
        if (logger.isDebugEnabled()) {
            logger.debug("sessionid :" + sessionid);
            System.out.println("登录的时候的验证码:" + session.getAttribute("vocode"));
        }
        if (username.equals("admin") && password.equals("admin")) {
            AdminPojo user = new AdminPojo();
            user.setName(username);
            user.setPassword(password);
            session.setAttribute("user", user);
            if (logger.isDebugEnabled()) {
                logger.debug("cookies :" + cookies);
            }
            return true;
        } else {
            return false;
        }

        // if (cookies == null || cookies.length == 0) {
        // System.out.println("没有cookie");
        // } else {
        // for (Cookie cookie : cookies) {
        // String name = cookie.getName();
        // String value = cookie.getValue();
        // if (logger.isDebugEnabled()) {
        // logger.debug("name : " + name);
        // logger.debug("value : " + value);
        // }
        // }
        // }
        // if (cookies != null) {
        // httpServletResponse.addCookie(cookies[0]);
        // }
        // return true;
    }

}