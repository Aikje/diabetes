package com.aikje.diabetes3;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * @author Aike Brakel
 */

public class Fragment_graph extends Fragment{

	ArrayList<Double> data = new ArrayList<Double>();
	Context context;
	
	public void onCreate(Bundle savedInstanceState, Fragment_graph context)
	{
		super.onCreate(savedInstanceState);
		context = this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
		
        try
        {        	
			GraphView graphView = new LineGraphView(getActivity(), "Waarden in mmol/L");
			
			graphView.getGraphViewStyle().setGridColor(Color.BLACK);
			graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
			graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
			graphView.getGraphViewStyle().setNumVerticalLabels(9);
			graphView.getGraphViewStyle().setNumHorizontalLabels(3);
			graphView.setViewPort(3, 3);
			graphView.setScrollable(true);
			graphView.setScalable(true);
			graphView.setManualYAxisBounds(10, 2);
			((LineGraphView) graphView).setDrawBackground(true);
		    ((LineGraphView) graphView).setBackgroundColor(Color.rgb(255, 105, 4));
		    
		    
		    
		    DownloadGraphDataTask ddt = new DownloadGraphDataTask(this, this.getActivity());

			try
		    {
		    	ddt.execute();
		    	Thread.sleep(1000);
		    	data = ddt.getData();
		    	Log.d("Graph", data.toString());
		    }
		    catch(Exception e)
		    {
		    	//Log.d("Graph", e.toString());
		    	//throw new Exception(e);
		    }

			// timestamp wordt geparst naar string om weer te geven onder de grafiek.
			String timestamp1 = new String ("eerder");
			String timestamp2 = new String (ddt.getLastDate());
			graphView.setHorizontalLabels(new String[] {timestamp1, timestamp2});
			graphView.addSeries(setGraphData()); // data
		
			 
			LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graph1);
			layout.addView(graphView);
        }
        catch(Exception e)
        {
			e.printStackTrace();
        }
        return rootView;
	}

	public GraphViewSeries setGraphData()
	{
		//System.out.println(data.size());
		// data voor grafiek (haalt de 6 laatste meting uit de arraylist.
		// De ArrayList is gevult met alle metinging dus kan worden aangepast.
			try
			{
				GraphViewSeries graphData = new GraphViewSeries(new GraphViewData[] 
				{	
				    new GraphViewData(1, data.get(data.size()-6))
				    , new GraphViewData(2, data.get(data.size()-5))
				    , new GraphViewData(3, data.get(data.size()-4))
				    , new GraphViewData(4, data.get(data.size()-3))
				    , new GraphViewData(5, data.get(data.size()-2))
				    , new GraphViewData(6, data.get(data.size()-1))
				});
				return graphData;
			}
			catch(Exception e)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Er is te weinig data om een grafiek weer te geven, u moet minimaal 6 waarden invoeren!", Toast.LENGTH_LONG).show();
				Log.d("Graph", e.toString());
				changeFragment();
			}
			return null;
	}
	
	/*
	 * Na het verzenden van de data de fragmentview veranderen naar fragment_graph
	 */
	private void changeFragment() {
		final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
		ft.replace(R.id.main, Fragment.instantiate(getActivity(), "com.aikje.diabetes3.Fragment_input")); 
		ft.commit(); 
	}
}
