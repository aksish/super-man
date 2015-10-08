package com.tektak.thft;

import com.tektak.inv.Inventory;
import com.tektak.inv.PeopleInventory;
import org.apache.thrift.TException;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;

/**
 * Created by tektak on 7/14/15.
 */
public class MultiServer {
    public static TMultiplexedProcessor processor;

    public static void main(String[] args) {
        try{
            processor = new TMultiplexedProcessor();
            processor.registerProcessor(
                    "multiplication",
                    new  MultiplicationService.Processor(new  MultiHandler()));

            processor.registerProcessor(
                    "inventory",
                    new Inventory.Processor(new PeopleInventory())
            );

            TServerTransport serverTransport = new TServerSocket(9090);

            TTransportFactory factory = new TTransportFactory();

            TServer.Args args1 = new TServer.Args(serverTransport);
            args1.processor(processor);
            args1.transportFactory(factory);

            TSimpleServer server = new TSimpleServer(args1);
            System.out.println("Simple server starting...");
            server.serve();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
