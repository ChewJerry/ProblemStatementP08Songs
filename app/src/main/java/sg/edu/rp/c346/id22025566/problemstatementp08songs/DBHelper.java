package sg.edu.rp.c346.id22025566.problemstatementp08songs;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "songs.db";

    private static final String TABLE_SONG = "songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE " + TABLE_SONG + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, " + COLUMN_SINGERS + " TEXT, " + COLUMN_YEAR + " INTEGER, " + COLUMN_STARS + " INTEGER )";
        db.execSQL(createTableSQL);
        Log.i("info", "created tables");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);


    }

    public void insertSong(String title, String singers, int year, int Stars) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, Stars);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_SONG, null, values);
        // Close the database connection
        db.close();

    }

    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song obj = new Song(_id, title, singers, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Song> getAllFiveStarSongs() {
        ArrayList<Song> fiveStarSongs = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String selection = COLUMN_STARS + " = ?";
        String[] selectionArgs = {"5"};

        Cursor cursor = db.query(TABLE_SONG, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song song = new Song(_id, title, singers, year, stars);
                fiveStarSongs.add(song);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return fiveStarSongs;
    }

    public void updateSong(int songId, String title, String singers, int year, int stars) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(songId)};

        db.update(TABLE_SONG, values, selection, selectionArgs);

        db.close();
    }

    public void deleteSong(int songId) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(songId)};

        db.delete(TABLE_SONG, selection, selectionArgs);

        db.close();
    }

    public Song getSong(int songId) {

        // Get the database
        SQLiteDatabase db = this.getReadableDatabase();

        // Create a cursor to query the database
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(songId)};
        Cursor cursor = db.query(TABLE_SONG, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {

            // Create a Song object from the cursor
            int _id = cursor.getInt(0);
            String title = cursor.getString(1);
            String singers = cursor.getString(2);
            int year = cursor.getInt(3);
            int stars = cursor.getInt(4);

            Song song = new Song(_id, title, singers, year, stars);

            // Close the cursor
            cursor.close();

            // Return the Song object
            return song;

        }

        return null;
    }




}
