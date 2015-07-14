package me.ryanw.overcast.objects;

import com.google.common.base.Optional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.ryanw.overcast.mappings.MappingsParser;
import me.ryanw.overcast.utils.MojangUtils;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
public class OvercastPlayer {

    public OvercastPlayer(Document doc) throws IOException {
        MappingsParser parser = new MappingsParser(doc);

        this.username = parser.getString("username");
        this.formerUsername = Optional.fromNullable(MojangUtils.getFormerUsername(MojangUtils.getUUID(username)));
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
    private int globalKdRatio;
    private int globalKkRatio;
    private int globalDaysPlayed;

    private int projectAresKills;
    private int projectAresDeaths;
    private int projectAresKd;
    private int projectAresKk;
    private int projectAresDaysPlayed;
    private int projectAresDayObserved;

    private int blitzKills;
    private int blitzDeaths;
    private int blitzKd;
    private int blitzKk;
    private int blitzDaysPlayed;
    private int blitzDaysObserved;

    private int ghostSquadronKills;
    private int ghostSquadronDeaths;
    private int ghostSquadronKd;
    private int ghostSquadronKk;
    private int ghostSquadronDaysPlayed;
    private int ghostSquadronDaysObserved;

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
    private Optional<String> gender;
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
}
