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

import android.util.Log;

public class checkLogin {
	
	static String UID = "";
//	static String PASSWORD = LoginActivity.field_passString;
	static String PASSWORD = "";
	
	private static String urlCheckLogin = "";
	private static InputStream is = null;
	
	public boolean getValidationFromServer()
	{
		UID = LoginActivity.field_uidString;
		PASSWORD = LoginActivity.field_passString;
		urlCheckLogin = "http://recoma.samba-ti.nl/php/loginCheck.php" + "?uid=" + UID + "&pass=" + PASSWORD;
		String jStr = null;
		boolean jBool = false;
		try 
		{
			Log.d("URL", urlCheckLogin);
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlCheckLogin);
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
				strBuilder.append(line);
			}
			is.close();

			jStr = strBuilder.toString();
			int jInt = Integer.parseInt(jStr.replace("\"", ""));
			System.out.print(strBuilder.toString());
			
			//jBool = Boolean.valueOf(jString);
			//jBool = Boolean.parseBoolean(jStr);

			if(jInt == 1)
			{
				jBool = true;
			}
			else
			{
				jBool = false;
			}
			//jBool = true;
			Log.d("checkLogin (jStr)", jStr);
			//Log.d("checkLogin (jString)", String.valueOf(jInt));
			Log.d("checkLogin (jBool)", String.valueOf(jBool));
			
		}
		catch(Exception e)
		{
			Log.e("JSONParser", e.toString());
		}

		return jBool;
	}
}
