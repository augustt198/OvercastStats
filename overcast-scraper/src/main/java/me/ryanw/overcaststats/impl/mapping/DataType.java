package me.ryanw.overcaststats.impl.mapping;
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
public enum DataType {

    USERNAME("username"),
    FRIENDS("friends"),
    GLOBAL_KILLS("globalKills"),
    GLOBAL_DEATHS("globalDeaths"),
    GLOBAL_KD_RATIO("globalKdRatio"),
    GLOBAL_KK_RATIO("globalKkRatio"),
    GLOBAL_DAYS_PLAYED("globalDaysPlayed"),
    PROJECT_ARES_KILLS("projectAresKills"),
    PROJECT_ARES_DEATHS("projectAresDeaths"),
    PROJECT_ARES_KD_RATIO("projectAresKdRatio"),
    PROJECT_ARES_KK_RATIO("projectAresKkRatio"),
    PROJECT_ARES_DAYS_PLAYED("projectAresDaysPlayed"),
    PROJECT_ARES_DAYS_OBSERVED("projectAresDaysObserved"),
    BLITZ_KILLS("blitzKills"),
    BLITZ_DEATHS("blitzDeaths"),
    BLITZ_KD_RATIO("blitzKdRatio"),
    BLITZ_KK_RATIO("blitzKkRatio"),
    BLITZ_DAYS_PLAYED("blitzDaysPlayed"),
    BLITZ_DAYS_OBSERVED("blitzDaysObserved"),
    GHOST_SQUADRON_KILLS("ghostSquadronKills"),
    GHOST_SQUADRON_DEATHS("ghostSquadronDeaths"),
    GHOST_SQUADRON_KD_RATIO("ghostSquadronKdRatio"),
    GHOST_SQUADRON_KK_RATIO("ghostSquadronKkRatio"),
    GHOST_SQUADRON_DAYS_PLAYED("ghostSquadronDaysPlayed"),
    GHOST_SQUADRON_DAYS_OBSERVED("ghostSquadronDaysObserved"),
    SERVER_JOINS("serverJoins"),
    RAINDROPS("raindrops"),
    FORUM_POSTS("forumPosts"),
    FORUM_TOPICS("forumTopics"),
    MONUMENTS("monuments"),
    WOOLS("wools"),
    CORES("cores"),
    PROFILE_INFO("profileInfo"),
    GENDER("gender"),
    LOCATION("location"),
    OCCUPATION("occupation"),
    INTERESTS("interests"),
    BIOGRAPHY("biography"),
    PROFILE_LINKS("profileLinks"),
    SKYPE_HANDLE("skypeHandle"),
    TWITTER_HANDLE("twitterHandle"),
    FACEBOOK_HANDLE("facebookHandle"),
    STEAM_HANDLE("steamHandle"),
    TWITCH_HANDLE("twitchHandle"),
    GITHUB_HANDLE("githubHandle"),
    REDDIT_HANDLE("redditHandle"),
    FRIENDS_COUNT("friendsCount"),
    STATS("stats"),
    STATS_PAGE("statsPage"),
    OBJECTIVES("objectives"),
    ACCOUNT_AGE("accountAge"),
    HOURS_PLAYED("hoursPlayed"),
    TEAM_JOINS("teamJoins");

    private String id;

    public enum Types {
        LIST, MAP, SINGLE
    }

    DataType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static DataType getEnumById(String id) {
        for (DataType dataType : DataType.values()) {
            if (dataType.getId().equalsIgnoreCase(id)) return dataType;
        }
        return null;
    }
}
