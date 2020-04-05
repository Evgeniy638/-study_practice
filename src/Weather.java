public class Weather {
    private double temperature;
    private double humidity;
    private double clouds;
    private double windSpeed;

    public Weather(double temperature, double humidity, double clouds, double windSpeed) {
        this.temperature = temperature - 273;
        this.humidity = humidity;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getClouds() {
        return clouds;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}
