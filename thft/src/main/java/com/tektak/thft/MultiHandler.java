package com.tektak.thft;

import org.apache.thrift.TException;

/**
 * Created by tektak on 7/14/15.
 */
public class MultiHandler implements MultiplicationService.Iface {
    @Override
    public int multiply(int n1, int n2) throws TException {
        System.out.println("Multiply(" + n1 + "," + n2 + ")");
        return n1 * n2;
    }
}
