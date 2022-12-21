package src.enums;

public enum mealType {
    MEAT("Мясо", 50),
    PORRIDGE("Каша", 30),
    BORSCH("Борщ", 50),
    SOUP("Суп", 50);
    
    private String name;
    private int tasteCharacteristic;

    mealType(String aName, int aTasteCharacteristic) {
        this.name = aName;
        this.tasteCharacteristic = aTasteCharacteristic;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTasteCharacteristic(int tasteCharacteristic) {
        this.tasteCharacteristic = tasteCharacteristic;
    }
    public int getTasteCharacteristic() {
        return tasteCharacteristic;
    }
}
