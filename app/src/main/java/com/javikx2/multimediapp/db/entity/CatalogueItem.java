package com.javikx2.multimediapp.db.entity;

public class CatalogueItem {
    private int id;
    private int idm;
    private int mediaType;
    private String name;
    private String imgURL;

    public CatalogueItem(int idm, int mediaType, String name, String imgURL) {
        this.idm = idm;
        this.mediaType = mediaType;
        this.name = name;
        this.imgURL = imgURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
