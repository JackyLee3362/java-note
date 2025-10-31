package edu.note.thirft.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import edu.note.thirft.sdk.HelloWorldService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-31 11:09
 */
@Slf4j
public class HelloServerDemo {
    // 端口号
    static final int SERVER_PORT = 8090;

    // HelloWorldService.Processor<HelloWorldService.Iface>
    static final TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());

    // 处理工厂
    static final TProtocolFactory binaryFactory = new TBinaryProtocol.Factory();
    static final TProtocolFactory compactFactory = new TCompactProtocol.Factory();
    static final TProtocolFactory jsonFactory = new TJSONProtocol.Factory();

    static final TTransportFactory framedTransport = new TFramedTransport.Factory();

    // 简单服务器
    static TServer simpleServer() throws TTransportException {

        // 定义 Socket
        TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
        // 定义 Args
        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(tprocessor);
        tArgs.protocolFactory(binaryFactory);
        // 简单的单线程服务模型，一般用于测试
        TServer server = new TSimpleServer(tArgs);
        return server;
    }

    // 线程池服务模型
    static TServer threadPoolServer() throws TTransportException {
        // 定义 Socket
        TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
        // 定义 Args
        TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
        ttpsArgs.processor(tprocessor);
        ttpsArgs.protocolFactory(binaryFactory);
        // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
        TServer server = new TThreadPoolServer(ttpsArgs);
        return server;
    }

    // 非阻塞服务器
    static TServer nonBlockServer() throws TTransportException {
        // 定义 Socket
        TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(SERVER_PORT);
        // 定义 Args
        TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
        tnbArgs.processor(tprocessor);
        tnbArgs.transportFactory(framedTransport);
        tnbArgs.protocolFactory(compactFactory);
        // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tnbArgs);
        return server;
    }

    public static void startServer(TServer server) {
        try {
            log.info("HelloWorld TSimpleServer start ....");
            server.serve();
        } catch (Exception e) {
            log.error("Server start error!!!");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws TTransportException
     */
    public static void main(String[] args) throws TTransportException {
        // TServer simpleServer = simpleServer();
        // startServer(simpleServer);
        // TServer threadPoolServer = threadPoolServer();
        // startServer(threadPoolServer);
        TServer nonBloServer = nonBlockServer();
        startServer(nonBloServer);

    }
}
