package com.easygo.controller;

import com.easygo.api.PayClient;
import com.easygo.utils.IdWorker;
import com.easygo.utils.MessageResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-22 16:25
 * @Description: TODO
 */
@Controller
@Scope("prototype")
public class PayController {

    @Autowired
    PayClient payClient;

    /**
     * 01-测试二维码生成接口
     * @param model
     * @return
     */
    @RequestMapping("/pay")
    public String pay(Model model){

        //模拟要支付订单编号,订单编号是参数需要传进来
        IdWorker idWorker=new IdWorker();
        String  out_trade_no= idWorker.nextId()+"";
        System.out.println("要支付的订单编号是:"+out_trade_no);
        Map map = payClient.createNative(out_trade_no, "1");//模拟1分钱
        System.out.println("调用接口返回的数据:"+map);
        //要支付的微信地址，后期生成二维码
        Object code_url = map.get("code_url");
        Double total_fee = Double.valueOf(map.get("total_fee").toString());

        model.addAttribute("out_trade_no",out_trade_no);
        model.addAttribute("code_url",code_url);
        model.addAttribute("total_fee",total_fee);

        //页面显示支付的二维码
        return "pay";
    }

    /**
     * 查询订单状态
     * @return
     */
    @RequestMapping("/queryPayStatus")
    @ResponseBody
    public MessageResults queryPayStatus(String out_trade_no){
        System.out.println("用户的订单编号是:"+out_trade_no);
        MessageResults results=new MessageResults(500,"支付失败");
        int count=0;
        while(true){
            System.out.println("--------->轮询微信支付接口,查询支付状态...........");
            //调用查询接口
            Map<String,String> map = payClient.queryPayStatus(out_trade_no);
            System.out.println("------>查询接口:"+map);
            if(map==null){
                results=new MessageResults(500,"微信接口异常");
                break;
            }
            if(map.get("trade_state").equals("SUCCESS")){
                System.out.println("支付成功");
                results=new MessageResults(200,"支付成功");
                break;
            }
            try {
                Thread.sleep(4000); //间隔4秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
            count++;
            if(count>=100){
                results=new MessageResults(505,"二维码超时");
            }
        }
        return results;
    }

    /**
     * 支付成功跳转失败页面
     * @return
     */
    @RequestMapping("/payFail")
    public String payFail(){
        System.out.println("支付失败");
        return "payFail";
    }

    /**
     * 支付成功跳转成功页面
     * @param total_fee
     * @param model
     * @return
     */
    @RequestMapping("/paySuccess/{total_fee}")
    public String paySuccess(@PathVariable("total_fee") Double total_fee, Model model){
        System.out.println("支付成功");
        model.addAttribute("total_fee",total_fee);
        return "paySuccess";
    }

}
