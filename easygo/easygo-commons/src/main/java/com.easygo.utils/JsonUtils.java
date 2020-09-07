package com.easygo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo.utils
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-03 10:20
 * @Description: Json解析工具类
 */
public class JsonUtils {

    //原字符串：[{"id":23,"text":"美特斯邦威"},{"id":24,"text":"森马"},{"id":32,"text":"阿迪达斯"},{"id":42,"text":"花花公子"}]
    //结果: 美特斯邦威,森马,阿迪达斯,花花公子
    public static String parseJson(String json){
        ObjectMapper objectMapper=new ObjectMapper();
        StringBuffer sb=new StringBuffer();
        try {
            List<JsonObject> brandObjects= objectMapper.readValue(json,new TypeReference<ArrayList<JsonObject>>(){});
            for (JsonObject brandObject : brandObjects) {
                if(brandObject!=null){
                    String text = brandObject.getText();
                    sb.append(text+",");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = sb.toString();
        s=s.substring(0,s.length()-1);
        return s;
    }

    public static List<JsonObject> parsejson2List(String json){
        ObjectMapper objectMapper=new ObjectMapper();
        List<JsonObject> list=null;
        try {
            list= objectMapper.readValue(json,new TypeReference<ArrayList<JsonObject>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    //这个方法是把图片JSON字符串转为List集合
    public static List<OrderUrl> parseitem_images2List(String json){
        ObjectMapper objectMapper=new ObjectMapper();
        List<OrderUrl> list=null;
        try {
            list= objectMapper.readValue(json,new TypeReference<ArrayList<OrderUrl>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //这个方法是把图片JSON字符串转为List集合
    public static List<AttributeNameObject> parseAttributeName2List(String json){
        ObjectMapper objectMapper=new ObjectMapper();
        List<AttributeNameObject> list=null;
        try {
            list= objectMapper.readValue(json,new TypeReference<ArrayList<AttributeNameObject>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * JSon转换Map集合
     * @param json
     * @return
     */
    public static Map json2Map(String json){
        Map map=null;
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            map=objectMapper.readValue(json,new TypeReference<Map>(){});
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return map;
    }

}

