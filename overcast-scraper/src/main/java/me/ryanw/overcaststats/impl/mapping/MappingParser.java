package me.ryanw.overcaststats.impl.mapping;
/*
 * This file is part of OvercastStats, licensed under the MIT License (MIT).
 *
 * Copyright (c) Ryan W
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.ryanw.overcaststats.impl.util.StringUtil;
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
        String url = "https://raw.githubusercontent.com/ryanw-se/mappings/master/" + fileName + ".json";
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonParser jsonParser = new JsonFactory().createParser(new URL(url).openStream());
            mappingEntries = Arrays.asList(mapper.readValue(jsonParser, MappingEntry[].class));
        } catch (MalformedURLException e) {
            throw new NullPointerException("Cannot fetch latest mappings file from Github: " + e.getMessage());
        } catch (JsonParseException e) {
            throw new NullPointerException("Error occurred while attempting to parse mappings file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Primitive type calls, takes a mappings result and converts it to the called type.
     */
    public byte getByte(DataType dataType) {
        return Byte.parseByte(getString(dataType));
    }

    public short getShort(DataType dataType) {
        return Short.parseShort(getString(dataType));
    }

    public long getLong(DataType dataType) {
        return Long.parseLong(getString(dataType));
    }

    public float getFloat(DataType dataType) {
        return Float.parseFloat(getString(dataType));
    }

    public double getDouble(DataType dataType) {
        return Double.parseDouble(getString(dataType));
    }

    public boolean getBoolean(DataType dataType) {
        return Boolean.parseBoolean(getString(dataType));
    }

    public int getInteger(DataType dataType) {
        return Integer.parseInt(getString(dataType));
    }

    /**
     * Gets an entry from the mapping file, passes it through Jsoup and returns the result as a String.
     * @param dataType The id of the mapping entry we want to read from.
     * @return Formatted result compiled by Jsoup using the selector tag.
     */
    public String getString(DataType dataType) {
        for (MappingEntry entry : mappingEntries) {
            if (entry.getId().equalsIgnoreCase(dataType.name())) {
                if (entry.getType().equalsIgnoreCase(DataType.Types.SINGLE.name())) {
                    if (doc.select(entry.getTarget()).first() == null) return null;
                    String result = doc.select(entry.getTarget()).first().ownText();
                    if (entry.getAttribute() != null) result = doc.select(entry.getTarget()).first().attr(entry.getAttribute());
                    if (entry.getFilter() != null) result = StringUtil.runRegex(entry.getFilter(), result);
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
     * @param dataType The id of the mapping entry we want to read from.
     * @return Formatted list of results.
     */
    public List<String> getList(DataType dataType) {
        for (MappingEntry entry : mappingEntries) {
            List<String> resultList = new ArrayList<String>();
            if (entry.getId().equalsIgnoreCase(dataType.name())) {
                if (entry.getType().equalsIgnoreCase(DataType.Types.LIST.name())) {
                    int amountOfResults = doc.select(entry.getTarget()).size();
                    for (int i = 0; i < amountOfResults; i++) {
                        String result = doc.select(entry.getTarget()).get(i).ownText();
                        if (entry.getAttribute() != null) result = doc.select(entry.getTarget()).get(i).attr(entry.getAttribute());
                        if (entry.getFilter() != null) result = StringUtil.runRegex(entry.getFilter(), result);
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
     * @param dataType The id of the mapping entry we want to read from.
     * @return Formatted map of results.
     */
    public Map<DataType, String> getMap(DataType dataType) {
        for (MappingEntry entry : mappingEntries) {
            Map<DataType, String> resultMap = new HashMap<DataType, String>();
            if (entry.getId().equalsIgnoreCase(dataType.getId())) {
                if (entry.getType().equalsIgnoreCase(DataType.Types.MAP.name())) {
                    for (MappingEntry.Cases entryCase : entry.getCases()) {
                        DataType name = DataType.getEnumById(entryCase.getName());
                        List<Element> matchingElements = new ArrayList<Element>();
                        Elements parentElements = doc.select(entry.getParent());
                        for (Element parentElement : parentElements) {
                            for (Element matchingElement : parentElement.getElementsContainingOwnText(entryCase.getIdentifiedByText())) {
                                matchingElements.add(matchingElement);
                            }
                        }
                        if (matchingElements.size() > 0) {
                            Element targetElement = matchingElements.get(0);
                            if (entryCase.getIdentifiedByIndex() != null)
                                targetElement = matchingElements.get(entryCase.getIdentifiedByIndex());
                            String elementName = entry.getParent();
                            if (entry.getTarget() != null) elementName = entry.getTarget();
                            if (targetElement.lastElementSibling() != null && targetElement.lastElementSibling().getElementById(entry.getTarget()) != null) {
                                String payload = targetElement.lastElementSibling().ownText();
                                if (entryCase.getAttribute() != null)
                                    payload = targetElement.lastElementSibling().attr(entryCase.getAttribute());
                                if (entryCase.getFilter() != null) payload = StringUtil.runRegex(entryCase.getFilter(), payload);
                                resultMap.put(name, payload);
                                continue;
                            }
                            if (targetElement.siblingElements().select(elementName).first() != null) {
                                String payload = targetElement.siblingElements().select(elementName).first().ownText();
                                if (entryCase.getAttribute() != null)
                                    payload = targetElement.siblingElements().select(elementName).attr(entryCase.getAttribute());
                                if (entryCase.getFilter() != null) payload = StringUtil.runRegex(entryCase.getFilter(), payload);
                                resultMap.put(name, payload);
                                continue;
                            }
                            if (targetElement.parent().nodeName().equalsIgnoreCase(elementName)) {
                                String payload = targetElement.parent().ownText();
                                if (entryCase.getAttribute() != null)
                                    payload = targetElement.parent().attr(entryCase.getAttribute());
                                if (entryCase.getFilter() != null) payload = StringUtil.runRegex(entryCase.getFilter(), payload);
                                resultMap.put(name, payload);
                            }
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