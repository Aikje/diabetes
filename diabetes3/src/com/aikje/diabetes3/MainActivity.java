package com.aikje.diabetes3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	static final String[] menuEntries	= { "Invoer", "Grafiek", "Kalender"};
	static final String[] fragments	= { "com.aikje.diabetes3.Fragment_input", "com.aikje.diabetes3.Fragment_graph", "com.aikje.diabetes3.Fragment_calendar"};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getSupportActionBar();
		setContentView(R.layout.activity_main);

		// Set up the action bar to show a dropdown list.
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, menuEntries), this);
	}
	
	public void changeFragment(int index)
	{
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	    ft.replace(R.id.main, Fragment.instantiate(MainActivity.this, fragments[index]));
	    ft.commit();
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getSupportActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getSupportActionBar()
				.getSelectedNavigationIndex());
	}

	/**
	 * A ListContentFragment class.
	 */
		  public boolean onNavigationItemSelected(int position, long itemId) {
		    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		    // Replace whatever is in the fragment container with this fragment
		    // and give the fragment a tag name equal to the string at the position
		    // selected
		    ft.replace(R.id.main, Fragment.instantiate(MainActivity.this, fragments[position]));

		    // Apply changes
		    ft.commit();
		    return true;
		};
	
	/**
	 * A ListContentFragment class.
	 */
	
	@SuppressLint("ValidFragment") public class ListContentFragment extends Fragment {
	    private String mText; 

	    public void onAttach(Activity activity) {
	      // This is the first callback received; here we can set the text for
	      // the fragment as defined by the tag specified during the fragment
	      // transaction
	      super.onAttach(activity);
	      mText = getTag();
	    }

	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        // This is called to define the layout for the fragment;
	        // we just create a TextView and set its text to be the fragment tag
	        TextView text = new TextView(getActivity());
	        text.setText(mText);
	        return text;
	    }
	}
}