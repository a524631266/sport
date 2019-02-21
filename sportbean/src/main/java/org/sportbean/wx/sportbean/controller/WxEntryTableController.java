package org.sportbean.wx.sportbean.controller;

import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Update;

// import com.sportbean.wx.service.WxUserService;

import org.sportbean.wx.sportbean.pojo.WxUserPojo;
import org.sportbean.wx.sportbean.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @RestController
// @RequestMapping("/wxuser/onloading")
public class WxEntryTableController {
    @Autowired
    private WxUserService wxUserInfoService;

    @GetMapping(value = "/hello")
    public List<WxUserPojo> getMethodName() {
        System.out.println("11111");
        List<WxUserPojo> datalist = wxUserInfoService.getAllInfo();
        // System.out.println(wxUserInfoService.getAllInfo());
        for (WxUserPojo info : wxUserInfoService.getAllInfo()) {
            System.out.println(info);
        }
        return datalist;
    }

    @RequestMapping(value = "/{openid}", method = RequestMethod.GET)
    public WxUserPojo getAllInfo(@PathVariable("openid") String openid) {
        WxUserPojo wp = wxUserInfoService.getOneInfo(openid);
        System.out.println(wp);
        if (wp == null) {
            return null;
        }
        return wp;
    }

    @PostMapping(value = "/{openid}")
    public Boolean postMethodName(@PathVariable("openid") String openid, HttpServletResponse response,
            HttpServletRequest request, @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "rid", required = true) Integer rid,
            @RequestParam(value = "cid", required = true) String cid,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "sex", required = true) Integer sex) {
        WxUserPojo wxUser = new WxUserPojo();
        wxUser.setName(name);
        wxUser.setCid(cid);
        wxUser.setSex(sex);
        wxUser.setRid(rid);
        wxUser.setPhone(phone);
        wxUser.setOpenid(openid);
        // System.out.println(request.getParameter("name"));
        // System.out.println(request.getParameter("phone"));
        // System.out.println(request.getParameter("cid"));
        // System.out.println(request.getParameter("sex"));
        // System.out.println(request.getParameter("rid"));
        try {
            WxUserPojo wx = wxUserInfoService.getOneInfo(openid);
            if (wx != null) {// 如果没有用户id的话就更新
                // wxUserInfoService.insert(WxUser);
                // System.out.println("有数据");
                // System.out.println(wxUser);
            } else {
                wxUserInfoService.insert(wxUser);
                // System.out.println("无数据");
            }
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            // System.out.println("无数据");
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "/{openid}/name/{name}")
    public Boolean updatename(@PathVariable("openid") String openid, @RequestBody WxUserPojo WxUser) {
        try {
            wxUserInfoService.updateWxUserName(WxUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}