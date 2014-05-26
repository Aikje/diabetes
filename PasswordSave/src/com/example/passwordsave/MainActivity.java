package com.example.passwordsave;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	
	  public static final String SETTING_INFOS = "SETTING_Infos";
	  public static final String NAME = "NAME";
	  public static final String PASSWORD = "PASSWORD";
	  
	  private EditText field_name;
	  private EditText filed_pass;
	 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        field_name = (EditText) findViewById(R.id.name);
        filed_pass = (EditText) findViewById(R.id.password);
        
        
       
    }
    protected void onStop(){
        super.onStop();
    
    }
    
    public void set_info(View v)
    { 
    	SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
        settings.edit()
          .putString(NAME, field_name.getText().toString())
          .putString(PASSWORD, filed_pass.getText().toString())
          .commit();
        
        Toast.makeText(getApplicationContext(), "Info is saved!", Toast.LENGTH_SHORT).show();
    }
    
    public void get_info(View v)
    {
    	 
         SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0);
     String name = settings.getString(NAME, "");
     String password = settings.getString(PASSWORD, "");
     field_name.setText(name);
     filed_pass.setText(password);
     
     Toast.makeText(getApplicationContext(), "Info is retrieved!", Toast.LENGTH_SHORT).show();
    }
    
    
    
}






	  

