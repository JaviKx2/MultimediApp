package com.javikx2.multimediapp.api.tviso.dto.latestnews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("idn")
    @Expose
    private String idn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_text")
    @Expose
    private String shortText;
    @SerializedName("idm")
    @Expose
    private Integer idm;
    @SerializedName("mediaType")
    @Expose
    private Integer mediaType;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("tags")
    @Expose
    private transient List<String> tags = new ArrayList<String>();
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("total_votes")
    @Expose
    private Integer totalVotes;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("working")
    @Expose
    private Integer working;
    @SerializedName("section")
    @Expose
    private Integer section;
    @SerializedName("promoted")
    @Expose
    private Boolean promoted;
    @SerializedName("video")
    @Expose
    private Video video;
    @SerializedName("text")
    @Expose
    private String text;

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public Integer getIdm() {
        return idm;
    }

    public void setIdm(Integer idm) {
        this.idm = idm;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getWorking() {
        return working;
    }

    public void setWorking(Integer working) {
        this.working = working;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Boolean getPromoted() {
        return promoted;
    }

    public void setPromoted(Boolean promoted) {
        this.promoted = promoted;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}