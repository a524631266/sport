package org.sportbean.wx.sportbean.controller;

import java.util.List;
// import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// import org.apache.ibatis.annotations.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sportbean.wx.sportbean.pojo.EntryTablePojo;

// import com.sportbean.wx.service.WxUserService;

import org.sportbean.wx.sportbean.pojo.WxUserPojo;
import org.sportbean.wx.sportbean.service.WxUserService;
import org.sportbean.wx.sportbean.utils.wechart.OpenIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/wxuser")
public class WxUserInfoController {
    // 记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WxUserService wxUserInfoService;
    @Value("${wx.open.appid}")
    private String appid;
    @Value("${wx.open.secretKey}")
    private String secretKey;

    @GetMapping(value = "/hello")
    public List<WxUserPojo> getMethodName() {
        List<WxUserPojo> datalist = wxUserInfoService.getAllInfo();
        // System.out.println(wxUserInfoService.getAllInfo());
        if (logger.isDebugEnabled()) {
            for (WxUserPojo info : wxUserInfoService.getAllInfo()) {
                System.out.println(info);
            }
        }
        return datalist;
    }

    // /**
    // * 获取全部用户数据测试是否能够与mysql结合
    // *
    // * @param openid
    // * @return
    // */
    // @RequestMapping(value = "/{openid}", method = RequestMethod.GET)
    // public WxUserPojo getAllInfo(@PathVariable("openid") String openid) {
    // WxUserPojo wp = wxUserInfoService.getOneInfo(openid);

    // if (logger.isDebugEnabled()) {
    // logger.debug("这是debug日志..." + wp);
    // }

    // if (wp == null) {
    // return null;
    // }
    // return wp;
    // }

    // 用户注册入口
    @PostMapping(value = "/register/{openid}")
    public Boolean register(@PathVariable("openid") String openid, HttpServletResponse response,
            HttpServletRequest request, @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "rid", required = true) Integer rid,
            @RequestParam(value = "cid", required = true) String cid,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "sex", required = true) Integer sex,
            @RequestParam(value = "vocode", required = true) String vocode, HttpSession session) {
        // http://localhost:9090/wxuser/2223?name=zhangll&phone=1000&cid=3330303321&sex=0&rid=1&vocode=312010
        // 只有获取验证码的时候才能注册信息
        if (session.getAttribute("vocode") != null && session.getAttribute("vocode").equals(vocode)) {
            WxUserPojo wxUser = new WxUserPojo();
            wxUser.setName(name);
            wxUser.setCid(cid);
            wxUser.setSex(sex);
            wxUser.setRid(rid);
            wxUser.setPhone(phone);
            wxUser.setOpenid(openid);
            if (logger.isDebugEnabled()) {
                logger.debug("获取到的是 name属性" + request.getParameter("name"));
                logger.debug("获取到的是 cid属性" + request.getParameter("cid"));
                logger.debug("获取到的是 sex属性" + request.getParameter("sex"));
                logger.debug("获取到的是 rid属性" + request.getParameter("rid"));
            }
            try {
                WxUserPojo wx = wxUserInfoService.getOneInfo(openid);
                if (wx != null) {// 如果没有用户id的话就更新
                } else {
                    wxUserInfoService.insert(wxUser);
                }
                return true;
            } catch (Exception e) {
                // TODO: handle exception
                // System.out.println("无数据");
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

    }

    @PostMapping(value = "/openid/{openid}/name/{name}")
    public Boolean updatename(@PathVariable("openid") String openid, @RequestBody WxUserPojo WxUser) {
        try {
            wxUserInfoService.updateWxUserName(WxUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "/entry")
    public Boolean entrySportEvent(@RequestParam(value = "uid", required = true) Integer uid,
            @RequestParam(value = "sid", required = true) Integer sid) {
        // http://localhost:9090/wxuser/entry?uid=10&sid=21
        EntryTablePojo ETP = new EntryTablePojo();
        ETP.setSid(sid);
        ETP.setUid(uid);
        try {
            // 设置状态
            Boolean setstate = wxUserInfoService.entrySportEvent(ETP);
            if (logger.isDebugEnabled()) {
                logger.debug("状态是否好:" + setstate);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 在用户登录的时候就直接后去用户的openid 通过code
    @RequestMapping(value = "/openid/{code}", method = RequestMethod.GET)
    public String getPpenid(@PathVariable("code") String code) {
        if (logger.isDebugEnabled()) {
            System.out.println("获取到的是 appid:" + appid);
            System.out.println("获取到的是 secretKey: " + secretKey);
            System.out.println("code:" + code);
        }
        String openid = OpenIdUtil.oauth2GetOpenid(code, appid, secretKey);
        return openid;
    }

    // @GetMapping("/userInfo")
    // public String userInfo(@RequestParam("code") String code,
    // @RequestParam("state") String returnUrl)
    // throws Exception {
    // logger.info("【微信网页授权】code={}", code);
    // logger.info("【微信网页授权】state={}", returnUrl);
    // WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
    // try {
    // wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
    // } catch (WxErrorException e) {
    // logger.info("【微信网页授权】{}", e);
    // throw new Exception(e.getError().getErrorMsg());
    // }
    // String openId = wxMpOAuth2AccessToken.getOpenId();
    // logger.info("【微信网页授权】openId={}", openId);
    // return "redirect:" + returnUrl + "?openid=" + openId;
    // }

}