package me.ryanw.overcaststats.api;

import java.util.List;

//TODO: javadocs
public interface Overcast {

    OvercastPlayer getPlayer(String username);

    OvercastPlayer getPlayerByUrl(String url);

    List<OvercastPlayer> getPlayers(List<String> usernames);

    List<OvercastPlayer> getPlayersByUrl(List<String> urls);

    void setConnectionTimeout(int timeout);

    void setUserAgent(String userAgent);
}
