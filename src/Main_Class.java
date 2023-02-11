import java.util.ArrayList;
import java.util.List;

public class Main_Class {
    public static void main(String[] args) {
        List<Vehicle> vhList = new ArrayList<>();
        Vehicle vh1 = new Vehicle("D2343",2015,"Toyota", new VehicleLicense(1001,2024,"Dubai"));
        Vehicle vh2 = new Vehicle("AD5434",2020,"Tesla", new VehicleLicense(1002,2025,"Abu Dhabi"));
        vhList.add(vh1);
        vhList.add(vh2);
        for (Vehicle veh : vhList)
            System.out.println(veh.toString());

    }
}
