package me.ryanw.sunset.impl.mapping;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.ryanw.sunset.impl.util.HelperUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class MappingParser {

    private final Document doc;
    private List<MappingEntry> mappingEntries;
    public MappingParser(Document doc, String fileName) {
        this.doc = doc;
        String url = "https://raw.githubusercontent.com/SunsetAPI/mappings/master/" + fileName + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            mappingEntries = Arrays.asList(mapper.readValue(jsonParser, MappingEntry[].class));
        } catch (MalformedURLException e) {
            throw new NullPointerException("Cannot fetch latest mappings file from Github: " + e.getMessage());
        } catch (JsonParseException e) {
            throw new NullPointerException("Error occured while attempting to parse mappings file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a call to {@link #getString(MappingEnum)} (MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Filtered byte result of a {@link #getString(MappingEnum)} (MappingEnum)}
     */
    public byte getByte(MappingEnum mappingEnum) {
        return Byte.parseByte(getString(mappingEnum));
    }

    /**
     * Sends a call to {@link #getString(MappingEnum)} (MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Short result of a {@link #getString(MappingEnum)} (MappingEnum)}
     */
    public short getShort(MappingEnum mappingEnum) {
        return Short.parseShort(getString(mappingEnum));
    }

    /**
     * Sends a call to {@link #getString(MappingEnum)} (MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Long result of a {@link #getString(MappingEnum)} (MappingEnum)}
     */
    public long getLong(MappingEnum mappingEnum) {
        return Long.parseLong(getString(mappingEnum));
    }

    /**
     * Sends a call to {@link #getString(MappingEnum)} (MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Float result of a {@link #getString(MappingEnum)} (MappingEnum)}
     */
    public float getFloat(MappingEnum mappingEnum) {
        return Float.parseFloat(getString(mappingEnum));
    }

    /**
     * Sends a call to {@link #getString(MappingEnum)} (MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Double result of a {@link #getString(MappingEnum)} (MappingEnum)}
     */
    public double getDouble(MappingEnum mappingEnum) {
        return Double.parseDouble(getString(mappingEnum));
    }

    /**
     * Sends a call to {@link #getString(MappingEnum)} (MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Boolean result of a {@link #getString(MappingEnum)} (MappingEnum)}
     */
    public boolean getBoolean(MappingEnum mappingEnum) {
        return Boolean.parseBoolean(getString(mappingEnum));
    }

    /**
     * Sends a call to {@link #getString(MappingEnum)} and returns the result.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Integer result of a {@link #getString(MappingEnum)}
     */
    public int getInteger(MappingEnum mappingEnum) {
        return Integer.parseInt(getString(mappingEnum));
    }

    /**
     * Gets an entry from the mapping file, passes it through Jsoup and returns the result as a String.
     * @param mappingEnum The id of the mapping entry we want to read from.
     * @return Formatted result compiled by Jsoup using the selector tag.
     */
    public String getString(MappingEnum mappingEnum) {
        for (MappingEntry entry : mappingEntries) {
            if (entry.getId().equalsIgnoreCase(mappingEnum.name())) {
                if (entry.getType().equalsIgnoreCase(MappingEnum.Types.SINGLE.name())) {
                    String result = doc.select(entry.getTarget()).first().ownText();
                    if (entry.getAttribute() != null) result = doc.select(entry.getTarget()).first().attr(entry.getAttribute());
                    if (entry.getFilter() != null) result = HelperUtil.runRegex(entry, result);
                    return result;
                }
                throw new NullPointerException("Invalid call, expected type single, received type " + entry.getType());
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
        for (MappingEntry entry : mappingEntries) {
            List<String> resultList = new ArrayList<String>();
            if (entry.getId().equalsIgnoreCase(mappingEnum.name())) {
                if (entry.getType().equalsIgnoreCase(MappingEnum.Types.LIST.name())) {
                    int amountOfResults = doc.select(entry.getTarget()).size();
                    for (int i = 0; i < amountOfResults; i++) {
                        String result = doc.select(entry.getTarget()).get(i).ownText();
                        if (entry.getAttribute() != null) result = doc.select(entry.getTarget()).get(i).attr(entry.getAttribute());
                        if (entry.getFilter() != null) result = HelperUtil.runRegex(entry, result);
                        resultList.add(result);
                    }
                    return resultList;
                }
                throw new NullPointerException("Invalid call, expected type list, received type " + entry.getType());
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
        for (MappingEntry entry : mappingEntries) {
            Map<MappingEnum, String> resultMap = new HashMap<MappingEnum, String>();
            if (entry.getId().equalsIgnoreCase(mappingEnum.getId())) {
                if (entry.getType().equalsIgnoreCase(MappingEnum.Types.MAP.name())) {
                    for (MappingEntry.Cases entryCase : entry.getCases()) {
                        MappingEnum name = HelperUtil.getEnumById(entryCase.getName());
                        List<Element> matchingElements = new ArrayList<Element>();
                        Elements parentElements = doc.select(entry.getParent());
                        for (Element parentElement : parentElements) {
                            for (Element matchingElement : parentElement.getElementsMatchingOwnText(entryCase.getIdentifiedByText())) {
                                matchingElements.add(matchingElement);
                            }
                        }
                        Element targetElement = matchingElements.get(0);
                        if (entryCase.getIdentifiedByIndex() != null) targetElement = matchingElements.get(entryCase.getIdentifiedByIndex());
                        if (targetElement.lastElementSibling() != null && targetElement.lastElementSibling().getElementById(entry.getTarget()) != null) {
                            String payload = targetElement.lastElementSibling().ownText();
                            if (entryCase.getAttribute() != null)
                                payload = targetElement.lastElementSibling().attr(entryCase.getAttribute());
                            if (entryCase.getFilter() != null) payload = HelperUtil.runRegex(entryCase, payload);
                            resultMap.put(name, payload);
                            break;
                        }
                        if (targetElement.firstElementSibling() != null && targetElement.firstElementSibling().getElementById(entry.getTarget()) != null) {
                            String payload = targetElement.firstElementSibling().ownText();
                            if (entryCase.getAttribute() != null)
                                payload = targetElement.firstElementSibling().attr(entryCase.getAttribute());
                            if (entryCase.getFilter() != null) payload = HelperUtil.runRegex(entryCase, payload);
                            resultMap.put(name, payload);
                            break;
                        }
                        String elementName = entry.getParent();
                        if (entry.getTarget() != null) elementName = entry.getTarget();
                        if (targetElement.parent().nodeName().equalsIgnoreCase(elementName)) {
                            String payload = targetElement.parent().ownText();
                            if (entryCase.getAttribute() != null) payload = targetElement.parent().attr(entryCase.getAttribute());
                            if (entryCase.getFilter() != null) payload = HelperUtil.runRegex(entryCase, payload);
                            resultMap.put(name, payload);
                        }
                    }
                    return resultMap;
                } else {
                    throw new NullPointerException("Invalid call, expected type map, received type " + entry.getType());
                }
            }
        }
        return null;
    }
}