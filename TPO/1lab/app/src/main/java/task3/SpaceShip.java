package task3;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SpaceShip {
    public enum ShipState {
        MOVING("Передвижение"),
        JUMPING("Прыжок"),
        CHECKING("Осмотр"),
        REPAIRING("Починка");

        private String description;

        ShipState(String aDescription) {
            description = aDescription;
        }
    }

    private boolean isHyperShip;
    private Crew crew;
    private SpaceObject direction;
    private ShipState state;

    private List<Message> messageList;

    public SpaceShip(boolean isHyper, Crew aCrew, SpaceObject aDirection, ShipState aState) {
        // hyper space ships can make hyper jumps into hyper space areas
        isHyperShip = isHyper;
        crew = aCrew;
        direction = aDirection;
        state = aState;

        messageList = new ArrayList<>();
        Message message = new Message(
                "Создан " + (isHyper ? "гипер" : "") + " корабль, который находится в состоянии '" + state.description
                        + "'. Корабль направлен на " + direction.getName() + ". Во главе корабля капитан "
                        + crew.getCapitan().getName() + " " + crew.getCapitan().getSurname());
        messageList.add(message);
    }

    public void setHyperShip(boolean isHyperShip) {
        Message message = new Message((!isHyperShip ? "Сняли" : "Поставили") + " оборудование для гиперперемещений.");
        messageList.add(message);
        this.isHyperShip = isHyperShip;
    }

    public void setCrew(Crew crew) {
        Message message = new Message("Обновлен экипаж!. Новый экипаж: " + crew);
        messageList.add(message);
        this.crew = crew;
    }

    public void setDirection(SpaceObject direction) {
        if (direction != this.direction) {
            Message message = new Message("Новая точка назначения: " + direction.getName());
            messageList.add(message);
            this.direction = direction;
        }
    }

    public Message setState(ShipState state) {
        Message message = new Message("Новое состоянии у корабля: " + state.description);
        messageList.add(message);
        this.state = state;

        return message;
    }
}
