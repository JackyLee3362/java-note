package edu.note.thirft.server;

import org.apache.thrift.TException;

import edu.note.thirft.sdk.HelloWorldService;

/**
 * @author jackylee
 * @date 2025-10-31 11:00
 */
public class HelloWorldImpl implements HelloWorldService.Iface {

    @Override
    public String sayHello(String username) throws TException {
        return "hello " + username;
    }

}
