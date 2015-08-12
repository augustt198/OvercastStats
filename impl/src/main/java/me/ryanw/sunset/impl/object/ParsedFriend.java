package me.ryanw.sunset.impl.object;

import me.ryanw.sunset.api.OvercastFriend;
import me.ryanw.sunset.api.OvercastPlayer;
import me.ryanw.sunset.impl.Sunset;

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
    public OvercastPlayer getAsPlayerObject() throws IOException {
        return Sunset.getSunset().getPlayer(username);
    }
}
