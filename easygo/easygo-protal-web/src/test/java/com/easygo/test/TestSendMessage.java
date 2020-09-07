package com.easygo.test;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.test
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-16 16:36
 * @Description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSendMessage {

    //短信地址
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    @Test
    public void test(){
        String phone="18030527896"; //这是我的小号
        sendMsg(phone);
    }

    public static void sendMsg(String phoneNumber){
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
        //验证码
        int mobile_code = (int)((Math.random()*9+1)*100000);
        //这个是短信的内容，没有付费之前，短信的模板是不能修改的，只能测试!!!! 只有验证码可以改！
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");  //短信内容
        //把短信的配置信息，配置到配置文件中 后期优化
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "C93877773"), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", "20d8cea615d96ecfc59718a54d1a3cc2"),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", phoneNumber), // 发送人的手机号
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();
            //System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally{
            method.releaseConnection();
        }
    }

}
