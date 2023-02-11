public abstract class SolidShape {
    protected double density;
    protected double height;
    protected double volume;
    protected double mass;
    public SolidShape(){

    }
    public SolidShape(double density, double height, double volume, double mass){
        this.density = density;
        this.height = height;
        this.volume = volume;
        this.mass = mass;
    }
    public abstract void calculateVolume();
    public abstract void calculateMass();
    public String toString(){
        return "The height of the cone is " + height + " and density is " + density;
    }
}
