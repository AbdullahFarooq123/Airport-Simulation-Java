public abstract class Person {
    protected String name;

    protected Person(String name) {
        this.name = name;
    }

    public abstract String getName();

    public abstract void setName(String name);
}
