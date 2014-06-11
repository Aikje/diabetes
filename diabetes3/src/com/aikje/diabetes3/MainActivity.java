package com.aikje.diabetes3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class MainActivity extends ActionBarActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	static final String[]					menuEntries	= { "Login", "Invoer", "Grafiek", "Kalender", "Lijst"};
	static final String[]					fragments	= { "com.aikje.diabetes3.fragment_login", "com.aikje.diabetes3.fragment_input", "com.aikje.diabetes3.fragment_graph", "com.aikje.diabetes3.fragment_calendar", "com.aikje.diabetes3.fragment_list"};
	
	@Override
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

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getSupportActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getSupportActionBar()
				.getSelectedNavigationIndex());
	}

	
	/**
	 * A ListContentFragment class.
	 */

		  @Override
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
	
	@SuppressLint("ValidFragment")
	public class ListContentFragment extends Fragment {
	    private String mText; 

	    @Override
	    public void onAttach(Activity activity) {
	      // This is the first callback received; here we can set the text for
	      // the fragment as defined by the tag specified during the fragment
	      // transaction
	      super.onAttach(activity);
	      mText = getTag();
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        // This is called to define the layout for the fragment;
	        // we just create a TextView and set its text to be the fragment tag
	        TextView text = new TextView(getActivity());
	        text.setText(mText);
	        return text;
	    }
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
			Log.d("PlaceholderFragment", "Changed fragment");
			return rootView;
		}
	}
}
