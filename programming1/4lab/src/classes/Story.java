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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arrayOfScenarySentences == null) ? 0 : arrayOfScenarySentences.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Story other = (Story) obj;
        if (arrayOfScenarySentences == null) {
            if (other.arrayOfScenarySentences != null)
                return false;
        } else if (!arrayOfScenarySentences.equals(other.arrayOfScenarySentences))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Story [arrayOfScenarySentences=" + arrayOfScenarySentences + "]";
    }
}
