package com.aikje.diabetes3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class InputData {
	static String UID = "";
	
	private static String urlInputData = "http://recoma.samba-ti.nl/php/inputData.php";
	
	public void sendDataToDatabase(double d, String c)
	{
		UID = LoginActivity.field_uidString;
		if(!(d < 2.0 || d > 12.0))
		{
			try 
			{
				DefaultHttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(urlInputData);
				
				List<NameValuePair> nvp = new ArrayList<NameValuePair>(3);
				nvp.add(new BasicNameValuePair("uid", UID));
				nvp.add(new BasicNameValuePair("waarde", String.valueOf(d)));
				nvp.add(new BasicNameValuePair("commentaar", c));
				post.setEntity(new UrlEncodedFormEntity(nvp));
				
				@SuppressWarnings("unused")
				HttpResponse response = client.execute(post);
				
	
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
