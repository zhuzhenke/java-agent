package com.java.agent.pre;


import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuzhenke
 * @date 2018/02/21
 */
public class MyAgent {
    /**
     * 以vm参数的形式载入，在程序main方法执行之前执行
     * 其jar包的manifest需要配置属性Premain-Class
     * <p/>
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中 * 并被同一个System ClassLoader装载 * 被统一的安全策略(security policy)和上下文(context)管理 * * @param agentOps * @param inst * @author SHANHY * @create 2016年3月30日
     */
    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("=========premain方法执行========");

        System.out.println("agentOps:" + agentOps);

        Class[] classes = inst.getAllLoadedClasses();
        System.out.println("classes.length:" + classes.length);
        for (Class cl : classes) {
            System.out.println(cl.getName());
        }

        System.out.println("===ObjectSize===");
        System.out.println("new Integer(1):" + inst.getObjectSize(new Integer(1)));
        System.out.println("new Long(9):" + inst.getObjectSize(new Long(9)));

        Map<Long, Long> map = new HashMap<Long, Long>();
        map.put(1L, 1L);
        System.out.println("Map<Long, Long> map:" + inst.getObjectSize(map));


        /**
         * 这里可以操作字节码
         */
//        // Instrumentation提供的addTransformer方法，在类加载时会回调ClassFileTransformer接口
        inst.addTransformer(new PerfMonXformer());


        /**
         * premain这里出现异常，那么jvm将启动不了
         */
//        throw new RuntimeException("异常退出");
    }

    /**
     * 如果不存在 premain(String agentOps, Instrumentation inst) * 则会执行 premain(String agentOps) * * @param agentOps * @author SHANHY * @create 2016年3月30日
     */
    public static void premain(String agentOps) {
        System.out.println("=========premain方法执行2========");
        System.out.println(agentOps);
    }

    /**
     * 以Attach的方式载入，在Java程序启动后执行
     * 其jar包的manifest需要配置属性Agent-Class
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        /**
         * 这里的话会输出到main启动程序，而不是attach程序
         */
        System.out.println("agentmain start...");
        System.out.println("agentmain:agentArgs" + agentArgs);
    }

}
