package edu.note.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author jackylee
 * @date 2025-10-13 20:01
 */
public class MyRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hello?period=")
                .routeId("hello")
                .transform()
                .method("myBean", "saySomething")
                .filter(simple("${body} contain 'foo'"))
                .to("log:foo")
                .end()
                .to("stream:out");
    }

}
