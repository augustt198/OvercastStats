package me.ryanw.overcaststats.impl;
/*
 * This file is part of OvercastStats, licensed under the MIT License (MIT).
 *
 * Copyright (c) Ryan W
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import me.ryanw.overcaststats.api.Overcast;
import me.ryanw.overcaststats.api.OvercastPlayer;
import me.ryanw.overcaststats.api.util.Callback;
import me.ryanw.overcaststats.impl.object.ParsedPlayer;
import me.ryanw.overcaststats.impl.util.MojangUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OvercastStats implements Overcast {

    private String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";
    private int connectionTimeout = 3000;

    @Override
    public OvercastPlayer getPlayerByName(String username) {
        String url = "https://oc.tc/users/" + username;
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            return new ParsedPlayer(this, response.parse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getPlayerByNameAsync(final String username, final Callback<OvercastPlayer> callback) {
        final OvercastStats overcastStats = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://oc.tc/users/" + username;
                try {
                    Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                    callback.call(new ParsedPlayer(overcastStats, response.parse()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public OvercastPlayer getPlayerByUrl(URL url)  {
        try {
            Connection.Response response = Jsoup.connect(url.toString()).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            return new ParsedPlayer(this, response.parse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getPlayerByUrlAsync(final URL url, final Callback<OvercastPlayer> callback) {
        final OvercastStats overcastStats = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection.Response response = Jsoup.connect(url.toString()).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                    callback.call(new ParsedPlayer(overcastStats, response.parse()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public OvercastPlayer getPlayerByUuid(UUID uuid) {
        String url = "https://oc.tc/users/" + MojangUtil.getUsername(String.valueOf(uuid));
        try {
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
            return new ParsedPlayer(this, response.parse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void getPlayerByUuidAsync(final UUID uuid, final Callback<OvercastPlayer> callback) {
        final OvercastStats overcastStats = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://oc.tc/users/" + MojangUtil.getUsername(String.valueOf(uuid));
                try {
                    Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                    callback.call(new ParsedPlayer(overcastStats, response.parse()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public List<OvercastPlayer> getPlayersByName(List<String> usernames) {
        List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
        for (String username : usernames) {
            String url = "https://oc.tc/users/" + username;
            try {
                Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                playerList.add(new ParsedPlayer(this, response.parse()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playerList;
    }

    @Override
    public void getPlayersByNameAsync(final List<String> usernames, final Callback<List<OvercastPlayer>> callback) {
        final OvercastStats overcastStats = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
                for (String username : usernames) {
                    String url = "https://oc.tc/users/" + username;
                    try {
                        Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                        playerList.add(new ParsedPlayer(overcastStats, response.parse()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                callback.call(playerList);
            }
        }).start();
    }

    @Override
    public List<OvercastPlayer> getPlayersByUrl(List<URL> urls) {
        List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
        for (URL url : urls) {
            try {
                Connection.Response response = Jsoup.connect(url.toString()).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                playerList.add(new ParsedPlayer(this, response.parse()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playerList;
    }

    @Override
    public void getPlayersByUrlAsync(final List<URL> urls, final Callback<List<OvercastPlayer>> callback) {
        final OvercastStats overcastStats = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
                for (URL url : urls) {
                    try {
                        Connection.Response response = Jsoup.connect(url.toString()).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                        playerList.add(new ParsedPlayer(overcastStats, response.parse()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                callback.call(playerList);
            }
        }).start();
    }

    @Override
    public List<OvercastPlayer> getPlayersByUuid(List<UUID> uuids) {
        List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
        for (UUID uuid : uuids) {
            String url = "https://oc.tc/users/" + MojangUtil.getUsername(String.valueOf(uuid));
            try {
                Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                playerList.add(new ParsedPlayer(this, response.parse()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playerList;
    }

    @Override
    public void getPlayersByUuidAsync(final List<UUID> uuids, final Callback<List<OvercastPlayer>> callback) {
        final OvercastStats overcastStats = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<OvercastPlayer> playerList = new ArrayList<OvercastPlayer>();
                for (UUID uuid : uuids) {
                    String url = "https://oc.tc/users/" + MojangUtil.getUsername(String.valueOf(uuid));
                    try {
                        Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).userAgent(userAgent).timeout(connectionTimeout).execute();
                        playerList.add(new ParsedPlayer(overcastStats, response.parse()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                callback.call(playerList);
            }
        }).start();
    }

    @Override
    public void setConnectionTimeout(int timeout) {
        this.connectionTimeout = timeout;
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
