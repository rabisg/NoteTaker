package com.example.notetaker.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		newNoteButton = (Button) rootView.findViewById(R.id.newNoteButton);
		quickNoteText = (EditText) rootView.findViewById(R.id.quick_note_text);
		newNoteButton.setOnClickListener(this);
		quickNoteText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE) {
					// Add New Note
					NotesListFragment.addNote(new Note("", quickNoteText.getText().toString()));
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
}
