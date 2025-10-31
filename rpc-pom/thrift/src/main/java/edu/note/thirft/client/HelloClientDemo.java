package edu.note.thirft.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import edu.note.thirft.sdk.HelloWorldService;
import edu.note.thirft.sdk.HelloWorldService.Client;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jackylee
 * @date 2025-10-31 12:46
 */
@Slf4j
public class HelloClientDemo {

    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;
    public static TTransport transport;

    static HelloWorldService.Client buildClient() {
        transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
        // 协议要和服务端一致
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        return client;
    }

    static HelloWorldService.Client buildNonBlockingClient() {
        transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
        // 协议要和服务端一致
        TProtocol protocol = new TCompactProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        return client;
    }

    /**
     *
     * @param userName
     */
    public static void startClient(HelloWorldService.Client client, String userName) {
        // TTransport transport = null;
        try {
            transport.open();
            String result = client.sayHello(userName);
            log.info("Thrify client result =: " + result);
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Client client = buildClient();
        // startClient(client, "Bob");
        Client nonBlockingClient = buildNonBlockingClient();
        startClient(nonBlockingClient, "Bob");
    }
}
