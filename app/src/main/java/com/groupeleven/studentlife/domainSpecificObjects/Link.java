package com.groupeleven.studentlife.domainSpecificObjects;

import java.util.Objects;

public class Link implements ILinkObject{
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

    public String getLinkName() {
        return linkName;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return linkName.equals(link.linkName) &&
                linkAddress.equals(link.linkAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkName, linkAddress);
    }
}
