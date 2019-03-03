package com.weiun.serialize;

import com.weiun.serialize.bean.Photo;
import com.weiun.serialize.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William
 * @Date 2019/2/27
 * @Description
 */
public class SerializeUtils {

    public static User generate() {
        User user = new User();
        user.setName("William");
        user.setAge(18);

        Photo avatar = new Photo();
        avatar.setNote("Avatar");
        avatar.setUrl("http://xxxx.xxx.xxx");
        user.setAvatar(avatar);

        Map<Integer, List<Photo>> map = new HashMap<>();
        List<Photo> list = new ArrayList<>();
        Photo photo = new Photo();
        photo.setNote("Photo");
        photo.setUrl("https://zzzz.zzz.zzzz");
        list.add(photo);
        map.put(1, list);
        user.setPhotoMap(map);
        return user;
    }

}