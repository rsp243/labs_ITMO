package task3;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class Connection {
    public enum ConnectionState {
        CREATED,
        ESTABLISHED,
        FINISHED
    }

    private ConnectionState state;
    private List<Message> messageList;

    public Connection() {
        state = ConnectionState.CREATED;
        messageList = new LinkedList<>();
    }

    public int sendMessage(Message message) {
        if (state == ConnectionState.ESTABLISHED) {
            messageList.add(message);

            return 0;
        }

        return 1;
    }

    public void sendSeveralMessages(List<Message> messageList) {
        for (Message message : messageList) {
            this.messageList.add(message);
        }
    }

    public void startConnection() {
        Message message = new Message("Установлено соединение. Начало связи.");
        messageList.add(message);
        this.setState(ConnectionState.ESTABLISHED);
    }

    public void finishConnection() {
        Message message = new Message("Конец связи. Соединение прервано.");
        messageList.add(message);
        this.setState(ConnectionState.FINISHED);
    }
}
