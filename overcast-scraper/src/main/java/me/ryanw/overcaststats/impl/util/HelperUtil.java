package me.ryanw.overcaststats.impl.util;

import me.ryanw.overcaststats.api.OvercastFriend;
import me.ryanw.overcaststats.api.util.Gender;
import me.ryanw.overcaststats.impl.mapping.MappingEntry;
import me.ryanw.overcaststats.impl.mapping.MappingEnum;
import me.ryanw.overcaststats.impl.object.ParsedFriend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperUtil {

    public static List<OvercastFriend> buildFriendObjects(List<String> friends) {
        List<OvercastFriend> friendsList = new ArrayList<OvercastFriend>();
        for (String friend : friends) {
            friendsList.add(new ParsedFriend(friend));
        }
        return friendsList;
    }

    public static Gender determineGender(String gender) {
        if (gender == null) return Gender.UNKNOWN;
        if (gender.equalsIgnoreCase(Gender.MALE.name())) return Gender.MALE;
        if (gender.equalsIgnoreCase(Gender.FEMALE.name())) return Gender.FEMALE;
        return Gender.UNKNOWN;
    }

    public static MappingEnum getEnumById(String id) {
        for (MappingEnum mappingEnum : MappingEnum.values()) {
            if (mappingEnum.getId().equalsIgnoreCase(id)) return mappingEnum;
        }
        return null;
    }

    public static String runRegex(MappingEntry entry, String resultToFilter) {
        List<String> matches = new ArrayList<String>();
        Pattern regularExp = Pattern.compile(entry.getFilter());
        Matcher matcher = regularExp.matcher(resultToFilter);

        while (matcher.find()) {
            matches.add(matcher.group().trim());
        }

        StringBuilder stringBuilder = new StringBuilder(matches.size());
        for (String match : matches) stringBuilder.append(match);
        return stringBuilder.toString().trim();
    }

    public static String runRegex(MappingEntry.Cases entryCase, String resultToFilter) {
        List<String> matches = new ArrayList<String>();
        Pattern regularExp = Pattern.compile(entryCase.getFilter());
        Matcher matcher = regularExp.matcher(resultToFilter);

        while (matcher.find()) {
            matches.add(matcher.group().trim());
        }

        StringBuilder stringBuilder = new StringBuilder(matches.size());
        for (String match : matches) stringBuilder.append(match);
        return stringBuilder.toString().trim();
    }
}
