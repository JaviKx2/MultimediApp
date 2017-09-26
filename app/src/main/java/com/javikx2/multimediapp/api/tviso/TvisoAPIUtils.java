package com.javikx2.multimediapp.api.tviso;

public class TvisoAPIUtils {
    private final static String URL_BASE = "http://img.tviso.com/";

    public static String getNewsItemImgURL(String img_value, String img_country, String img_size){
        String img_url = URL_BASE;
        img_url += img_country;
        img_url += "/notice/";
        img_url += img_size;
        img_url += img_value;
        return img_url;
    }

    public static String getCatalogueItemImgURL(String img_value, String img_country, String img_size){
        String img_url = URL_BASE;
        img_url += img_country;
        img_url += "/backdrop/";
        img_url += img_size;
        img_url += img_value;
        return img_url;
    }
}
