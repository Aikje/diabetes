package com.aikje.diabetes3;

import java.util.Calendar;

import android.content.Intent;
import android.net.Uri;
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

		/*
		// maak een afspraak in de kalender
		Button openKalender = (Button) rootView.findViewById(R.id.openKalender);
		openKalender.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(Intent.ACTION_EDIT);  
				intent.setType("vnd.android.cursor.item/event");
				intent.putExtra("title", "Bloedsuikerwaarden invoeren");
				intent.putExtra("description", "Meet de bloedsuikerwaarden en voer deze in in de ZorgZaak applicatie.");
				intent.putExtra("beginTime", fragment_input.getCurrentTimeStamp());
				startActivity(intent);
				
				
				// toast om te laten zien wat er is ingevoerd
				Toast.makeText(getActivity(), "Kalender geopend", Toast.LENGTH_LONG).show();
				// logje om te laten zien wat er is ingevoerd
				Log.d("fragment_calendar", "Kalender geopend");
			}
		});
		*/
		
		// maak een alarm in de alarm-app
		Button maakHerinnering = (Button) rootView.findViewById(R.id.maakHerinnering);
		maakHerinnering.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(Intent.ACTION_EDIT);  
				intent.setType("vnd.android.cursor.item/event");
				intent.putExtra("title", "Bloedsuikerwaarden invoeren");
				intent.putExtra("description", "Meet de bloedsuikerwaarden en voer deze in in de ZorgZaak applicatie.");
				intent.putExtra("beginTime", fragment_input.getCurrentTimeStamp());
				startActivity(intent);
				
				
				// toast om te laten zien wat er is ingevoerd
				Toast.makeText(getActivity(), "Herinnering wordt gemaakt", Toast.LENGTH_LONG).show();
				// logje om te laten zien wat er is ingevoerd
				Log.d("fragment_calendar", "Herinnering wordt gemaakt");
			}
		});
		
		Button openKalender = (Button) rootView.findViewById(R.id.openKalender);
		openKalender.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Calendar today = Calendar.getInstance();

		        Uri uriCalendar = Uri.parse("content://com.android.calendar/time/" + String.valueOf(today.getTimeInMillis()));
		        Intent intentCalendar = new Intent(Intent.ACTION_VIEW,uriCalendar);

		        //Use the native calendar app to view the date
		        startActivity(intentCalendar);
				
				// toast om te laten zien wat er is ingevoerd
				Toast.makeText(getActivity(), "Kalender geopend", Toast.LENGTH_LONG).show();
				// logje om te laten zien wat er is ingevoerd
				Log.d("fragment_calendar", "Kalender geopend");
			}
		});
		
		

				
		return rootView;

	}
	
}
