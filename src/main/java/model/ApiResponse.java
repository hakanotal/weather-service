package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private int id;
    private String name;
    private int timezone;
    private int cod;
    private float visibility;
    private float dt;
    private String base;

    Coord coord;
    ArrayList<Object> weather;
    Main main;
    Wind wind;
    Clouds clouds;
    Sys sys;

    public Weather toWeather(){
        Weather weather = new Weather();
        weather.setName(name);
        weather.setTimezone(timezone);

        weather.setLon(coord.getLon());
        weather.setLat(coord.getLat());

        weather.setWindDeg(wind.getDeg());
        weather.setWindSpeed(wind.getSpeed());

        weather.setTemp(main.getTemp());
        weather.setTemp_min(main.getTemp_min());
        weather.setTemp_max(main.getTemp_max());
        weather.setFeels_like(main.getFeels_like());
        weather.setPressure(main.getPressure());
        weather.setHumidity(main.getHumidity());

        return weather;
    }
}

@Data
class Sys {
    private float type;
    private float id;
    private String country;
    private float sunrise;
    private float sunset;
}

@Data
class Clouds {
    private float all;
}

@Data
class Wind {
    private float speed;
    private float deg;
}

@Data
class Main {
    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private float pressure;
    private float humidity;
}

@Data
class Coord {
    private float lon;
    private float lat;
}
