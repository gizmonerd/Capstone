package com.csumb.viewer;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class WiredFrag extends Fragment {

	Data datas;

	ListView listView;
	LayoutInflater inflaterT;
	
	
	public WiredFrag(Data data)
	{
		this.datas=data;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{	
				
		View view = inflater.inflate(R.layout.wired, container, false);
        inflaterT = inflater;
        listView = (ListView) view.findViewById(R.id.list);
        
	ArrayList<DisplayResults> List =  new ArrayList<DisplayResults>();
		
		DisplayResults temp= new DisplayResults();
		
		for(int i=0; i<datas.features.size(); i++)
		{
			if(datas.features.get(i).attributes.ServiceTyp.equalsIgnoreCase("Fixed"))
			{
				temp= new DisplayResults();
				temp.setName(datas.features.get(i).attributes.dbANAME);
				
				if(datas.features.get(i).attributes.maxADUP!=null)
				{
					temp.setImageNumber(Key.downKey(datas.features.get(i).attributes.maxADUP));
				}
				else
				{
					//temp.setImageNumber(imageNumber);
				}
				
				if(datas.features.get(i).attributes.maxADDOWN!=null)
				{
					temp.setImageNumber2(Key.downKey(datas.features.get(i).attributes.maxADDOWN));
				}
				else
				{
					//temp.setImageNumber2(imageNumber);
				}
				List.add(temp);
			}
		}
        listView.setAdapter(new MyCustomBaseAdapter(getActivity(), List)); 

        return view;    
		
	 	}
	
}
