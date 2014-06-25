package com.aikje.diabetes3;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	private static final String SETTING_INFOS = "SETTING_Infos";
	private static final String UID = "NAME";
	private static final String PASSWORD = "PASSWORD";
	public static int uidInt = 0;
	public static String passStr = "";
	
	private EditText field_uid;
	private EditText field_pass;
	
	//private Context context = this.getApplicationContext();
	
	// SharedPreferences ophalen uit MainActivity, hierna NAME & PASSWORD vullen met values.
	SharedPreferences settings; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		settings = this.getSharedPreferences(SETTING_INFOS, 0);
		
		field_uid = (EditText) findViewById(R.id.uid); 
        field_pass = (EditText)findViewById(R.id.password);
        
     // informatie uit sharedPreferences ophalen en invullen in de juiste velden.
        getInfo();
        
        // zodat er geen typewriter-font wordt gebruikt voor de hint in het wachtwoord-veld.
        field_pass.setTypeface(Typeface.DEFAULT);

        // ImageView voor login button
		ImageView login_image = (ImageView) findViewById(R.id.image_button_login);
		
		// onTouchListener voor login_image 
		login_image.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {

	            switch (event.getAction()) {
	                case MotionEvent.ACTION_DOWN: {
	                	// knop donkerder maken als hij aangeraakt word
	                    ImageView view = (ImageView) v;
	                    view.getDrawable().setColorFilter(0x77000000,PorterDuff.Mode.SRC_ATOP);
	                    view.invalidate();
	                		
	                	// omzetten van de invoer naar string
	                	String field_uidString = field_uid.getText().toString();
	                	String field_passString = field_pass.getText().toString();
	                	 	
	                	checkLogin();
	                    
	    				// if else structuur om te kijken of gebruikernaam en wachtwoord zijn ingevuld
	    				if ((field_uidString == null || field_uidString.equals("")) && (field_passString == null || field_passString.equals(""))) {
	    					settings.edit().putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
	    					Toast.makeText(getApplicationContext(), "Je hebt geen gebruikersnaam en wachtwoord ingevoerd!", Toast.LENGTH_SHORT).show(); 
	    					Log.d("Login", "Je hebt geen gebruikersnaam en wachtwoord ingevoerd!");
	    				}
	    				else if (field_uidString == null || field_uidString.equals("")) {
	    					settings.edit().putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
	    				    Toast.makeText(getApplicationContext(), "Je hebt geen gebruikersnaam ingevuld!", Toast.LENGTH_SHORT).show(); 
	    				    Log.d("Login", "Je hebt geen gebruikersnaam ingevuld!");
	    				}
	    				else if (field_passString == null || field_passString.equals("")) {
	    					uidInt = Integer.parseInt(field_uidString);
	    					settings.edit().putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
		    				Toast.makeText(getApplicationContext(), "Je hebt geen wachtwoord ingevoerd! User ID: " + uidInt, Toast.LENGTH_SHORT).show(); 
		    				Log.d("Login", "Je hebt geen wachtwoord ingevoerd! User ID: " + uidInt);
	    				}	
	    				else {	   
	    					uidInt = Integer.parseInt(field_uidString);
	    					settings.edit().putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
		    				// toast om te laten zien wat er is ingevoerd
		    				//Toast.makeText(null, "Gebruikersnaam: " + field_uidString + " Wachtwoord: " + field_passString + " User ID " + uidInt, Toast.LENGTH_LONG).show();
		    				// logje om te laten zien wat er is ingevoerd
		    				Log.d("fragment_input", "Gebruikersnaam: " + field_uidString + " Wachtwoord: " + field_passString + " User ID: " + uidInt);
	
		    		        //
		    				// connectie maken met database, toast geven als het gelukt is.
		    				//
		    		        
		    		        Log.d("Login", "login button geklikt, gegevens opgeslagen in SharedPreferences");
		    		        //Toast.makeText(context.getApplicationContext(), "U bent verbonden met de database", Toast.LENGTH_SHORT).show();
		    		        //Toast.makeText(context.getApplicationContext(), "U bent NIET verbonden met de database", Toast.LENGTH_SHORT).show();
	    				}
	                    break;    
	                }
	                case MotionEvent.ACTION_UP:
	                case MotionEvent.ACTION_CANCEL: {
	                	// knop lichter maken
	                    ImageView view = (ImageView) v;
	                    view.getDrawable().clearColorFilter();
	                    view.invalidate();
	                    break;
	                }
	            }
	            return true;
	        }
	    });	
	}
    
    public void getInfo()
    { 
		//settings = this.getSharedPreferences(SETTING_INFOS, 0);
		String name = settings.getString(UID, "");
		String password = settings.getString(PASSWORD, "");
		try 
		{
			uidInt = Integer.parseInt(name);
			passStr = password;
		}
		catch(Exception e)
		{
			uidInt = 0;
			Log.e("get Info", e.toString());
		}
		field_uid.setText(name);
		field_pass.setText(password);	
    }
    
    public void checkLogin()
    {
    	CheckLoginTask clt = new CheckLoginTask(this, getApplicationContext());
    	Boolean val = false;
    	
        try
	    {
	    	clt.execute();
	    	Thread.sleep(1000);
	    	val = clt.getData();
	    }
	    catch(Exception e)
	    {
	    	//Log.d("Login", e.toString());
	    	//throw new Exception(e);
	    }
        
        if(val == true)
        {
        	Toast.makeText(getApplicationContext(), "U bent ingelogt", Toast.LENGTH_SHORT).show();
        	//Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        	//startActivity(intent);
        	Log.i("Login", "Logged in!");
        }
        else
        {
        	Toast.makeText(getApplicationContext(), "U bent niet ingelogt", Toast.LENGTH_SHORT).show();
        	Log.i("Login", "Not logged in!");
        }
        
        //verwijder dit wanneer check klaar is!
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    	startActivity(intent);
    }

}
