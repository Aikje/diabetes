package com.aikje.diabetes3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class CheckLogin {
	
	static String UID = "";
	static String PASSWORD = "";
	
	private static String urlCheckLogin = "";
	private static InputStream is = null;
	
	public boolean getValidationFromServer()
	{
		UID = LoginActivity.field_uidString;
		PASSWORD = LoginActivity.field_passString;
		urlCheckLogin = "http://recoma.samba-ti.nl/php/loginCheck.php";
		
		String jStr = null;
		boolean jBool = false;
		
		try 
		{
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlCheckLogin);
			
			List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
			nvp.add(new BasicNameValuePair("uid", UID));
			nvp.add(new BasicNameValuePair("pass", PASSWORD));
			post.setEntity(new UrlEncodedFormEntity(nvp));
			
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

			if(jInt == 1)
			{
				jBool = true;
			}
			else
			{
				jBool = false;
			}
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
	
	public String getUserName()
	{
		UID = LoginActivity.field_uidString;
		PASSWORD = LoginActivity.field_passString;
		urlCheckLogin = "http://recoma.samba-ti.nl/php/getName.php";
		
		String name = null;
		
		try 
		{
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlCheckLogin);
			
			List<NameValuePair> nvp = new ArrayList<NameValuePair>(2);
			nvp.add(new BasicNameValuePair("uid", UID));
			nvp.add(new BasicNameValuePair("pass", PASSWORD));
			post.setEntity(new UrlEncodedFormEntity(nvp));
			
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

			name = strBuilder.toString().replace("\"", "");
			System.out.print(strBuilder.toString());
		}
		catch(Exception e)
		{
			Log.e("JSONParser", e.toString());
		}
		Log.d("checkLogin (name)", name);
		
		return name;
	}
}