package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.User;
import com.example.myapplication.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                                            + Util.USERNAME + " TEXT, " + Util.PASSWORD + " TEXT);";

        String CREATE_LINKS_TABLE = "CREATE TABLE " + Util.PLAYLIST_TABLE + " (" +
                Util.LINK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Util.LINK_USER_ID + " INTEGER, " +
                Util.LINK + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + Util.LINK_USER_ID + ") REFERENCES " + Util.TABLE_NAME + "(" + Util.USER_ID + ") ON DELETE CASCADE);";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_LINKS_TABLE);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        String DROP_PLAYLIST_TABLE = "DROP TABLE IF EXISTS " + Util.PLAYLIST_TABLE;


        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PLAYLIST_TABLE);
        onCreate(db);
    }

    public long insertUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userContentValues = new ContentValues();
        userContentValues.put(Util.USERNAME, user.getUsername());
        userContentValues.put(Util.PASSWORD, user.getPassword());
        long newRowId = db.insert(Util.TABLE_NAME, null, userContentValues);
        db.close();
        return newRowId;
    }

    public long addYouTubeLink(int userId, String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.LINK_USER_ID, userId);
        values.put(Util.LINK, link);
        long result = db.insert(Util.PLAYLIST_TABLE, null, values);
        db.close();
        return result;
    }

    public List<String> getYouTubeLinks(int userId) {
        List<String> links = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.PLAYLIST_TABLE,
                new String[]{Util.LINK},
                Util.LINK_USER_ID + "=?",
                new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                links.add(cursor.getString(cursor.getColumnIndexOrThrow(Util.LINK)));
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return links;
    }


    /*public boolean fetchUser(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.USER_ID}, Util.USERNAME + "=? and " + Util.PASSWORD + "=?",
                                new String[] {username, password}, null, null, null);
        int numberOfRows = cursor.getCount();
        db.close();

        if (numberOfRows > 0)
            return true;
        else
            return false;
    }*/
    public int fetchUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1;

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.USER_ID},
                Util.USERNAME + "=? AND " + Util.PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow(Util.USER_ID));
            cursor.close();
        }

        db.close();
        return userId; // Will return actual ID or -1 if user not found
    }

}
