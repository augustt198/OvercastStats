package me.ryanw.overcaststats.impl.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MojangUtil {

    public static String getUsername(String uuid) {
        String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.replace("-", "");
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            return ((TextNode) new ObjectMapper().readTree(jsonParser).get("name")).textValue();
        } catch (IOException ignored) {}
        return null;
    }

    public static String getFormerUsername(UUID uuid) {
        List<FormerUsername> formerUsernames;
        String url = "https://api.mojang.com/user/profiles/" + uuid.toString().replaceAll("-", "") + "/names";
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            formerUsernames = Arrays.asList(new ObjectMapper().readValue(jsonParser, FormerUsername[].class));
            if (formerUsernames.size() >= 2) return formerUsernames.get(formerUsernames.size() - 2).getName();
        } catch (IOException ignored) {}
        return null;
    }

    public static String getFormerUsername(String username) {
        List<FormerUsername> formerUsernames;
        String url = "https://api.mojang.com/user/profiles/" + MojangUtil.getUUID(username, true) + "/names";
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            formerUsernames = Arrays.asList(new ObjectMapper().readValue(jsonParser, FormerUsername[].class));
            if (formerUsernames.size() >= 2) return formerUsernames.get(formerUsernames.size() - 2).getName();
        } catch (IOException ignored) {}
        return null;
    }

    public static List<String> getFormerUsernames(String uuid) {
        List<FormerUsername> formerUsernames;
        String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";
        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            formerUsernames = new ArrayList<FormerUsername>(Arrays.asList(new ObjectMapper().readValue(jsonParser, FormerUsername[].class)));
            formerUsernames.remove(formerUsernames.size() - 1);
            List<String> previousUsernames = new ArrayList<String>();
            for (FormerUsername username : formerUsernames) previousUsernames.add(username.getName());
            return previousUsernames;
        } catch (IOException ignored) {}
        return null;
    }

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

    private static class FormerUsername {
        private String name;
        private String changedToAt;
        public String getName() { return name; }
        public Date getChangedToAt() { return new Date(changedToAt); }
    }
}
