import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plane {
    private String name;
    private final List<Flight> flights;
    private int capacity;
    private boolean far;
    private Delay delay;
    private int gas;
    private int consumption;
    private final MaintenanceStaff maintenanceStaff;
    private boolean faultOccurred;

    public Plane(String name, int capacity, boolean far, Delay delay, int gas, int consumption) {
        this.name = name;
        this.flights = new ArrayList<>();
        this.capacity = capacity;
        this.far = far;
        this.delay = delay;
        this.gas = gas;
        this.consumption = consumption;
        this.maintenanceStaff = new MaintenanceStaff(name + " maintenance staff.");
        this.faultOccurred = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFar() {
        return far;
    }

    public void setFar(boolean far) {
        this.far = far;
    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public String toString() {
        List <String> flight = new ArrayList<>();
        for (Flight f : flights)
            flight.add(f.toString());
        for (int i = 1 ; i < flight.size() ; i++)
            flight.set(i,String.format("%-16s%-16s%-16s%-16s%-16s|","|","|","|","|","|")+flight.get(i));
        StringBuilder flightPattern = new StringBuilder();
        for(String s : flight)
            flightPattern.append(s);
        return "|" + String.format("%-15s", name) + "|" + String.format("%-15d", capacity) + "|" + String.format("%-15s", far ? "FAR" : "ON GATE") + "|" + String.format("%-15d", gas) + "|" + String.format("%-15d", consumption) + "|" + flightPattern;
    }

    public Delay checkFault(){
        if(!faultOccurred) {
            if ((delay = maintenanceStaff.randomizeInjury()) != null) {
                faultOccurred = true;
                return delay;
            }
            else if (new Random().nextBoolean()) {
                int delayTime = Static.getRandomInt(20, 10);
                faultOccurred = true;
                return new Delay("Departure " + delayTime + " min late.", "Plane Malfunction", new Time(0, delayTime, 0));
            }
        }
        return null;
    }

    public boolean isFaultOccurred() {
        return faultOccurred;
    }

    public void setFaultOccurred(boolean faultOccurred) {
        this.faultOccurred = faultOccurred;
    }
}
