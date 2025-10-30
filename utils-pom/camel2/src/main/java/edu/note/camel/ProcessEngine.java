package edu.note.camel;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.DefaultErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.processor.RedeliveryPolicy;

/**
 * 该段代码来自 cbc-product ProcessEngine
 * 
 * @author jackylee
 * @date 2025-10-30 19:39
 */
public class ProcessEngine {

    private static final CamelContext CAMEL_CONTEXT;
    private static final ProducerTemplate PRODUCER_TEMPLATE;

    static {
        // CAMEL_CONTEXT = (CamelContext) SpringBeanUtil.getBean(CamelContext.class);
        CAMEL_CONTEXT = new DefaultCamelContext();
        DefaultErrorHandlerBuilder defaultErrorHandlerBuilder = new DefaultErrorHandlerBuilder();
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setLogStackTrace(false);
        defaultErrorHandlerBuilder.setRedeliveryPolicy(redeliveryPolicy);
        CAMEL_CONTEXT.setErrorHandlerBuilder(defaultErrorHandlerBuilder);
        // Assertions.assertNotNull(CAMEL_CONTEXT, "camelContext cannot be null");
        PRODUCER_TEMPLATE = CAMEL_CONTEXT.createProducerTemplate();
    }

    private ProcessEngine() {
        throw new IllegalStateException("ProcessEngine cannot be constructed");
    }

    public static void process(String processId, Object activityRequest, Object activityContext) {
        PRODUCER_TEMPLATE.requestBody(endpointUri(processId), new Object[] { activityRequest, activityContext });
    }

    public static void processWithBodyAndHeaders(String processId, Object body, Map<String, Object> headers) {
        PRODUCER_TEMPLATE.requestBodyAndHeaders(endpointUri(processId), body, headers);
    }

    public static void addComponent(String name, Component component) {
        if (CAMEL_CONTEXT.hasComponent(name) == null) {
            CAMEL_CONTEXT.addComponent(name, component);
        }
    }

    public static void addDSL(RouteBuilder routeBuilder) throws Exception {
        CAMEL_CONTEXT.addRoutes(routeBuilder);
    }

    public static void deleteDSL(String routeId, int timeout, TimeUnit timeUnit) throws Exception {
        CAMEL_CONTEXT.stopRoute(routeId, timeout, timeUnit);
        CAMEL_CONTEXT.removeRoute(routeId);
    }

    private static String endpointUri(String processId) {
        return "direct:" + processId;
    }

}
