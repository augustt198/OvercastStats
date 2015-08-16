package me.ryanw.overcaststats.impl.object;

import me.ryanw.overcaststats.api.OvercastFriend;
import me.ryanw.overcaststats.api.OvercastPlayer;
import me.ryanw.overcaststats.impl.OvercastStats;

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
    public OvercastPlayer getAsPlayerObject() {
        return OvercastStats.getOvercastStats().getPlayer(username);
    }
}
