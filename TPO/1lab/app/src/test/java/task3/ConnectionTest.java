package task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectionTest {

    private Connection connection;

    @BeforeEach
    void setUp() {
        // Создаем экземпляр Connection перед каждым тестом
        connection = new Connection();
    }

    @Test
    void testConstructor() {
        // Проверяем, что объект создан с правильными параметрами
        assertEquals(Connection.ConnectionState.CREATED, connection.getState());
        assertTrue(connection.getMessageList().isEmpty());
    }

    @Test
    void testStartConnection() {
        // Проверяем, что соединение успешно устанавливается
        connection.startConnection();
        assertEquals(Connection.ConnectionState.ESTABLISHED, connection.getState());
        assertEquals(1, connection.getMessageList().size());
        assertEquals("Установлено соединение. Начало связи.", connection.getMessageList().get(0).getText());
    }

    @Test
    void testFinishConnection() {
        // Проверяем, что соединение успешно завершается
        connection.startConnection(); // Сначала устанавливаем соединение
        connection.finishConnection();
        assertEquals(Connection.ConnectionState.FINISHED, connection.getState());
        assertEquals(2, connection.getMessageList().size());
        assertEquals("Конец связи. Соединение прервано.", connection.getMessageList().get(1).getText());
    }

    @Test
    void testSendMessage() {
        // Проверяем отправку сообщения при установленном соединении
        connection.startConnection(); // Устанавливаем соединение
        Message message = new Message("Привет!");
        assertEquals(0, connection.sendMessage(message)); // Успешная отправка
        assertEquals(2, connection.getMessageList().size());
        assertEquals("Привет!", connection.getMessageList().get(1).getText());

        // Проверяем отправку сообщения при отсутствии соединения
        connection.finishConnection(); // Устанавливаем соединение
        assertEquals(1, connection.sendMessage(message)); // Неуспешная отправка
        assertEquals(3, connection.getMessageList().size()); // Сообщение не добавлено
    }

    @Test
    void testSendSeveralMessages() {
        // Проверяем отправку нескольких сообщений
        connection.startConnection(); // Устанавливаем соединение
        List<Message> messages = Arrays.asList(
                new Message("Сообщение 1"),
                new Message("Сообщение 2"),
                new Message("Сообщение 3")
        );
        connection.sendSeveralMessages(messages);
        assertEquals(4, connection.getMessageList().size()); // 1 (начало) + 3 сообщения
        assertEquals("Сообщение 3", connection.getMessageList().get(3).getText());
    }
}