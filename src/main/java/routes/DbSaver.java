package routes;

import model.Weather;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repo.WeatherRepo;

@Component
public class DbSaver extends RouteBuilder {

    @Autowired
    private WeatherRepo weatherRepo;

    @Override
    public void configure() throws Exception {
        from("direct:db-save")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Weather weather = exchange.getIn().getBody(Weather.class);
                        weatherRepo.save(weather);
                    }
                })
                .wireTap("log:saved-to-db");
    }
}
