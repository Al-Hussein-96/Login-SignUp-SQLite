package com.example.al_hussein.login_signup_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "create table if not EXISTS " + UserContract.ContactEntry.TABLE_NAME +
            "(" + UserContract.ContactEntry.CONTACT_ID + " INTEGER PRIMARY KEY ," + UserContract.ContactEntry.NAME + " TEXT," +
            UserContract.ContactEntry.PASSWORD + " TEXT," + UserContract.ContactEntry.EMAIL + " TEXT )";
    public static final String DROP_TABLE = "drop table if exists " + UserContract.ContactEntry.TABLE_NAME;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.ContactEntry.NAME, user.userName);
        contentValues.put(UserContract.ContactEntry.EMAIL, user.email);
        contentValues.put(UserContract.ContactEntry.PASSWORD, user.password);

        database.insert(UserContract.ContactEntry.TABLE_NAME, null, contentValues);
        Log.i("Insert", "inserted row");

    }

    public Cursor ReadUsers() {
        SQLiteDatabase database = this.getReadableDatabase();

        String[] projection = {UserContract.ContactEntry.CONTACT_ID,
                UserContract.ContactEntry.NAME, UserContract.ContactEntry.EMAIL,
                UserContract.ContactEntry.PASSWORD};

        Cursor cursor = database.query(UserContract.ContactEntry.TABLE_NAME,
                projection, null, null, null, null, null);
        return cursor;
    }

    public User checkUser(User user) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(UserContract.ContactEntry.TABLE_NAME,// Selecting Table
                new String[]{UserContract.ContactEntry.CONTACT_ID, UserContract.ContactEntry.NAME, UserContract.ContactEntry.EMAIL,
                        UserContract.ContactEntry.PASSWORD},//Selecting columns want to query
                UserContract.ContactEntry.NAME + "=?",
                new String[]{user.userName},//Where clause
                null, null, null);

        Log.i("Cursor", String.valueOf(cursor.getCount()));
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            Log.i("Cursor", user.password + " : " + user1.password);
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean isUserExists(String Username) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UserContract.ContactEntry.TABLE_NAME,// Selecting Table
                new String[]{UserContract.ContactEntry.CONTACT_ID, UserContract.ContactEntry.NAME, UserContract.ContactEntry.PASSWORD,
                        UserContract.ContactEntry.EMAIL},//Selecting columns want to query
                UserContract.ContactEntry.NAME + "=?",
                new String[]{Username},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            Log.i("email", String.valueOf(cursor.moveToFirst()));
            return true;
        }

        //if email does not exist return false
        return false;
    }
}
