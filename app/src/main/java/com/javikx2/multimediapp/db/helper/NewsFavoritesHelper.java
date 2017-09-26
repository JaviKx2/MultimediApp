package com.javikx2.multimediapp.db.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.javikx2.multimediapp.db.entity.NewsItem;

import java.util.ArrayList;
import java.util.List;

import static com.javikx2.multimediapp.db.contract.NewsContract.NewsColumns;

public class NewsFavoritesHelper extends SQLiteOpenHelper {
    private final static String DB_FILE = NewsColumns.TABLE_NAME + ".db";
    private final static int DB_VERSION = 1;

    private final static String SQL_CREATE =
            "CREATE TABLE " + NewsColumns.TABLE_NAME
                    + "( " +
                    NewsColumns.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NewsColumns.COL_TITLE + " TEXT, " +
                    NewsColumns.COL_TEXT + " TEXT, " +
                    NewsColumns.COL_IMG_URL + " TEXT, " +
                    NewsColumns.COL_LINK + " TEXT" +
                    " );";

    private final static String SQL_DROP =
            "DROP TABLE IF EXISTS " + NewsColumns.TABLE_NAME;

    public NewsFavoritesHelper(Context context) {
        super(context, DB_FILE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP);
    }

    public long count() {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, NewsColumns.TABLE_NAME);
    }


    public List<NewsItem> getAllNews() {
        String consultaSQL = "SELECT * FROM " + NewsColumns.TABLE_NAME;
        List<NewsItem> news = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                NewsItem resultado = new NewsItem(
                        cursor.getInt(cursor.getColumnIndex(NewsColumns.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(NewsColumns.COL_TITLE)),
                        cursor.getString(cursor.getColumnIndex(NewsColumns.COL_TEXT)),
                        cursor.getString(cursor.getColumnIndex(NewsColumns.COL_IMG_URL)),
                        cursor.getString(cursor.getColumnIndex(NewsColumns.COL_LINK))
                );

                news.add(resultado);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return news;
    }

    public Cursor getCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(NewsColumns.TABLE_NAME,
                null, null, null, null, null, null);
    }

    public long insert(String title, String text, String imgURL, String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NewsColumns.COL_TITLE, title);
        values.put(NewsColumns.COL_TEXT, text);
        values.put(NewsColumns.COL_IMG_URL, imgURL);
        values.put(NewsColumns.COL_LINK, link);
        return db.insert(NewsColumns.TABLE_NAME, null, values);
    }

    public int delete(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereCondition = NewsColumns.COL_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return db.delete(NewsColumns.TABLE_NAME, whereCondition, whereArgs);
    }

    public int deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NewsColumns.TABLE_NAME, "1", null);
    }
}
