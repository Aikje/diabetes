package com.aikje.diabetes3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

@SuppressLint("CommitPrefEdits") public class LoginActivity extends Activity {

//	private static String SETTING_INFOS = "SETTING_Infos";
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
	static String field_uidString = "";
	static String field_passString = "";
	
	//private Context context = this.getApplicationContext();

	private checkLogin check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor settings = preferences.edit();
    	settings.clear();
        // strings aanmaken om te loggen
        final String name = preferences.getString(UID, "");
		final String password = preferences.getString(PASSWORD, "");
//		
		Log.d("Stored Name", "" + name);
//		settings = getSharedPreferences();

		
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
	                	// knop donkerder maken als hij aangeraakt word
	                    ImageView view = (ImageView) v;
	                    view.getDrawable().setColorFilter(0x77000000,PorterDuff.Mode.SRC_ATOP);
	                    view.invalidate();
	                    
	                	// omzetten van de invoer naar string
	                	field_uidString = field_uid.getText().toString();
	                	field_passString = field_pass.getText().toString();
	                	 	
//	                	getInfo();
	                	
	                	

	                	
	    				// if else structuur om te kijken of gebruikernaam en wachtwoord zijn ingevuld
	    				if ((field_uidString == null || field_uidString.equals("")) && (field_passString == null || field_passString.equals(""))) {
	    					settings.putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
		            		Log.d("IF ELSE", "Stored ID: " + name + " Password: " + password);
	    					Toast.makeText(getApplicationContext(), "Je hebt geen gebruikersnaam en wachtwoord ingevoerd!", Toast.LENGTH_SHORT).show(); 
	    					Log.d("Login", "Je hebt geen gebruikersnaam en wachtwoord ingevoerd!");
	    				}
	    				else if (field_uidString == null || field_uidString.equals("")) {
	    					settings.putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
		            		Log.d("IF ELSE", "Stored ID: " + name + " Password: " + password);
	    				    Toast.makeText(getApplicationContext(), "Je hebt geen gebruikersnaam ingevuld!", Toast.LENGTH_SHORT).show(); 
	    				    Log.d("Login", "Je hebt geen gebruikersnaam ingevuld!");
	    				}
	    				else if (field_passString == null || field_passString.equals("")) {
	    					uidInt = Integer.parseInt(field_uidString);
	    					settings.putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
		            		Log.d("IF ELSE", "Stored ID: " + name + " Password: " + password);
		    				Toast.makeText(getApplicationContext(), "Je hebt geen wachtwoord ingevoerd! User ID: " + uidInt, Toast.LENGTH_SHORT).show(); 
		    				Log.d("Login", "Je hebt geen wachtwoord ingevoerd! User ID: " + uidInt);
	    				}	
	    				else {	   
	    					uidInt = Integer.parseInt(field_uidString);
	    					passStr = preferences.getString(password, "");
	    					settings.putString(UID, field_uidString);
	    					settings.putString(PASSWORD, field_passString);
	    					
	    					Log.d("After get", "" + preferences.getString(UID, ""));
	    					Log.d("After get", preferences.getString(PASSWORD, ""));

	    					settings.commit();
		            		Log.d("IF ELSE", "Stored ID: " + name + " Password: " + password);

		    				// logje om te laten zien wat er is ingevoerd
		    				Log.d("fragment_input", "Gebruikersnaam: " + field_uidString + " Wachtwoord: " + field_passString + " User ID: " + uidInt);
	
		    		        //
		    				// connectie maken met database, toast geven als het gelukt is.
		    				//
		    		        
//		    		        Log.d("Login", "login button geklikt, gegevens opgeslagen in SharedPreferences");
		    		        //Toast.makeText(context.getApplicationContext(), "U bent verbonden met de database", Toast.LENGTH_SHORT).show();
		    		        //Toast.makeText(context.getApplicationContext(), "U bent NIET verbonden met de database", Toast.LENGTH_SHORT).show();
	    				}
	    				
	    				try 
	                	{
	    					uidString = field_uidString;
	    					//passStr = field_passString;
							checkLogin();
						} 
	                	catch (Exception e) 
						{
							e.printStackTrace();
						}
	    				
	                    break; 
	                    
	                    
	                }
	                case MotionEvent.ACTION_UP:
	                case MotionEvent.ACTION_CANCEL: {
	                	// knop lichter maken
	                    ImageView view = (ImageView) v;
	                    view.getDrawable().clearColorFilter();
	                    view.invalidate();
	                    // strings aanmaken om te loggen
//	                    String name = preferences.getString(UID, "");
//	            		String password = preferences.getString(PASSWORD, "");
//	            		Log.d("ACTION_CANCEL", "Stored ID: " + name + " Password: " + password);
	            		
	            		// omzetten van de invoer naar string
	            		field_uidString = field_uid.getText().toString();
	            		field_passString = field_pass.getText().toString();
	            		
	            		Log.d("ACTION_CANCEL", "Inserting ID: " + field_uidString + " Password: " + field_passString);
	            		
//    					settings.edit().putString(UID, field_uidString).putString(PASSWORD, field_passString).commit();
//	            		Log.d("ACTION_CANCEL", "Stored2 ID: " + name + " Password: " + password);

	                    break;
	                }
	            }
	            return true;
	        }
	    });	
	}
    
    public void getInfo()
    { 
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		final SharedPreferences.Editor settings = preferences.edit();
    	settings.clear();
		//settings = this.getSharedPreferences(SETTING_INFOS, 0);
		String name = preferences.getString(UID, "");
		String password = preferences.getString(PASSWORD, "");
		try 
		{
			Log.d("getInfo name", name);
			Log.d("getInfo password", password);
			uidInt = Integer.parseInt(name);
			passStr = password;
			Log.d("getInfo passStr", passStr);
		}
		catch(Exception e)
		{
			uidInt = 0;
			Log.e("get Info", e.toString());
		}
		field_uid.setText(name);
		field_pass.setText(password);	
    }
    
    public void checkLogin() throws Exception
    {   	
    	new Thread(new Runnable()
    	{
    	    public void run()
    	    {
    	    	check = new checkLogin();
//    	    	boolean val = check.getValidationFromServer();
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
//            		Log.d("Stored Password", "" + password);
    	        	Log.i("Login", "Not logged in!");
    	        }
    	    }
    	}).start();
    	
//    	if (val == true)
//    	{
//        	Toast.makeText(getApplicationContext(), "U bent succesvol ingelogd", Toast.LENGTH_SHORT).show();
//    	}
//    	else if (val == false)
//    	{
//        	Toast.makeText(getApplicationContext(), "U bent niet ingelogd, mogelijk heeft u een foutief wachtwoord ingevoerd of heeft u geen internetverbinding", Toast.LENGTH_SHORT).show();
//    	}
//    	else
//    	{
//        	Toast.makeText(getApplicationContext(), "U bent succesvol ingelogd", Toast.LENGTH_SHORT).show();
//    	}
         
    	
         
        
        
        //verwijder dit wanneer check klaar is!
        //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    	//startActivity(intent);
    }

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
