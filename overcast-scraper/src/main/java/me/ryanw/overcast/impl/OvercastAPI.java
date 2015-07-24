package me.ryanw.overcast.impl;

import me.ryanw.overcast.impl.object.ParsedPlayer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class OvercastAPI {

    private static Document doc;

    public static void main(String[] args) throws IOException {
        System.out.println(getPlayer("rockymma"));
    }

    public static ParsedPlayer getPlayer(String username) throws IOException {
        Connection.Response response = Jsoup.connect("https://oc.tc/users/" + username).method(Connection.Method.GET).execute();
        if (response.statusCode() != 200) return null;
        return new ParsedPlayer(response.parse());
    }
}
