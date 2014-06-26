package com.aikje.diabetes3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private static String UID = "NAME";
	private static String PASSWORD = "PASSWORD";
	public static int uidInt = 0;
	public static String passStr = "";
	public static String uidString = "";
	public static Boolean val = false;
	
	EditText field_uid;
	EditText field_pass;
	ImageView login_image;
	// omzetten van de invoer naar string
	static String field_uidString;
	static String field_passString;
	// checkLogin object aanmaken
	private checkLogin check;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor settings = preferences.edit();
    	settings.clear();
        // strings vullen om in te loggen
		final String password = preferences.getString(PASSWORD, "");		
		field_uid = (EditText) findViewById(R.id.uid); 
        field_pass = (EditText)findViewById(R.id.password);

		getInfo();
        
        // zodat er geen typewriter-font wordt gebruikt voor de hint in het wachtwoord-veld.
        field_pass.setTypeface(Typeface.DEFAULT);

        // ImageView voor login button
		login_image = (ImageView) findViewById(R.id.image_button_login);
		
		// onTouchListener voor login_image 
		login_image.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {

	            switch (event.getAction()) {
	                case MotionEvent.ACTION_DOWN: {
	                	// knop donkerder maken als hij aangeraakt wordt
	                    ImageView view = (ImageView) v;
	                    view.getDrawable().setColorFilter(0x77000000,PorterDuff.Mode.SRC_ATOP);
	                    view.invalidate();
	                    
	                	// omzetten van de invoer naar string
	                	field_uidString = field_uid.getText().toString();
	                	field_passString = field_pass.getText().toString();

	    				// if else structuur om te kijken of gebruikernaam en wachtwoord zijn ingevuld
	    				if ((field_uidString == null || field_uidString.equals("")) && (field_passString == null || field_passString.equals(""))) {
	    					settings.putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
	    					showToast("Je hebt geen gebruikersnaam en wachtwoord ingevoerd!"); 
	    				}
	    				else if (field_uidString == null || field_uidString.equals("")) {
	    					settings.putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
	    				    showToast("Je hebt geen gebruikersnaam ingevuld!");
	    				}
	    				else if (field_passString == null || field_passString.equals("")) {
	    					uidInt = Integer.parseInt(field_uidString);
	    					settings.putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
		    				showToast("Je hebt geen wachtwoord ingevoerd!");
	    				}	
	    				else {	   
	    					uidInt = Integer.parseInt(field_uidString);
	    					passStr = preferences.getString(password, "");
	    					settings.putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();

		    				// logje om te laten zien wat er is ingevoerd
		    				Log.d("fragment_input", "Gebruikersnaam: " + field_uidString + " Wachtwoord: " + field_passString + " User ID: " + uidInt);
		    				
		    				try 
		                	{
		    					uidString = field_uidString;
		    					checkLogin();
							} 
		                	catch (Exception e) 
							{
								e.printStackTrace();
							}
		                    break; 
	    				}
	                }
	                case MotionEvent.ACTION_UP:
	                case MotionEvent.ACTION_CANCEL: {
	                	// knop lichter maken als hij niet meer aangeraakt wordt
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
    
	/*
	 * Eerder opgeslagen gegevens invullen in de editText-velden.
	 */
    @SuppressLint("CommitPrefEdits") public void getInfo()
    { 
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor settings = preferences.edit();
    	settings.clear();
		String name = preferences.getString(UID, "");
		String password = preferences.getString(PASSWORD, "");
		field_uid.setText(name);
		field_pass.setText(password);	
    }
    
    /*
     * controleren of de user-ID en password valid zijn.
     */
    public void checkLogin() throws Exception
    {   	
    	new Thread(new Runnable()
    	{
    	    public void run()
    	    {
    	    	check = new checkLogin();
    	    	LoginActivity.val = check.getValidationFromServer();
    	    	Log.d("new val", String.valueOf(val));
    	    	
    	    	if(val == true)
    	        {
    	    		showToast("U bent succesvol ingelogd");
    	        	Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    	        	startActivity(intent);
    	        	Log.i("Login", "Logged in!");
    	        }
    	        else
    	        {
    	        	showToast("U bent niet ingelogd, mogelijk heeft u een foutief wachtwoord ingevoerd of heeft u geen internetverbinding");
    	        	Log.i("Login", "Not logged in!");
    	        }
    	    }
    	}).start();
    }

    /*
     * Toast bericht weergeven.
     */
    public void showToast(final String toast)
	{
	    runOnUiThread(new Runnable() {
	        public void run()
	        {
	            Toast.makeText(LoginActivity.this, toast, Toast.LENGTH_SHORT).show();
	        }
	    });
	} 
}
