package com.aikje.diabetes3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class fragment_calendar extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

		Button openKalender = (Button) rootView.findViewById(R.id.openKalender);
		//OnClick listener
		openKalender.setOnClickListener(new View.OnClickListener()
		{
			/**
			 * @return knop om een kalender intent te openen.
			 */
			public void onClick(View v)
			{
				Intent i = new Intent();
				i.setClassName("com.android.calendar","com.android.calendar.AgendaActivity");
				startActivity(i);
				
				
				// toast om te laten zien wat er is ingevoerd
				Toast.makeText(getActivity(), "Kalender gestart", Toast.LENGTH_LONG).show();
				// logje om te laten zien wat er is ingevoerd
				Log.d("fragment_calendar", "Kalender gestart");
			}
		});
		
		return rootView;

	}
	
}
