package server.commands;

import server.data.Receiver;

public interface IdCheckerable {
    public boolean checkIdOrKey(Receiver worker, String key);
}
