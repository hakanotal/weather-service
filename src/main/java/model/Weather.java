package model;

import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Annotation;


@Data
@Entity
@Table(name = "weathers")
public class Weather{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int timezone;

    private float lon;
    private float lat;

    private float windSpeed;
    private float windDeg;

    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private float pressure;
    private float humidity;

}