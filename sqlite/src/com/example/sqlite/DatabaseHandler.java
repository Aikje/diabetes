/**
 * Code ontwikkeld met behulp van:
 * http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 */

package com.example.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	
	//Variabelen
	//Database versie
	private static final int DATABASE_VERSION = 1;
	
	//Database naam
	private static final String DATABASE_NAME = "recoma.db";
	
	//Database table + kollomen
	private static final String TABLE_METING = "meting";
	//De mid van de sqlite database moet niet verzonden worden wanneer een meting naar de centrale database gaat!
	private static final String METING_MID = "mid";
	//De pid die wordt verstuurd naar de centrale database moet altijd de id van de ingelogde patiënt zijn!
	private static final String METING_PID = "pid";
	//Timestamp van de genomen meting.
	private static final String METING_DATUM = "datum";
	//Gekozen status van de patiënt, er staan in de app verscheidene statussen die gelinkt zijn aan een integer waarde.
	private static final String METING_STATUS = "status";
	//Bloedsuikerspiegel van de patiënt tijdens de meting.
	private static final String METING_BLOEDSUIKER = "bloedsuiker";
	//Commentaar toegevoegd aan de meting door de patiënt.
	private static final String METING_COMMENTAAR = "commentaar";
	
	//private SQLiteDatabase localdb;
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//this.localdb = this.getWritableDatabase();
	}

	//Maak alle tables aan
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_METING_TABLE = "CREATE TABLE " + TABLE_METING + "(" 
									+ METING_MID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
									+ METING_PID + " INTEGER,"
									+ METING_DATUM + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
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
	
	//Voeg nieuwe meting toe
	public void addMeting(int pid, String datum, int status, int bloedsuiker, String commentaar)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(METING_PID, pid);
		values.put(METING_DATUM, datum);
		values.put(METING_STATUS, status);
		values.put(METING_BLOEDSUIKER, bloedsuiker);
		values.put(METING_COMMENTAAR, commentaar);
		
		db.insert(TABLE_METING, null, values);
		db.close();
	}
	
	public List<Meting> listMeting()
	{
		List<Meting> metingen = new ArrayList<Meting>();
		
		String query = "SELECT * FROM " + TABLE_METING;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		cursor.moveToFirst();
		while(cursor.isAfterLast() == false)
		{
			Meting meting = new Meting();
			meting.setPid(cursor.getInt(1));
			meting.setDatum(cursor.getString(2));
			meting.setStatus(cursor.getInt(3));
			meting.setBloedsuiker(cursor.getInt(4));
			meting.setCommentaar(cursor.getString(5));
			metingen.add(meting);
		}
		
		return metingen;
	}
	
	public int countMeting()
	{
		String query = "SELECT * FROM " + TABLE_METING;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		cursor.close();
		return cursor.getCount();
	}
}
