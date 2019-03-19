package com.weiun.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.weiun.serialize.SerializeUtils;
import com.weiun.serialize.bean.User;

import java.util.Date;

/**
 * @author William
 * @Date 2019/3/18
 * @Description
 */
public class FastjsonTest {

    public static void main(String[] args) {
        User user = SerializeUtils.generate();
        SerializeConfig config = new SerializeConfig();
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));

        String s = JSON.toJSONString(user, config, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(s);
        User object = JSON.parseObject(s, User.class);
        object.print();
    }

}
