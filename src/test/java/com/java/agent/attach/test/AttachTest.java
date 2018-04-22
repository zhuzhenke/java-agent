package com.java.agent.attach.test;

import org.junit.Test;

import java.util.List;
import com.sun.tools.attach.*;
/**
 * @author zhuzhenke
 * @date 2018/02/23
 */
public class AttachTest {
    @Test
    public void testAgent() {
        System.out.println("===VirtualMachine===");
        List<VirtualMachineDescriptor> descriptorList = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : descriptorList) {
            System.out.println("pid:" + vmd.id() + ":" + vmd.displayName());
        }
    }
}
