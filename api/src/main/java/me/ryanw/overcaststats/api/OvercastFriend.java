package me.ryanw.overcaststats.api;

public interface OvercastFriend {

    /**
     * Gets the username of the player
     */
    String getUsername();

    /**
     * Converts the friend object into a player one.
     */
    OvercastPlayer getAsPlayerObject();
}
