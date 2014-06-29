package com.aikje.diabetes3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class Fragment_input extends Fragment{

	EditText waardeInvoer;
	EditText commentaarInvoer;
	Button verzendGegevens;
	String inputID = Integer.toString(LoginActivity.uidInt);
	private InputData input;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActivity().supportInvalidateOptionsMenu();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_input, container, false);

		getActivity().supportInvalidateOptionsMenu();
		
		waardeInvoer = (EditText)rootView.findViewById(R.id.waardeInvoer);
		commentaarInvoer = (EditText)rootView.findViewById(R.id.commentaarInvoer);		
		verzendGegevens = (Button) rootView.findViewById(R.id.verzendGegevens);
		
		onClickListener();
		
		return rootView;
	}

	/*
	 * onClickListener voor de verzendknop
	 */
	void onClickListener()
	{
		verzendGegevens.setOnClickListener(new View.OnClickListener()
		{
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
					// logje om te laten zien wat er is ingevoerd
					Log.d("fragment_input", "Waarde: " + waardeInvoerString + " Commentaar: " + commentaarInvoerString + " ID: " + inputID);
					// omzetten van waarde naar Double
					final Double data = Double.parseDouble(waardeInvoer.getText().toString());
					final String comment = commentaarInvoer.getText().toString();
					if(!(data < 2.0 || data > 10.0))
					{
						Toast.makeText(getActivity(), "Gegevens worden verzonden...", Toast.LENGTH_LONG).show();
						Log.d("Input", "(data)" + data);
						Log.d("input", "(comment)" + comment);
						new Thread(new Runnable()
				    	{
				    	    public void run()
				    	    {
				    	    	input = new InputData();
				    	    	input.sendDataToDatabase(data, comment);
				    	    	Log.d("Input", "Data verzonden");
				    	    }
				    	}).start();
						waardeInvoer.setText("");
						commentaarInvoer.setText("");
						// view veranderen naar grafiek waar nieuwe waarde in staat
						changeFragment();
					}
					else 
					{
						Toast.makeText(getActivity(), "Je hebt een foutieve waarde ingevoerd", Toast.LENGTH_LONG).show();
					}
				    return;
				}
				
				else {
					// omzetten van waarde naar Double
					final Double data = Double.parseDouble(waardeInvoer.getText().toString());
					final String comment = commentaarInvoer.getText().toString();
					if(!(data < 2.0 || data > 10.0))
					{
						Toast.makeText(getActivity(), "Gegevens worden verzonden...", Toast.LENGTH_LONG).show();
						Log.d("Input", "(data)" + data);
						Log.d("input", "(comment)" + comment);
						new Thread(new Runnable()
				    	{
				    	    public void run()
				    	    {
				    	    	input = new InputData();
				    	    	input.sendDataToDatabase(data, comment);
				    	    	Log.d("Input", "Data verzonden");
				    	    }
				    	}).start();
						waardeInvoer.setText("");
						commentaarInvoer.setText("");
						// view veranderen naar grafiek waar nieuwe waarde in staat
						changeFragment();
					}
					else
					{
						Toast.makeText(getActivity(), "Je hebt een foutieve waarde ingevoerd", Toast.LENGTH_LONG).show();
					}
					// logje om te laten zien wat er is ingevoerd
					Log.d("fragment_input", "Waarde: " + waardeInvoerString + " Commentaar: " + commentaarInvoerString + " ID: " + inputID);
				}
			}
		});
	};
	
	/*
	 * Na het verzenden van de data de fragmentview veranderen naar fragment_graph
	 */
	private void changeFragment() {
		final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
		ft.replace(R.id.main, Fragment.instantiate(getActivity(), "com.aikje.diabetes3.Fragment_graph")); 
		ft.commit();
		getActivity().supportInvalidateOptionsMenu();
	}
}