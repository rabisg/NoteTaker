package com.example.notetaker.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NotesListFragment extends ListFragment {

	private static String[] notes = new String[]{"one", "two", "three"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setListAdapter(new NotesListAdapter(getActivity()));
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
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int pos, View view, ViewGroup viewGroup) {
			TextView tView = new TextView(mContext);
			tView.setText(notes[pos]);
			return tView;
		}
	}
}
