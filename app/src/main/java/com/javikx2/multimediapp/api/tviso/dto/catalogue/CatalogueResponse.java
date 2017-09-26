
package com.javikx2.multimediapp.api.tviso.dto.catalogue;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatalogueResponse {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("error")
    @Expose
    private Integer error;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CatalogueResponse() {
    }

    /**
     * 
     * @param error
     * @param results
     */
    public CatalogueResponse(List<Result> results, Integer error) {
        this.results = results;
        this.error = error;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The error
     */
    public Integer getError() {
        return error;
    }

    /**
     * 
     * @param error
     *     The error
     */
    public void setError(Integer error) {
        this.error = error;
    }

}
