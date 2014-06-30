package com.example.notetaker;

import java.util.Vector;

import com.example.notetaker.models.Note;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new HomeFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class HomeFragment extends Fragment implements
			OnClickListener, OnEditorActionListener {

		Button newNoteButton;
		EditText editText;
		ListView notesList;
		NotesListAdapter notesListAdapter;

		public HomeFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home, container,
					false);
			newNoteButton = (Button) rootView.findViewById(R.id.button1);
			newNoteButton.setOnClickListener(this);

			notesListAdapter = new NotesListAdapter(getActivity());
			notesList = (ListView) rootView.findViewById(R.id.notes_list);
			notesList.setAdapter(notesListAdapter);

			editText = (EditText) rootView.findViewById(R.id.new_note);
			editText.setOnEditorActionListener(this);
			return rootView;
		}

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.button1:
				Intent intent = new Intent(getActivity(), NoteActivity.class);
				startActivity(intent);
				break;
			}
		}

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				String noteContent = editText.getText().toString();
				notesListAdapter.notes.add(new Note("", noteContent));
				notesListAdapter.notifyDataSetChanged();
				editText.setText("");
				return true;
			}
			return false;
		}
	}

	public static class NotesListAdapter extends BaseAdapter {

		public Vector<Note> notes = new Vector<Note>();
		Context mContext;

		NotesListAdapter(Context cxt) {
			mContext = cxt;
			// notes.add(new Note("Title", "Content"));
		}

		@Override
		public int getCount() {
			return notes.size();
		}

		@Override
		public Note getItem(int pos) {
			return notes.elementAt(pos);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int pos, View view, ViewGroup viewGroup) {
			View noteView = LayoutInflater.from(mContext).inflate(
					R.layout.single_note_layout, null);
			TextView title = (TextView) noteView.findViewById(R.id.note_title);
			title.setText(notes.elementAt(pos).title);
			TextView content = (TextView) noteView
					.findViewById(R.id.note_content);
			content.setText(notes.elementAt(pos).content);
			return noteView;
			// TextView textView = new TextView(mContext);
			// textView.setText(notes.elementAt(pos).content);
			// return textView;
		}

	}
}
