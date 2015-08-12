package me.ryanw.sunset.api;

import me.ryanw.sunset.api.util.Role;

import java.util.Calendar;
import java.util.Collection;

public interface TeamPlayer extends OvercastPlayer {

    Role getRole();

    Collection<TeamPlayer> getTeamMates();

    Calendar getAccepted();

}
