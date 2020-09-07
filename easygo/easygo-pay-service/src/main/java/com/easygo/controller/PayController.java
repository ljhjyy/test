package com.easygo.controller;

import com.easygo.utils.HttpClient;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-22 15:59
 * @Description: 支付的服务端控制器
 */
@RestController
@Scope("prototype")
public class PayController {

    //目前先写死，后期写到配置文件中
    //@Value("${appid}")
    private String appid = "wx632c8f211f8122c6";

    //@Value("${partner}")
    private String partner = "1497984412";

    //@Value("${partnerkey}")
    private String partnerkey = "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";


    private String notifyurl = "http://a31ef7db.ngrok.io/WeChatPay/WeChatPayNotify";


    /**
     * 01-调用统一下单API，获取下单的MAP信息，得到这个MAP信息，就可以生成支付的二维码
     * https://api.mch.weixin.qq.com/pay/unifiedorder
     * @param out_trade_no
     * @param total_fee
     * @return
     */
    @RequestMapping("/createNative")
    public Map createNative(@RequestParam("out_trade_no") String out_trade_no, @RequestParam("total_fee") String total_fee) {
        //1.准备一个Map参数集合
        Map<String, String> param = new HashMap<String, String>();
        //调用接口必须的参数，少一个都会报错
        param.put("appid", appid);// 公众号
        param.put("mch_id", partner);// 商户号
        param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
        param.put("body", "easygo商城支付");// 商品描述
        param.put("out_trade_no", out_trade_no);// 商户订单号
        param.put("total_fee", total_fee);// 总金额（分）  分为单位  1分钱
        param.put("spbill_create_ip", "127.0.0.1");// IP
        param.put("notify_url", notifyurl);// 回调地址(随便写)
        param.put("trade_type", "NATIVE");// 交易类型  扫码支付
        try {
            // 2.生成要发送的xml
            //将map参数集合转为XML格式
            String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
            System.out.println(xmlParam);
            //发送一个调用请求，注意一定是post请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setHttps(true);
            client.setXmlParam(xmlParam);
            client.post();

            // 3.获得结果，接口返回的数据，但是返回的是XML数据
            String result = client.getContent();
            System.out.println(result);
            //将返回的XML数据转为集合
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);

            //结果集
            Map<String, String> map = new HashMap<>();
            map.put("code_url", resultMap.get("code_url"));// 支付地址，要支付的地址，后期生成二维码
            map.put("total_fee", total_fee);// 总金额，要支付的金额
            map.put("out_trade_no", out_trade_no);// 订单号

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }


    /**
     * 02-查询订单接口 https://api.mch.weixin.qq.com/pay/orderquery
     * @param out_trade_no
     * @return
     */
    @RequestMapping("/queryPayStatus")
    public Map queryPayStatus(@RequestParam("out_trade_no") String out_trade_no){
        //调用的参数集合
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", appid);// 公众号
        param.put("mch_id", partner);// 商户号
        param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
        param.put("out_trade_no", out_trade_no);// 订单号

        try {
            // 2.生成要发送的xml
            //将map参数集合转为XML格式,转XML并设置签名
            String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
            System.out.println(xmlParam);
            //发送一个调用请求，注意一定是post请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setHttps(true);
            client.setXmlParam(xmlParam);
            client.post();

            // 3.获得结果，接口返回的数据，但是返回的是XML数据
            String result = client.getContent();
            System.out.println(result);
            //将返回的XML数据转为集合
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            System.out.println("支付状态的map:"+resultMap);
            return  resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}