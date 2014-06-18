package com.aikje.diabetes3;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

	double[] doubleArray = new double[6];
	//double[] doubleArray = {2.0, 4.0, 3.0, 3.0, 4.0, 2.0};
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
		
		
        try{
            String urlString = "http://recoma.samba-ti.nl/php/aikeAppTest.php";
            Log.d("Arraylist gegevens", "test1");
//            URL url = new URL(urlString);
            Log.d("Arraylist gegevens", "test2");
//            JSONObject json = new JSONObject(MainActivity.getJSONfromURL(url.toString()));
//            JSONObject resource = json.getJSONObject("bloedsuiker");
            JSONObject jsnobject = new JSONObject(MainActivity.getJSONfromURL());
            Log.d("Arraylist gegevens", "test3");
            JSONArray jsonArray = jsnobject.getJSONArray(urlString);
            Log.d("Arraylist gegevens", "test4");
            for (int i = 0; i < jsonArray.length(); i++) {
            		JSONObject meetwaarden = jsonArray.getJSONObject(i);
            		Log.d("Arraylist gegevens", "test" + jsonArray.length());
            	}
            
            
            
//            Iterator<String> keys = resource.keys();
//            while (keys.hasNext())
//            {
//                String obj = keys.next();
//                String keyValuePair = obj +": "+ resource.getString(obj);
//                System.out.println(keyValuePair);
//                gegevens.add(keyValuePair);
//
//            }


        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
		parseJSON();
		
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
		graphView.addSeries(setGraphData()); // data

		 
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graph1);
		layout.addView(graphView);

		return rootView;
	}

	private void parseJSON() {
		// TODO Auto-generated method stub
		
	}

	public GraphViewSeries setGraphData()
	{
		// data voor grafiek
				GraphViewSeries graphData = new GraphViewSeries(new GraphViewData[] {
				    new GraphViewData(1, doubleArray[0])
				    , new GraphViewData(2, doubleArray[1])
				    , new GraphViewData(3, doubleArray[2])
				    , new GraphViewData(4, doubleArray[3])
				    , new GraphViewData(5, doubleArray[4])
				    , new GraphViewData(6, doubleArray[5])
				});
				return graphData;
	}

	public static void addValueToGraphData(Double waardeInvoerDouble) {		
	}
}
