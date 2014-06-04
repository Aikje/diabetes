package com.aikje.diabetes3;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class fragment_login extends Fragment{

	public static final String SETTING_INFOS = "SETTING_Infos";
	public static final String NAME = "NAME";
	public static final String PASSWORD = "PASSWORD";
	
	private EditText field_name;
	private EditText field_pass;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_login, container, false);
		
        field_name = (EditText) rootView.findViewById(R.id.name); 
        field_pass = (EditText) rootView.findViewById(R.id.password);
        
        // zodat er geen typewriter-font wordt gebruikt voor de hint in het wachtwoord-veld.
        field_pass.setTypeface(Typeface.DEFAULT);
        
        // informatie uit sharedPreferences ophalen en invullen in de juiste velden.
        get_info();

        // ImageView voor login button
		ImageView login_image = (ImageView) rootView.findViewById(R.id.image_button_login);
		
		// onTouchListener voor login_image 
		login_image.setOnTouchListener(new OnTouchListener() {
			
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {

	            switch (event.getAction()) {
	                case MotionEvent.ACTION_DOWN: {
	                	// knop donkerder maken als hij aangeraakt word
	                    ImageView view = (ImageView) v;
	                    view.getDrawable().setColorFilter(0x77000000,PorterDuff.Mode.SRC_ATOP);
	                    view.invalidate();
	                    
	                	// SharedPreferences ophalen uit MainActivity, hierna NAME & PASSWORD vullen met values.
	                	final SharedPreferences settings = getActivity().getSharedPreferences(SETTING_INFOS, 0);
	                		
	                	// omzetten van de invoer naar string
	                	String field_nameString = field_name.getText().toString();
	                	String field_passString = field_pass.getText().toString();
	                    
	    				// if else structuur om te kijken of gebruikernaam en wachtwoord zijn ingevuld
	    				if ((field_nameString == null || field_nameString.equals("")) && (field_passString == null || field_passString.equals(""))) {
	    					settings.edit().putString(NAME, field_nameString).putString(PASSWORD, field_passString).commit();
	    					Toast.makeText(getActivity(), "Je hebt geen gebruikersnaam en wachtwoord ingevoerd!", Toast.LENGTH_SHORT).show(); 
	    					Log.d("fragment_login", "Je hebt geen gebruikersnaam en wachtwoord ingevoerd!");
	    				}
	    				else if (field_nameString == null || field_nameString.equals("")) {
	    					settings.edit().putString(NAME, field_nameString).putString(PASSWORD, field_passString).commit();
	    				    Toast.makeText(getActivity(), "Je hebt geen gebruikersnaam ingevuld!", Toast.LENGTH_SHORT).show(); 
	    				    Log.d("fragment_login", "Je hebt geen gebruikersnaam ingevuld!");
	    				}
	    				else if (field_passString == null || field_passString.equals("")) {
	    					settings.edit().putString(NAME, field_nameString).putString(PASSWORD, field_passString).commit();
		    				Toast.makeText(getActivity(), "Je hebt geen wachtwoord ingevoerd!", Toast.LENGTH_SHORT).show(); 
		    				Log.d("fragment_login", "Je hebt geen wachtwoord ingevoerd!");
	    				}	
	    				else {	    
	    					settings.edit().putString(NAME, field_nameString).putString(PASSWORD, field_passString).commit();
		    				// toast om te laten zien wat er is ingevoerd
		    				Toast.makeText(getActivity(), "Gebruikersnaam: " + field_nameString + " Wachtwoord: " + field_passString, Toast.LENGTH_LONG).show();
		    				// logje om te laten zien wat er is ingevoerd
		    				Log.d("fragment_input", "Gebruikersnaam: " + field_nameString + " Wachtwoord: " + field_passString);
	
		    		        //
		    				// connectie maken met database, toast geven als het gelukt is.
		    				//
		    		        
		    		        Log.d("fragment_login", "login button geklikt, gegevens opgeslagen in SharedPreferences");
		    		        // Toast.makeText(getActivity().getApplicationContext(), "U bent verbonden met de database", Toast.LENGTH_SHORT).show();
		    		        Toast.makeText(getActivity().getApplicationContext(), "U bent NIET verbonden met de database", Toast.LENGTH_SHORT).show();
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
		return rootView;
	}
  
    public void onStop(){
        super.onStop();
    }
    
    public void get_info()
    { 
         SharedPreferences settings = getActivity().getSharedPreferences(SETTING_INFOS, 0);
         String name = settings.getString(NAME, "");
         String password = settings.getString(PASSWORD, "");
         field_name.setText(name);
         field_pass.setText(password);
         // Toast.makeText(getActivity().getApplicationContext(), "Inloggegevens ingevuld", Toast.LENGTH_SHORT).show();
    }   
}