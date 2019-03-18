package com.weiun.serialize.json;

import com.alibaba.fastjson.JSON;
import com.weiun.serialize.SerializeUtils;
import com.weiun.serialize.bean.User;

/**
 * @author William
 * @Date 2019/3/18
 * @Description
 */
public class FastjsonTest {

    public static void main(String[] args) {
        User user = SerializeUtils.generate();
        String s = JSON.toJSONString(user);
        System.out.println(s);
        User object = JSON.parseObject(s, User.class);
        object.print();
    }

}
