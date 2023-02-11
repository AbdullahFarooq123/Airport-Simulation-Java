public class Cone extends SolidShape {
    private double radius;

    public Cone() {

    }

    public Cone(double density, double height, double radius) {
        this.density = density;
        this.height = height;
        this.radius = radius;
    }

    @Override
    public void calculateVolume() {
        this.volume = 1 / 3 * 3.14 * Math.pow(radius, 2) * height;
    }

    @Override
    public void calculateMass() {
        this.mass = volume * density;
    }

    public String toString() {
        return "The mass of the cone is " + mass + " and the volume is " + volume;
    }
}
