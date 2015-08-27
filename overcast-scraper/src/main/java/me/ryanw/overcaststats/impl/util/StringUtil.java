package me.ryanw.overcaststats.impl.util;
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
import me.ryanw.overcaststats.api.OvercastFriend;
import me.ryanw.overcaststats.impl.OvercastStats;
import me.ryanw.overcaststats.impl.object.ParsedFriend;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * Takes a string int value, if it isn't null it parses it, otherwise it returns -1.
     */
    public static int parseInt(String integerValue) {
        if (integerValue == null) return -1;
        return Integer.parseInt(integerValue);
    }

    /**
     * Takes a string double value, if it isn't null it parses it, otherwise it returns -1D.
     */
    public static double parseDouble(String doubleValue) {
        if (doubleValue == null) return -1D;
        return Double.parseDouble(doubleValue);
    }

    /**
     * Converts a list of friends into a list of {@link me.ryanw.overcaststats.api.OvercastFriend} objects.
     */
    public static List<OvercastFriend> buildFriendObjects(OvercastStats overcastStats, List<String> friends) {
        List<OvercastFriend> overcastFriends = new ArrayList<OvercastFriend>();
        for (String friend : friends) overcastFriends.add(new ParsedFriend(overcastStats, friend));
        return overcastFriends;
    }

    /**
     * Builds a pattern from a regex string, applies it to the text and returns the result.
     */
    public static String runRegex(String filter, String text) {
        List<String> matches = new ArrayList<String>();
        Pattern regularExp = Pattern.compile(filter);
        Matcher matcher = regularExp.matcher(text);
        while (matcher.find()) matches.add(matcher.group().trim());
        StringBuilder stringBuilder = new StringBuilder();
        for (String match : matches) stringBuilder.append(match);
        return stringBuilder.toString().trim();
    }
}
