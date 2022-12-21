package src.enums;

public enum EmotionType {
    HAPPY("Счастье", true),
    UNHAPPY("Несчастье", true),
    SADNESS("Грусть", true),
    ANGER("Злость", true),
    TERRIFIED("Страх", true),
    CALM("Спокойствие", true),
    OFFENCE("Обида", true),
    MOTIVATED("Мотивация", true);
    private String name;
    private boolean isEmotionTruly;

    EmotionType(String nameOfEmotion, boolean isEmotionTruly1) {
        this.name = nameOfEmotion;
        this.isEmotionTruly = isEmotionTruly1;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsEmotionTruly() {
        return isEmotionTruly;
    }

    public void setEmotionTruly(boolean isEmotionTruly) {
        this.isEmotionTruly = isEmotionTruly;
    }
}
