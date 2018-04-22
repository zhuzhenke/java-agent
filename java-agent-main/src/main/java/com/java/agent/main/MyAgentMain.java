package com.java.agent.main;

/**
 * @author zhuzhenke
 * @date 2018/02/21
 */
public class MyAgentMain {

    /**
     * java -javaagent:java-agent-jar-1.0-SNAPSHOT.jar=ABCDEF
     * -javaagent:java-agent-jar-1.0-SNAPSHOT.jar=OPQRST
     * -jar java-agent-main-1.0-SNAPSHOT.jar
     */
    public static void main(String[] args) {
        System.out.println("myAgentMain Start ...");
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new WaitThread()).start();
        }
    }

    static class WaitThread implements Runnable {
        @Override
        public void run() {
            write();
        }
    }

    public static void write() {
        System.out.println("Hello");
    }
}
