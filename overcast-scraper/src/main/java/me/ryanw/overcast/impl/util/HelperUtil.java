package me.ryanw.overcast.impl.util;

import me.ryanw.overcast.api.OvercastFriend;
import me.ryanw.overcast.impl.object.ParsedFriend;

import java.util.ArrayList;
import java.util.List;

public class HelperUtil {

    public static List<OvercastFriend> buildFriendObjects(List<String> friends) {
        List<OvercastFriend> friendsList = new ArrayList<OvercastFriend>();
        for (String friend : friends) {
            friendsList.add(new ParsedFriend(friend));
        }
        return friendsList;
    }
}
