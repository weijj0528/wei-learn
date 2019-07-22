package com.weiun.reflect;

import com.weiun.reflect.bean.User;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @author William
 * @Date 2019/3/19
 * @Description 反射方法操作
 */
public class ReflectMethodTest {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 获取Public方法,能获取到父类的方法
        Method[] methods = User.class.getMethods();
        printMethods(methods);
        System.out.println("-------------------------------------");
        // 获取所有方法包括私有方法，但不能获取父类的方法
        Method[] declaredMethods = User.class.getDeclaredMethods();
        printMethods(declaredMethods);
        // 方法的执行
    }

    private static void printMethods(Method[] methods) {
        for (int i = 0; i < methods.length; i++) {
            printMethod(methods[i]);
        }
    }

    private static void printMethod(Method method) {
        // 方法名
        String name = method.getName();
        // 返回类型
        Class<?> returnType = method.getReturnType();
        // 参数
        Parameter[] parameters = method.getParameters();
        int modifiers = method.getModifiers();
        StringBuilder sb = new StringBuilder();
        if (Modifier.isPublic(modifiers)) {
            sb.append("public ");
        } else if (Modifier.isProtected(modifiers)) {
            sb.append("protected ");
        } else if (Modifier.isPrivate(modifiers)) {
            sb.append("private ");
        }
        if (Modifier.isStatic(modifiers)) {
            sb.append("static ");
        }
        if (Modifier.isFinal(modifiers)) {
            sb.append("final ");
        }
        sb.append(returnType.getName());
        sb.append(" ");
        sb.append(name);
        sb.append("(");
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            sb.append(parameter.getType().getSimpleName());
            sb.append(" ");
            sb.append(parameter.getName());
            sb.append(",");
        }
        if (parameters.length > 0) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append(")");
        System.out.println(sb.toString());
    }


}
