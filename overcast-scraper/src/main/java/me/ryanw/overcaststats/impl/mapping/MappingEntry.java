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
import java.util.ArrayList;
import java.util.List;

public class MappingEntry {
    private String id;
    private String type;
    private String parent;
    private String target;
    private String attribute;
    private String filter;

    private List<Cases> cases = new ArrayList<Cases>();

    public String getId() {
        return id;
    }

    public String getTarget() {
        return target;
    }

    public String getParent() {
        return parent;
    }

    public String getType() {
        return type;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getFilter() {
        return filter;
    }

    public List<Cases> getCases() {
        return cases;
    }

    public static class Cases {
        private String name;
        private String select;
        private String identifiedByText;
        private Integer identifiedByIndex;
        private String attribute;
        private String filter;

        public String getName() {
            return name;
        }

        public String getSelect() {
            return select;
        }

        public String getIdentifiedByText() {
            return identifiedByText;
        }

        public Integer getIdentifiedByIndex() {
            return identifiedByIndex;
        }

        public String getAttribute() {
            return attribute;
        }

        public String getFilter() {
            return filter;
        }
    }
}