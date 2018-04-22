package com.java.agent.pre;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author zhuzhenke
 * @date 2018/02/23
 */
public class PerfMonXformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        System.out.println("PerfMonXformer.transform:className1:" + className);

        if (!"com/java/agent/main".equals(className)) {
            // 只修改指定的Class
            return classfileBuffer;
        }
        System.out.println("PerfMonXformer.transform:className2:" + className);
        byte[] transformed = null;
        CtClass cl = null;
        try {
            // CtClass、ClassPool、CtMethod、ExprEditor都是javassist提供的字节码操作的类
            ClassPool pool = ClassPool.getDefault();
            cl = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
            CtMethod[] methods = cl.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                System.out.println("PerfMonXformer.transform:methods:(" + i + "):" + methods[i].getName());
                methods[i].instrument(new ExprEditor() {

                    @Override
                    public void edit(MethodCall m) throws CannotCompileException {
                        System.out.println("MethodCall.getMethodName:" + m.getMethodName());
                        // 把方法体直接替换掉，其中 $proceed($$);是javassist的语法，用来表示原方法体的调用
                        m.replace("{ long stime = System.currentTimeMillis();" + " $_ = $proceed($$);"
                                + "System.out.println(\"" + m.getClassName() + "." + m.getMethodName()
                                + " cost:\" + (System.currentTimeMillis() - stime) + \" ms\"); }");
                    }
                });
            }
            // javassist会把输入的Java代码再编译成字节码byte[]
            transformed = cl.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cl != null) {
                cl.detach();// ClassPool默认不会回收，需要手动清理
            }
        }
        return transformed;

    }
}
