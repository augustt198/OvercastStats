package me.ryanw.overcaststats.api;

import java.util.List;

public interface Overcast {

    /**
     * Gets an overcast player by username and parses the player page for that user.
     * @param username The username of the player page to parse.
     * @return Parsed OvercastPlayer object.
     */
    OvercastPlayer getPlayer(String username);

    /**
     * Gets an overcast player by their player page url and parses the contents of that url.
     * @param url The url to parse and identify the user by.
     * @return Parsed OvercastPlayer object.
     */
    OvercastPlayer getPlayerByUrl(String url);

    /**
     * Gets a list of overcast players by their usernames and parses their player pages.
     * @param usernames The usernames of the players whoms pages we want to parse.
     * @return List of parsed OvercastPlayer objects.
     */
    List<OvercastPlayer> getPlayers(List<String> usernames);

    /**
     * Gets a list of overcast players by their player page urls and parses their player pages.
     * @param urls The urls that we want to identify each user by and parse.
     * @return List of parsed OvercastPlayer objects.
     */
    List<OvercastPlayer> getPlayersByUrl(List<String> urls);

    /**
     * Sets JSoups connection timeout time, if none is set it will default to 3000.
     * @param timeout The timeout time as a int to provide JSoup with.
     */
    void setConnectionTimeout(int timeout);

    /**
     * Sets JSoups user agent to use when getting information, if none is provided it will use the default.
     * @param userAgent The user agent to provide JSoup with.
     */
    void setUserAgent(String userAgent);
}
