public class BoardingPass {
    private boolean permit;
    private long flightNumber;
    private int seatNo;
    private Time departure;
    private Time arrival;

    public BoardingPass(boolean permit, long flightNumber, int seatNo, Time departure, Time arrival) {
        this.permit = permit;
        this.flightNumber = flightNumber;
        this.seatNo = seatNo;
        this.departure = departure;
        this.arrival = arrival;
    }

    public boolean isPermit() {
        return permit;
    }

    public void setPermit(boolean permit) {
        this.permit = permit;
    }

    public long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Time getDeparture() {
        return departure;
    }

    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public Time getArrival() {
        return arrival;
    }

    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }
}
