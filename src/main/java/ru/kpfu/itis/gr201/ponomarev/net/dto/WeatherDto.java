package ru.kpfu.itis.gr201.ponomarev.net.dto;

public class WeatherDto {
    private String city;
    private double temp;
    private double humidity;
    private String description;

    public WeatherDto(String city, double temp, double humidity, String description) {
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }
}
