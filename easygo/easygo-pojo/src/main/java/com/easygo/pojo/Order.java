package com.easygo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.pojo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-19 16:10
 * @Description: 订单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = -4292954630373046273L;
    /**
     * 订单id  不可以重复 不自增
     */
    private Long order_id;
    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private Double payment;
    /**
     * 支付类型，1、在线支付，2、货到付款
     */
    private String payment_type;
    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String post_fee;
    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价
     */
    private String status;
    /**
     * 订单创建时间
     */
    private String create_time;
    /**
     * 订单更新时间
     */
    private String update_time;
    /**
     * 付款时间
     */
    private String payment_time;
    /**
     * 发货时间
     */
    private String consign_time;
    /**
     * 交易完成时间
     */
    private String end_time;
    /**
     * 交易关闭时间
     */
    private String close_time;
    /**
     * 物流名称
     */
    private String shipping_name;
    /**
     * 物流单号
     */
    private String shipping_code;
    /**
     * 用户id
     */
    private String user_id;
    /**
     * 买家留言
     */
    private String buyer_message;
    /**
     * 买家昵称
     */
    private String buyer_nick;
    /**
     * 买家是否已经评价
     */
    private String buyer_rate;
    /**
     * 收货人地区名称(省，市，县)街道
     */
    private String receiver_area_name;
    /**
     * 收货人手机
     */
    private String receiver_mobile;
    /**
     * 收货人邮编
     */
    private String receiver_zip_code;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 过期时间，定期清理
     */
    private String expire;
    /**
     * 发票类型(普通发票，电子发票，增值税发票)
     */
    private String invoice_type;
    /**
     * 订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端
     */
    private String source_type;
    /**
     * 商家ID
     */
    private String seller_id;
}
