package me.ryanw.overcast.impl.object;

import com.google.common.base.Optional;
import me.ryanw.overcast.api.OvercastFriend;
import me.ryanw.overcast.api.OvercastPlayer;
import me.ryanw.overcast.api.OvercastTeam;
import me.ryanw.overcast.api.util.Gender;
import me.ryanw.overcast.impl.mapping.MappingEnum;
import me.ryanw.overcast.impl.mapping.MappingParser;
import me.ryanw.overcast.impl.util.HelperUtil;
import me.ryanw.overcast.impl.util.MojangUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ParsedPlayer implements OvercastPlayer {

    public ParsedPlayer(Document doc) throws IOException {
        MappingParser parser = new MappingParser(doc, "player");
        this.username = parser.getString(MappingEnum.USERNAME);
        this.formerUsername = Optional.fromNullable(MojangUtil.getFormerUsername(MojangUtil.getUUID(username)));

        Map<MappingEnum, String> personalDetails = parser.getMap(MappingEnum.PROFILE_INFO);
        this.gender = HelperUtil.determineGender(personalDetails.get(MappingEnum.GENDER));
        this.location = Optional.fromNullable(personalDetails.get(MappingEnum.LOCATION));
        this.occupation = Optional.fromNullable(personalDetails.get(MappingEnum.OCCUPATION));
        this.interests = Optional.fromNullable(personalDetails.get(MappingEnum.INTERESTS));
        this.biography = Optional.fromNullable(personalDetails.get(MappingEnum.BIOGRAPHY));

        this.globalKills = parser.getInteger(MappingEnum.GLOBAL_KILLS);
        this.globalDeaths = parser.getInteger(MappingEnum.GLOBAL_DEATHS);
        this.globalKdRatio = parser.getDouble(MappingEnum.GLOBAL_KD_RATIO);
        this.globalKkRatio = parser.getDouble(MappingEnum.GLOBAL_KK_RATIO);
        this.globalDaysPlayed = parser.getDouble(MappingEnum.GLOBAL_DAYS_PLAYED);

        this.raindrops = parser.getInteger(MappingEnum.RAINDROPS);
        this.forumPosts = parser.getInteger(MappingEnum.FORUM_POSTS);
        this.forumTopics = parser.getInteger(MappingEnum.FORUM_TOPICS);
        this.serverJoins = parser.getInteger(MappingEnum.SERVER_JOINS);

        this.projectAresKills = parser.getInteger(MappingEnum.PROJECT_ARES_KILLS);
        this.projectAresDeaths = parser.getInteger(MappingEnum.PROJECT_ARES_DEATHS);
        this.projectAresKdRatio = parser.getDouble(MappingEnum.PROJECT_ARES_KD_RATIO);
        this.projectAresKkRatio = parser.getDouble(MappingEnum.PROJECT_ARES_KK_RATIO);
        this.projectAresDaysPlayed = parser.getDouble(MappingEnum.PROJECT_ARES_DAYS_PLAYED);
        this.projectAresDaysObserved = parser.getDouble(MappingEnum.PROJECT_ARES_DAYS_OBSERVED);

        this.blitzKills = parser.getInteger(MappingEnum.BLITZ_KILLS);
        this.blitzDeaths = parser.getInteger(MappingEnum.BLITZ_DEATHS);
        this.blitzKdRatio = parser.getDouble(MappingEnum.BLITZ_KD_RATIO);
        this.blitzKkRatio = parser.getDouble(MappingEnum.BLITZ_KK_RATIO);
        this.blitzDaysPlayed = parser.getDouble(MappingEnum.BLITZ_DAYS_PLAYED);
        this.blitzDaysObserved = parser.getDouble(MappingEnum.BLITZ_DAYS_OBSERVED);

        this.ghostSquadronKills = parser.getInteger(MappingEnum.GHOST_SQUADRON_KILLS);
        this.ghostSquadronDeaths = parser.getInteger(MappingEnum.GHOST_SQUADRON_DEATHS);
        this.ghostSquadronKdRatio = parser.getDouble(MappingEnum.GHOST_SQUADRON_KD_RATIO);
        this.ghostSquadronKkRatio = parser.getDouble(MappingEnum.GHOST_SQUADRON_KK_RATIO);
        this.ghostSquadronDaysPlayed = parser.getDouble(MappingEnum.GHOST_SQUADRON_DAYS_PLAYED);
        this.ghostSquadronDaysObserved = parser.getDouble(MappingEnum.GHOST_SQUADRON_DAYS_OBSERVED);
        this.globalDaysObserved = projectAresDaysObserved + blitzDaysObserved + ghostSquadronDaysObserved;

        this.monumentsDestroyed = parser.getInteger(MappingEnum.MONUMENTS_DESTROYED);
        this.woolsPlaced = parser.getInteger(MappingEnum.WOOLS_PLACED);
        this.coresLeaked = parser.getInteger(MappingEnum.CORES_LEAKED);
        this.friends = HelperUtil.buildFriendObjects(parser.getList(MappingEnum.FRIENDS));
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
    private int monumentsDestroyed;
    private int woolsPlaced;
    private int coresLeaked;

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
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", formerUsername='" + formerUsername.orNull() + '\'' +
                ", friends=" + friends.size() +
                ", globalKills=" + globalKills +
                ", globalDeaths=" + globalDeaths +
                ", globalKdRatio=" + globalKdRatio +
                ", globalKkRatio=" + globalKkRatio +
                ", globalDaysPlayed=" + globalDaysPlayed +
                ", globalDaysObserved=" + globalDaysObserved +
                ", projectAresKills=" + projectAresKills +
                ", projectAresDeaths=" + projectAresDeaths +
                ", projectAresKd=" + projectAresKdRatio +
                ", projectAresKk=" + projectAresKkRatio +
                ", projectAresDaysPlayed=" + projectAresDaysPlayed +
                ", projectAresDayObserved=" + projectAresDaysObserved +
                ", blitzKills=" + blitzKills +
                ", blitzDeaths=" + blitzDeaths +
                ", blitzKd=" + blitzKdRatio +
                ", blitzKk=" + blitzKkRatio +
                ", blitzDaysPlayed=" + blitzDaysPlayed +
                ", blitzDaysObserved=" + blitzDaysObserved +
                ", ghostSquadronKills=" + ghostSquadronKills +
                ", ghostSquadronDeaths=" + ghostSquadronDeaths +
                ", ghostSquadronKd=" + ghostSquadronKdRatio +
                ", ghostSquadronKk=" + ghostSquadronKkRatio +
                ", ghostSquadronDaysPlayed=" + ghostSquadronDaysPlayed +
                ", ghostSquadronDaysObserved=" + ghostSquadronDaysObserved +
                ", serverJoins=" + serverJoins +
                ", raindrops=" + raindrops +
                ", forumPosts=" + forumPosts +
                ", forumTopics=" + forumTopics +
                ", monumentsDestroyed=" + monumentsDestroyed +
                ", woolsPlaced=" + woolsPlaced +
                ", coresLeaked=" + coresLeaked +
                ", gender=" + gender +
                ", location=" + location.orNull() +
                ", occupation=" + occupation.orNull() +
                ", interests=" + interests.orNull() +
                ", biography=" + biography.orNull() +
                ", skypeHandle=" + skypeHandle +
                ", steamHandle=" + steamHandle +
                ", twitterHandle=" + twitterHandle +
                ", twitchHandle=" + twitchHandle +
                ", facebookHandle=" + facebookHandle +
                ", githubHandle=" + githubHandle +
                ", redditHandle=" + redditHandle +
                '}';
    }

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
    public int getMonumentsDestroyed() {
        return monumentsDestroyed;
    }

    @Override
    public int getWoolsPlaced() {
        return woolsPlaced;
    }

    @Override
    public int getCoresLeaked() {
        return coresLeaked;
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
}
