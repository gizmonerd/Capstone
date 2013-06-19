package com.csumb.viewer;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView.OnEditorActionListener;
import com.csumb.viewer.Response.Candidates;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements OnMapClickListener, OnMapLongClickListener, OnMarkerClickListener {

	private LatLng currentLocation;
	private LocationManager lManager;
	private Location location;
	private Response response;
	private double longitude;
	private double latitude;
	private GoogleMap mMap;
	private Candidates bestChoice;
	private String address;
	private String request;
	private String URLXY;
	private int spatial;
	private GoogleJ Gj;
	private Marker marker;
	private EditText search;
	private String reverseGeo;
	private	Intent intent;
	public static  int keyItem;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			
			android.app.FragmentManager myFragmentManager = getFragmentManager();
			MapFragment mMapFragment 
			   = (MapFragment)myFragmentManager.findFragmentById(R.id.map);
			  mMap = mMapFragment.getMap();
			  mMap.setMyLocationEnabled(true);
			  
			intent = new Intent(this, DisplayInfo.class);
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			lManager= (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			location=lManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			longitude=location.getLongitude();latitude=location.getLatitude();
			currentLocation=new LatLng(latitude, longitude);
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 7));
			
			ActionBar actionBar = getActionBar();
			actionBar.setCustomView(R.layout.actionbar_view);
			search = (EditText) actionBar.getCustomView().findViewById(R.id.searchfield);
			search.setOnEditorActionListener(new OnEditorActionListener() 
			{
				@Override
				public boolean onEditorAction(TextView v, int actionId,KeyEvent event) 
				{
					if(marker!=null)
					{
						mMap.clear();
					}
					
					address= search.getText().toString();			
					try {
						 request=URLEncoder.encode(address, "UTF-8");
						 
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					URLXY="http://tasks.arcgisonline.com/ArcGIS/rest/services/Locators/TA_Address_NA_10/" +
							"GeocodeServer/findAddressCandidates?Address=&City=&State=&Zip=&Zip4=&Country=&" +
							"SingleLine="+request+"&outFields=*&outSR=102113&f=pjson";
					try {
						response=new ResponseSync(MainActivity.this).execute(URLXY).get();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					
					spatial=response.spatialReference.wkid;
					List<Candidates> candidates = response.candidates;
					bestChoice= new Candidates();
					try {
						bestChoice=getBestScore(candidates);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					intent.putExtra("address", bestChoice.address);
					intent.putExtra("spatial", spatial);
					intent.putExtra("x", bestChoice.location.x);
					intent.putExtra("y", bestChoice.location.y);
					startActivity(intent);	
					return true;
				}});
			
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
			mMap.setOnMapLongClickListener(this);
			mMap.setOnMarkerClickListener(this);
			mMap.setOnMapClickListener(this);
			
	}

		public Candidates getBestScore(final List<Candidates> list) throws InterruptedException
		{
			Candidates candidate= new Candidates();
			ArrayList<String> SpinnerArray =  new ArrayList<String>();
			 
			 
			 for(int i=0; i<list.size(); i++)
			 {
				 
				 if(list.get(i).score > 60)
				 {
					 SpinnerArray.add(list.get(i).address);
				 }
				 
			 }

			 keyItem=0;
			 
			 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SpinnerArray); 		 
			 AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 builder.setTitle("Choose Address");
			 builder.setAdapter(adapter, new DialogInterface.OnClickListener(){
		
			     @Override      
				 public void onClick(DialogInterface dialog, int item) {
			        	
			        	   keyItem=item;
			        	   
			             }
			         });
			 
			 AlertDialog alert = builder.create();
			 alert.show();
			 Thread.sleep(3000);
			 candidate=list.get(keyItem);
			
			 return candidate;
			}
		/*
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    getMenuInflater().inflate(R.menu.options, menu);
		    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
		    // Configure the search info and add any event listeners
		    ...
		    return super.onCreateOptionsMenu(menu);
		   
		}

*/d
		@Override
		public boolean onMarkerClick(Marker arg0) {
			
			 try {
					Gj=new ReverseGeo().execute(reverseGeo).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				 			
				 String address=Gj.results.get(0).formatted_address;
				
				 try {
					 request=URLEncoder.encode(address, "UTF-8");
					 
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				URLXY="http://tasks.arcgisonline.com/ArcGIS/rest/services/Locators/TA_Address_NA_10/" +
						"GeocodeServer/findAddressCandidates?Address=&City=&State=&Zip=&Zip4=&Country=&" +
						"SingleLine="+request+"&outFields=*&outSR=102113&f=pjson";
				
				try {
					response=new ResponseSync(MainActivity.this).execute(URLXY).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				spatial=response.spatialReference.wkid;
				bestChoice=response.candidates.get(0);			
			if(isSiteAvailable())
			{
				
				AlertDialog.Builder serviceAlert = new AlertDialog.Builder(this);
				serviceAlert.setTitle("Service is not Available"); //Set Alert dialog title here
				serviceAlert.setMessage("Please try again later"); //Message here
				serviceAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {}});
	        	 AlertDialog Service = serviceAlert.create();
				 Service.show();
				 
				return false;
			}
			else
			{
				intent.putExtra("address", bestChoice.address);
				intent.putExtra("spatial", spatial);
				intent.putExtra("x", bestChoice.location.x);
				intent.putExtra("y", bestChoice.location.y);
				startActivity(intent);	
			}
			
			return false;
		}

		@Override
		public void onMapLongClick(LatLng arg0) {
			
			if(marker!=null)
			{
				mMap.clear();
			}
			
			marker= mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.latitude, arg0.longitude)));
			
			String reverseGeo="http://maps.googleapis.com/maps/api/geocode/json?latlng="+arg0.latitude+","+arg0.longitude+"&sensor=true";
			
			 try {
				Gj=new ReverseGeo().execute(reverseGeo).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			 			
			 String address=Gj.results.get(0).formatted_address;
			
			 try {
				 request=URLEncoder.encode(address, "UTF-8");
				 
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			URLXY="http://tasks.arcgisonline.com/ArcGIS/rest/services/Locators/TA_Address_NA_10/" +
					"GeocodeServer/findAddressCandidates?Address=&City=&State=&Zip=&Zip4=&Country=&" +
					"SingleLine="+request+"&outFields=*&outSR=102113&f=pjson";
			
			try {
				response=new ResponseSync(MainActivity.this).execute(URLXY).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			spatial=response.spatialReference.wkid;
			bestChoice=response.candidates.get(0);
			intent.putExtra("address", bestChoice.address);
			intent.putExtra("spatial", spatial);
			intent.putExtra("x", bestChoice.location.x);
			intent.putExtra("y", bestChoice.location.y);
			startActivity(intent);
		}

		@Override
		public void onMapClick(LatLng arg0) {
			
		if(marker!=null)
			{
				mMap.clear();
			}
			
			marker= mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.latitude, arg0.longitude)));
			reverseGeo="http://maps.googleapis.com/maps/api/geocode/json?latlng="+arg0.latitude+","+arg0.longitude+"&sensor=true";
		
		}
		
		public boolean isSiteAvailable() {

		    try {
		        URL u = new URL("http://132.241.99.43/ArcGIS/rest/services/CPUC/ALL_AVAILABILITY_RD6/MapServer");
		        HttpURLConnection huc =  (HttpURLConnection) u.openConnection(); 
		        huc.setRequestMethod("GET"); 
		        huc.connect(); 
		        huc.setRequestProperty("Connection", "close");
		        huc.setConnectTimeout(3000); // Timeout 3 seconds.
		        int response=huc.getResponseCode();

		        if ((response >= 400) || (response < 200))
		        {
		            return false;
		        }

		        return true;
		    }
		    catch (Exception e) {
		        System.out.println("OOPSIE");
		        e.printStackTrace();
		    } 

		    return false;
		}

}

 
