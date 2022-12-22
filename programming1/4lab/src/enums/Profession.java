package src.enums;

public enum Profession {
    TRADING_SALT("Торговля солью", 100),
    WORKER("Каменьщик", 70),
    UNEMPLOYED("Безработный", 0);
    
    private String name;
    private int salary;

    Profession(String aName, int aSalary) {
        this.name = aName;
        this.salary = aSalary;
    }

    public String getName() {
        return name;
    }
    public int getSalary() {
        return salary;
    }
}
