public class FlightAttendants extends Person implements Staff{
    private Delay delay;
    private Time workingHours;
    public FlightAttendants(String name) {
        super(name);
        this.delay = null;
        workingHours = new Time(0,0,0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public Time getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Time workingHours) {
        this.workingHours = workingHours;
    }
}
