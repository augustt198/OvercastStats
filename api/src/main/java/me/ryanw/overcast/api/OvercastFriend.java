package me.ryanw.overcast.api;

import java.io.IOException;

//TODO: javadoc
public interface OvercastFriend {

    String getUsername();

    OvercastPlayer getPlayerObject() throws IOException;
}