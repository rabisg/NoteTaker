package com.example.notetaker;

import android.provider.BaseColumns;

public final class NoteTakerContract {

	public NoteTakerContract() {
	}

	public static abstract class NoteEntry implements BaseColumns {
		public static final String TABLE_NAME = "notes";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_CONTENT = "content";
	}
}
