package task3;

import java.util.List;

import lombok.Data;

@Data
public class Crew {
    private Person capitan;
    private List<Person> crew;
    
    public Crew(Person capitan, List<Person> crew) {
        this.capitan = capitan;
        this.crew = crew;
    }
}
