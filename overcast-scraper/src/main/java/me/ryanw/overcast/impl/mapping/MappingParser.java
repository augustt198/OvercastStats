package me.ryanw.overcast.impl.mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import me.ryanw.overcast.impl.util.HelperUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MappingParser {

    private final Document document;
    private final JsonArray mappingArray;

    public MappingParser(Document document, String fileName) throws IOException {
        this.document = document;
        String url = "https://bitbucket.org/ryanw-se/mappings/raw/master/OvercastAPI/" + fileName + ".json";

        try {
            mappingArray = new JsonParser().parse(new InputStreamReader(new URL(url).openStream())).getAsJsonArray();
        } catch (IOException e) {
            throw new NullPointerException("Cannot fetch the latest mapping file from Github: " + e.getMessage());
        }
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Filtered byte result of a {@link #get(MappingEnum)}
     */
    public byte getByte(MappingEnum mappingEnum) {
        return Byte.parseByte(get(mappingEnum));
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Short result of a {@link #get(MappingEnum)}
     */
    public short getShort(MappingEnum mappingEnum) {
        return Short.parseShort(get(mappingEnum));
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Long result of a {@link #get(MappingEnum)}
     */
    public long getLong(MappingEnum mappingEnum) {
        return Long.parseLong(get(mappingEnum));
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Float result of a {@link #get(MappingEnum)}
     */
    public float getFloat(MappingEnum mappingEnum) {
        return Float.parseFloat(get(mappingEnum));
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Double result of a {@link #get(MappingEnum)}
     */
    public double getDouble(MappingEnum mappingEnum) {
        return Double.parseDouble(get(mappingEnum));
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Boolean result of a {@link #get(MappingEnum)}
     */
    public boolean getBoolean(MappingEnum mappingEnum) {
        return Boolean.parseBoolean(get(mappingEnum));
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Integer result of a {@link #get(MappingEnum)}
     */
    public int getInteger(MappingEnum mappingEnum) {
        return Integer.parseInt(get(mappingEnum));
    }

    /**
     * Sends a call to {@link #get(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return String result of a {@link #get(MappingEnum)}
     */
    public String getString(MappingEnum mappingEnum) {
        return get(mappingEnum);
    }

    /**
     * Gets an entry from the mapping file, passes it through Jsoup and returns the result as a String.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Formatted result compiled by Jsoup using the selector tag.
     */
    private String get(MappingEnum mappingEnum) {
        for (JsonElement mapping : mappingArray) {
            MappingEntry mappingsEntry = new Gson().fromJson(mapping, MappingEntry.class);

            // Verifies that the entry ID matches the one we want to get.
            if (mappingsEntry.getId().equals(mappingEnum.getEntryName())) {

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
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Formatted list of results.
     */
    public List<String> getList(MappingEnum mappingEnum) {
        for (JsonElement mapping : mappingArray) {
            MappingEntry mappingEntry = new Gson().fromJson(mapping, MappingEntry.class);
            List<String> resultList = new ArrayList<String>();

            // Verifies that the entry ID matches the one we want to get.
            if (mappingEntry.getId().equals(mappingEnum.getEntryName())) {

                // Verifies that the requested entry is either a list or map result.
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

    /**
     * Gets an entry from the mapping file, passes it through Jsou and iterates through all of the results using the selector
     * tag to find the identifier element, then using that element to get the target elmement and putting the name and result
     * in a map of results that will be returned at the end of iteration.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Formatted map of results.
     */
    public Map<MappingEnum, String> getMap(MappingEnum mappingEnum) {
        for (JsonElement mapping : mappingArray) {
            MappingEntry mappingEntry = new Gson().fromJson(mapping, MappingEntry.class);
            Map<MappingEnum, String> resultMap = new HashMap<MappingEnum, String>();

            // Verifies that the entry ID matches the one we want to get.
            if (mappingEntry.getId().equalsIgnoreCase(mappingEnum.getEntryName())) {

                // Verifies that the requested entry is either a list or map result.
                if (mappingEntry.getTarget() != null) return null;
                Elements targetElements = document.select(mappingEntry.getSelector());

                for (Element targetElement : targetElements) {

                    // Gets the element we are using to identify the target element.
                    String identifier = targetElement.ownText().toLowerCase();

                    // If it exists, get the information inside of the attribute of the element we are using to identify sub-elements with.
                    if (mappingEntry.getAttribute() != null) {
                        identifier = targetElement.attr(mappingEntry.getAttribute()).toLowerCase();
                    }

                    // If it exists, get the filter tag and filter the identifier element before checking conditions.
                    if (mappingEntry.getFilter() != null) {
                        List<String> matches = new ArrayList<String>();
                        Pattern regex = Pattern.compile(mappingEntry.getFilter());
                        Matcher matcher = regex.matcher(identifier);

                        while (matcher.find()) {
                            matches.add(matcher.group().trim());
                        }

                        StringBuilder matchBuilder = new StringBuilder(matches.size());
                        for (String match : matches) matchBuilder.append(match);
                        identifier = matchBuilder.toString().trim();
                    }

                    /**
                     * We've gotten the identifier element, we've cleaned it up by filtering it and getting the attribute if it exists.
                     * Now lets get the target element, which is the first element by the specified id after the identifier element. Then,
                     * run the same logic as before (filter, attribute) just this time with the result. Put the result in a map and once
                     * done building the map, return it as the result.
                     */
                    for (MappingEntry.Conditions condition : mappingEntry.getConditions()) {
                        if (identifier.equalsIgnoreCase(condition.getContains())) {
                            MappingEnum entryKey = HelperUtil.getEnumById(condition.getName());
                            String entryValue = targetElement.siblingElements().select(mappingEntry.getTargetElement()).first().ownText();

                            if (condition.getAttribute() != null) {
                                entryValue = targetElement.siblingElements().select(mappingEntry.getTargetElement()).attr(condition.getAttribute());
                            }

                            if (condition.getFilter() != null) {
                                List<String> matches = new ArrayList<String>();
                                Pattern regex = Pattern.compile(condition.getFilter());
                                Matcher matcher = regex.matcher(entryValue);

                                while (matcher.find()) {
                                    matches.add(matcher.group().trim());
                                }

                                StringBuilder matchBuilder = new StringBuilder(matches.size());
                                for (String match : matches) matchBuilder.append(match);
                                entryValue = matchBuilder.toString().trim();
                            }

                            resultMap.put(entryKey, entryValue);
                        }
                    }
                }
                return resultMap;
            }
        }
        return null;
    }
}