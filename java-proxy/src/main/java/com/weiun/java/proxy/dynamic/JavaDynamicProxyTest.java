package com.weiun.java.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * The type Java dynamic proxy test.
 * Java动态代理
 *
 * @author William.Wei
 */
public class JavaDynamicProxyTest {

    /**
     * The entry point of application.
     * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // 目标对象
        IUserDao userDao = new UserDao();
        // 代理对象
        IUserDao userDaoProxy = (IUserDao) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IUserDao.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Proxy:开始保存！");
                userDao.save();
                System.out.println("Proxy:保存结束！");
                return null;
            }
        });
        // 执行代理对象方法
        userDaoProxy.save();
        // 可以扩展 InvocationHandler 实现如Mybatis Plugin体系
    }

}

/**
 * The interface User dao.
 */
interface IUserDao {
    /**
     * Save.
     */
    void save();
}

/**
 * The type User dao.
 */
class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("UserDao：保存成功！");
    }
}


