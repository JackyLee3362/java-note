package edu.note.thirft.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import edu.note.thrift.sdk.HelloWorldService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-31 11:09
 */
@Slf4j
public class HelloServerDemo {
    public static final int SERVER_PORT = 8090;

    public void startServer() {
        try {
            log.info("HelloWorld TSimpleServer start ....");

            // tprocessor 也可以是这个类型 HelloWorldService.Processor<HelloWorldService.Iface>
            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());

            // 简单的单线程服务模型，一般用于测试
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            // 设置不同的协议 Binary/Compact/JSON
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            // tArgs.protocolFactory(new TCompactProtocol.Factory());
            // tArgs.protocolFactory(new TJSONProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (Exception e) {
            log.error("Server start error!!!");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        HelloServerDemo server = new HelloServerDemo();
        server.startServer();
    }
}
