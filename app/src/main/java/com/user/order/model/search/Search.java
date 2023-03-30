
package com.user.order.model.search;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search implements Serializable
{

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions;
    @SerializedName("results")
    @Expose
    private List<Result> results;
    @SerializedName("status")
    @Expose
    private String status;
    private final static long serialVersionUID = -315295470317745468L;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Search withHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
        return this;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Search withResults(List<Result> results) {
        this.results = results;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Search withStatus(String status) {
        this.status = status;
        return this;
    }

}
