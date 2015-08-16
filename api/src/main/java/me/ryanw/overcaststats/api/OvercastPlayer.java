package me.ryanw.overcaststats.api;

import com.google.common.base.Optional;
import me.ryanw.overcaststats.api.util.Gender;

import java.util.List;

public interface OvercastPlayer {

    /**
     * Gets the username of the player
     */
    String getUsername();

    /**
     * Gets the previous username of the player
     **/
    Optional<String> getFormerUsername();

    /**
     * Gets the first 16 friends of the player (limited to 16 due to not having authentication)
     **/
    List<OvercastFriend> getFriends();

    /**
     * Gets the total amount of kills the player has accumulated across all game modes
     **/
    int getGlobalKills();

    /**
     * Gets the total amount of deaths the player has accumulated across all game modes.
     */
    int getGlobalDeaths();

    /**
     * Gets the total kill to death ratio for the player across all game modes.
     */
    double getGlobalKdRatio();

    /**
     * Gets the total kill to kill ratio for the player across all game modes.
     */
    double getGlobalKkRatio();

    /**
     * Gets the total amount of time the player has spent on the server.
     */
    double getGlobalDaysPlayed();

    /**
     * Gets the total amount of time the player has spent observing matches across all game modes.
     */
    double getGlobalDaysObserved();

    /**
     * Gets the total amount of kills the player has accumulated in the game mode "Project Ares".
     */
    int getProjectAresKills();

    /**
     * Gets the total amount of deaths the player has accumulated in the game mode "Project Ares".
     */
    int getProjectAresDeaths();

    /**
     * Gets the kill to death ratio for the player in the game mode "Project Ares".
     */
    double getProjectAresKdRatio();

    /**
     * Gets the kill to kill ratio for the player in the game mode "Project Ares".
     */
    double getProjectAresKkRatio();

    /**
     * Gets the total amount of time the player has spent playing the game mode "Project Ares".
     */
    double getProjectAresDaysPlayed();

    /**
     * Gets the total amount of time the player has spent observing matches in the game mode "Project Ares".
     */
    double getProjectAresDaysObserved();

    /**
     * Gets the total amount of kills the player has accumulated in the game mode "Blitz".
     */
    int getBlitzKills();

    /**
     * Gets the total amount of deaths the player has accumulated in the game mode "Blitz".
     */
    int getBlitzDeaths();

    /**
     * Gets the kill to death ratio for the player in the game mode "Blitz".
     */
    double getBlitzKdRatio();

    /**
     * Gets the total kill to kill ratio for the player in the game mode "Blitz".
     */
    double getBlitzKkRatio();

    /**
     * Gets the total amount of time the player has spent playing the game mode "Blitz".
     */
    double getBlitzDaysPlayed();

    /**
     * Gets the total amount of time the player has spent observing matches in the game mode "Blitz".
     */
    double getBlitzDaysObserved();

    /**
     * Gets the total amount of kills the player has accumulated in the game mode "Ghost Squadron".
     */
    int getGhostSquadronKills();

    /**
     * Gets the total amount of deaths the player has accumulated in the game mode "Ghost Squadron".
     */
    int getGhostSquadronDeaths();

    /**
     * Gets the kill to death ratio for the player in the game mode "Ghost Squadron".
     */
    double getGhostSquadronKdRatio();

    /**
     * Gets the kill to kill ratio for the player in the game mode "Ghost Squadron".
     */
    double getGhostSquadronKkRatio();

    /**
     * Gets the total amount of time the player has spent playing the game mode "Ghost Squadron".
     */
    double getGhostSquadronDaysPlayed();

    /**
     * Gets the total amount of time the player has spent observing matches in the game mode "Ghost Squadron".
     */
    double getGhostSquadronDaysObserved();

    /**
     * Gets the total amount of times the player has connected to the server over the life span of the account.
     */
    int getServerJoins();

    /**
     * Gets the total amount of raindrops the player has accumulated (In-game currency for gizmo's).
     */
    int getRaindrops();

    /**
     * Gets the total amount of times the user has posted on the forums, either in reply to the users own threads or others.
     */
    int getForumPosts();

    /**
     * Gets the amount of forum topics (Individual forum threads) that the player has created.
     */
    int getForumTopics();

    /**
     * Gets the total amount of monuments the user has broke partially or completely over the life span of the account.
     */
    int getMonuments();

    /**
     * Gets the total amount of wools the user has capped over the life span of the account.
     */
    int getWools();

    /**
     * Gets the total amount of cores the user has touched or leaked completely over the life span of the account.
     */
    int getCores();

    /**
     * Gets the gender of the player if specified. If it isn't specified, it will be UNKNOWN.
     */
    Gender getGender();

    /**
     * Gets the location string from the players profile page, will return null if nothing is entered.
     */
    Optional<String> getLocation();

    /**
     * Gets the occupation string from the players profile page, will return null if nothing is entered.
     */
    Optional<String> getOccupation();

    /**
     * Gets the interests string from the players profile page, will return null if nothing is entered.
     */
    Optional<String> getInterests();

    /**
     * Gets the biography string from the players profile page, will return null if nothing is entered.
     */
    Optional<String> getBiography();

    /**
     * Gets the users skype handle if specified, otherwise it will return null.
     */
    Optional<String> getSkypeHandle();

    /**
     * Gets the users steam handle if specified, otherwise it will return null.
     */
    Optional<String> getSteamHandle();

    /**
     * Gets the users twitter handle if specified, otherwise it will return null.
     */
    Optional<String> getTwitterHandle();

    /**
     * Gets the users twitch handle if specified, otherwise it will return null.
     */
    Optional<String> getTwitchHandle();

    /**
     * Gets the users facebook handle if specified, otherwise it will return null.
     */
    Optional<String> getFacebookHandle();

    /**
     * Gets the users github handle if specified, otherwise it will return null.
     */
    Optional<String> getGithubHandle();

    /**
     * Gets the users reddit handle if specified, otherwise it will return null.
     */
    Optional<String> getRedditHandle();
}
