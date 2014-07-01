package com.example.notetaker.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.notetaker.NoteTakerContract.NoteEntry;
import com.example.notetaker.NoteTakerDBHelper;

public class Note {

	public Note(Context cxt, String title, String content) {
		this.mContext = cxt;
		this.title = title;
		this.id = -1;
		this.content = content;
		this.mDBHelper = new NoteTakerDBHelper(this.mContext);
	}

	public long id;
	public String title, content;
	NoteTakerDBHelper mDBHelper;
	Context mContext;

	public void save() {
		if (id == -1)
			create();
		else
			update();
	}

	private void update() {
		SQLiteDatabase db = mDBHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(NoteEntry.COLUMN_NAME_TITLE, title);
		values.put(NoteEntry.COLUMN_NAME_CONTENT, content);

		// Which row to update, based on the ID
		String selection = NoteEntry._ID + "=?";
		String[] selectionArgs = { String.valueOf(this.id) };

		int count = db.update(NoteEntry.TABLE_NAME, values, selection,
				selectionArgs);
	}

	private void create() {
		SQLiteDatabase db = mDBHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(NoteEntry.COLUMN_NAME_TITLE, title);
		values.put(NoteEntry.COLUMN_NAME_CONTENT, content);

		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(NoteEntry.TABLE_NAME, null, values);
		this.id = newRowId;
	}
}
