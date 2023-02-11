import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineCompany {
    private String name;
    private Time time;
    private final List<Plane> planes;
    private int noOfFlights;
    private int noOfPlanes;
    private Delay delay;

    public AirlineCompany(String name, Time time, int noOfFlights, int noOfPlanes) {
        this.name = name;
        this.time = time;
        this.noOfFlights = noOfFlights;
        this.noOfPlanes = noOfPlanes;
        this.planes = new ArrayList<>();
        this.delay = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void addPlane(Plane plane) {
        this.planes.add(plane);
    }

    public List<String> readPassengers() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("passengers.txt"));
        List<String> passengerNames = new ArrayList<>();
        while (fileScanner.hasNextLine())
            passengerNames.add(fileScanner.nextLine());
        return passengerNames;
    }

    public List<String> readPlanes() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("planes.txt"));
        List<String> planeNames = new ArrayList<>();
        while (fileScanner.hasNextLine())
            planeNames.add(fileScanner.nextLine());
        return planeNames;
    }

    public List<String> readAirports() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("airports.txt"));
        List<String> airportNames = new ArrayList<>();
        while (fileScanner.hasNextLine())
            airportNames.add(fileScanner.nextLine());
        return airportNames;
    }

    public void run() throws IOException {
        initialize();
        boolean quit = false;
        FileWriter writer = new FileWriter("Output.txt");
        while (!quit) {
            System.out.println(this);
            writer.write(this +"\n");
            quit = true;
            int planeIndex = 0;
            for (Plane plane : planes) {
                for (Flight flight : plane.getFlights()) {
                    if (flight.getLanding().equals(time) && flight.getStatus().equals("FLYING"))
                        flight.setStatus("LANDING");
                    else if (flight.getTakeOff().equals(time) && flight.getStatus().equals("GROUNDED")) {
                        if ((this.delay = flight.weatherForecast()) != null)
                            flight.setDelay(delay);
                        else if ((this.delay = plane.checkFault()) != null)
                            flight.setDelay(delay);
                        else if ((this.delay = flight.checkFuel(plane))!=null) {
                            planes.get(planeIndex).setGas(Static.getRandomInt(30000, 20000));
                            flight.setDelay(delay);
                        }
                        else if ((this.delay = flight.checkStaffDelay())!=null)
                            flight.setDelay(delay);
                        else if ((this.delay = flight.taxiFull()) != null)
                            flight.setDelay(delay);
                        if(this.delay!=null)
                            continue;
                        flight.setDelay(null);
                        flight.setStatus("TAKING OFF");
                        flight.getFlightAttendants().setWorkingHours(new Time(flight.getLanding().getDifferenceInHours(flight.getTakeOff()),0,0));
                        plane.setGas(plane.getGas() - (plane.getConsumption()*flight.getLanding().getDifferenceInHours(flight.getTakeOff())));
                    } else {
                        if (flight.getStatus().equals("LANDING")) {
                            flight.setStatus("GROUNDED");
                            flight.setLanded(true);
                            flight.setReview();
                        } else if (flight.getStatus().equals("TAKING OFF"))
                            flight.setStatus("FLYING");
                    }
                    if (quit)
                        quit = flight.isLanded();
                }
                planeIndex++;
            }
            time.tick();
        }
        System.out.println(this);
        writer.write(this +"\n");
        System.out.println("ALL FLIGHTS LANDED SUCCESSFULLY!");
        System.out.println("OUTPUT RESULTS TO OUTPUT.txt");
        writer.close();
    }

    public void initialize() throws FileNotFoundException {
        List<String> passengerNames = readPassengers();
        List<String> planeNames = readPlanes();
        List<String> airportNames = readAirports();
        List<Flight> flights = new ArrayList<>();
        String[] weather = {"Sunny", "Cloudy", "Rainy"};
        for (int i = 0; i < noOfFlights; i++) {
            int randomAirport = Static.getRandomInt(airportNames.size(), 0);
            Airport departure = new Airport(airportNames.get(randomAirport), weather[Static.getRandomInt(weather.length, 0)]);
            Airport landing = new Airport(airportNames.get(randomAirport + 1 == airportNames.size() ? 0 : randomAirport + 1), weather[Static.getRandomInt(weather.length, 0)]);
            Time departureTime = Time.getRandomTime();
            Time arrival = new Time(departureTime.getHours() + Static.getRandomInt(6, 1), departureTime.getMinutes(), departureTime.getSeconds());
            flights.add(new Flight(departureTime, arrival, departure, landing, "GROUNDED", ""));
        }
        for (int i = 0; i < noOfPlanes; i++)
            planes.add(new Plane(planeNames.get(i), Static.getRandomInt(180, 150), Static.getRandomBoolean(), null, Static.getRandomInt(30000, 20000), Static.getRandomInt(2000, 1000)));
        int planeIndex = 0;
        for (Flight flight : flights) {
            planes.get(planeIndex).addFlight(flight);
            planeIndex = ++planeIndex == planes.size() ? 0 : planeIndex;
        }
        int flightNo = 0;
        for (Plane plane : planes) {
            for (Flight flight : plane.getFlights()) {
                int seatNo = 0;
                for (int i = 0; i < plane.getCapacity(); i++)
                    flight.addPassengers(new Passenger(passengerNames.get(i), Static.getRandomInt(999999999, 100000000), new BoardingPass(Static.getRandomBoolean(), flightNo++, seatNo++, flight.getTakeOff(), flight.getLanding()), new Time(0, 0, 0)));
            }
        }
    }

    public int getNoOfFlights() {
        return noOfFlights;
    }

    public void setNoOfFlights(int noOfFlights) {
        this.noOfFlights = noOfFlights;
    }

    public int getNoOfPlanes() {
        return noOfPlanes;
    }

    public void setNoOfPlanes(int noOfPlanes) {
        this.noOfPlanes = noOfPlanes;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("TIME : ").append(time.getTime()).append("\n");
        s.append("+").append("-".repeat(262)).append("+\n");
        s.append(String.format("%-16s%-16s%-16s%-16s%-16s%-51s%-16s%-16s%-16s%-16s%-26s%-26s%-16s|\n", "|PLANE NAME", "|CAPACITY", "|PARKED", "|FUEL", "|CONSUMPTION", "|FLIGHT", "|DEPARTURE", "|ARRIVAL", "|SEATS", "|STATUS", "|DELAY", "|REASON", "|REVIEW"));
        s.append("+").append("-".repeat(262)).append("+\n");
        for (Plane p : planes)
            s.append(p.toString()).append("+").append("-".repeat(262)).append("+\n");
        return s.toString();
    }
}
