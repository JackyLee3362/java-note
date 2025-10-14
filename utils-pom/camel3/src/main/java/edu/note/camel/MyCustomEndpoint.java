package edu.note.camel;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.support.DefaultEndpoint;

/**
 * @author jackylee
 * @date 2025-10-14 12:30
 */
public class MyCustomEndpoint extends DefaultEndpoint {

    public MyCustomEndpoint(String uri, MyCustomComponent component) {
        super(uri, component);
    }

    @Override
    public Producer createProducer() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'createProducer'");
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        return new MyCustomConsumer(this, processor);
    }

}
