package me.ryanw.overcast.impl.object;

public class MappingEntry {

    private String id;
    private String selector;
    private String filter;
    private String attribute;
    private Integer target;

    public String getId() {
        return id;
    }

    public String getSelector() {
        return selector;
    }

    public String getFilter() {
        return filter;
    }

    public String getAttribute() {
        return attribute;
    }

    public Integer getTarget() {
        return target;
    }
}