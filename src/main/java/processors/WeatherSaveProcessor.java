package processors;

import model.Weather;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import repo.WeatherRepo;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("weatherSaveProcessor")
public class WeatherSaveProcessor implements Processor {

    @Autowired
    private WeatherRepo weatherRepo;

    @Override
    public void process(Exchange exchange) throws Exception {
        Weather weather = exchange.getIn().getBody(Weather.class);
        weatherRepo.save(weather);
    }

}
