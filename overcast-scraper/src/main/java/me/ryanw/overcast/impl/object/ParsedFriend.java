package me.ryanw.overcast.impl.object;

import me.ryanw.overcast.api.OvercastFriend;
import me.ryanw.overcast.api.OvercastPlayer;
import me.ryanw.overcast.impl.OvercastAPI;

import java.io.IOException;

public class ParsedFriend implements OvercastFriend {

    private String username;

    public ParsedFriend(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public OvercastPlayer getPlayerObject() throws IOException {
        return OvercastAPI.getPlayer(username);
    }
}
