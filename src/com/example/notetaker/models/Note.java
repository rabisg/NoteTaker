package com.example.notetaker.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.notetaker.NoteTakerDBHelper;

public class Note {

	public long id;
	public String title, content;
	private NoteTakerDBHelper dbHelper;

	public Note(Context cxt, String title, String content) {
		this.title = title;
		this.content = content;
		this.id = -1;
		dbHelper = new NoteTakerDBHelper(cxt);
	}

	public void save() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("TITLE", this.title);
		values.put("CONTENT", this.content);
		this.id = db.insert("Notes", null, values);
		/*
		 * db.execSQL("INSERT INTO Notes (TITLE, CONTENT) VALUES ("+ this.title
		 * + ", " + this.content + ")" );
		 */

	}
}
