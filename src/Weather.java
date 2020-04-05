public class Weather {
    private long temperature;
    private long humidity;
    private String clouds;
    private long windSpeed;

    public Weather(double temperature, double humidity, String clouds, double windSpeed) {
        this.temperature = Math.round(temperature - 273);
        this.humidity = Math.round(humidity);
        this.clouds = clouds;
        this.windSpeed = Math.round(windSpeed);
    }

    public long getTemperature() {
        return temperature;
    }

    public long getHumidity() {
        return humidity;
    }

    public String getClouds() {
        return clouds;
    }

    public long getWindSpeed() {
        return windSpeed;
    }
}
