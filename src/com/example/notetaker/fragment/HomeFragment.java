package com.example.notetaker.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.notetaker.NoteActivity;
import com.example.notetaker.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements OnClickListener {

	Button newNoteButton;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		newNoteButton = (Button) rootView.findViewById(R.id.newNoteButton);
		newNoteButton.setOnClickListener(this);
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
