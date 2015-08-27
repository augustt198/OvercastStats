package me.ryanw.overcaststats.api;
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
import java.util.List;
import java.util.UUID;

public interface Overcast {

    /**
     * Gets an overcast player by username and parses the profile page for that user.
     * @param username The username of the profile page to parse.
     * @return Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object.
     */
    OvercastPlayer getPlayerByName(String username);

    /**
     * Gets an overcast player by their profile page url and parses the contents of that url.
     * @param url The url to parse and identify the user by.
     * @return Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object.
     */
    OvercastPlayer getPlayerByUrl(String url);

    /**
     * Gets an overcast player by their uuid and parses the profile page for that user.
     * @param uuid The uuid of the player page to parse.
     * @return Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object.
     */
    OvercastPlayer getPlayerByUUID(UUID uuid);

    /**
     * Gets a list of overcast players by their usernames and parses their profile pages.
     * @param usernames The usernames of the players whoms profile pages we want to parse.
     * @return List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects.
     */
    List<OvercastPlayer> getPlayersByName(List<String> usernames);

    /**
     * Gets a list of overcast players by their player page urls and parses their profile pages.
     * @param urls The urls that we want to identify each user by and parse.
     * @return List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects.
     */
    List<OvercastPlayer> getPlayersByUrl(List<String> urls);

    /**
     * Gets a list of overcast players by their uuids and parses their profile pages.
     * @param uuids The uuids of the players whoms profile pages we want to parse.
     * @return List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects.
     */
    List<OvercastPlayer> getPlayersByUUID(List<UUID> uuids);

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
