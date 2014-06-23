package com.aikje.diabetes3;

import java.util.ArrayList;

import org.json.JSONException;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * @author Aike Brakel
 */

public class fragment_graph extends Fragment{

	ArrayList<Double> doubleArray = new ArrayList<Double>();
	ArrayList<Double> doubleArray2 = new ArrayList<Double>();
	
	JSONParser testParser = new JSONParser();
	//double[] doubleArray = new double[6];
	//double[] doubleArray = {2.0, 4.0, 3.0, 3.0, 4.0, 2.0};
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		doubleArray2.add(2.0);
		doubleArray2.add(4.0);
		doubleArray2.add(3.0);
		doubleArray2.add(3.0);
		doubleArray2.add(4.0);
		doubleArray2.add(2.0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
		
		
        try
        {        	
			// timestamp wordt geparst naar string om weer te geven onder de grafiek.
			String timestamp1 = new String ("eerder");
			String timestamp2 = new String ("vandaag");
			 
			GraphView graphView = new LineGraphView(getActivity(), "Waarden in mmol/L");
			
			graphView.getGraphViewStyle().setGridColor(Color.BLACK);
			graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
			graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
			graphView.getGraphViewStyle().setNumVerticalLabels(9);
			graphView.getGraphViewStyle().setNumHorizontalLabels(3);
			graphView.setViewPort(1, 5);
			// graphView.setScrollable(true);
			// graphView.setScalable(true);
			graphView.setManualYAxisBounds(10, 2);
			((LineGraphView) graphView).setDrawBackground(true);
		    ((LineGraphView) graphView).setBackgroundColor(Color.rgb(255, 105, 4));
		    
		    graphView.setHorizontalLabels(new String[] {timestamp1, timestamp2});
		    DownloadDataTask ddt = new DownloadDataTask()
		    {
		    @Override
		    public void onPostExecute(ArrayList<Double> dArray)
		    {
		    	System.out.println(dArray.size());
		    	doubleArray = dArray;
		    	}
		    };
		    
		    try
		    {
		    	//ddt.execute();
		    }
		    catch(RuntimeException e)
		    {
		    	Log.d("Graph", e.toString());
		    	throw new RuntimeException(e);
		    }
		    //setGraphData();
			graphView.addSeries(setGraphData()); // data
		
			 
			LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graph1);
			layout.addView(graphView);
        }
        catch(RuntimeException e)
        {
        	throw new RuntimeException(e);
        }
        return rootView;
	}
	
	
	

	public GraphViewSeries setGraphData()
	{
		//System.out.println(doubleArray.size());
		// data voor grafiek
		GraphViewSeries graphData = new GraphViewSeries(new GraphViewData[] 
		{
				
		    new GraphViewData(1, doubleArray2.get(0))
		    , new GraphViewData(2, doubleArray2.get(1))
		    , new GraphViewData(3, doubleArray2.get(2))
		    , new GraphViewData(4, doubleArray2.get(3))
		    , new GraphViewData(5, doubleArray2.get(4))
		    , new GraphViewData(6, doubleArray2.get(5))
		});
		return graphData;
	}

	public static void addValueToGraphData(Double waardeInvoerDouble) {		
	}
}
