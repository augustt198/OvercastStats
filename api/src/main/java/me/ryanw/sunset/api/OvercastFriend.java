package me.ryanw.sunset.api;

import java.io.IOException;

//TODO: javadoc
public interface OvercastFriend {

    String getUsername();

    OvercastPlayer getAsPlayerObject() throws IOException;
}
