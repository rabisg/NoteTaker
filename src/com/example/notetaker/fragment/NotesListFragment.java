package com.example.notetaker.fragment;

import com.example.notetaker.R;
import com.example.notetaker.models.Note;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NotesListFragment extends ListFragment {

	private static NotesListAdapter notesListAdapter;
	private static Note[] notes = new Note[] {
		new Note("Title", "This is a sample Note"),
		new Note("Title", "This is a sample Note"),
		new Note("Title", "This is a sample Note")
	};

	public static void addNote(Note n) {
		notes[0] = n;
		notesListAdapter.notifyDataSetChanged();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		notesListAdapter = new NotesListAdapter(getActivity());
		setListAdapter(notesListAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public class NotesListAdapter extends BaseAdapter {

		private Context mContext;
		NotesListAdapter(Context context) {
			mContext = context;
		}
		@Override
		public int getCount() {
			return notes.length;
		}

		@Override
		public Object getItem(int pos) {
			return notes[pos];
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
			titleTextView.setText(notes[pos].title);
			TextView contentTextView = (TextView)view.findViewById(R.id.item_note_content);
			contentTextView.setText(notes[pos].content);
			return view;
		}
	}
}
