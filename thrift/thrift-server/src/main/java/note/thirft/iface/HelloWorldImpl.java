package note.thirft.iface;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.thrift.TException;

/**
 * @author jackylee
 * @date 2025/7/2 12:08
 */
public class HelloWorldImpl implements HelloWorldService.Iface  {

    public HelloWorldImpl() {
    }

    @Override
    public String sayHello(String username) throws TException {
        new ArrayList<>();
        new HashMap<>();
        return "Hi " + username + ", Welcome to Thrift.";
    }
}
