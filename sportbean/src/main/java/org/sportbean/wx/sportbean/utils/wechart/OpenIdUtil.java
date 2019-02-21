package org.sportbean.wx.sportbean.utils.wechart;

public class OpenIdUtil {
    public static String oauth2GetOpenid(String code, String appid, String appsecret) {
        // 切换appid和appsecret账号
        // String appid = "";
        // String appsecret = "";
        // switch (classify) {
        // case "jiankangka":
        // // 自己的配置appid
        // appid = "********";
        // // 自己的配置APPSECRET;
        // appsecret = "*********";
        // break;
        // }

        // 授权（必填）
        String grant_type = "authorization_code";
        // URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        // 请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        // 发送请求
        String data = HttpUtil.get(requestUrl, params);
        return data;
    }
}