package com.weiun.serialize.json;

import com.weiun.serialize.SerializeUtils;
import com.weiun.serialize.bean.User;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @author William
 * @Date 2019/3/18
 * @Description
 */
public class JsonLibTest {

    public static void main(String[] args) {
        User user = SerializeUtils.generate();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setAllowNonStringKeys(true);

        JSONObject jsonObject = JSONObject.fromObject(user, jsonConfig);
        System.out.println(jsonObject.toString());

        User bean = (User) JSONObject.toBean(jsonObject, User.class);
        bean.print();
    }

}
