package com.example.notetaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteTakerDBHelper extends SQLiteOpenHelper {

	private final static String dbName = "NotesDB";
	private final static int dbVersion = 1;

	public NoteTakerDBHelper(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE Notes ( " + "_id INTEGER PRIMARY KEY, "
				+ "TITLE TEXT, CONTENT TEXT" + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Notes");
		db.execSQL("CREATE TABLE Notes ( " + "_id INTEGER PRIMARY KEY, "
				+ "TITLE TEXT, CONTENT TEXT" + ")");
	}
}
