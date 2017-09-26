package com.javikx2.multimediapp.db.contract;

import android.provider.BaseColumns;

public class CatalogueContract {

    private CatalogueContract(){
    }

    public static class CatalogueColumns implements BaseColumns {
        public final static String TABLE_NAME = "catalogue";
        public final static String COL_ID = "_id";
        public final static String COL_IDM = "idm";
        public final static String COL_MEDIA_TYPE = "mediatype";
        public final static String COL_NAME = "name";
        public final static String COL_IMG_URL = "imgurl";
    }
}
