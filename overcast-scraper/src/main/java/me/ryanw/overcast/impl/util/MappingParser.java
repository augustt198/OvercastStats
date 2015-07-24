package me.ryanw.overcast.impl.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.ryanw.overcast.impl.MappingEntry;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MappingParser {

    private final String url;
    private final Document document;

    public MappingParser(Document document, String fileName) throws IOException {
        this.document = document;
        this.url = "https://bitbucket.org/ryanw-se/mappings/raw/e815adf23ea581d9fe75036418310438be0b9b05/OvercastAPI/" + fileName + ".json";
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a byte
     * @return Filtered byte result of a {@link #getEntry(String)}
     */
    public byte getByte(String id) {
        return Byte.parseByte(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a short
     * @return Short result of a {@link #getEntry(String)}
     */
    public short getShort(String id) {
        return Short.parseShort(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a long
     * @return Long result of a {@link #getEntry(String)}
     */
    public long getLong(String id) {
        return Long.parseLong(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a float
     * @return Float result of a {@link #getEntry(String)}
     */
    public float getFloat(String id) {
        return Float.parseFloat(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a double
     * @return Double result of a {@link #getEntry(String)}
     */
    public double getDouble(String id) {
        return Double.parseDouble(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a boolean
     * @return Boolean result of a {@link #getEntry(String)}
     */
    public boolean getBoolean(String id) {
        return Boolean.parseBoolean(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result.
     *
     * @param id The id of the json object to fetch and return as a integer
     * @return Integer result of a {@link #getEntry(String)}
     */
    public int getInteger(String id) {
        return Integer.parseInt(getEntry(id));
    }

    /**
     * Sends a call to {@link #getEntry(String)} and returns the result, alternative call.
     *
     * @param id The id of the json object to fetch and return as a string
     * @return String result of a {@link #getEntry(String)}
     */
    public String getString(String id) {
        return getEntry(id);
    }

    /**
     * Gets a entry from the mappings file, passes the selector through Jsoup and formats the result.
     * @param id The id to look for in the mappings file
     * @return formatted result selected in the html file by Jsoup using the selector
     */
    public String getEntry(String id) {
        JsonArray mappingArray;

        try {
            mappingArray = new JsonParser().parse(new InputStreamReader(new URL(url).openStream())).getAsJsonArray();
        } catch (IOException e) {
            throw new NullPointerException("Cannot fetch the latest mapping file from Github: " + e.getMessage());
        }

        for (JsonElement mapping : mappingArray) {
            MappingEntry mappingsEntry = new Gson().fromJson(mapping, MappingEntry.class);

            // Verifies that the entry ID matches the one we want to get.
            if (mappingsEntry.getId().equals(id)) {

                // Gets the target content inside of the element.
                String payload = document.select(mappingsEntry.getSelector()).get(mappingsEntry.getTarget()).ownText();

                // If it exists get the target from the specified attribute instead.
                if (mappingsEntry.getAttribute() != null) {
                    payload = document.select(mappingsEntry.getSelector()).get(mappingsEntry.getTarget()).attr(mappingsEntry.getAttribute());
                }

                // It there is a regex filter tag, filter the content and set the result to return.
                if (mappingsEntry.getFilter() != null) {
                    List<String> matches = new ArrayList<String>();
                    Pattern regex = Pattern.compile(mappingsEntry.getFilter());
                    Matcher matcher = regex.matcher(payload);

                    while (matcher.find()) {
                        matches.add(matcher.group().trim());
                    }

                    StringBuilder matchBuilder = new StringBuilder(matches.size());
                    for (String match : matches) matchBuilder.append(match);
                    payload = matchBuilder.toString().trim();
                }
                return payload;
            }
        }
        return null;
    }
}