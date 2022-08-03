package routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class KafkaSender extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:weather-kafka")
                .log("Sending to Kafka: ${body}")
                .to("kafka://weather-topic");

    }
}
