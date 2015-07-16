package me.ryanw.overcast.api;

import java.util.Collection;

//TODO: javadocs
public interface OvercastTeam {

    String getName();

    Collection<TeamPlayer> getMemebers();

    int getWools();

    int getCores();

    int getMonuments();

    double getKd();

    double getKk();

    int getKills();

    int getDeaths();

}
