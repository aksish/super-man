package com.tektak.thft;

import com.tektak.inv.Inventory;
import com.tektak.inv.PeopleInventory;
import com.tektak.inv.Person;
import org.apache.thrift.TException;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by tektak on 7/14/15.
 */
public class MultiClient {
    public static void main(String [] args) {

        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol mul = new TMultiplexedProtocol(protocol, "multiplication");
            MultiplicationService.Client client = new MultiplicationService.Client(mul);
            System.out.println("result : " + client.multiply(4,7));

            TMultiplexedProtocol inv = new TMultiplexedProtocol(protocol, "inventory");
            Inventory.Client invClient = new Inventory.Client(inv);
            Person p = new Person();
            p.setUserId(10001);
            System.out.println("added person: " + invClient.addPerson(p));
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

}
