package server.fillers;


public class Increment {
    private Long iterId;
    
    public Increment(Long startVal) {
        iterId = ((startVal != 0) ? Math.abs(startVal) - 1 : 0);
    }

    public long getNewId() {
        iterId += 1;
        return iterId;
    }

    public long getCurrentID() {
        return iterId;
    }

    public void setIterId(Long iterId) {
        this.iterId = iterId;
    }
}
