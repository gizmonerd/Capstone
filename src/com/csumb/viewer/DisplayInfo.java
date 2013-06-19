package com.csumb.viewer;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayInfo extends Activity{
	
	public static Data data;
	public ProjectedAll transfer;
	
	Fragment fieldCellDataFragment;
	Fragment adverCellDataFragment;
    Fragment wiredDataTabFragment;
    Fragment satDataFragment;
    
	ActionBar.Tab fieldCellDataTab;
	ActionBar.Tab adverCellDataTab;
	ActionBar.Tab wiredDataTab;
	ActionBar.Tab satDataTab;

	ActionBar actionbar;
	
	Intent myIntent; // gets the previously created intent
	double x; // will return "x"
	double y; // will return "y"
	int spatial; // will return "spatial"
	String address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);        
			myIntent= getIntent(); // gets the previously created intent
			 address=myIntent.getStringExtra("address");
			 x = myIntent.getDoubleExtra("x",0); // will return "x"
			 y = myIntent.getDoubleExtra("y", 0); // will return "y"
			 spatial= myIntent.getIntExtra("spatial", 0); // will return "spatial"   
			 
			actionbar = getActionBar();
			//setCustomView(R.layout.addressview);
			//extView addressview= (TextView) actionbar.getCustomView().findViewById(R.id.addressId);
					
					//findViewById(R.id.addressId);
			if(address!=null)
			{
				actionbar.setTitle(address);

			}
			else
			{
				System.out.println("Crapers");
			}			
		}
	@Override
	public void onStart()
	{
		super.onStart();
		String URL="http://132.241.99.43/ArcGIS/rest/services/CPUC/ALL_AVAILABILITY_RD6/MapServer/0/query?text=&geometry=" +
				x+"%2C"+y+"&geometryType=esriGeometryPoint&inSR="+spatial+"&spatialRel=esriSpatialRelIntersects&" +
				"relationParam=&objectIds=&where=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowable" +
				"Offset=&outSR=102113&outFields=*&f=pjson";
		
		//ActionBar gets initiated
		//actionbar = getActionBar();
		//Tell the ActionBar we want to use Tabs.
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//initiating both tabs and set text to it.
		
		//fieldCellDataTab = actionbar.newTab().setText("Projected wireless").setIcon(R.drawable.mobileicon);
		fieldCellDataTab=actionbar.newTab().setCustomView(R.layout.fieldtab);
		adverCellDataTab = actionbar.newTab().setCustomView(R.layout.advertab);
		wiredDataTab = actionbar.newTab().setCustomView(R.layout.wiredtab);
		satDataTab = actionbar.newTab().setCustomView(R.layout.sattab);
		
		fieldCellDataFragment = new FieldCellFrag(x,y,spatial);
		
		DataResponse dataR= new DataResponse(DisplayInfo.this);
		dataR.execute(URL);
		dataR.addObserver(new DataResponse.Callback() {
			@Override
			public void onFailure() {
				// TODO Auto-generated method stub
			}
			@Override
			public void onComplete(Data datas) {
				 adverCellDataFragment = new AdverCellFrag(datas);
		         wiredDataTabFragment = new WiredFrag(datas);
		         satDataFragment = new satDataFrag(datas);
		         fieldCellDataTab.setTabListener(new MyTabsListener(fieldCellDataFragment));
			     adverCellDataTab.setTabListener(new MyTabsListener(adverCellDataFragment));
			     wiredDataTab.setTabListener(new MyTabsListener(wiredDataTabFragment));
			     satDataTab.setTabListener(new MyTabsListener(satDataFragment));	        
			        
			     actionbar.addTab(fieldCellDataTab);
			     actionbar.addTab(adverCellDataTab);
			     actionbar.addTab(wiredDataTab);
			     actionbar.addTab(satDataTab);
			     
			}});
		 	
			setContentView(R.layout.displayinfo);
	}
	
	class MyTabsListener implements ActionBar.TabListener {
		public Fragment fragment;
		public MyTabsListener(Fragment fragment) {
			this.fragment = fragment;
		}
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {	
		}
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace(R.id.fragment_container, fragment);
		}
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
		}
	}

}





