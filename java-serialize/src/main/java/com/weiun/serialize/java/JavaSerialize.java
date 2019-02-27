package com.weiun.serialize.java;

import com.weiun.serialize.SerializeUtils;
import com.weiun.serialize.bean.User;

import java.io.*;

/**
 * @author William
 * @Date 2019/2/27
 * @Description Java序列化
 */
public class JavaSerialize {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serialize();
        // deserialize
        FileInputStream fis = new FileInputStream(new File("./java-serialize/user.ser"));
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user = (User) ois.readObject();
        System.out.println("===============================================");
        user.print();
        ois.close();
    }

    private static void serialize() throws IOException {
        User user = SerializeUtils.generate();
        user.print();
        FileOutputStream fos = new FileOutputStream(new File("./java-serialize/user.ser"));
        ObjectOutput oop = new ObjectOutputStream(fos);
        oop.writeObject(user);
        oop.flush();
        oop.close();
    }

}
