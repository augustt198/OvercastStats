package me.ryanw.overcaststats.impl.object;
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
import me.ryanw.overcaststats.api.Gender;
import me.ryanw.overcaststats.api.OvercastFriend;
import me.ryanw.overcaststats.api.OvercastPlayer;
import me.ryanw.overcaststats.impl.OvercastStats;
import me.ryanw.overcaststats.impl.mapping.DataType;
import me.ryanw.overcaststats.impl.mapping.MappingParser;
import me.ryanw.overcaststats.impl.util.MojangUtil;
import me.ryanw.overcaststats.impl.util.StringUtil;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class ParsedPlayer implements OvercastPlayer {
    private GlobalStats globalStats;
    private ProjectAresStats projectAresStats;
    private BlitzStats blitzStats;
    private GhostSquadronStats ghostSquadronStats;
    private PersonalInfo personalInfo;
    private PersonalLinks personalLinks;
    private ObjectiveStats objectiveStats;

    public ParsedPlayer(OvercastStats overcastStats, Document doc) throws IOException {
        MappingParser parser = new MappingParser(doc, "player");
        Map<DataType, String> playerDetailsMap = parser.getMap(DataType.PROFILE_INFO);
        Map<DataType, String> playerStatsDetailedMap = parser.getMap(DataType.STATS_PAGE);
        Map<DataType, String> playerStatsBasicMap = parser.getMap(DataType.STATS);
        Map<DataType, String> playerObjectivesMap = parser.getMap(DataType.OBJECTIVES);
        Map<DataType, String> personalLinksMap = parser.getMap(DataType.PROFILE_LINKS);

        globalStats = new GlobalStats();
        projectAresStats = new ProjectAresStats();
        blitzStats = new BlitzStats();
        ghostSquadronStats = new GhostSquadronStats();
        personalInfo = new PersonalInfo();
        personalLinks = new PersonalLinks();
        objectiveStats = new ObjectiveStats();
        
        setUsername(parser.getString(DataType.USERNAME));
        setFormerUsername(MojangUtil.getFormerUsername(username));
        setFriends(StringUtil.buildFriendObjects(overcastStats, parser.getList(DataType.FRIENDS)));

        globalStats.setGlobalKills(StringUtil.parseInt(playerStatsBasicMap.get(DataType.GLOBAL_KILLS)));
        globalStats.setGlobalDeaths(StringUtil.parseInt(playerStatsBasicMap.get(DataType.GLOBAL_DEATHS)));
        globalStats.setFriendsCount(StringUtil.parseInt(playerStatsBasicMap.get(DataType.FRIENDS_COUNT)));

        globalStats.setAccountAge(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.ACCOUNT_AGE)));
        globalStats.setHoursPlayed(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.HOURS_PLAYED)));
        globalStats.setTeamJoins(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.TEAM_JOINS)));
        globalStats.setRaindrops(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.RAINDROPS)));
        globalStats.setForumPosts(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.FORUM_POSTS)));
        globalStats.setForumTopics(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.FORUM_TOPICS)));
        
        projectAresStats.setProjectAresKills(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.PROJECT_ARES_KILLS)));
        projectAresStats.setProjectAresDeaths(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.PROJECT_ARES_DEATHS)));
        projectAresStats.setProjectAresKdRatio(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.PROJECT_ARES_KD_RATIO)));
        projectAresStats.setProjectAresKkRatio(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.PROJECT_ARES_KK_RATIO)));
        projectAresStats.setProjectAresDaysObserved(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.PROJECT_ARES_DAYS_OBSERVED)));
        projectAresStats.setProjectAresDaysPlayed(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.PROJECT_ARES_DAYS_PLAYED)));
        
        blitzStats.setBlitzKills(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.BLITZ_KILLS)));
        blitzStats.setBlitzDeaths(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.BLITZ_DEATHS)));
        blitzStats.setBlitzKdRatio(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.BLITZ_KD_RATIO)));
        blitzStats.setBlitzKkRatio(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.BLITZ_KK_RATIO)));
        blitzStats.setBlitzDaysPlayed(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.BLITZ_DAYS_PLAYED)));
        blitzStats.setBlitzDaysObserved(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.BLITZ_DAYS_OBSERVED)));
        
        ghostSquadronStats.setGhostSquadronKills(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.GHOST_SQUADRON_KILLS)));
        ghostSquadronStats.setGhostSquadronDeaths(StringUtil.parseInt(playerStatsDetailedMap.get(DataType.GHOST_SQUADRON_DEATHS)));
        ghostSquadronStats.setGhostSquadronKdRatio(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.GHOST_SQUADRON_KD_RATIO)));
        ghostSquadronStats.setGhostSquadronKkRatio(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.GHOST_SQUADRON_KK_RATIO)));
        ghostSquadronStats.setGhostSquadronDaysPlayed(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.GHOST_SQUADRON_DAYS_PLAYED)));
        ghostSquadronStats.setGhostSquadronDaysObserved(StringUtil.parseDouble(playerStatsDetailedMap.get(DataType.GHOST_SQUADRON_DAYS_OBSERVED)));

        globalStats.setGlobalDaysObserved(Double.valueOf(new DecimalFormat("#.##").format(calculateTotalObservedTime())));
        globalStats.setGlobalDaysPlayed(Double.valueOf(new DecimalFormat("#.##").format(calculateTotalPlayedTime())));
        globalStats.setGlobalKdRatio(Double.valueOf(new DecimalFormat("#.##").format(calculateGlobalKdRatio())));
        globalStats.setGlobalKkRatio(Double.valueOf(new DecimalFormat("#.##").format(calculateGlobalKkRatio())));
        
        personalInfo.setGender(Gender.determineGender(playerDetailsMap.get(DataType.GENDER)));
        personalInfo.setLocation(playerDetailsMap.get(DataType.LOCATION));
        personalInfo.setOccupation(playerDetailsMap.get(DataType.OCCUPATION));
        personalInfo.setInterests(playerDetailsMap.get(DataType.INTERESTS));
        personalInfo.setBiography(parser.getString(DataType.BIOGRAPHY));

        objectiveStats.setMonuments(StringUtil.parseInt(playerObjectivesMap.get(DataType.MONUMENTS)));
        objectiveStats.setWools(StringUtil.parseInt(playerObjectivesMap.get(DataType.WOOLS)));
        objectiveStats.setCores(StringUtil.parseInt(playerObjectivesMap.get(DataType.CORES)));

        personalLinks.setSkypeHandle(personalLinksMap.get(DataType.SKYPE_HANDLE));
        personalLinks.setSteamHandle(personalLinksMap.get(DataType.STEAM_HANDLE));
        personalLinks.setTwitterHandle(personalLinksMap.get(DataType.TWITTER_HANDLE));
        personalLinks.setTwitchHandle(personalLinksMap.get(DataType.TWITCH_HANDLE));
        personalLinks.setFacebookHandle(personalLinksMap.get(DataType.FACEBOOK_HANDLE));
        personalLinks.setGithubHandle(personalLinksMap.get(DataType.GITHUB_HANDLE));
        personalLinks.setRedditHandle(personalLinksMap.get(DataType.REDDIT_HANDLE));
    }

    private String username;
    private String formerUsername;
    private List<OvercastFriend> friends;

    private void setUsername(String username) {
        this.username = username;
    }

    private void setFormerUsername(String formerUsername) {
        this.formerUsername = formerUsername;
    }

    private void setFriends(List<OvercastFriend> friends) {
        this.friends = friends;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFormerUsername() {
        return formerUsername;
    }

    @Override
    public List<OvercastFriend> getFriends() {
        return friends;
    }

    @Override
    public GlobalStats getGlobalStats() {
        return globalStats;
    }

    @Override
    public ProjectAresStats getProjectAresStats() {
        return projectAresStats;
    }

    @Override
    public BlitzStats getBlitzStats() {
        return blitzStats;
    }

    @Override
    public GhostSquadronStats getGhostSquadronStats() {
        return ghostSquadronStats;
    }

    @Override
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    @Override
    public PersonalLinks getPersonalLinks() {
        return personalLinks;
    }

    @Override
    public ObjectiveStats getObjectiveStats() {
        return objectiveStats;
    }

    private class GlobalStats implements OvercastPlayer.GlobalStats {
        private int globalKills;
        private int globalDeaths;
        private double globalKdRatio;
        private double globalKkRatio;
        private double globalDaysPlayed;
        private double globalDaysObserved;
        private double raindrops;
        private int forumPosts;
        private int forumTopics;
        private int friendsCount;
        private int accountAge;
        private int hoursPlayed;
        private int teamJoins;

        private void setGlobalKills(int globalKills) {
            this.globalKills = globalKills;
        }

        private void setGlobalDeaths(int globalDeaths) {
            this.globalDeaths = globalDeaths;
        }

        private void setGlobalKdRatio(double globalKdRatio) {
            this.globalKdRatio = globalKdRatio;
        }

        private void setGlobalKkRatio(double globalKkRatio) {
            this.globalKkRatio = globalKkRatio;
        }

        private void setGlobalDaysPlayed(double globalDaysPlayed) {
            this.globalDaysPlayed = globalDaysPlayed;
        }

        private void setGlobalDaysObserved(double globalDaysObserved) {
            this.globalDaysObserved = globalDaysObserved;
        }

        private void setRaindrops(double raindrops) {
            this.raindrops = raindrops;
        }

        private void setForumPosts(int forumPosts) {
            this.forumPosts = forumPosts;
        }

        private void setForumTopics(int forumTopics) {
            this.forumTopics = forumTopics;
        }

        private void setAccountAge(int accountAge) {
            this.accountAge = accountAge;
        }

        private void setHoursPlayed(int hoursPlayed) {
            this.hoursPlayed = hoursPlayed;
        }

        private void setTeamJoins(int teamJoins) {
            this.teamJoins = teamJoins;
        }

        private void setFriendsCount(int friendsCount) { this.friendsCount = friendsCount; }

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
        public double getRaindrops() {
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
        public int getFriendsCount() {
            return friendsCount;
        }

        @Override
        public int getAccountAge() {
            return accountAge;
        }

        @Override
        public int getHoursPlayed() {
            return hoursPlayed;
        }

        @Override
        public int getTeamJoins() {
            return teamJoins;
        }

        @Override
        public String toString() {
            return "GlobalStats{" +
                    "globalKills=" + globalKills +
                    ", globalDeaths=" + globalDeaths +
                    ", globalKdRatio=" + globalKdRatio +
                    ", globalKkRatio=" + globalKkRatio +
                    ", globalDaysPlayed=" + globalDaysPlayed +
                    ", globalDaysObserved=" + globalDaysObserved +
                    ", raindrops=" + raindrops +
                    ", forumPosts=" + forumPosts +
                    ", forumTopics=" + forumTopics +
                    ", friendsCount=" + friendsCount +
                    ", accountAge=" + accountAge +
                    ", hoursPlayed=" + hoursPlayed +
                    ", teamJoins=" + teamJoins +
                    '}';
        }
    }

    private class ProjectAresStats implements OvercastPlayer.ProjectAresStats {
        private int projectAresKills;
        private int projectAresDeaths;
        private double projectAresKdRatio;
        private double projectAresKkRatio;
        private double projectAresDaysPlayed;
        private double projectAresDaysObserved;

        private void setProjectAresKills(int projectAresKills) {
            this.projectAresKills = projectAresKills;
        }

        private void setProjectAresDeaths(int projectAresDeaths) {
            this.projectAresDeaths = projectAresDeaths;
        }

        private void setProjectAresKdRatio(double projectAresKdRatio) {
            this.projectAresKdRatio = projectAresKdRatio;
        }

        private void setProjectAresKkRatio(double projectAresKkRatio) {
            this.projectAresKkRatio = projectAresKkRatio;
        }

        private void setProjectAresDaysPlayed(double projectAresDaysPlayed) {
            this.projectAresDaysPlayed = projectAresDaysPlayed;
        }

        private void setProjectAresDaysObserved(double projectAresDaysObserved) {
            this.projectAresDaysObserved = projectAresDaysObserved;
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
        public String toString() {
            return "ProjectAresStats{" +
                    "projectAresKills=" + projectAresKills +
                    ", projectAresDeaths=" + projectAresDeaths +
                    ", projectAresKdRatio=" + projectAresKdRatio +
                    ", projectAresKkRatio=" + projectAresKkRatio +
                    ", projectAresDaysPlayed=" + projectAresDaysPlayed +
                    ", projectAresDaysObserved=" + projectAresDaysObserved +
                    '}';
        }
    }

    private class BlitzStats implements OvercastPlayer.BlitzStats {
        private int blitzKills;
        private int blitzDeaths;
        private double blitzKdRatio;
        private double blitzKkRatio;
        private double blitzDaysPlayed;
        private double blitzDaysObserved;

        private void setBlitzKills(int blitzKills) {
            this.blitzKills = blitzKills;
        }

        private void setBlitzDeaths(int blitzDeaths) {
            this.blitzDeaths = blitzDeaths;
        }

        private void setBlitzKdRatio(double blitzKdRatio) {
            this.blitzKdRatio = blitzKdRatio;
        }

        private void setBlitzKkRatio(double blitzKkRatio) {
            this.blitzKkRatio = blitzKkRatio;
        }

        private void setBlitzDaysPlayed(double blitzDaysPlayed) {
            this.blitzDaysPlayed = blitzDaysPlayed;
        }

        private void setBlitzDaysObserved(double blitzDaysObserved) {
            this.blitzDaysObserved = blitzDaysObserved;
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
        public String toString() {
            return "BlitzStats{" +
                    "blitzKills=" + blitzKills +
                    ", blitzDeaths=" + blitzDeaths +
                    ", blitzKdRatio=" + blitzKdRatio +
                    ", blitzKkRatio=" + blitzKkRatio +
                    ", blitzDaysPlayed=" + blitzDaysPlayed +
                    ", blitzDaysObserved=" + blitzDaysObserved +
                    '}';
        }
    }

    private class GhostSquadronStats implements OvercastPlayer.GhostSquadronStats {
        private int ghostSquadronKills;
        private int ghostSquadronDeaths;
        private double ghostSquadronKdRatio;
        private double ghostSquadronKkRatio;
        private double ghostSquadronDaysPlayed;
        private double ghostSquadronDaysObserved;

        private void setGhostSquadronKills(int ghostSquadronKills) {
            this.ghostSquadronKills = ghostSquadronKills;
        }

        private void setGhostSquadronDeaths(int ghostSquadronDeaths) {
            this.ghostSquadronDeaths = ghostSquadronDeaths;
        }

        private void setGhostSquadronKdRatio(double ghostSquadronKdRatio) {
            this.ghostSquadronKdRatio = ghostSquadronKdRatio;
        }

        private void setGhostSquadronKkRatio(double ghostSquadronKkRatio) {
            this.ghostSquadronKkRatio = ghostSquadronKkRatio;
        }

        private void setGhostSquadronDaysPlayed(double ghostSquadronDaysPlayed) {
            this.ghostSquadronDaysPlayed = ghostSquadronDaysPlayed;
        }

        private void setGhostSquadronDaysObserved(double ghostSquadronDaysObserved) {
            this.ghostSquadronDaysObserved = ghostSquadronDaysObserved;
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
        public String toString() {
            return "GhostSquadronStats{" +
                    "ghostSquadronKills=" + ghostSquadronKills +
                    ", ghostSquadronDeaths=" + ghostSquadronDeaths +
                    ", ghostSquadronKdRatio=" + ghostSquadronKdRatio +
                    ", ghostSquadronKkRatio=" + ghostSquadronKkRatio +
                    ", ghostSquadronDaysPlayed=" + ghostSquadronDaysPlayed +
                    ", ghostSquadronDaysObserved=" + ghostSquadronDaysObserved +
                    '}';
        }
    }

    private class ObjectiveStats implements OvercastPlayer.ObjectiveStats {
        private int monuments;
        private int wools;
        private int cores;

        private void setMonuments(int monuments) {
            this.monuments = monuments;
        }

        private void setWools(int wools) {
            this.wools = wools;
        }

        private void setCores(int cores) {
            this.cores = cores;
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
        public String toString() {
            return "ObjectiveStats{" +
                    "monuments=" + monuments +
                    ", wools=" + wools +
                    ", cores=" + cores +
                    '}';
        }
    }

    private class PersonalInfo implements OvercastPlayer.PersonalInfo {
        private Gender gender;
        private String location;
        private String occupation;
        private String interests;
        private String biography;

        private void setGender(Gender gender) {
            this.gender = gender;
        }

        private void setLocation(String location) {
            this.location = location;
        }

        private void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        private void setInterests(String interests) {
            this.interests = interests;
        }

        private void setBiography(String biography) {
            this.biography = biography;
        }

        @Override
        public Gender getGender() {
            return gender;
        }

        @Override
        public String getLocation() {
            return location;
        }

        @Override
        public String getOccupation() {
            return occupation;
        }

        @Override
        public String getInterests() {
            return interests;
        }

        @Override
        public String getBiography() {
            return biography;
        }

        @Override
        public String toString() {
            return "PersonalInfo{" +
                    "gender=" + gender +
                    ", location='" + location + '\'' +
                    ", occupation='" + occupation + '\'' +
                    ", interests='" + interests + '\'' +
                    ", biography='" + biography + '\'' +
                    '}';
        }
    }

    private class PersonalLinks implements OvercastPlayer.PersonalLinks {
        private String skypeHandle;
        private String steamHandle;
        private String twitterHandle;
        private String twitchHandle;
        private String facebookHandle;
        private String githubHandle;
        private String redditHandle;

        private void setSkypeHandle(String skypeHandle) {
            this.skypeHandle = skypeHandle;
        }

        private void setSteamHandle(String steamHandle) {
            this.steamHandle = steamHandle;
        }

        private void setTwitterHandle(String twitterHandle) {
            this.twitterHandle = twitterHandle;
        }

        private void setTwitchHandle(String twitchHandle) {
            this.twitchHandle = twitchHandle;
        }

        private void setFacebookHandle(String facebookHandle) {
            this.facebookHandle = facebookHandle;
        }

        private void setGithubHandle(String githubHandle) {
            this.githubHandle = githubHandle;
        }

        private void setRedditHandle(String redditHandle) {
            this.redditHandle = redditHandle;
        }

        @Override
        public String getSkypeHandle() {
            return skypeHandle;
        }
        @Override
        public String getSteamHandle() {
            return steamHandle;
        }

        @Override
        public String getTwitterHandle() {
            return twitterHandle;
        }

        @Override public String getTwitchHandle() {
            return twitchHandle;
        }

        @Override public String getFacebookHandle() {
            return facebookHandle;
        }

        @Override public String getGithubHandle() {
            return githubHandle;
        }

        @Override public String getRedditHandle() {
            return redditHandle;
        }

        @Override
        public String toString() {
            return "PersonalLinks{" +
                    "skypeHandle='" + skypeHandle + '\'' +
                    ", steamHandle='" + steamHandle + '\'' +
                    ", twitterHandle='" + twitterHandle + '\'' +
                    ", twitchHandle='" + twitchHandle + '\'' +
                    ", facebookHandle='" + facebookHandle + '\'' +
                    ", githubHandle='" + githubHandle + '\'' +
                    ", redditHandle='" + redditHandle + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ParsedPlayer{" +
                "username='" + username + '\'' +
                ", formerUsername='" + formerUsername + '\'' +
                ", friends=" + friends.size() +
                ", globalStats=" + globalStats +
                ", projectAresStats=" + projectAresStats +
                ", blitzStats=" + blitzStats +
                ", ghostSquadronStats=" + ghostSquadronStats +
                ", objectiveStats=" + objectiveStats +
                ", personalInfo=" + personalInfo +
                ", personalLinks=" + personalLinks +
                '}';
    }

    private double calculateTotalObservedTime() {
        return projectAresStats.getProjectAresDaysObserved() + ghostSquadronStats.getGhostSquadronDaysObserved() + blitzStats.getBlitzDaysObserved();
    }

    private double calculateTotalPlayedTime() {
        return projectAresStats.getProjectAresDaysPlayed() + ghostSquadronStats.getGhostSquadronDaysPlayed() + blitzStats.getBlitzDaysPlayed();
    }

    private double calculateGlobalKdRatio() {
        double totalKills = projectAresStats.getProjectAresKills() + ghostSquadronStats.getGhostSquadronKills() + blitzStats.getBlitzKills();
        double totalDeaths = projectAresStats.getProjectAresDeaths() + ghostSquadronStats.getGhostSquadronDeaths() + blitzStats.getBlitzDeaths();
        return totalKills / totalDeaths;
    }

    private double calculateGlobalKkRatio() {
        int blitzKilled = (int) (blitzStats.getBlitzKkRatio() * blitzStats.getBlitzKills());
        int ghostKilled = (int) (ghostSquadronStats.getGhostSquadronKkRatio() * ghostSquadronStats.getGhostSquadronKills());
        int paresKilled = (int) (projectAresStats.getProjectAresKkRatio() * projectAresStats.getProjectAresKills());

        double killed = blitzKilled + ghostKilled + paresKilled;
        double kills = blitzStats.getBlitzKills() + ghostSquadronStats.getGhostSquadronKills() + projectAresStats.getProjectAresKills();
        return kills / killed;
    }

}
