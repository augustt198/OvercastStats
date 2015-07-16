package me.ryanw.overcast.api;

import com.google.common.base.Optional;
import me.ryanw.overcast.api.util.Gender;

import java.util.List;

//TODO: javadocs
public interface OvercastPlayer {

    String getUsername();

    Optional<String> getFormerUsername();

    List<OvercastPlayer> getFriends();

    int getGlobalKills();

    int getGlobalDeaths();

    double getGlobalKdRatio();

    double getGlobalKkRatio();

    double getGlobalDaysPlayed();

    int getProjectAresKills();

    int getProjectAresDeaths();

    double getProjectAresKd();

    double getProjectAresKk();

    double getProjectAresDaysPlayed();

    double getProjectAresDayObserved();

    int getBlitzKills();

    int getBlitzDeaths();

    double getBlitzKd();

    double getBlitzKk();

    double getBlitzDaysPlayed();

    double getBlitzDaysObserved();

    int getGhostSquadronKills();

    int getGhostSquadronDeaths();

    double getGhostSquadronKd();

    double getGhostSquadronKk();

    double getGhostSquadronDaysPlayed();

    double getGhostSquadronDaysObserved();

    int getServerJoins();

    int getRaindrops();

    int getForumPosts();

    int getForumTopics();

    int getMonumentsDestroyed();

    int getWoolsPlaced();

    int getCoresLeaked();

    Optional<Gender> getGender();

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
