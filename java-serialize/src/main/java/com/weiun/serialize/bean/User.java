package com.weiun.serialize.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author William
 * @Date 2019/2/27
 * @Description 用户
 */
public class User implements Serializable {

    private String name;

    private Integer age;

    private Double amount;

    private Photo avatar;

    private Map<Integer, List<Photo>> photoMap;

    public void print() {
        System.out.println("name:" + name);
        System.out.println("age:" + name);
        System.out.println("amount:" + amount);
        System.out.println("avatar:" + (avatar == null ? "NULL" : avatar.toString()));
        if (photoMap != null) {
            photoMap.keySet().stream().forEach(key -> {
                System.out.println("photoMap-" + key);
                List<Photo> photos = photoMap.get(key);
                if (photos != null && !photos.isEmpty()) {
                    photos.forEach(photo -> System.out.println("photo:" + photo == null ? "NULL" : photo.toString()));
                } else {
                    System.out.println("Empty");
                }
            });
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    public Map<Integer, List<Photo>> getPhotoMap() {
        return photoMap;
    }

    public void setPhotoMap(Map<Integer, List<Photo>> photoMap) {
        this.photoMap = photoMap;
    }
}
