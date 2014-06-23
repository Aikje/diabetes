package com.aikje.diabetes3;

import java.util.ArrayList;

import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadDataTask extends AsyncTask<ArrayList<Double>, Void, ArrayList<Double>> 
{
	ArrayList<Double> dArray = new ArrayList<Double>();
	JSONParser jParser = new JSONParser();
			
	protected ArrayList<Double> doInBackground(ArrayList<Double>... params) 
	{
		Log.d("AsyncTask", "doInBackground...");
		double[] data = null;
		try 
		{
			Log.d("JsonData", jParser.getJSONFromUrl().toString());
			data = jParser.getDataFromJSON(jParser.getJSONFromUrl());
		} 
		catch (JSONException e) 
		{
			Log.d("JsonData", e.toString());
			e.printStackTrace();
		}
    	
    	for(int i = 0; i <= 6; ++i)
    	{
    		String log = Double.toString(data[i]);
    		Log.d("Waarde data:", log);
    		dArray.add(data[i]);
    	}
    	return dArray;
	}
	
	protected void onProgressUpdate()
	{
		
    	
	}
	
	protected ArrayList<Double> onPostExecute() 
	{
		Log.d("AsyncTask", "onPostExecute...");
		
		return dArray;
	}
}

	


