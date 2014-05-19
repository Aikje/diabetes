package com.aikje.diabetes3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class fragment_graph extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
		
		// timestamp wordt geparst naar string om weer te geven onder de grafiek.
		String timestamp1 = new String ("eerder");
		String timestamp2 = new String ("gisteren");
		String timestamp3 = new String ("vandaag");
		
		// init example series data
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
		    new GraphViewData(1, 5)
		    , new GraphViewData(2, 7.5)
		    , new GraphViewData(3, 5.5)
		    , new GraphViewData(4, 10.0)
		    , new GraphViewData(5, 9.2)
		    , new GraphViewData(6, 8.5)
		    , new GraphViewData(7, 5.9)
		    , new GraphViewData(8, 7.1)
		});
		 
		GraphView graphView = new LineGraphView(getActivity(), "Overzicht bloedsuikerwaarden in mmol/L");
		
		graphView.addSeries(exampleSeries); // data
		graphView.getGraphViewStyle().setGridColor(Color.BLACK);
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
		graphView.setViewPort(1, 4);
		// graphView.setScrollable(true);
		// graphView.setScalable(true);
		graphView.setManualYAxisBounds(10, 2);
		((LineGraphView) graphView).setDrawBackground(true);
        ((LineGraphView) graphView).setBackgroundColor(Color.rgb(255, 105, 4));
        
        graphView.setHorizontalLabels(new String[] {timestamp1, timestamp2, timestamp3});
		 
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graph1);
		layout.addView(graphView);

		return rootView;
	}
}
