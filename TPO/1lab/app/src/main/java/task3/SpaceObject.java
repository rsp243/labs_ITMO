package task3;

import lombok.Data;

@Data
public class SpaceObject {
    private String name;

    public SpaceObject(String name) {
        this.name = name;
    }
}
