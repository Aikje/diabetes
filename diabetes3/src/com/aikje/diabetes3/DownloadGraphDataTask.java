package com.aikje.diabetes3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadGraphDataTask extends AsyncTask<ArrayList<Double>, Void, ArrayList<Double>>
{
	private ArrayList<Double> data = new ArrayList<Double>();
	private String datum = "";
	ProgressDialog dialog;
	private static InputStream is = null;
	private static String getUid = Integer.toString(LoginActivity.uidInt);
	private static String urlGraphData = "http://recoma.samba-ti.nl/php/graphData.php" + "?uid=" + getUid;
	public DownloadGraphDataTask(Fragment_graph obj, Activity activity)
	{
		dialog = new ProgressDialog(activity);
	}
	
	protected void onPreExecute()
	{
		this.dialog.setMessage("Download gegevens...");
		this.dialog.show();
	}
	
	protected ArrayList<Double> doInBackground(ArrayList<Double>...arrayLists ) 
	{
		Log.d("AsyncTask", "doInBackground(DGDT)");
		try 
		{
			// gegevens uit LoginActivity halen en deze vervolgens meesturen naar de server voor de query
			getUid = Integer.toString(LoginActivity.uidInt);
			urlGraphData = "http://recoma.samba-ti.nl/php/graphData.php" + "?uid=" + getUid;
			
			Log.d("JsonData", getJSONFromUrl(urlGraphData).toString());
			getGraphDataFromJSON(getJSONFromUrl(urlGraphData));
		} 
		catch (JSONException e) 
		{
			Log.d("JsonData", e.toString());
			e.printStackTrace();
		}

		return data;
	}
	
	protected void onPostExecute(ArrayList<Double> data) 
	{
		
		Log.d("AsyncTask", "onPostExecute(DGDT)");
		Log.d("AsyncTask", data.toString());
		
		if (dialog.isShowing())
		{
			dialog.dismiss();
		}
	}
	
	protected static JSONArray getJSONFromUrl(String url) throws JSONException 
	{
		JSONArray jArr = null;
		String jsonStr = "";
		try 
		{
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder strBuilder = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null)
			{
				strBuilder.append(line).append("\n");
			}
			is.close();
			jsonStr = strBuilder.toString();
		}
		catch(Exception e)
		{
			Log.e("JSONParser", e.toString());
		}
		
		try
		{
			jArr = new JSONArray(jsonStr);
		}
		catch(Exception e)
		{
			Log.e("JSONParser", e.toString());
		}
		return jArr;
	}
	
	protected void getGraphDataFromJSON(JSONArray jArr) throws JSONException 
	{
		for(int i=0; i < jArr.length(); ++i)
		{
			JSONObject jObj = jArr.getJSONObject(i);
			data.add(jObj.getDouble("bloedsuiker"));
			datum = jObj.getString("datum");
		}
	}
	
	protected ArrayList<Double> getData()
	{
		return data;
	}
	
	protected String getLastDate()
	{
		return datum;
	}
}