package me.ryanw.sunset.impl.mapping;

public enum MappingEnum {

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
    OBJECTIVES("objectives");

    private String id;

    public enum Types {
        LIST(), MAP(), SINGLE()
    }

    MappingEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
