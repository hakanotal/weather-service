package routes;

import model.ApiResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiCaller extends RouteBuilder {

    @Value("${API_KEY}")
    private String apiKey;

    @Override
    public void configure() throws Exception {

        from("timer:my-restapi-consumer?period=15m")
                .toD("http://api.openweathermap.org/data/2.5/weather?q=London&appid={$apiKey}")
                .wireTap("log:api-response")
                .unmarshal()
                .json(JsonLibrary.Jackson, ApiResponse.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        ApiResponse apiResponse = exchange.getIn().getBody(ApiResponse.class);
                        exchange.getIn().setBody(apiResponse.toWeather());
                    }
                })
                .wireTap("log:weather-data")
                .multicast()
                    .to("direct:weather-kafka")
                    .to("direct:db-save")
                .end();
    }
}
