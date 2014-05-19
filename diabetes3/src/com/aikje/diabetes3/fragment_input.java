package com.aikje.diabetes3;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class fragment_input extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_input, container, false);

		final EditText waardeInvoer = (EditText)rootView.findViewById(R.id.waardeInvoer);
		final EditText commentaarInvoer = (EditText)rootView.findViewById(R.id.commentaarInvoer);
		final int crossWaardeInteger = 0;
		
		Button verzendGegevens = (Button) rootView.findViewById(R.id.verzendGegevens);
		//OnClick listener
		verzendGegevens.setOnClickListener(new View.OnClickListener()
		{
			/**
			 * @return Verzendknop in fragment_input
			 */
			public void onClick(View v)
			{
				// omzetten van de invoer naar string
				String waardeInvoerString = waardeInvoer.getText().toString();
				String commentaarInvoerString = commentaarInvoer.getText().toString();
				
				// omzetten van waarde naar integer
				Integer waardeInvoerInteger = Integer.parseInt(waardeInvoer.getText().toString());
				
				// toast om te laten zien wat er is ingevoerd
				Toast.makeText(getActivity(), "Waarde: " + waardeInvoerString + ". Commentaar: " + commentaarInvoerString + ". Timestamp: " + getCurrentTimeStamp(), Toast.LENGTH_LONG).show();
				// logje om te laten zien wat er is ingevoerd
				Log.d("fragment_home", "Waarde: " + waardeInvoerString + ". Commentaar: " + commentaarInvoerString + ". Timestamp: " + getCurrentTimeStamp());
			}
		});

		return rootView;
	}
	/**
	 * @return Timestamp in het formaat: yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTimeStamp(){
	    try {

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

	        return currentTimeStamp;
	    } catch (Exception e) {
	        e.printStackTrace();

	        return null;
	    }
	}
}
