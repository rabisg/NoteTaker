package com.example.notetaker.fragment;

import java.util.Vector;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.notetaker.NoteActivity;
import com.example.notetaker.R;
import com.example.notetaker.models.Note;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements OnClickListener {

	Button newNoteButton;
	EditText quickNoteText;
	NotesListAdapter notesListAdapter;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		newNoteButton = (Button) rootView.findViewById(R.id.newNoteButton);
		newNoteButton.setOnClickListener(this);

		ListView notesList = (ListView) rootView.findViewById(R.id.notes_list);
		notesListAdapter = new NotesListAdapter(getActivity());
		notesList.setAdapter(notesListAdapter);

		quickNoteText = (EditText) rootView.findViewById(R.id.quick_note_text);
		quickNoteText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE) {
					// Add New Note
					notesListAdapter.addNote(new Note("", quickNoteText.getText().toString()));
					quickNoteText.setText("");
					return true;
				}
				return false;
			}
		});
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.newNoteButton:
				Intent intent = new Intent(getActivity(), NoteActivity.class);
				startActivity(intent);
				break;
			default:
				Log.d("DEBUG", "Not Implemented");
				break;
		}
	}

	public class NotesListAdapter extends BaseAdapter {

		private Context mContext;
		private Vector<Note> notes = new Vector<Note>();

		NotesListAdapter(Context context) {
			mContext = context;
		}
		public void addNote(Note note) {
			notes.add(note);
			this.notifyDataSetChanged();
		}
		@Override
		public int getCount() {
			return notes.size();
		}

		@Override
		public Object getItem(int pos) {
			return notes.elementAt(pos);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int pos, View view, ViewGroup viewGroup) {
			if(view == null)
				view = LayoutInflater.from(mContext).inflate(R.layout.list_item_note, null);

			TextView titleTextView = (TextView)view.findViewById(R.id.item_note_title);
			titleTextView.setText(notes.elementAt(pos).title);
			TextView contentTextView = (TextView)view.findViewById(R.id.item_note_content);
			contentTextView.setText(notes.elementAt(pos).content);
			return view;
		}
	}
}
