package edu.note.thirft.server;

import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Slf4JLoggerFactory;

import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServerConfig;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;

import edu.note.thirft.sdk.anno.UserService;

public class Server {

    public static final int PORT = 8090;

    public void startServer() {
        UserService userService = new UserServiceImpl();

        ThriftCodecManager codecManager = new ThriftCodecManager();
        ThriftServiceProcessor processor = new ThriftServiceProcessor(codecManager, ImmutableList.of(),
                userService);
        ThriftServerConfig config = new ThriftServerConfig();
        ThriftServer server = new ThriftServer(processor, config);
        try {
            InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
            server.start();
            System.out.println("Thrift server started...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thrift server closed...");
            server.close();
        }
    }

}
