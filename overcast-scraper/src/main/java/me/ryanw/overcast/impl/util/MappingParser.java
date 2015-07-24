package me.ryanw.overcast.impl.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.ryanw.overcast.impl.object.MappingEntry;
import org.jsoup.nodes.Document;

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
    private final JsonArray mappingArray;

    public MappingParser(Document document, String fileName) throws IOException {
        this.document = document;
        this.url = "https://bitbucket.org/ryanw-se/mappings/raw/master/OvercastAPI/" + fileName + ".json";

        try {
            mappingArray = new JsonParser().parse(new InputStreamReader(new URL(url).openStream())).getAsJsonArray();
        } catch (IOException e) {
            throw new NullPointerException("Cannot fetch the latest mapping file from Github: " + e.getMessage());
        }
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result.
     * @param id The id of the mapping entry we want to read from.
     * @return Filtered byte result of a {@link #getContent(String)}
     */
    public byte getByte(String id) {
        return Byte.parseByte(getContent(id));
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result.
     * @param id The id of the mapping entry we want to read from.
     * @return Short result of a {@link #getContent(String)}
     */
    public short getShort(String id) {
        return Short.parseShort(getContent(id));
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result.
     * @param id The id of the mapping entry we want to read from.
     * @return Long result of a {@link #getContent(String)}
     */
    public long getLong(String id) {
        return Long.parseLong(getContent(id));
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result.
     * @param id The id of the mapping entry we want to read from.
     * @return Float result of a {@link #getContent(String)}
     */
    public float getFloat(String id) {
        return Float.parseFloat(getContent(id));
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result.
     * @param id The id of the mapping entry we want to read from.
     * @return Double result of a {@link #getContent(String)}
     */
    public double getDouble(String id) {
        return Double.parseDouble(getContent(id));
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result.
     * @param id The id of the mapping entry we want to read from.
     * @return Boolean result of a {@link #getContent(String)}
     */
    public boolean getBoolean(String id) {
        return Boolean.parseBoolean(getContent(id));
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result.
     * @param id The id of the mapping entry we want to read from.
     * @return Integer result of a {@link #getContent(String)}
     */
    public int getInteger(String id) {
        return Integer.parseInt(getContent(id));
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result, alternative call.
     * @param id The id of the mapping entry we want to read from.
     * @return String result of a {@link #getContent(String)}
     */
    public String getString(String id) {
        return getContent(id);
    }

    /**
     * Sends a call to {@link #getContent(String)} and returns the result, alternative call.
     * @param id The id of the mapping entry we want to read from.
     * @return String list result of a {@link #getContentList(String)}
     */
    public List<String> getStringList(String id) {
        return getContentList(id);
    }

    /**
     * Gets an entry from the mapping file, passes it through Jsoup and returns the result as a String.
     * @param id The id of the mapping entry we want to read from.
     * @return Formatted result compiled by Jsoup using the selector tag.
     */
    public String getContent(String id) {
        for (JsonElement mapping : mappingArray) {
            MappingEntry mappingsEntry = new Gson().fromJson(mapping, MappingEntry.class);

            // Verifies that the entry ID matches the one we want to get.
            if (mappingsEntry.getId().equals(id)) {

                // Make sure that people aren't using getContent() to get list results
                if (mappingsEntry.getTarget() == null) return null;

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

    /**
     * Gets an entry from the mapping file, passes it through Jsoup and iterates through all of the results and creates
     * a list of results that will be returned at the end of iteration.
     * @param id The id of the mapping entry we want to read from.
     * @return Formatted list of results compiled by Jsoup using the selector tag.
     */
    public List<String> getContentList(String id) {
        for (JsonElement mapping : mappingArray) {
            MappingEntry mappingEntry = new Gson().fromJson(mapping, MappingEntry.class);
            List<String> resultList = new ArrayList<String>();

            // Verifies that the entry ID matches the one we want to get.
            if (mappingEntry.getId().equals(id)) {

                // Verifies that the requested entry is actually a list one.
                if (mappingEntry.getTarget() != null) return null;
                int listSize = document.select(mappingEntry.getSelector()).size();

                for (int i = 0; i < listSize; i++) {

                    // Gets the target content inside of the element.
                    String payload = document.select(mappingEntry.getSelector()).get(i).ownText();

                    // If it exists get the target from the specified attribute instead.
                    if (mappingEntry.getAttribute() != null) {
                        payload = document.select(mappingEntry.getSelector()).get(i).attr(mappingEntry.getAttribute());
                    }

                    // It there is a regex filter tag, filter the content and set the result to return.
                    if (mappingEntry.getFilter() != null) {
                        List<String> matches = new ArrayList<String>();
                        Pattern regex = Pattern.compile(mappingEntry.getFilter());
                        Matcher matcher = regex.matcher(payload);

                        while (matcher.find()) {
                            matches.add(matcher.group().trim());
                        }

                        StringBuilder matchBuilder = new StringBuilder(matches.size());
                        for (String match : matches) matchBuilder.append(match);
                        payload = matchBuilder.toString().trim();
                    }

                    resultList.add(payload);
                }
                return resultList;
            }
        }
        return null;
    }
}