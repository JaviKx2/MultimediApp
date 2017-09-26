package com.javikx2.multimediapp.db.contract;

import android.provider.BaseColumns;

public class NewsContract {

    private NewsContract(){

    }

    public static class NewsColumns implements BaseColumns {
        public final static String TABLE_NAME = "news";
        public final static String COL_ID = "_id";
        public final static String COL_TITLE = "title";
        public final static String COL_TEXT = "text";
        public final static String COL_IMG_URL = "imgurl";
        public final static String COL_LINK = "link";
    }
}
