package me.ryanw.overcaststats.impl;

import me.ryanw.overcaststats.api.Overcast;
import me.ryanw.overcaststats.api.OvercastPlayer;
import me.ryanw.overcaststats.impl.object.ParsedPlayer;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OvercastStats implements Overcast {

    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";
    private static OvercastStats overcastStats;
    private int connectionTimeout = 3000;
    public OvercastStats() { overcastStats = this; }

    /**
     * Creates a new OvercastPlayer object, by scrapping the HTML page of the player provided.
     * @param username The username of the page we want to get the HTML from.
     * @return Parsed player object for the user specified.
     */
    @Override
    public OvercastPlayer getPlayer(String username) {
        String url = "https://oc.tc/users/" + username;
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            return new ParsedPlayer(response.parse());
        } catch (IOException ignored) {}
        return null;
    }

    /**
     * Creates a new OvercastPlayer object, by scrapping the html page of the player url page provided.
     * @param url The URL of the users profile page that we want to get the HTML from.
     * @return Parsed player object for the user URL specified.
     */
    @Override
    public OvercastPlayer getPlayerByUrl(String url)  {
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            return new ParsedPlayer(response.parse());
        } catch (IOException ignored) {}
        return null;
    }

    /**
     * Creates a list of new OvercastPlayer objects, by getting the HTML documents to scrap for each of them and parsing it.
     * @param usernames List of all usernames we want to scrap HTML from and return new objects for.
     * @return List of parsed player objects for the list of usernames provided.
     */
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

    /**
     * Creates a list of new OvercastPlayer objects, by getting the HTML documents at each of the URLs in the list and parsing it.
     * @param urls List of all player profile URLs we want to scrap HTML from and return new objects for.
     * @return List of parsed player objects for the list of player profile URLs provided.
     */
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

    /**
     * Sets the timeout time for Jsoup, when connecting and reading a document.
     * @param timeout the timeout time as a int.
     */
    @Override
    public void setConnectionTimeout(int timeout) {
        this.connectionTimeout = timeout;
    }

    /**
     * Sets the user agent for Jsoup, when contacting and reading the Overcast Network website.
     * @param userAgent The user agent we want to use while reading contacting the website.
     */
    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * When the API is instantiated in the constructor, this instance will be used to convert friend objects to player ones.
     * @return A new instance of OvercastStats that can be used to convert friend objects to player ones.
     */
    public static OvercastStats getOvercastStats() {
        return overcastStats;
    }
}
