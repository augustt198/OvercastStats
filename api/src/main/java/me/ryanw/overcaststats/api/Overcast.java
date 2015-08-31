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
import me.ryanw.overcaststats.api.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface Overcast {

    /**
     * Gets an {@link me.ryanw.overcaststats.api.OvercastPlayer} by username and parses the profile page for that user.
     * Runs logic and returns on the main thread, not recommended for production environments.
     * @param username The username of the profile page to parse.
     * @return Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object.
     */
    OvercastPlayer getPlayerByName(String username);

    /**
     * Gets an {@link me.ryanw.overcaststats.api.OvercastPlayer} by username and parses the profile page for that user.
     * Runs logic and returns a callback on a separate thread, recommended for production environments.
     * @param username The username of the profile page to parse.
     * @param callback Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object from the other thread.
     */
    void getPlayerByNameAsync(String username, Callback<OvercastPlayer> callback);


    /**
     * Gets an {@link me.ryanw.overcaststats.api.OvercastPlayer} by their profile page url and parses the contents of that url.
     * Runs logic and returns on the main thread, not recommended for production environments.
     * @param url The url to parse and identify the user by.
     * @return Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object.
     */
    OvercastPlayer getPlayerByUrl(URL url);

    /**
     * Gets an {@link me.ryanw.overcaststats.api.OvercastPlayer} by their profile page url and parses the contents of that url.
     * Runs logic and returns a callback on a separate thread, recommended for production environments.
     * @param url The url to parse and identify the user by.
     * @param callback Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object from the other thread.
     */
    void getPlayerByUrlAsync(URL url, Callback<OvercastPlayer> callback);

    /**
     * Gets an {@link me.ryanw.overcaststats.api.OvercastPlayer} by their uuid and parses the profile page for that user.
     * Runs logic and returns on the main thread, not recommended for production environments.
     * @param uuid The uuid of the player page to parse.
     * @return Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object.
     */
    OvercastPlayer getPlayerByUuid(UUID uuid);

    /**
     * Gets an {@link me.ryanw.overcaststats.api.OvercastPlayer} by their uuid and parses the profile page for that user.
     * Runs logic and returns a callback on a separate thread, recommended for production environments.
     * @param uuid The uuid of the player page to parse.
     * @param callback Parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} object from the other thread.
     */
    void getPlayerByUuidAsync(UUID uuid, Callback<OvercastPlayer> callback);

    /**
     * Gets a list of {@link me.ryanw.overcaststats.api.OvercastPlayer} by their usernames and parses their profile pages.
     * Runs logic and returns on the main thread, not recommended for production environments.
     * @param usernames The usernames of the players whoms profile pages we want to parse.
     * @return List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects.
     */
    List<OvercastPlayer> getPlayersByName(List<String> usernames);

    /**
     * Gets a list of {@link me.ryanw.overcaststats.api.OvercastPlayer} by their usernames and parses their profile pages.
     * Runs logic and returns a callback on a separate thread, recommended for production environments.
     * @param usernames The usernames of the players whom profile pages we want to parse.
     * @param callback List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects from the other thread.
     */
    void getPlayersByNameAsync(List<String> usernames, Callback<List<OvercastPlayer>> callback);

    /**
     * Gets a list of {@link me.ryanw.overcaststats.api.OvercastPlayer} by their player page urls and parses their profile pages.
     * Runs logic and returns on main thread, not recommended for production environments.
     * @param urls The urls that we want to identify each user by and parse.
     * @return List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects.
     */
    List<OvercastPlayer> getPlayersByUrl(List<URL> urls);

    /**
     * Gets a list of {@link me.ryanw.overcaststats.api.OvercastPlayer} by their usernames and parses their profile pages.
     * Runs logic and returns a callback on a separate thread, recommended for production environments.
     * @param urls The urls that we want to identify each user by and parse.
     * @param callback List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects from the other thread.
     */
    void getPlayersByUrlAsync(List<URL> urls, Callback<List<OvercastPlayer>> callback);

    /**
     * Gets a list of {@link me.ryanw.overcaststats.api.OvercastPlayer} by their uuids and parses their profile pages.
     * Runs logic and returns on main thread, not recommended for production environments.
     * @param uuids The uuids of the players whoms profile pages we want to parse.
     * @return List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects.
     */
    List<OvercastPlayer> getPlayersByUuid(List<UUID> uuids);

    /**
     * Gets a list of {@link me.ryanw.overcaststats.api.OvercastPlayer} by their uuids and parses their profile pages.
     * Runs logic and returns a callback on a separate thread, recommended for production environments.
     * @param uuids The uuids of the players whoms profile pages we want to parse.
     * @param callback List of parsed {@link me.ryanw.overcaststats.api.OvercastPlayer} objects from the other thread.
     */
    void getPlayersByUuidAsync(List<UUID> uuids, Callback<List<OvercastPlayer>> callback);

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
