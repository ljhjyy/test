package com.easygo.controller;

import com.easygo.api.ItemClient;
import com.easygo.pojo.CartBean;
import com.easygo.pojo.Item;
import com.easygo.utils.JsonObject;
import com.easygo.utils.MessageResults;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.controller
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-20 15:09
 * @Description: 购物车控制器
 */
@Controller
@Scope("prototype")
public class CartController {

    @Autowired
    ItemClient itemClient;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 03-移除购物车
     * @param cartId
     * @return
     */
    @RequestMapping("/cart_delete")
    @ResponseBody
    public MessageResults cart_delete(Long cartId,HttpServletRequest request,HttpServletResponse response){
        MessageResults results=null;
        try {
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject.getPrincipal();
            //1.登录还是不登录，本地有可能有购物车Cookie
            String easygocarts_localCookies = this.getCookie("easygocarts", request);
            ObjectMapper mapper = new ObjectMapper();
            //浏览器中购物车的Cookie数据
            List<CartBean> cartBeans = mapper.readValue(easygocarts_localCookies, new TypeReference<ArrayList<CartBean>>() {});
            if(principal==null){
                System.out.println("没有登录，删除本地Cookie中对应的数据");
                cartBeans=this.deleteCarts(cartBeans,cartId);
                //把购物车集合存入本地Cookie
                String cartsCookiesJson = mapper.writeValueAsString(cartBeans);
                System.out.println("删除完之后的Cookie集合:"+cartsCookiesJson);
                //存入本地浏览器Cookie,存入放入名字要和取的时候名字一致
                this.setCookie("easygocarts",cartsCookiesJson,response);
                return new MessageResults(200, "移除购物车成功!");
            }else{
                System.out.println("用户登录");
                String loginName = principal.toString();
                System.out.println("商城用户目前是登录状态:" + loginName);
                //从该用户的redis中获取原来的购物车数据集合
                redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
                List<CartBean> redisCartBeans = (ArrayList<CartBean>)redisTemplate.opsForValue().get(loginName);//key redis中的key  用户名不重复
                if(redisCartBeans==null){
                    redisCartBeans=new ArrayList<CartBean>();//如果Redis连key都没有，第一次
                }
                //移除之后的集合
                redisCartBeans = this.deleteCarts(redisCartBeans, cartId);

                redisTemplate.opsForValue().set(loginName,redisCartBeans);
                return new MessageResults(200, "移除购物车成功!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return new MessageResults(500,"移除成功");
    }

    /**
     * 删除集合中购物车数据
     * @param carts
     * @param cartId
     * @return
     */
    public List<CartBean> deleteCarts(List<CartBean> carts,Long cartId){
        List<CartBean> newcarts=new ArrayList<CartBean>();
        for (CartBean cart : carts) {
            if(!cart.getItem_id().toString().equals(cartId.toString())){
                newcarts.add(cart);
            }
        }
        return newcarts;
    }


    /**
     * 02-显示购物车中的数据
     * @return
     */
    @RequestMapping("/cart_show")
    public String showCarts(HttpServletRequest request, Model model,HttpServletResponse response){
        try {
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject.getPrincipal();
            //1.登录还是不登录，本地有可能有购物车Cookie
            String easygocarts_localCookies = this.getCookie("easygocarts", request);
            ObjectMapper mapper = new ObjectMapper();
            //浏览器中购物车的Cookie数据
            List<CartBean> cartBeans = mapper.readValue(easygocarts_localCookies, new TypeReference<ArrayList<CartBean>>() {});
            if (principal == null) {
                System.out.println("用户未登录!");
                model.addAttribute("mycarts",cartBeans);
            }else{
                System.out.println("用户登录");
                String loginName = principal.toString();
                System.out.println("商城用户目前是登录状态:" + loginName);
                //从该用户的redis中获取原来的购物车数据集合
                redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
                List<CartBean> redisCartBeans = (ArrayList<CartBean>)redisTemplate.opsForValue().get(loginName);//key redis中的key  用户名不重复
                if(redisCartBeans==null){
                    redisCartBeans=new ArrayList<CartBean>();//如果Redis连key都没有，第一次
                }
                //把本地的购物车的Cookie集合和用户下Redis购物车集合并,如果是相同sku那么数量相加，小计计算，如果么有重复的部分合并
                List<CartBean> mergeCartList = this.mergeCartList(redisCartBeans, cartBeans);
                //把合并之后的集合存入redis
                redisTemplate.opsForValue().set(loginName,mergeCartList);
                model.addAttribute("mycarts",mergeCartList);
                this.deleteCookie("easygocarts",response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return "cart";
    }


    /**
     * 01-新增购物车
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/cart_add")
    public MessageResults addGoodsToCart(Long itemId, Long num, HttpServletRequest request,HttpServletResponse response) {
        MessageResults results = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject.getPrincipal();
            //1.登录还是不登录，本地有可能有购物车Cookie
            String easygocarts_localCookies = this.getCookie("easygocarts", request);
            ObjectMapper mapper = new ObjectMapper();
            //浏览器中购物车的Cookie数据
            List<CartBean> cartBeans = mapper.readValue(easygocarts_localCookies, new TypeReference<ArrayList<CartBean>>() {});
            System.out.println("添加购物车之前，浏览器Cookie数据:" + cartBeans);
            if (principal == null) {
                System.out.println("商城用户目前是没有登录!");
                cartBeans=this.addCarts(itemId,num,cartBeans);
                //把购物车集合存入本地Cookie
                String cartsCookiesJson = mapper.writeValueAsString(cartBeans);
                System.out.println("新增完之后的Cookie集合:"+cartsCookiesJson);
                //存入本地浏览器Cookie,存入放入名字要和取的时候名字一致
                this.setCookie("easygocarts",cartsCookiesJson,response);
                return new MessageResults(200, "加入购物车成功!");
            } else {
                String loginName = principal.toString();
                System.out.println("商城用户目前是登录状态:" + loginName);
                //用户登录啦数据存储？Redis
                cartBeans=this.addCarts(itemId,num,cartBeans);
                //从该用户的redis中获取原来的购物车数据集合
                redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
                List<CartBean> redisCartBeans = (ArrayList<CartBean>)redisTemplate.opsForValue().get(loginName);//key redis中的key  用户名不重复
                if(redisCartBeans==null){
                    redisCartBeans=new ArrayList<CartBean>();//如果Redis连key都没有，第一次
                }
                //把本地的购物车的Cookie集合和用户下Redis购物车集合并,如果是相同sku那么数量相加，小计计算，如果么有重复的部分合并
                List<CartBean> mergeCartList = this.mergeCartList(redisCartBeans, cartBeans);
                //把合并之后的集合存入redis
                redisTemplate.opsForValue().set(loginName,mergeCartList);
                //情况本地的cookie
                this.deleteCookie("easygocarts",response);

                return new MessageResults(200, "加入购物车成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new MessageResults(500, "加入购物车异常!");
    }

    /**
     * 合并本地和redis合并
     * @param redisCartBeans
     * @param cartBeans
     * @return
     */
    public List<CartBean> mergeCartList(List<CartBean> redisCartBeans,List<CartBean> cartBeans){
        List<CartBean> mergeCartList=new ArrayList<CartBean>();
        for (CartBean cartBean : redisCartBeans) {
            boolean b = this.isExits(mergeCartList, cartBean.getItem_id());
            if(b==false){
                mergeCartList.add(cartBean);
            }else{
                for (CartBean bean : mergeCartList) {
                    if(bean.getItem_id().toString().equals(cartBean.getItem_id().toString())){
                        bean.setNum(bean.getNum()+cartBean.getNum());
                        bean.setTotal_fee(bean.getNum()*bean.getPrice());
                    }
                }
            }
        }
        for (CartBean cartBean : cartBeans) {
            boolean b = this.isExits(mergeCartList, cartBean.getItem_id());
            if(b==false){
                mergeCartList.add(cartBean);
            }else{
                for (CartBean bean : mergeCartList) {
                    if(bean.getItem_id().toString().equals(cartBean.getItem_id().toString())){
                        bean.setNum(bean.getNum()+cartBean.getNum());
                        bean.setTotal_fee(bean.getNum()*bean.getPrice());
                    }
                }
            }
        }
        return mergeCartList;
    }

    /**
     * 把一个SKU添加到购物车集合中，
     * 如果集合中事先有这个sku商品，那么集合中中这款商品的数量+num，且total_fee计算一下
     * 如果这个集合中没有这个sku商品，那么把这个新的sku商品添加到集合中
     *
     * @param itemId
     * @param num
     * @param cartBeans
     * @return
     */
    public List<CartBean> addCarts(Long itemId, Long num, List<CartBean> cartBeans) {
        boolean exits = isExits(cartBeans, itemId);
        if(exits==false){
            //购物车集合中，没有添加过这个sku商品，那么新增一个商品到集合中
            CartBean cartBean=new CartBean();
            //SKU对象
            Item item = itemClient.getItemById(itemId);
            cartBean.setItem_id(itemId);
            cartBean.setGoods_id(item.getGoods_id());
            cartBean.setTitle(item.getTitle());
            cartBean.setPrice(item.getPrice());
            cartBean.setSeller_id(item.getSeller_id());
            cartBean.setNum(num);
            cartBean.setTotal_fee(item.getPrice()*item.getNum()); //小计
            cartBean.setPic_path(item.getImage());
            //添加一个购物车SKU
            cartBeans.add(cartBean);
        }else{
            //更新这个SKU商品在集合中的数量以及它对应的total_fee
            for(CartBean cartBean:cartBeans){
                if(cartBean.getItem_id().toString().equals(itemId.toString())){
                    cartBean.setNum(cartBean.getNum()+num);
                    cartBean.setTotal_fee(cartBean.getPrice()*cartBean.getNum()); //小计
                }
            }
        }
        return cartBeans;
    }

    /**
     * 判断一个itemId是否在购物车中存在
     * @param cartBeans
     * @param itemId
     * @return
     */
    public boolean isExits(List<CartBean> cartBeans, Long itemId) {
        for (CartBean cartBean : cartBeans) {
            if (cartBean.getItem_id().toString().equals(itemId.toString())) {
                //购物车中有这个SKU商品
                return true;
            }
        }
        return false;
    }


    /**
     * 存入cookie到本地浏览器
     * @param key
     * @param value
     * @param response
     */
    public void setCookie(String key, String value, HttpServletResponse response){
        try {
            Cookie cookie=new Cookie(key, URLEncoder.encode(value,"UTF-8"));
            //强哥他们Cookie有效期多长呢？
            cookie.setMaxAge(60*60); //假设存储1小时
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 读取本地的Cookie
     * @param key
     * @param request
     * @return
     */
    public String getCookie(String key, HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (key.equals(cookie.getName())) {
                        //cookie中有中文怎读取？乱码问题,Cookie中只能存储字符串，存购物车JSON格式
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
        return "[]"; //防止JSON字符串转对象报错！
    }

    /**
     * 清除用户Cookie
     */
    public void deleteCookie(String key,HttpServletResponse response) {
        try {
            Cookie cookie=new Cookie(key,URLEncoder.encode("[]","UTF-8"));
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            System.out.println("本地的cookie清除:"+key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
    }


}
