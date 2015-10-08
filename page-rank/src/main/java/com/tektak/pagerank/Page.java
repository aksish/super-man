package com.tektak.pagerank;

/**
 * Created by tektak on 6/2/15.
 */
public class Page {
    public String getLinkl() {
        return linkl;
    }

    public double getPageRank() {
        return pageRank;
    }

    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }

    public void setLinkl(String linkl) {
        this.linkl = linkl;
    }

    private String linkl;
    private double    pageRank;
}
