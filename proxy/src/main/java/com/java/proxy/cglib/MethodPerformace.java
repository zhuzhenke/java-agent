package com.java.proxy.cglib;

public class MethodPerformace {
    private long begin;
    private long end;
    private String serviceMethod;

    public MethodPerformace(String serviceMethod) {
        this.serviceMethod = serviceMethod;
        this.begin = System.currentTimeMillis();//记录方法调用开始时的系统时间
    }

    public void printPerformace() {
        //以下两行程序得到方法调用后的系统时间，并计算出方法执行花费时间
        end = System.currentTimeMillis();
        long elapse = end - begin;
        //报告业务方法执行时间
        System.out.println(serviceMethod + "花费" + elapse + "毫秒。");
    }
}