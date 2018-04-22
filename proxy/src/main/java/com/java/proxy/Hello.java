package com.java.proxy;

/**
 * @author zhuzhenke
 * @date 2018/01/27
 */
public final class Hello implements IHello {

    @Override
    public void sayHello() {
        System.out.println("hello!");
    }
}
