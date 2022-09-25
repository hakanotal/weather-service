package processors;

import model.ApiResponse;
import org.apache.camel.Processor;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named("apiResponseProcessor")
public class ApiResponseProcessor implements Processor {

    @Override
    public void process(org.apache.camel.Exchange exchange) throws Exception {
        ApiResponse apiResponse = exchange.getIn().getBody(ApiResponse.class);
        exchange.getIn().setBody(apiResponse.toWeather());
    }
}
