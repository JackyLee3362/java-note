package edu.note.camel.router;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author jackylee
 * @date 2025-10-30 22:36
 */
public class CopyFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:data/inbox?noop=true")
                .log("文件传输成功")
                .to("file:data/outbox"); 
    }

}
