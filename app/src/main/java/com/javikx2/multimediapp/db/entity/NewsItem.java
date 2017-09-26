package com.javikx2.multimediapp.db.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsItem implements Parcelable {
    private int id;
    private String title;
    private String text;
    private String imgURL;
    private String link;


    public NewsItem(int id, String title, String text, String imgURL, String link) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imgURL = imgURL;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    protected NewsItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        text = in.readString();
        imgURL = in.readString();
        link = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeString(imgURL);
        dest.writeString(link);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsItem> CREATOR = new Parcelable.Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };
}
