package me.ryanw.overcast.api;

import com.google.common.base.Optional;
import me.ryanw.overcast.api.util.Gender;

import java.util.List;

//TODO: javadocs
public interface OvercastPlayer {

    String getUsername();

    Optional<String> getFormerUsername();

    List<OvercastFriend> getFriends();

    int getGlobalKills();

    int getGlobalDeaths();

    double getGlobalKdRatio();

    double getGlobalKkRatio();

    double getGlobalDaysPlayed();

    double getGlobalDaysObserved();

    int getProjectAresKills();

    int getProjectAresDeaths();

    double getProjectAresKdRatio();

    double getProjectAresKkRatio();

    double getProjectAresDaysPlayed();

    double getProjectAresDaysObserved();

    int getBlitzKills();

    int getBlitzDeaths();

    double getBlitzKdRatio();

    double getBlitzKkRatio();

    double getBlitzDaysPlayed();

    double getBlitzDaysObserved();

    int getGhostSquadronKills();

    int getGhostSquadronDeaths();

    double getGhostSquadronKdRatio();

    double getGhostSquadronKkRatio();

    double getGhostSquadronDaysPlayed();

    double getGhostSquadronDaysObserved();

    int getServerJoins();

    int getRaindrops();

    int getForumPosts();

    int getForumTopics();

    int getMonumentsDestroyed();

    int getWoolsPlaced();

    int getCoresLeaked();

    Gender getGender();

    Optional<String> getLocation();

    Optional<String> getOccupation();

    Optional<String> getInterests();

    Optional<String> getBiography();

    Optional<String> getSkypeHandle();

    Optional<String> getSteamHandle();

    Optional<String> getTwitterHandle();

    Optional<String> getTwitchHandle();

    Optional<String> getFacebookHandle();

    Optional<String> getGithubHandle();

    Optional<String> getRedditHandle();

    Optional<OvercastTeam> getTeam();
}
