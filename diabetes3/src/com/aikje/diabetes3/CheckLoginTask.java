package com.aikje.diabetes3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class CheckLoginTask extends AsyncTask<Boolean, Void, Boolean>
{
	public boolean finished = false;
	private Boolean data = false;
	public ProgressDialog dialog;
	private static String urlCheckLogin = "http://recoma.samba-ti.nl/php/loginCheck.php" + "?uid=" + Integer.toString(LoginActivity.uidInt) + "&pass=" + LoginActivity.passStr;
	private static InputStream is = null;

	public CheckLoginTask(LoginActivity obj, Context context)
	{
		dialog = new ProgressDialog(context);
	}
	
	protected void onPreExecute()
	{
		super.onPreExecute();
		dialog.setMessage("Login verifiëren...");
		dialog.show();
	}
	
	protected Boolean doInBackground(Boolean...booleans ) 
	{
		Log.d("AsyncTask", "doInBackground(CLT)");
		
		try 
		{
			Log.d("JsonData", getJSONFromUrl(urlCheckLogin));
			getLoginValidation(getJSONFromUrl(urlCheckLogin));	
		} 
		catch (JSONException e) 
		{
			Log.d("JsonData", e.toString());
			e.printStackTrace();
		}
		return data;
	}
		
	protected void onPostExecute(Boolean data) 
	{			
		Log.d("AsyncTask", "onPostExecute(CLT)");
		Log.d("AsyncTask", data.toString());
		
		if (dialog.isShowing())
		{
			super.onPostExecute(true);
			dialog.dismiss();
		}
	}
	
	protected static String getJSONFromUrl(String url) throws JSONException 
	{
		String jStr = null;
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
			jStr = strBuilder.toString();
			Log.d("jsonFromURL", jStr);
		}
		catch(Exception e)
		{
			Log.e("JSONParser", e.toString());
		}
		return jStr;
	}
	
	protected void getLoginValidation(String jStr) throws JSONException
	{
		boolean val = Boolean.parseBoolean(jStr);
		String log = jStr + " -> " + Boolean.toString(val); 
		Log.d("getLoginValidation", log);
		data = val;
		finished = true;
	}

	protected boolean getData()
	{
		return data;
	}
}

	


