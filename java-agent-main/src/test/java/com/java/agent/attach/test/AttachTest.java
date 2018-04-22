package com.java.agent.attach.test;

import org.junit.Test;

/**
 * @author zhuzhenke
 * @date 2018/02/23
 */
public class AttachTest {
    @Test
    public void testAgentByAttach() throws Exception {
//        System.out.println("===VirtualMachine===");
//        List<VirtualMachineDescriptor> descriptorList = VirtualMachine.list();
//        for (VirtualMachineDescriptor vmd : descriptorList) {
//            System.out.println("pid:" + vmd.id() + ":" + vmd.displayName());
//
//
//            if (vmd.displayName().contains("java-agent")) {
//                String agentPath = "/Users/admin/Desktop/java-agent-jar-1.0-SNAPSHOT.jar";
//                VirtualMachine virtualmachine = VirtualMachine.attach(vmd.id());
//                virtualmachine.loadAgent(agentPath, "com.sun.management.jmxremote");
//
//                Properties properties = virtualmachine.getAgentProperties();
//                System.out.println("sun.jvm.args:"+properties.get("sun.jvm.args"));
//                System.out.println("sun.java.command:"+properties.get("sun.java.command"));
//                virtualmachine.detach();
//            }
//        }
    }
}
