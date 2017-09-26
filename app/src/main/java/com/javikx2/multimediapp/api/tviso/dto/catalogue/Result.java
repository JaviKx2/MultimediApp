
package com.javikx2.multimediapp.api.tviso.dto.catalogue;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("idm")
    @Expose
    private Integer idm;
    @SerializedName("mediaType")
    @Expose
    private Integer mediaType;
    @SerializedName("mediaStyle")
    @Expose
    private String mediaStyle;
    @SerializedName("imdb")
    @Expose
    private String imdb;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("ageRating")
    @Expose
    private Integer ageRating;
    @SerializedName("mainGenre")
    @Expose
    private String mainGenre;
    @SerializedName("originalName")
    @Expose
    private String originalName;
    @SerializedName("shortPlot")
    @Expose
    private String shortPlot;
    @SerializedName("numEpisodes")
    @Expose
    private Integer numEpisodes;
    @SerializedName("numSeasons")
    @Expose
    private Integer numSeasons;
    @SerializedName("availability")
    @Expose
    private transient Object availability;

    public Result() {
    }

    public Result(Integer idm, Integer mediaType, String mediaStyle, String imdb, Double rating, String name, Images images, Integer year, Integer runtime, Integer ageRating, String mainGenre, String originalName, String shortPlot, Integer numEpisodes, Integer numSeasons, Object availability) {
        this.idm = idm;
        this.mediaType = mediaType;
        this.mediaStyle = mediaStyle;
        this.imdb = imdb;
        this.rating = rating;
        this.name = name;
        this.images = images;
        this.year = year;
        this.runtime = runtime;
        this.ageRating = ageRating;
        this.mainGenre = mainGenre;
        this.originalName = originalName;
        this.shortPlot = shortPlot;
        this.numEpisodes = numEpisodes;
        this.numSeasons = numSeasons;
        this.availability = availability;
    }

    /**
     * 
     * @return
     *     The idm
     */
    public Integer getIdm() {
        return idm;
    }

    /**
     * 
     * @param idm
     *     The idm
     */
    public void setIdm(Integer idm) {
        this.idm = idm;
    }

    /**
     * 
     * @return
     *     The mediaType
     */
    public Integer getMediaType() {
        return mediaType;
    }

    /**
     * 
     * @param mediaType
     *     The mediaType
     */
    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 
     * @return
     *     The mediaStyle
     */
    public String getMediaStyle() {
        return mediaStyle;
    }

    /**
     * 
     * @param mediaStyle
     *     The mediaStyle
     */
    public void setMediaStyle(String mediaStyle) {
        this.mediaStyle = mediaStyle;
    }

    /**
     * 
     * @return
     *     The imdb
     */
    public String getImdb() {
        return imdb;
    }

    /**
     * 
     * @param imdb
     *     The imdb
     */
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The images
     */
    public Images getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The ageRating
     */
    public Integer getAgeRating() {
        return ageRating;
    }

    /**
     * 
     * @param ageRating
     *     The ageRating
     */
    public void setAgeRating(Integer ageRating) {
        this.ageRating = ageRating;
    }

    /**
     * 
     * @return
     *     The mainGenre
     */
    public String getMainGenre() {
        return mainGenre;
    }

    /**
     * 
     * @param mainGenre
     *     The mainGenre
     */
    public void setMainGenre(String mainGenre) {
        this.mainGenre = mainGenre;
    }

    /**
     * 
     * @return
     *     The originalName
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * 
     * @param originalName
     *     The originalName
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * 
     * @return
     *     The shortPlot
     */
    public String getShortPlot() {
        return shortPlot;
    }

    /**
     * 
     * @param shortPlot
     *     The shortPlot
     */
    public void setShortPlot(String shortPlot) {
        this.shortPlot = shortPlot;
    }

    /**
     * 
     * @return
     *     The numEpisodes
     */
    public Integer getNumEpisodes() {
        return numEpisodes;
    }

    /**
     * 
     * @param numEpisodes
     *     The numEpisodes
     */
    public void setNumEpisodes(Integer numEpisodes) {
        this.numEpisodes = numEpisodes;
    }

    /**
     * 
     * @return
     *     The numSeasons
     */
    public Integer getNumSeasons() {
        return numSeasons;
    }

    /**
     * 
     * @param numSeasons
     *     The numSeasons
     */
    public void setNumSeasons(Integer numSeasons) {
        this.numSeasons = numSeasons;
    }

    /**
     * 
     * @return
     *     The availability
     */
    public Object getAvailability() {
        return availability;
    }

    /**
     * 
     * @param availability
     *     The availability
     */
    public void setAvailability(Object availability) {
        this.availability = availability;
    }

}
