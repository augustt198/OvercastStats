package me.ryanw.overcast.impl.util;

import me.ryanw.overcast.api.OvercastFriend;
import me.ryanw.overcast.api.util.Gender;
import me.ryanw.overcast.impl.mapping.MappingEnum;
import me.ryanw.overcast.impl.object.ParsedFriend;

import java.util.ArrayList;
import java.util.List;

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
        String upperCaseGender = gender.toUpperCase();
        if (upperCaseGender.equals(Gender.MALE.name())) return Gender.MALE;
        if (upperCaseGender.equals(Gender.FEMALE.name())) return Gender.FEMALE;
        return Gender.UNKNOWN;
    }

    /**
     * Iterates through a list of MappingEnums and finds one with the same id as the one we want and returns the enum.
     * @param id The entry id of the enum we are looking for.
     * @return MappingEnum object of the id specified.
     */
    public static MappingEnum getEnumById(String id) {
        for (MappingEnum mappingEnum : MappingEnum.values()) {
            if (mappingEnum.getEntryName().equals(id)) return mappingEnum;
        }
        return null;
    }
}
