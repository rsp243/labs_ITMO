package task3;

import lombok.Data;

@Data
public class Message {
    private String text;

    public Message(String text) {
        this.text = text;
    }
}
