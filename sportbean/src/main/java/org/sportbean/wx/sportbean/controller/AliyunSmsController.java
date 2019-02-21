package org.sportbean.wx.sportbean.controller;

import javax.servlet.http.HttpSession;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sportbean.wx.sportbean.utils.RandomStringTLUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class AliyunSmsController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${ali.ssm.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.ssm.accessKeySecret}")
    private String accessKeySecret;

    @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
    private String ui(@PathVariable("userid") String userid) {
        if (logger.isDebugEnabled()) {
            System.out.println(accessKeyId);
            System.out.println(accessKeySecret);
        }
        return "mtlogin";// 返回页面
    }

    // 测试 用来保存session
    @RequestMapping(value = "/{phone}", method = RequestMethod.GET)
    private String postphone(@PathVariable("phone") String phone, HttpSession httpsession) {
        // 生成几位的验证码
        String random = RandomStringTLUtils.createRandomNum(6);
        httpsession.setAttribute("vocode", random);
        if (logger.isDebugEnabled()) {
            System.out.println(accessKeyId);
            System.out.println(accessKeySecret);
            System.out.println("用户id:" + httpsession.getId());
            System.out.println("用户注册的时候获取的验证码" + random);
        }
        try {
            this.sendMsg(phone, random);
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "mtlogin";// 返回页面
    }

    public String sendMsg(String phoneNum, String random) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient需要的几个参数
        final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
        // 替换成你的AK
        // final String accessKeyId = accessKeyId;// 你的accessKeyId,参考本文档步骤2
        // final String accessKeySecret = accessKeySecret;// 你的accessKeySecret，参考本文档步骤2
        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phoneNum);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("短信签名名称");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("短信模板");
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败

        request.setTemplateParam("{\"code\":\"" + random + "\"}");
        // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("qf");
        // 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            // 请求成功
            // 真实应用的时候验证码在服务端有记录
            // 客户端由客户来输入
            // 客户输入的验证码和服务端做匹配
            return random;
        }
        return "error";
    }

}