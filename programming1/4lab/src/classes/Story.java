package src.classes;

import java.util.ArrayList;

import src.interfaces.Performable;

public class Story implements Performable{
    private ArrayList<Scenary> arrayOfScenarySentences;

    public Story(ArrayList<Scenary> anArrayOfScenarySentences) {
        this.arrayOfScenarySentences = anArrayOfScenarySentences;
    }

    @Override
    public String execute() {
        String StoryString = "";
        for(int i = 0; i < arrayOfScenarySentences.size(); i++) {
            try {
                StoryString += arrayOfScenarySentences.get(i).execute() + "\n";
            } catch (TroubleWithDoerException e) {
                StoryString = e.getMessage();                
            }
        }
        return StoryString;
    }
}
