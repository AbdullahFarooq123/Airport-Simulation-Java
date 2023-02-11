import java.util.ArrayList;
import java.util.List;

public class Flight {
    private final List<Passenger> passengers;
    private Time takeOff;
    private Time landing;
    private Airport departure;
    private Airport arrival;
    private String status;
    private boolean landed;
    private Delay delay;
    private String review;
    private FlightAttendants flightAttendants;
    private boolean weatherDelayed;
    private boolean taxiDelayed;
    private boolean staffDelayed;

    public Flight(Time takeOff, Time landing, Airport departure, Airport arrival, String status, String review) {
        this.takeOff = takeOff;
        this.landing = landing;
        this.departure = departure;
        this.arrival = arrival;
        this.status = status;
        this.review = review;
        landed = false;
        passengers = new ArrayList<>();
        delay = null;
        flightAttendants = new FlightAttendants("FLIGHT ATTENDANTS");
        weatherDelayed = taxiDelayed = staffDelayed = false;
    }

    public Delay taxiFull() {
        if (!taxiDelayed) {
            int delay = getPassengers().size() / 20;
            takeOff.setMinutes(delay);
            landing.setMinutes(delay);
            taxiDelayed = true;
            return new Delay("Departure " + delay + " min late.", "Taxi Full.", new Time(0, delay, 0));
        }
        return null;
    }

    public Delay checkFuel(Plane plane){
        int remainingFuel = plane.getGas() - (plane.getConsumption()*landing.getDifferenceInHours(takeOff));
        if(remainingFuel<0){
            int delay = Static.getRandomInt(10,5);
            takeOff.setMinutes(delay);
            landing.setMinutes(delay);
            return new Delay("Departure " + delay + " min late.", "Refueling", new Time(0,delay,0));
        }
        return null;
    }

    public Delay checkStaffDelay(){
        if(!staffDelayed){
            if(flightAttendants.getWorkingHours().getTimeInHours()>=12){
                staffDelayed = true;
                return new Delay("Departure 5 min late.", "Staff Strike", new Time(0,5,0));
            }
        }
        return null;
    }

    public Delay weatherForecast() {
        if (!weatherDelayed) {
            if (departure.getWeather().equals("RAINY")) {
                takeOff.setHours(takeOff.getHours() + 2);
                landing.setHours(landing.getHours() + 2);
                departure.setWeather("SUNNY");
                weatherDelayed = true;
                return new Delay("Departure 2 hr late", "Rainy Weather" + departure.toString(), new Time(2, 0, 0));
            } else if (departure.getWeather().equals("CLOUDY")) {
                takeOff.setHours(takeOff.getHours() + 4);
                landing.setHours(landing.getHours() + 4);
                departure.setWeather("SUNNY");
                weatherDelayed = true;
                return new Delay("Departure 4 hr late", "Cloudy Weather." + departure.toString(), new Time(4, 0, 0));
            } else if (arrival.getWeather().equals("RAINY")) {
                takeOff.setHours(takeOff.getHours() + 2);
                landing.setHours(landing.getHours() + 2);
                departure.setWeather("SUNNY");
                weatherDelayed = true;
                return new Delay("Departure 2 hr late", "Rainy Weather" + arrival.toString(), new Time(2, 0, 0));
            } else if (arrival.getWeather().equals("CLOUDY")) {
                takeOff.setHours(takeOff.getHours() + 4);
                landing.setHours(landing.getHours() + 4);
                departure.setWeather("SUNNY");
                weatherDelayed = true;
                return new Delay("Departure 4 hr late", "Cloudy Weather." + arrival.toString(), new Time(4, 0, 0));
            }
        }
        return null;
    }

    public Time getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(Time takeOff) {
        this.takeOff = takeOff;
    }

    public Time getLanding() {
        return landing;
    }

    public void setLanding(Time landing) {
        this.landing = landing;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassengers(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public Airport getArrival() {
        return arrival;
    }

    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }

    public String toString() {
        if (landed)
            return String.format("%-50s", departure.toString() + "-" + arrival.toString()) + "|" + String.format("%-15s", takeOff.getTime()) + "|" + String.format("%-15s", landing.getTime()) + "|" + String.format("%-15s", "-".repeat(7)) + "|" + String.format("%-15s", "LANDED") + "|" + String.format("%-25s", "-".repeat(10)) + "|" + String.format("%-25s", "-".repeat(10)) + "|" + String.format("%-15s", review) + "|\n";
        return String.format("%-50s", departure.toString() + "-" + arrival.toString()) + "|" + String.format("%-15s", takeOff.getTime()) + "|" + String.format("%-15s", landing.getTime()) + "|" + String.format("%-15d", passengers.size()) + "|" + String.format("%-15s", status) + "|" + String.format("%-25s", delay == null ? "NONE" : delay.getDelay()) + "|" + String.format("%-25s", delay == null ? "NONE" : delay.getDelayReason()) + "|" + String.format("%-15s", review) + "|\n";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLanded() {
        return landed;
    }

    public void setLanded(boolean landed) {
        this.landed = landed;
    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
        if (this.delay != null)
            for (Passenger passenger : passengers)
                passenger.getWaitTime().addTime(delay.getDelayTime());

    }

    public String getReview() {
        return review;
    }

    public void setReview() {
        int waitTime = passengers.get(0).getWaitTime().getTimeInMinutes();
        if (waitTime >= 20)
            this.review = "Very Low";
        else if (waitTime >= 15)
            this.review = "Low";
        else if (waitTime >= 10)
            this.review = "Neutral";
        else
            this.review = "Satisfied";
    }

    public FlightAttendants getFlightAttendants() {
        return flightAttendants;
    }

    public void setFlightAttendants(FlightAttendants flightAttendants) {
        this.flightAttendants = flightAttendants;
    }

    public boolean isWeatherDelayed() {
        return weatherDelayed;
    }

    public void setWeatherDelayed(boolean weatherDelayed) {
        this.weatherDelayed = weatherDelayed;
    }

    public boolean isTaxiDelayed() {
        return taxiDelayed;
    }

    public void setTaxiDelayed(boolean taxiDelayed) {
        this.taxiDelayed = taxiDelayed;
    }

    public boolean isStaffDelayed() {
        return staffDelayed;
    }

    public void setStaffDelayed(boolean staffDelayed) {
        this.staffDelayed = staffDelayed;
    }
}
