package me.ryanw.overcast.impl.object;

import com.google.common.base.Optional;
import me.ryanw.overcast.api.OvercastPlayer;
import me.ryanw.overcast.api.OvercastTeam;
import me.ryanw.overcast.api.util.Gender;
import me.ryanw.overcast.impl.util.MappingParser;
import me.ryanw.overcast.impl.util.MojangUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class ParsedPlayer implements OvercastPlayer {

    public ParsedPlayer(Document doc) throws IOException {
        MappingParser parser = new MappingParser(doc, "player");

        this.username = parser.getString("username");
        this.formerUsername = Optional.fromNullable(MojangUtil.getFormerUsername(MojangUtil.getUUID(username)));
        this.globalKills = parser.getInteger("globalKills");
        this.globalDeaths = parser.getInteger("globalDeaths");

        //this.globalKdRatio = parser.getInteger("globalKdRatio");
    }

    /**
     * User Details
     */
    private String username;
    private Optional<String> formerUsername;
    private List<OvercastPlayer> friends;

    /**
     * Stat Details
     */
    private int globalKills;
    private int globalDeaths;
    private double globalKdRatio;
    private double globalKkRatio;
    private double globalDaysPlayed;

    private int projectAresKills;
    private int projectAresDeaths;
    private double projectAresKd;
    private double projectAresKk;
    private double projectAresDaysPlayed;
    private double projectAresDayObserved;

    private int blitzKills;
    private int blitzDeaths;
    private double blitzKd;
    private double blitzKk;
    private double blitzDaysPlayed;
    private double blitzDaysObserved;

    private int ghostSquadronKills;
    private int ghostSquadronDeaths;
    private double ghostSquadronKd;
    private double ghostSquadronKk;
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
    private Optional<Gender> gender;
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
                ", friends=" + friends +
                ", globalKills=" + globalKills +
                ", globalDeaths=" + globalDeaths +
                ", globalKdRatio=" + globalKdRatio +
                ", globalKkRatio=" + globalKkRatio +
                ", globalDaysPlayed=" + globalDaysPlayed +
                ", projectAresKills=" + projectAresKills +
                ", projectAresDeaths=" + projectAresDeaths +
                ", projectAresKd=" + projectAresKd +
                ", projectAresKk=" + projectAresKk +
                ", projectAresDaysPlayed=" + projectAresDaysPlayed +
                ", projectAresDayObserved=" + projectAresDayObserved +
                ", blitzKills=" + blitzKills +
                ", blitzDeaths=" + blitzDeaths +
                ", blitzKd=" + blitzKd +
                ", blitzKk=" + blitzKk +
                ", blitzDaysPlayed=" + blitzDaysPlayed +
                ", blitzDaysObserved=" + blitzDaysObserved +
                ", ghostSquadronKills=" + ghostSquadronKills +
                ", ghostSquadronDeaths=" + ghostSquadronDeaths +
                ", ghostSquadronKd=" + ghostSquadronKd +
                ", ghostSquadronKk=" + ghostSquadronKk +
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
                ", location=" + location +
                ", occupation=" + occupation +
                ", interests=" + interests +
                ", biography=" + biography +
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
    public List<OvercastPlayer> getFriends() {
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
    public int getProjectAresKills() {
        return projectAresKills;
    }

    @Override
    public int getProjectAresDeaths() {
        return projectAresDeaths;
    }

    @Override
    public double getProjectAresKd() {
        return projectAresKd;
    }

    @Override
    public double getProjectAresKk() {
        return projectAresKk;
    }

    @Override
    public double getProjectAresDaysPlayed() {
        return projectAresDaysPlayed;
    }

    @Override
    public double getProjectAresDayObserved() {
        return projectAresDayObserved;
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
    public double getBlitzKd() {
        return blitzKd;
    }

    @Override
    public double getBlitzKk() {
        return blitzKk;
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
    public double getGhostSquadronKd() {
        return ghostSquadronKd;
    }

    @Override
    public double getGhostSquadronKk() {
        return ghostSquadronKk;
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
    public Optional<Gender> getGender() {
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
