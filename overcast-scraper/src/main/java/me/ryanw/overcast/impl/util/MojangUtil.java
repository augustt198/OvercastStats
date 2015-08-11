package me.ryanw.overcast.impl.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MojangUtil {


    /**
     * Fetches the username of a user based on the UUID provided. The UUID can be full or trimmed.
     * @param uuid Trimmed or full UUID of the user whose username we want to fetch.
     * @return The current username of the UUID provided.
     */
    public static String getUsername(String uuid) {
        String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.replace("-", "");
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            return ((TextNode) new ObjectMapper().readTree(jsonParser).get("name")).textValue();
        } catch (IOException ignored) {}
        return null;
    }

    /**
     * Gets the previous username (former) the user has used.
     * @param uuid The UUID of the user we want to get the previous username for.
     * @return Previous former username that the player used.
     */
    public static FormerUsername getFormerUsername(UUID uuid) {
        List<FormerUsername> formerUsernames;
        String url = "https://api.mojang.com/user/profiles/" + uuid.toString().replaceAll("-", "") + "/names";
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            formerUsernames = Arrays.asList(new ObjectMapper().readValue(jsonParser, FormerUsername[].class));
            if (formerUsernames.size() >= 2) return formerUsernames.get(formerUsernames.size() - 2);
        } catch (IOException ignored) {}
        return null;
    }

    /**
     * Gets the previous username (former) the player has used.
     * @param username The username of the user we want to get the previous username for.
     * @return Previous former username that the player used.
     */
    public static FormerUsername getFormerUsername(String username) {
        List<FormerUsername> formerUsernames;
        String url = "https://api.mojang.com/user/profiles/" + MojangUtil.getUUID(username, true) + "/names";
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            formerUsernames = Arrays.asList(new ObjectMapper().readValue(jsonParser, FormerUsername[].class));
            if (formerUsernames.size() >= 2) return formerUsernames.get(formerUsernames.size() - 2);
        } catch (IOException ignored) {}
        return null;
    }


    /**
     * Fetches a list of former usernames, in order from oldest to newest.
     * @param uuid The UUID of the user we want to get username history for.
     * @return List of former usernames in order from oldest to newest.
     */
    public static List<FormerUsername> getFormerUsernames(String uuid) {
        List<FormerUsername> formerUsernames;
        String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            formerUsernames = new ArrayList<FormerUsername>(Arrays.asList(new ObjectMapper().readValue(jsonParser, FormerUsername[].class)));
            formerUsernames.remove(formerUsernames.size() - 1);
            return formerUsernames;
        } catch (IOException ignored) {}
        return null;
    }

    /**
     * Fetches the UUID of a player by using their current username to look the UUID up.
     * @param currentUsername The username to return the uuid of.
     * @return The uuid owner of the username provided.
     */
    public static String getUUID(String currentUsername, boolean trimmed) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + currentUsername;
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            String uuid = ((TextNode) new ObjectMapper().readTree(jsonParser).get("id")).textValue();
            if (!trimmed) return new StringBuilder(uuid).insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-").toString();
            return uuid;
        } catch (IOException ignored) {}
        return null;
    }

    /**
     * Represents one former username, in a possible array of previous former usernames.
     */
    public static class FormerUsername {
        private String name;
        private String changedToAt;
        public String getName() { return name; }
        public Date getChangedToAt() { return new Date(changedToAt); }
    }
}
