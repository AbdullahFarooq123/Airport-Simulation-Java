public class Airport {
    private String name;
    private String weather;

    public Airport(String name, String weather) {
        this.name = name;
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name ;
    }
}
