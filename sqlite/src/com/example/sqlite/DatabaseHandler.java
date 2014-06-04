/**
 * Code ontwikkeld met behulp van:
 * http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 */

package com.example.sqlite;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper{
	
	private Context localContext = null;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "recoma.db";
	private static final String TABLE_METING = "meting";
	private static final String METING_MID = "mid";
	private static final String METING_PID = "pid";
	private static final String METING_DATUM = "datum";
	private static final String METING_STATUS = "status";
	private static final String METING_BLOEDSUIKER = "bloedsuiker";
	private static final String METING_COMMENTAAR = "commentaar";
	private String httpposturl = "http://recoma.samba-ti.nl/php/syncRecord.php";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		localContext = context;
	}

	/**
	 * In de onCreate wordt de table Meting aangemaakt.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_METING_TABLE = "CREATE TABLE " + TABLE_METING + "(" 
									+ METING_MID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
									+ METING_PID + " INTEGER,"
									+ METING_DATUM + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
									+ METING_STATUS + " INTEGER,"
									+ METING_BLOEDSUIKER + " INTEGER,"
									+ METING_COMMENTAAR + " TEXT" + ")";
		db.execSQL(CREATE_METING_TABLE);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Drop table als hij al bestaat.
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_METING);
		
		//Maak tabellen weer aan
		onCreate(db);	
	}

	/**
	 * Voeg nieuwe meting toe.
	 * Mid is AUTO_INCREMENT dus die wordt niet meegezonden.
	 * Pid staat vast aan de ingelogde patiënt en moet met een methode worden opgehaald.
	 * Datum wordt automatisch ingevult.
	 */
	public void addMeting(int status, int bloedsuiker, String commentaar)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(METING_PID, getLocalPid());
		values.put(METING_STATUS, status);
		values.put(METING_BLOEDSUIKER, bloedsuiker);
		values.put(METING_COMMENTAAR, commentaar);
		
		db.insert(TABLE_METING, null, values);
		db.close();
	}
	
	/**
	 * Methode om een List aan te maken en deze te vullen met alle data van de tabel meting.
	 */
	public ArrayList<ArrayList<NameValuePair>> listMeting()
	{
		ArrayList<ArrayList<NameValuePair>> totalnvPairs = new ArrayList<ArrayList<NameValuePair>>();
		ArrayList<NameValuePair> nvPairs;
		
		String query = "SELECT * FROM " + TABLE_METING;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		cursor.moveToFirst();
		while(cursor.isAfterLast() == false)
		{
			nvPairs = new ArrayList<NameValuePair>(6);
			nvPairs.add(new BasicNameValuePair("mid", String.valueOf(cursor.getInt(0))));
			nvPairs.add(new BasicNameValuePair("pid", String.valueOf(cursor.getInt(1))));
			nvPairs.add(new BasicNameValuePair("datum", cursor.getString(2)));
			nvPairs.add(new BasicNameValuePair("status", String.valueOf(cursor.getInt(3))));
			nvPairs.add(new BasicNameValuePair("bloedsuiker", String.valueOf(cursor.getInt(4))));
			nvPairs.add(new BasicNameValuePair("commentaar", cursor.getString(5)));
			totalnvPairs.add(nvPairs);
		}
		
		return totalnvPairs;
	}
	
	/**
	 * Methode om het aantal metingen in de SQLite database te tellen.
	 */
	public int countMeting()
	{
		String query = "SELECT * FROM " + TABLE_METING;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		cursor.close();
		return cursor.getCount();
	}
	
	/**
	 * Methode om tabel meting leeg te maken.
	 */
	public void resetDatabase()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_METING,null,null);
	}
	
	/**
	 * verstuur een encoded ArrayList met raw data naar een PHP script op de server.
	 * Deze PHP script kijkt of de verzonden record al bestaat, zo niet dan zal deze worden toegevoegd aan de database.
	 */
	private void syncRecord(ArrayList<NameValuePair> data)
	{
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(httpposturl);
			httppost.setEntity(new UrlEncodedFormEntity(data));
			HttpResponse response = httpclient.execute(httppost);
			Log.i("syncRecord", response.getStatusLine().toString());
			String executeCode = EntityUtils.toString(response.getEntity());
			Log.i("webResponse", executeCode);
		}
		catch(Exception e)
		{
			Log.e("syncRecord", "Error: " + e.toString());
			Toast.makeText(null, "Kan data niet versturen: " + e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
	
	/*
	 * Sync gestart door de applicatie.
	 */
	public void syncDatabase()
	{
		if(isNetworkConnected() == true)
		{
			for(ArrayList<NameValuePair> nvp : listMeting())
			{
				syncRecord(nvp);
			}
		}
		else
		{
			//Geen netwerkverbinding
			Log.e("syncDatabase", "No network connection");
			Toast.makeText(null, "Geen netwerkconnectie", Toast.LENGTH_SHORT).show();
		}
	}
	
	/*
	 * Sync gestart door de gebruiker.
	 */
	public void forceSyncDatabase()
	{
		if(isNetworkConnected() == true)
		{
			
		}
		else
		{
			//Geen netwerkverbinding
			Log.e("syncDatabase", "No network connection");
			Toast.makeText(null, "Geen netwerkconnectie", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * Methode om te kijken of er een netwerkverbinding is.
	 */
	private boolean isNetworkConnected() 
	{
		boolean isConnected = false;
		ConnectivityManager cm = (ConnectivityManager) localContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
		for (NetworkInfo ni : networkInfos)
		{
			if(ni.isConnected())
			{
				isConnected = true;
				break;
			}
		}
		
		return isConnected;
	}
	
	/**
	 * Dummy methode voor het verkrijgen van de pid van de ingelogde gebruiker
	 */
	private int getLocalPid()
	{
		return 1;
	}
}
