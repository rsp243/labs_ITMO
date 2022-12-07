package src.enums;

public enum MessageType {
    TRULY("Правда"),
    FALSE("Ложь");

    String description;

    MessageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
