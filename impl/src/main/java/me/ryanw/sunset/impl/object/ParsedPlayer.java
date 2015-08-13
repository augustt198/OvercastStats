package me.ryanw.sunset.impl.object;

import com.google.common.base.Optional;
import me.ryanw.sunset.api.OvercastFriend;
import me.ryanw.sunset.api.OvercastPlayer;
import me.ryanw.sunset.api.OvercastTeam;
import me.ryanw.sunset.api.util.Gender;
import me.ryanw.sunset.impl.mapping.MappingEnum;
import me.ryanw.sunset.impl.mapping.MappingParser;
import me.ryanw.sunset.impl.util.HelperUtil;
import me.ryanw.sunset.impl.util.MojangUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class ParsedPlayer implements OvercastPlayer {

    public ParsedPlayer(Document doc) throws IOException {
        MappingParser parser = new MappingParser(doc, "player");
        this.username = parser.getString(MappingEnum.USERNAME);
        this.formerUsername = Optional.fromNullable(new MojangUtil().getFormerUsername(username).getName());
        this.friends = HelperUtil.buildFriendObjects(parser.getList(MappingEnum.FRIENDS));

        /**
         * Basic player statistics
         */
        Map<MappingEnum, String> playerStats = parser.getMap(MappingEnum.STATS);
        this.globalKills = Integer.parseInt(playerStats.get(MappingEnum.GLOBAL_KILLS));
        this.globalDeaths = Integer.parseInt(playerStats.get(MappingEnum.GLOBAL_DEATHS));
        this.globalKdRatio = Double.parseDouble(playerStats.get(MappingEnum.GLOBAL_KD_RATIO));
        this.globalKkRatio = Double.parseDouble(playerStats.get(MappingEnum.GLOBAL_KK_RATIO));
        this.globalDaysPlayed = Double.parseDouble(playerStats.get(MappingEnum.GLOBAL_DAYS_PLAYED));
        this.serverJoins = Integer.parseInt(playerStats.get(MappingEnum.SERVER_JOINS));
        this.raindrops = Integer.parseInt(playerStats.get(MappingEnum.RAINDROPS));

        /**
         * Advanced player statistics
         */
        Map<MappingEnum, String> playerStatsDetailed = parser.getMap(MappingEnum.STATS_PAGE);
        this.forumPosts = Integer.parseInt(playerStatsDetailed.get(MappingEnum.FORUM_POSTS));
        this.forumTopics = Integer.parseInt(playerStatsDetailed.get(MappingEnum.FORUM_TOPICS));
        this.projectAresKills = Integer.parseInt(playerStatsDetailed.get(MappingEnum.PROJECT_ARES_KILLS));
        this.projectAresDeaths = Integer.parseInt(playerStatsDetailed.get(MappingEnum.PROJECT_ARES_DEATHS));
        this.projectAresKdRatio = Double.parseDouble(playerStatsDetailed.get(MappingEnum.PROJECT_ARES_KD_RATIO));
        this.projectAresKkRatio = Double.parseDouble(playerStatsDetailed.get(MappingEnum.PROJECT_ARES_KK_RATIO));
        this.projectAresDaysPlayed = Double.parseDouble(playerStatsDetailed.get(MappingEnum.PROJECT_ARES_DAYS_PLAYED));
        this.projectAresDaysObserved = Double.parseDouble(playerStatsDetailed.get(MappingEnum.PROJECT_ARES_DAYS_OBSERVED));
        this.blitzKills = Integer.parseInt(playerStatsDetailed.get(MappingEnum.BLITZ_KILLS));
        this.blitzDeaths = Integer.parseInt(playerStatsDetailed.get(MappingEnum.BLITZ_DEATHS));
        this.blitzKdRatio = Double.parseDouble(playerStatsDetailed.get(MappingEnum.BLITZ_KD_RATIO));
        this.blitzKkRatio = Double.parseDouble(playerStatsDetailed.get(MappingEnum.BLITZ_KK_RATIO));
        this.blitzDaysPlayed = Double.parseDouble(playerStatsDetailed.get(MappingEnum.BLITZ_DAYS_PLAYED));
        this.blitzDaysObserved = Double.parseDouble(playerStatsDetailed.get(MappingEnum.BLITZ_DAYS_OBSERVED));
        this.ghostSquadronKills = Integer.parseInt(playerStatsDetailed.get(MappingEnum.GHOST_SQUADRON_KILLS));
        this.ghostSquadronDeaths = Integer.parseInt(playerStatsDetailed.get(MappingEnum.GHOST_SQUADRON_DEATHS));
        this.ghostSquadronKdRatio = Double.parseDouble(playerStatsDetailed.get(MappingEnum.GHOST_SQUADRON_KD_RATIO));
        this.ghostSquadronKkRatio = Double.parseDouble(playerStatsDetailed.get(MappingEnum.GHOST_SQUADRON_KK_RATIO));
        this.ghostSquadronDaysPlayed = Double.parseDouble(playerStatsDetailed.get(MappingEnum.GHOST_SQUADRON_DAYS_PLAYED));
        this.ghostSquadronDaysObserved = Double.parseDouble(playerStatsDetailed.get(MappingEnum.GHOST_SQUADRON_DAYS_OBSERVED));
        double globalDaysObservedRaw = projectAresDaysObserved + blitzDaysObserved + ghostSquadronDaysObserved;
        this.globalDaysObserved = Double.valueOf(new DecimalFormat("#.##").format(globalDaysObservedRaw));

        /**
         * Player profile information
         */
        //Map<MappingEnum, String> personalDetails = parser.getMap(MappingEnum.PROFILE_INFO);
        //this.gender = HelperUtil.determineGender(personalDetails.get(MappingEnum.GENDER));
        //this.location = Optional.fromNullable(personalDetails.get(MappingEnum.LOCATION));
        //this.occupation = Optional.fromNullable(personalDetails.get(MappingEnum.OCCUPATION));
        //this.interests = Optional.fromNullable(personalDetails.get(MappingEnum.INTERESTS));
        this.biography = Optional.fromNullable(parser.getString(MappingEnum.BIOGRAPHY));

        /**
         * Player objectives statistics
         */
        Map<MappingEnum, String> playerObjectives = parser.getMap(MappingEnum.OBJECTIVES);
        this.monuments = Integer.parseInt(playerObjectives.get(MappingEnum.MONUMENTS));
        this.wools = Integer.parseInt(playerObjectives.get(MappingEnum.WOOLS));
        this.cores = Integer.parseInt(playerObjectives.get(MappingEnum.CORES));

        /**
         * Player profile social networking information
         */
        //Map<MappingEnum, String> personalLinks = parser.getMap(MappingEnum.PROFILE_LINKS);
        //this.skypeHandle = Optional.fromNullable(personalLinks.get(MappingEnum.SKYPE_HANDLE));
        //this.steamHandle = Optional.fromNullable(personalLinks.get(MappingEnum.STEAM_HANDLE));
        //this.twitterHandle = Optional.fromNullable(personalLinks.get(MappingEnum.TWITTER_HANDLE));
        //this.twitchHandle = Optional.fromNullable(personalLinks.get(MappingEnum.TWITCH_HANDLE));
        //this.facebookHandle = Optional.fromNullable(personalLinks.get(MappingEnum.FACEBOOK_HANDLE));
        //this.githubHandle = Optional.fromNullable(personalLinks.get(MappingEnum.GITHUB_HANDLE));
        //this.redditHandle = Optional.fromNullable(personalLinks.get(MappingEnum.REDDIT_HANDLE));
    }

    /**
     * User Details
     */
    private String username;
    private Optional<String> formerUsername;
    private List<OvercastFriend> friends;

    /**
     * Stat Details
     */
    private int globalKills;
    private int globalDeaths;
    private double globalKdRatio;
    private double globalKkRatio;
    private double globalDaysPlayed;
    private double globalDaysObserved;

    private int projectAresKills;
    private int projectAresDeaths;
    private double projectAresKdRatio;
    private double projectAresKkRatio;
    private double projectAresDaysPlayed;
    private double projectAresDaysObserved;

    private int blitzKills;
    private int blitzDeaths;
    private double blitzKdRatio;
    private double blitzKkRatio;
    private double blitzDaysPlayed;
    private double blitzDaysObserved;

    private int ghostSquadronKills;
    private int ghostSquadronDeaths;
    private double ghostSquadronKdRatio;
    private double ghostSquadronKkRatio;
    private double ghostSquadronDaysPlayed;
    private double ghostSquadronDaysObserved;

    private int serverJoins;
    private int raindrops;
    private int forumPosts;
    private int forumTopics;

    /**
     * Objective Details
     */
    private int monuments;
    private int wools;
    private int cores;

    /**
     * Personal Details
     */
    private Gender gender;
    private Optional<String> location;
    private Optional<String> occupation;
    private Optional<String> interests;
    private Optional<String> biography;

    /**
     * Other accounts
     */
    private Optional<String> skypeHandle;
    private Optional<String> steamHandle;
    private Optional<String> twitterHandle;
    private Optional<String> twitchHandle;
    private Optional<String> facebookHandle;
    private Optional<String> githubHandle;
    private Optional<String> redditHandle;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Optional<String> getFormerUsername() {
        return formerUsername;
    }

    @Override
    public List<OvercastFriend> getFriends() {
        return friends;
    }

    @Override
    public int getGlobalKills() {
        return globalKills;
    }

    @Override
    public int getGlobalDeaths() {
        return globalDeaths;
    }

    @Override
    public double getGlobalKdRatio() {
        return globalKdRatio;
    }

    @Override
    public double getGlobalKkRatio() {
        return globalKkRatio;
    }

    @Override
    public double getGlobalDaysPlayed() {
        return globalDaysPlayed;
    }

    @Override
    public double getGlobalDaysObserved() {
        return globalDaysObserved;
    }

    @Override
    public int getProjectAresKills() {
        return projectAresKills;
    }

    @Override
    public int getProjectAresDeaths() {
        return projectAresDeaths;
    }

    @Override
    public double getProjectAresKdRatio() {
        return projectAresKdRatio;
    }

    @Override
    public double getProjectAresKkRatio() {
        return projectAresKkRatio;
    }

    @Override
    public double getProjectAresDaysPlayed() {
        return projectAresDaysPlayed;
    }

    @Override
    public double getProjectAresDaysObserved() {
        return projectAresDaysObserved;
    }

    @Override
    public int getBlitzKills() {
        return blitzKills;
    }

    @Override
    public int getBlitzDeaths() {
        return blitzDeaths;
    }

    @Override
    public double getBlitzKdRatio() {
        return blitzKdRatio;
    }

    @Override
    public double getBlitzKkRatio() {
        return blitzKkRatio;
    }

    @Override
    public double getBlitzDaysPlayed() {
        return blitzDaysPlayed;
    }

    @Override
    public double getBlitzDaysObserved() {
        return blitzDaysObserved;
    }

    @Override
    public int getGhostSquadronKills() {
        return ghostSquadronKills;
    }

    @Override
    public int getGhostSquadronDeaths() {
        return ghostSquadronDeaths;
    }

    @Override
    public double getGhostSquadronKdRatio() {
        return ghostSquadronKdRatio;
    }

    @Override
    public double getGhostSquadronKkRatio() {
        return ghostSquadronKkRatio;
    }

    @Override
    public double getGhostSquadronDaysPlayed() {
        return ghostSquadronDaysPlayed;
    }

    @Override
    public double getGhostSquadronDaysObserved() {
        return ghostSquadronDaysObserved;
    }

    @Override
    public int getServerJoins() {
        return serverJoins;
    }

    @Override
    public int getRaindrops() {
        return raindrops;
    }

    @Override
    public int getForumPosts() {
        return forumPosts;
    }

    @Override
    public int getForumTopics() {
        return forumTopics;
    }

    @Override
    public int getMonuments() {
        return monuments;
    }

    @Override
    public int getWools() {
        return wools;
    }

    @Override
    public int getCores() {
        return cores;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Optional<String> getLocation() {
        return location;
    }

    @Override
    public Optional<String> getOccupation() {
        return occupation;
    }

    @Override
    public Optional<String> getInterests() {
        return interests;
    }

    @Override
    public Optional<String> getBiography() {
        return biography;
    }

    @Override
    public Optional<String> getSkypeHandle() {
        return skypeHandle;
    }

    @Override
    public Optional<String> getSteamHandle() {
        return steamHandle;
    }

    @Override
    public Optional<String> getTwitterHandle() {
        return twitterHandle;
    }

    @Override
    public Optional<String> getTwitchHandle() {
        return twitchHandle;
    }

    @Override
    public Optional<String> getFacebookHandle() {
        return facebookHandle;
    }

    @Override
    public Optional<String> getGithubHandle() {
        return githubHandle;
    }

    @Override
    public Optional<String> getRedditHandle() {
        return redditHandle;
    }

    @Override
    public Optional<OvercastTeam> getTeam() {
        //TODO: implement
        return Optional.absent();
    }

    @Override
    public String toString() {
        return "ParsedPlayer{" +
                "username=" + username +
                ", formerUsername=" + formerUsername.orNull() +
                ", friends=" + friends.size() +
                ", globalKills=" + globalKills +
                ", globalDeaths=" + globalDeaths +
                ", globalKdRatio=" + globalKdRatio +
                ", globalKkRatio=" + globalKkRatio +
                ", globalDaysPlayed=" + globalDaysPlayed +
                ", globalDaysObserved=" + globalDaysObserved +
                ", projectAresKills=" + projectAresKills +
                ", projectAresDeaths=" + projectAresDeaths +
                ", projectAresKdRatio=" + projectAresKdRatio +
                ", projectAresKkRatio=" + projectAresKkRatio +
                ", projectAresDaysPlayed=" + projectAresDaysPlayed +
                ", projectAresDaysObserved=" + projectAresDaysObserved +
                ", blitzKills=" + blitzKills +
                ", blitzDeaths=" + blitzDeaths +
                ", blitzKdRatio=" + blitzKdRatio +
                ", blitzKkRatio=" + blitzKkRatio +
                ", blitzDaysPlayed=" + blitzDaysPlayed +
                ", blitzDaysObserved=" + blitzDaysObserved +
                ", ghostSquadronKills=" + ghostSquadronKills +
                ", ghostSquadronDeaths=" + ghostSquadronDeaths +
                ", ghostSquadronKdRatio=" + ghostSquadronKdRatio +
                ", ghostSquadronKkRatio=" + ghostSquadronKkRatio +
                ", ghostSquadronDaysPlayed=" + ghostSquadronDaysPlayed +
                ", ghostSquadronDaysObserved=" + ghostSquadronDaysObserved +
                ", serverJoins=" + serverJoins +
                ", raindrops=" + raindrops +
                ", forumPosts=" + forumPosts +
                ", forumTopics=" + forumTopics +
                ", monumentsDestroyed=" + monuments +
                ", woolsPlaced=" + wools +
                ", coresLeaked=" + cores +
                ", gender=" + gender +
                ", location=" + location +
                ", occupation=" + occupation +
                ", interests=" + interests +
                ", biography=" + biography.orNull() +
                ", skypeHandle=" + skypeHandle +
                ", steamHandle=" + steamHandle +
                ", twitterHandle=" + twitterHandle +
                ", twitchHandle=" + twitchHandle +
                ", facebookHandle=" + facebookHandle +
                ", githubHandle=" + githubHandle +
                ", redditHandle=" + redditHandle + '}';
    }
}
