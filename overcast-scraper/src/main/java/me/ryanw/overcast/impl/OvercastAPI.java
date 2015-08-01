package me.ryanw.overcast.impl;

import me.ryanw.overcast.api.OvercastPlayer;
import me.ryanw.overcast.impl.object.ParsedPlayer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OvercastAPI {

    private static String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";
    private static int connectionTimeout = 3000;

    public static OvercastPlayer getPlayer(String username) throws IOException {
        Connection.Response response = Jsoup.connect("https://oc.tc/users/" + username)
                .method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
        if (response.statusCode() != 200) return null;
        return new ParsedPlayer(response.parse());
    }

    public static List<OvercastPlayer> getPlayers(List<String> usernames) throws IOException {
        List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
        for (String username : usernames) {
            Connection.Response response = Jsoup.connect("https://oc.tc/users/" + username)
                    .method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            if (response.statusCode() != 200) break;
            playerList.add(new ParsedPlayer(response.parse()));
        }
        return playerList;
    }
}
