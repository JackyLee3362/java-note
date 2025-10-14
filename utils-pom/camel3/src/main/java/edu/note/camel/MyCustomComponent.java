package edu.note.camel;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;

/**
 * @author jackylee
 * @date 2025-10-14 12:36
 * @desc 本质是 EndpointFactory，工厂类
 */
public class MyCustomComponent extends DefaultComponent {

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return new MyCustomEndpoint(uri, this);
    }

}
