package com.aikje.diabetes3;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Aike Brakel
 */

@SuppressLint("SimpleDateFormat") public class fragment_input extends Fragment{

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
		@SuppressWarnings("unused")
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
				
				// checken of er een waarde is ingevoerd, anders error geven
				if (waardeInvoerString == null || waardeInvoerString.equals("")) {
				    Toast.makeText(getActivity(), "Je hebt geen waarde ingevoerd!", Toast.LENGTH_SHORT).show();
				    return;
				}
				
				// checken of er commentaar is ingevoerd, anders doorgaan zonder commentaar
				if (commentaarInvoerString == null || commentaarInvoerString.equals("")) {
				    // Toast.makeText(getActivity(), "Je hebt geen commentaar ingevoerd!", Toast.LENGTH_SHORT).show();
					
					// omzetten van waarde naar integer
					Double waardeInvoerDouble = Double.parseDouble(waardeInvoer.getText().toString());
					
					fragment_graph.addValueToGraphData(waardeInvoerDouble);
					
					// toast om te laten zien wat er is ingevoerd
					Toast.makeText(getActivity(), "Waarde: " + waardeInvoerString + " Commentaar: " + commentaarInvoerString, Toast.LENGTH_LONG).show();
					// logje om te laten zien wat er is ingevoerd
					Log.d("fragment_input", "Waarde: " + waardeInvoerString + " Commentaar: " + commentaarInvoerString);
					
				    return;
				}
				
				else {
					// omzetten van waarde naar integer
				Double waardeInvoerDouble = Double.parseDouble(waardeInvoer.getText().toString());
				
				fragment_graph.addValueToGraphData(waardeInvoerDouble);
				
				// toast om te laten zien wat er is ingevoerd
				Toast.makeText(getActivity(), "Waarde: " + waardeInvoerString + " Commentaar: " + commentaarInvoerString, Toast.LENGTH_LONG).show();
				// logje om te laten zien wat er is ingevoerd
				Log.d("fragment_input", "Waarde: " + waardeInvoerString + " Commentaar: " + commentaarInvoerString);
				}
			}
		});

		return rootView;
	}

}
