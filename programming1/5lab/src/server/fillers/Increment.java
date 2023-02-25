package src.server.fillers;


public class Increment {
    private int iterId;
    
    public Increment(int startInt) {
        iterId = (startInt != 0) ? Math.abs(startInt) - 1 : 0;
    }

    public int getNewId() {
        iterId += 1;
        return iterId;
    }

    public int getCurrentID() {
        return iterId;
    }
}
