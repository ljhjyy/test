package com.easygo;

import com.easygo.utils.JsonUtils;
import org.junit.Test;

/**
 * @BelongsProject: easygo
 * @BelongsPackage: com.easygo
 * @Author: bruceliu
 * @QQ:1241488705
 * @CreateTime: 2020-04-03 10:33
 * @Description: TODO
 */
public class TestJson {

    @Test
    public void test1(){
        String json="[{\"id\":25,\"text\":\"颜色\"},{\"id\":26,\"text\":\"尺码\"},{\"id\":34,\"text\":\"尺寸\"},{\"id\":35,\"text\":\"重量\"}]";
        String str = JsonUtils.parseJson(json);
        System.out.println(str);
    }
}
