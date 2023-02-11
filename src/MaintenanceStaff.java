import java.util.Random;

public class MaintenanceStaff extends Person implements Staff {
    private Delay delay;
    public MaintenanceStaff(String name) {
        super(name);
        delay = null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public Delay randomizeInjury(){
        return new Random().nextBoolean() ? new Delay("Departure 20 min late", "Staff Injured", new Time(0,20,0)) : null;
    }
}
