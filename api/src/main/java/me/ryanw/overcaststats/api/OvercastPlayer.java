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
import java.util.List;

public interface OvercastPlayer {
    /**
     * Gets the username of the player
     */
    String getUsername();

    /**
     * Gets the previous username of the player
     */
    String getFormerUsername();

    /**
     * Gets the first 16 friends of the player (limited to 16 due to not having authentication)
     */
    List<OvercastFriend> getFriends();

    /**
     * Gets the global statistics of the player.
     */
    GlobalStats getGlobalStats();

    /**
     * Gets the project ares specific statistics of the player.
     */
    ProjectAresStats getProjectAresStats();

    /**
     * Gets the blitz specific statistics of the player.
     */
    BlitzStats getBlitzStats();

    /**
     * Gets the ghost squadron specific statistics of the player.
     */
    GhostSquadronStats getGhostSquadronStats();

    /**
     * Gets the personal details about the player.
     */
    PersonalInfo getPersonalInfo();

    /**
     * Gets the personal links about the player.
     */
    PersonalLinks getPersonalLinks();

    /**
     * Gets the objectives specific statistics of the player.
     */
    ObjectiveStats getObjectiveStats();

    interface GlobalStats {
        /**
         * Gets the total amount of kills the player has accumulated across all game modes
         */
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
        double getGlobalDaysObserved();

        /**
         * Gets the total amount of time the player has spent observing matches across all game modes.
         */
        double getGlobalDaysPlayed();

        /**
         * Gets the total amount of raindrops the player has accumulated (In-game currency for gizmo's).
         */
        double getRaindrops();

        /**
         * Gets the total amount of times the user has posted on the forums, either in reply to the users own threads or others.
         */
        int getForumPosts();

        /**
         * Gets the amount of forum topics (Individual forum threads) that the player has created.
         */
        int getForumTopics();

        /**
         * Gets the total amount of friends have you.
         */
        int getFriendsCount();

        /**
         * Gets the age of your account in days, since you first connected.
         */
        int getAccountAge();

        /**
         * Gets the total amount of hours you've been on the network since you first connected.
         */
        int getHoursPlayed();

        /**
         * Gets the total amount of team joins since you first connected.
         */
        int getTeamJoins();
    }

    interface ProjectAresStats {
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
    }

    interface BlitzStats {
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
    }

    interface GhostSquadronStats {
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
    }

    interface ObjectiveStats {
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
    }

    interface PersonalInfo {
        /**
         * Gets the gender of the player if specified. If it isn't specified, it will be UNKNOWN.
         */
        Gender getGender();
        /**
         * Gets the location string from the players profile page, will return null if nothing is entered.
         */
        String getLocation();
        /**
         * Gets the occupation string from the players profile page, will return null if nothing is entered.
         */
        String getOccupation();
        /**
         * Gets the interests string from the players profile page, will return null if nothing is entered.
         */
        String getInterests();
        /**
         * Gets the biography string from the players profile page, will return null if nothing is entered.
         */
        String getBiography();
    }

    interface PersonalLinks {
        /**
         * Gets the users skype handle if specified, otherwise it will return null.
         */
        String getSkypeHandle();
        /**
         * Gets the users steam handle if specified, otherwise it will return null.
         */
        String getSteamHandle();
        /**
         * Gets the users twitter handle if specified, otherwise it will return null.
         */
        String getTwitterHandle();
        /**
         * Gets the users twitch handle if specified, otherwise it will return null.
         */
        String getTwitchHandle();
        /**
         * Gets the users facebook handle if specified, otherwise it will return null.
         */
        String getFacebookHandle();
        /**
         * Gets the users github handle if specified, otherwise it will return null.
         */
        String getGithubHandle();
        /**
         * Gets the users reddit handle if specified, otherwise it will return null.
         */
        String getRedditHandle();
    }
}
