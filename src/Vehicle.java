public class Vehicle{
    private String vehPlate;
    private int vehYear;
    public String vehModel;
    private VehicleLicense vehLicense;
    public Vehicle(String vehPlate, int vehYear, VehicleLicense vehLicense) {
        this.vehPlate = vehPlate;
        this.vehLicense = vehLicense;
        this.vehYear = vehYear;
    }
    public VehicleLicense getVehicleLicense(){
        return vehLicense;
    }
    public void setVehLicense(VehicleLicense vehLicense){
        this.vehLicense = vehLicense;
    }
    public String toString(){
        return "Vehicle Info\n"+
                "vehPlate : " + vehPlate + "\n"+
                "vehYear : " + vehYear+"\n"+
                "vehModel : " + vehModel+"\n"+
                vehLicense.toString();
    }
}
