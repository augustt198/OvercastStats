package me.ryanw.sunset.api;

import java.util.Collection;

//TODO: javadocs
public interface OvercastTeam {

    String getName();

    Collection<TeamPlayer> getMembers();

    int getWools();

    int getCores();

    int getMonuments();

    double getKd();

    double getKk();

    int getKills();

    int getDeaths();

}
