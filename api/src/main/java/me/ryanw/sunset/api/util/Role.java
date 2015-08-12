package me.ryanw.sunset.api.util;

import me.ryanw.sunset.api.OvercastTeam;

/**
 * Represents the role of a player on an {@link OvercastTeam}
 */
public enum Role {
    /**
     * The leader of a team
     */
    LEADER(),
    /**
     * An ordinary member of a team
     */
    MEMBER()
}
