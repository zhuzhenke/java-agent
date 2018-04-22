package com.java.proxy;

import com.java.proxy.cglib.CglibDynamicProxy;
import com.java.proxy.jdk.JdkDynamicProxy;

/**
 * @author zhuzhenke
 * @date 2018/01/27
 */
public class ProxyMain {
    public static void main(String[] args) {
        System.out.println("----jdk----");
        jdkProxy();

        System.out.println("----cglib----");
        cglibProxy();
    }

    public static void cglibProxy() {
        CglibDynamicProxy proxy = new CglibDynamicProxy();
        Hello hello = //① 通过动态生成子类的方式创建代理对象
                (Hello) proxy.getProxy(Hello.class);
        hello.sayHello();
    }


    public static void jdkProxy() {
        //加上这一句磁盘会产生一个名为“$Proxy0.class”的代理类Class文件
        //要在工程的根目录下创建com/sun/proxy目录，不是在模块下
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello iHello = (IHello) new JdkDynamicProxy().bind(new Hello());
        iHello.sayHello();
    }
}
