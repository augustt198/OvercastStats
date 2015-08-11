package me.ryanw.overcast.impl.mapping;

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

    public String getId() { return id; }
    public String getTarget() { return target; }
    public String getParent() { return parent; }
    public String getType() { return type; }
    public String getAttribute() { return attribute; }
    public String getFilter() { return filter; }
    public List<Cases> getCases() { return cases; }

    public static class Cases {
        private String name;
        private String select;
        private String identifiedByText;
        private Integer identifiedByIndex;
        private String attribute;
        private String filter;

        public String getName() { return name; }
        public String getSelect() { return select; }
        public String getIdentifiedByText() { return identifiedByText; }
        public Integer getIdentifiedByIndex() { return identifiedByIndex; }
        public String getAttribute() { return attribute; }
        public String getFilter() { return filter; }
    }
}