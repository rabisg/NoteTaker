package com.example.notetaker.fragment;

import java.util.Vector;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.notetaker.NoteActivity;
import com.example.notetaker.NoteTakerDBHelper;
import com.example.notetaker.R;
import com.example.notetaker.NoteTakerContract.NoteEntry;
import com.example.notetaker.models.Note;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements OnClickListener {

	Button newNoteButton;
	EditText quickNoteText;
	NotesListAdapter notesListAdapter;
	Context mContext;
	NoteTakerDBHelper mDBHelper;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mDBHelper = new NoteTakerDBHelper(mContext);

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		newNoteButton = (Button) rootView.findViewById(R.id.newNoteButton);
		newNoteButton.setOnClickListener(this);

		ListView notesList = (ListView) rootView.findViewById(R.id.notes_list);
		notesListAdapter = new NotesListAdapter(getActivity(), getAllNotes());
		notesList.setAdapter(notesListAdapter);

		quickNoteText = (EditText) rootView.findViewById(R.id.quick_note_text);
		quickNoteText
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {

					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							// Add New Note
							Note note = new Note(mContext, "", quickNoteText
									.getText().toString());
							note.save();
							// notesListAdapter.addNote(note);

							quickNoteText.setText("");
							notesListAdapter.changeCursor(getAllNotes());
							return true;
						}
						return false;
					}
				});
		return rootView;
	}

	protected Cursor getAllNotes() {
		return mDBHelper.getReadableDatabase().rawQuery(
				"SELECT * FROM " + NoteEntry.TABLE_NAME, null);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.newNoteButton:
			Intent intent = new Intent(getActivity(), NoteActivity.class);
			startActivity(intent);
			break;
		default:
			Log.d("DEBUG", "Not Implemented");
			break;
		}
	}

	public class NotesListAdapter extends CursorAdapter {

		// private Context mContext;
		// private Vector<Note> notes = new Vector<Note>();

		NotesListAdapter(Context context, Cursor cursor) {
			super(context, cursor);
			// mContext = context;
		}

		/*
		 * public void addNote(Note note) { notes.add(note);
		 * this.notifyDataSetChanged(); }
		 * 
		 * @Override public int getCount() { return notes.size(); }
		 * 
		 * @Override public Object getItem(int pos) { return
		 * notes.elementAt(pos); }
		 * 
		 * @Override public long getItemId(int arg0) { return 0; }
		 * 
		 * @Override public View getView(int pos, View view, ViewGroup
		 * viewGroup) { if(view == null) view =
		 * LayoutInflater.from(mContext).inflate(R.layout.list_item_note, null);
		 * 
		 * TextView titleTextView =
		 * (TextView)view.findViewById(R.id.item_note_title);
		 * titleTextView.setText(notes.elementAt(pos).title); TextView
		 * contentTextView =
		 * (TextView)view.findViewById(R.id.item_note_content);
		 * contentTextView.setText(notes.elementAt(pos).content); return view; }
		 */

		@Override
		public void bindView(View view, Context cxt, Cursor cursor) {
			TextView titleTextView = (TextView) view
					.findViewById(R.id.item_note_title);
			titleTextView.setText(cursor.getString(1));
			TextView contentTextView = (TextView) view
					.findViewById(R.id.item_note_content);
			contentTextView.setText(cursor.getString(2));
		}

		@Override
		public View newView(Context cxt, Cursor cursor, ViewGroup viewGroup) {
			return LayoutInflater.from(mContext).inflate(
					R.layout.list_item_note, null);
		}
	}
}
