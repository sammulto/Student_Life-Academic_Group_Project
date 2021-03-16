package com.groupeleven.studentlife.domainSpecificObjects;

public class Link {
    private String linkName;
    private String linkAddress;

    public Link(String name, String address){
        this.linkName = name;
        this.linkAddress = address;
    }

    public void setLinkName(String name){
        this.linkName = name;
    }

    public void setLinkAddress(String address){
        this.linkAddress = address;
    }
}
