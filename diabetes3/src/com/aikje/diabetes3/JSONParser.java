package com.aikje.diabetes3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray jArr = null;
	static String json = "";
	static String url = "http://recoma.samba-ti.nl/php/aikeAppTest.php";

	// constructor
	public JSONParser() {

	}

	@SuppressLint("NewApi") public String getJSONFromUrl() throws JSONException {

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
//			HttpGet get = new HttpGet(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			JSONArray json = new JSONArray(httpResponse);
//			HttpResponse httpResponse = httpClient.execute(get);
//			HttpEntity httpEntity = httpResponse.getEntity();
//			is = httpEntity.getContent();			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
		
		/*
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;
		*/
	}
	
	@SuppressWarnings("null")
	public double[] getDataFromJSON(String jStr) 
	{
		String[] data = null;
		double[] waarden = null;
		String[] dataStr = jStr.split("},");
			
		for (int i = 0; i < dataStr.length; ++i)
		{
			String substr[] = dataStr[i].split("bloedsuiker");
			data[i] = substr[1].substring(2, 6);
			waarden[i] = Double.parseDouble(data[i]);
		}
		
		return waarden;
	}
}