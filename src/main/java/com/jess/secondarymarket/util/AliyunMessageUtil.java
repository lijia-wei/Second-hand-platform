package com.jess.secondarymarket.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Jess
 * @date 2020/7/31 14:12
 */
@Slf4j
public class AliyunMessageUtil {
/*
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";
    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static final String accessKeyId = "LTAI4G3n98pF4u5SJPWHF9Kp";
    private static final String accessKeySecret = "zRdVtjo5kutIytQbD63uVtvZut1Eqy";

    public static SendSmsResponse sendSms(Map<String, String> paramMap) throws com.aliyuncs.exceptions.ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        //必填:待发送手机号
        request.setPhoneNumbers(paramMap.get("phoneNumber"));

        //必填:短信签名-可在短信控制台中找到
        request.setSignName(paramMap.get("msgSign"));

        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(paramMap.get("templateCode"));

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(paramMap.get("jsonContent"));

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }*/

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    /* 用户登录名称 lmj@1460508932314229.onaliyun.com
     登录密码 oG2?vkR#y2#8U8UwtAcUmcH}YJOmMErk
         AccessKey ID
         AccessKeySecret*/
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI4G3n98pF4u5SJPWHF9Kp";  // TODO 修改成自己的
    static final String accessKeySecret = "zRdVtjo5kutIytQbD63uVtvZut1Eqy";   // TODO 修改成自己的
    public static SendSmsResponse sendSms(String telephone, String code) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(telephone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("Jessblogs");    // TODO 修改成自己的
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_198672298");    // TODO 修改成自己的
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");
        }else {
            System.out.println("短信发送失败！");
        }
        return sendSmsResponse;
    }
}

