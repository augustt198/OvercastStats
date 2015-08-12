package me.ryanw.sunset.impl;

import me.ryanw.sunset.api.Overcast;
import me.ryanw.sunset.api.OvercastPlayer;
import me.ryanw.sunset.impl.object.ParsedPlayer;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sunset implements Overcast {

    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";
    private static Sunset sunset;
    private int connectionTimeout;

    public Sunset(int connectionTimeout, String userAgent) {
        this.connectionTimeout = connectionTimeout;
        if (userAgent != null) this.userAgent = userAgent;
        sunset = this;
    }

    @Override
    public OvercastPlayer getPlayer(String username) {
        String url = "https://oc.tc/users/" + username;
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            return new ParsedPlayer(response.parse());
        } catch (IOException ignored) {}
        return null;
    }

    @Override
    public OvercastPlayer getPlayerByUrl(String url)  {
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            return new ParsedPlayer(response.parse());
        } catch (IOException ignored) {}
        return null;
    }

    @Override
    public List<OvercastPlayer> getPlayers(List<String> usernames) {
        List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
        for (String username : usernames) {
            String url = "https://oc.tc/users/" + username;
            try {
                Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                System.out.println(username + " - " + response.statusCode());
                playerList.add(new ParsedPlayer(response.parse()));

            } catch (HttpStatusException ignored) {} catch (NullPointerException ignored) {} catch (IOException ignored) {}
        }
        return playerList;
    }

    @Override
    public List<OvercastPlayer> getPlayersByUrl(List<String> urls) {
        List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
        for (String url : urls) {
            try {
                Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                playerList.add(new ParsedPlayer(response.parse()));
            } catch (HttpStatusException ignored) {} catch (NullPointerException ignored) {} catch (IOException ignored) {}
        }
        return playerList;
    }

    public static Sunset getSunset() {
        return sunset;
    }
}
