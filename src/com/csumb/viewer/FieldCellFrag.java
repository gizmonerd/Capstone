package com.csumb.viewer;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FieldCellFrag extends Fragment {
	
	ProjectedAll datas= new ProjectedAll();
	ListView listView;
	LayoutInflater inflaterT;
	String attUp;
	String attDown;
	String sprintUp;
	String sprintDown;
	String verizionUp;
	String verizionDown;
	String tmobileUp;
	String tmobileDown;
	public ProjectedData attup;
	public ProjectedData attdown;
	public ProjectedData sprintup;
	public ProjectedData sprintdown;
	public ProjectedData verizionup;
	public ProjectedData veriziondown;
	public ProjectedData tmobileup;
	public ProjectedData tmobiledown;
	
	
	public FieldCellFrag(double x, double y, int spatial)
	{
		attUp="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_UP_ATT/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=MAXADUP&f=pjson";
		
		attDown="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_DOWN_ATT/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=MAXADDOWN&f=pjson";
		
		sprintUp="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_UP_SPRINT/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=MAXADUP&f=pjson";
		
		sprintDown="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_DOWN_SPRINT/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=MAXADDOWN&f=pjson";

		verizionUp="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_UP_VERIZON/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=MAXADUP&f=pjson";
		
		verizionDown="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_DOWN_VERIZON/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=*&f=pjson";
		
		tmobileUp="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_UP_TMOBILE/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=MAXADUP&f=pjson";
		
		tmobileDown="http://132.241.99.43/ArcGIS/rest/services/CPUC/MOBILE_WIRELESS_APP_RD1_DRIVE_TEST_PREDICTED_AVERAGE_DOWN_TMOBILE/" +
				"MapServer/0/query?geometry=%7B%22x%22%3A"+x+"%2C%22y%22%3A"+y+"%2C%22spatialReference%22%3A%7B%22wkid%" +
				"22%3A102113%7D%7D&geometryType=esriGeometryPoint&inSR=&spatialRel=esriSpatialRelIntersects&relationParam=&objectIds=&where" +
				"=&time=&returnCountOnly=false&returnIdsOnly=false&returnGeometry=false&maxAllowableOffset=&outSR=102113&outFields=MAXADDOWN&f=pjson";
		
		try {
			attup=new ProjectedResponse().execute(attUp).get();
			attdown=new ProjectedResponse().execute(attDown).get();  

			sprintup= new ProjectedResponse().execute(sprintUp).get();
			sprintdown= new ProjectedResponse().execute(sprintDown).get();
			  
			verizionup= new ProjectedResponse().execute(verizionUp).get();
			veriziondown= new ProjectedResponse().execute(verizionDown).get();
			  
			tmobileup= new ProjectedResponse().execute(tmobileUp).get();
			tmobiledown= new ProjectedResponse().execute(tmobileDown).get();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 datas.attDown=attdown;
		datas.attUp=attup;
		datas.sprintDown=sprintdown;
		datas.sprintUp=sprintup;
		datas.verizionUp=verizionup;
		datas.verizionDown=veriziondown;
		datas.tmobileDown=tmobiledown;
		datas.tmobileUp=tmobileup;
	}
	
	 String checkDown(ProjectedData Data)
	{
				
		if(Data.features.size()==0)
		{
			return "null";
		}
		else
		{
			return Data.features.get(0).attributes.maxADDOWN;
		}
		
	}
	 
	 String checkUp(ProjectedData Data)
	{
						
			if(Data.features.size()==0 || (null==Data))
			{
				return "null";
			}
			else
			{
				return Data.features.get(0).attributes.maxADUP;
			}
			
		}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{	
		
		 
			
		View view = inflater.inflate(R.layout.fieldcell, container, false);
        inflaterT = inflater;
        listView = (ListView) view.findViewById(R.id.list);
        		
		ArrayList<DisplayResults> List =  new ArrayList<DisplayResults>();

		DisplayResults temp = new DisplayResults();
		
		
		temp.setName("ATT Wireless");
		temp.setImageNumber(Key.upKey(checkUp(datas.attUp)));
		temp.setImageNumber2(Key.downKey(checkDown(datas.attDown)));
		
		List.add(temp);
		temp=new DisplayResults();
		
		temp.setName("Sprint");
		temp.setImageNumber(Key.upKey(checkUp(datas.sprintUp)));
		temp.setImageNumber2(Key.downKey(checkDown(datas.sprintDown)));
		
		List.add(temp);
		temp=new DisplayResults();
		
		temp.setName("Verizion Wireless");
		temp.setImageNumber(Key.upKey(checkUp(datas.verizionUp)));
		temp.setImageNumber2(Key.downKey(checkDown(datas.verizionDown)));
		
		List.add(temp);
		temp= new DisplayResults();
		
		temp.setName("T-Mobile");
		temp.setImageNumber(Key.upKey(checkUp(datas.tmobileUp)));
		temp.setImageNumber2(Key.downKey(checkDown(datas.tmobileDown)));
		
		List.add(temp);
		
        listView.setAdapter(new MyCustomBaseAdapter(getActivity(), List)); 

        return view;    
		
        
        
	 	}
	
	

}


