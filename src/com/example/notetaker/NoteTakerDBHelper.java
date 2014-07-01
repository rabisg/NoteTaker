package com.example.notetaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notetaker.NoteTakerContract.NoteEntry;

public class NoteTakerDBHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "FeedReader.db";

	public NoteTakerDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ NoteEntry.TABLE_NAME + " (" + NoteEntry._ID
			+ " INTEGER PRIMARY KEY," + NoteEntry.COLUMN_NAME_TITLE + " TEXT, "
			+ NoteEntry.COLUMN_NAME_CONTENT + " TEXT )";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ NoteEntry.TABLE_NAME;

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Upgrade schema depending on oldVersion and newVersion
		db.execSQL(SQL_DELETE_ENTRIES);
		db.execSQL(SQL_CREATE_ENTRIES);
	}
}
