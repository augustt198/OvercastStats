package me.ryanw.overcast.impl.mapping;

import java.util.ArrayList;
import java.util.List;

public class MappingEntry {
    private String id;
    private String selector;
    private String filter;
    private String attribute;
    private Integer target;
    private List<Conditions> conditions = new ArrayList<Conditions>();
    private String targetElement;

    public String getId() { return id; }
    public String getSelector() { return selector; }
    public String getFilter() { return filter; }
    public String getAttribute() { return attribute; }
    public Integer getTarget() { return target; }
    public List<Conditions> getConditions() { return conditions; }
    public String getTargetElement() { return targetElement; }

    public class Conditions {
        private String contains;
        private String name;
        private String filter;
        private String attribute;
        public String getContains() { return contains; }
        public String getName() { return name; }
        public String getFilter() { return filter; }
        public String getAttribute() { return attribute; }
    }
}