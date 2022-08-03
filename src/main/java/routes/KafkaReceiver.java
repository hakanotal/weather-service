package routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("kafka:weather-topic")
                .log("Received from Kafka: ${body}");
    }
}
