package com.aikje.diabetes3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class fragment_home extends Fragment{
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		
		
		Button button1 = (Button) rootView.findViewById(R.id.button1);
		//OnClick listener
		button1.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				// uitvoerbare code voor button1
				Toast.makeText(getActivity(), "Invoeren", Toast.LENGTH_LONG).show();
				Log.d("fragment_home", "Button1 clicked");
			}
		});
		
		Button button2 = (Button) rootView.findViewById(R.id.button2);
		//OnClick listener
		button2.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				// uitvoerbare code voor button2
				Toast.makeText(getActivity(), "Weergeven", Toast.LENGTH_LONG).show();
				Log.d("fragment_home", "Button2 clicked");
			}
		});
		
		Button button3 = (Button) rootView.findViewById(R.id.button3);
		//OnClick listener
		button3.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				// uitvoerbare code voor button3
				Toast.makeText(getActivity(), "Agenda", Toast.LENGTH_LONG).show();
				Log.d("fragment_home", "Button3 clicked");
			}
		});
		
		return rootView;
	}
	

}
