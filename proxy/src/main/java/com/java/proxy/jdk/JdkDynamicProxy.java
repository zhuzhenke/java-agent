package com.java.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhuzhenke
 * @date 2018/01/27
 */
public class JdkDynamicProxy implements InvocationHandler {
    Object originObject;

    public Object bind(Object proxy) {
        originObject = proxy;
        return Proxy.newProxyInstance(proxy.getClass().getClassLoader(), proxy.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke begin...");
        Object object = method.invoke(originObject, args);
        System.out.println("invoke end...");
        return object;
    }
}
