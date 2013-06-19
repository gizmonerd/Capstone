package com.csumb.viewer;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class satDataFrag extends Fragment {

	Data datas;

	ListView listView;
	LayoutInflater inflaterT;
	
	
	public satDataFrag(Data data)
	{
		this.datas=data;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{	
		View view = inflater.inflate(R.layout.sat, container, false);
        inflaterT = inflater;
        listView = (ListView) view.findViewById(R.id.listSat);

         
		ArrayList<DisplayResults> List =  new ArrayList<DisplayResults>();
		
		DisplayResults temp= new DisplayResults();
		
		for(int i=0; i<datas.features.size(); i++)
		{
			if(datas.features.get(i).attributes.ServiceTyp.equalsIgnoreCase("Satellite"))
			{
				temp= new DisplayResults();
				temp.setName(datas.features.get(i).attributes.dbANAME);
				temp.setImageNumber(Key.upKey(datas.features.get(i).attributes.maxADUP));
				temp.setImageNumber2(Key.downKey(datas.features.get(i).attributes.maxADDOWN));
				List.add(temp);
			}
		}
       
        //ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),
        	//	  R.layout.list_row, List);
        
        listView.setAdapter(new MyCustomBaseAdapter(getActivity(), List)); 
        return view;    
		
	 	}

}
