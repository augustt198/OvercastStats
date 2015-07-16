package me.ryanw.overcast.api;

import me.ryanw.overcast.api.util.Role;

import java.util.Calendar;
import java.util.Collection;

public interface TeamPlayer extends OvercastPlayer {

    Role getRole();

    Collection<TeamPlayer> getTeamMates();

    Calendar getAccepted();

}
