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

    /**
     * @param uri        完整的端点 uri: 包含组件，剩余路径和所有参数，通常表示记录端点的来源
     *                   比如 "mycomponent:foo?option1=bar&option2=baz"
     * @param remaining  去掉组件前缀后的剩余部分
     *                   比如 "mycomponent:foo" 就是 "foo"
     * @param parameters URI 中的所有参数的键值对 parameters，用于配置端点的属性
     *                   比如 {option1=foo, option2=baz}
     */
    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return new MyCustomEndpoint(uri, this);
    }

}
