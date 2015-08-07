package me.ryanw.overcast.impl.util;

import me.ryanw.overcast.api.OvercastFriend;
import me.ryanw.overcast.api.util.Gender;
import me.ryanw.overcast.impl.mapping.MappingEntry;
import me.ryanw.overcast.impl.mapping.MappingEnum;
import me.ryanw.overcast.impl.object.ParsedFriend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperUtil {

    /**
     * Takes a list of friend usernames and compiles them into a list of friend objects.
     * @param friends List of friends to compile into friend objects.
     * @return Compiled list of friend objects.
     */
    public static List<OvercastFriend> buildFriendObjects(List<String> friends) {
        List<OvercastFriend> friendsList = new ArrayList<OvercastFriend>();
        for (String friend : friends) {
            friendsList.add(new ParsedFriend(friend));
        }
        return friendsList;
    }

    /**
     * Determines whether or not to return the male or female enum by reading the string gender response.
     * @param gender The string representation of the gender we want to return.
     * @return Gender enum to the coresponding string representation of the gender.
     */
    public static Gender determineGender(String gender) {
        if (gender == null) return Gender.UNKNOWN;
        if (gender.equalsIgnoreCase(Gender.MALE.name())) return Gender.MALE;
        if (gender.equalsIgnoreCase(Gender.FEMALE.name())) return Gender.FEMALE;
        return Gender.UNKNOWN;
    }

    /**
     * Iterates through a list of MappingEnums and finds one with the same id as the one we want and returns the enum.
     * @param id The entry id of the enum we are looking for.
     * @return MappingEnum object of the id specified.
     */
    public static MappingEnum getEnumById(String id) {
        for (MappingEnum mappingEnum : MappingEnum.values()) {
            if (mappingEnum.name().equals(id)) return mappingEnum;
        }
        return null;
    }

    /**
     * Applies a regex statement to text and compiles the result as a string.
     * @param entry The mapping entry to get the regex statement to be applied from.
     * @param resultToFilter The result we want to apply the regex statement to
     * @return filtered result that we applied the regex statement to.
     */
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
}
