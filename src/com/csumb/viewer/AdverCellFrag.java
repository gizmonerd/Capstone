package com.csumb.viewer;

import java.util.ArrayList;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class AdverCellFrag extends Fragment{

	Data datas= new Data();
	ListView listView;
	LayoutInflater inflaterT;

	public AdverCellFrag(Data datas)
	{
		this.datas=datas;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{	
				
		View view = inflater.inflate(R.layout.advercell, container, false);
        inflaterT = inflater;
        listView = (ListView) view.findViewById(R.id.list);
        
        ArrayList<DisplayResults> List =  new ArrayList<DisplayResults>();
		
		DisplayResults temp= new DisplayResults();
		
		for(int i=0; i<datas.features.size(); i++)
		{
			if(datas.features.get(i).attributes.ServiceTyp.equalsIgnoreCase("Mobile"))
			{
				temp= new DisplayResults();
				temp.setName(datas.features.get(i).attributes.dbANAME);
				temp.setImageNumber(Key.upKey(datas.features.get(i).attributes.maxADUP));
				temp.setImageNumber2(Key.downKey(datas.features.get(i).attributes.maxADDOWN));
				List.add(temp);
			}
		}
        listView.setAdapter(new MyCustomBaseAdapter(getActivity(), List)); 
        return view;    
		
	 	}




public static Data useBest(Data data)
{
	int a;
	int b;
	Data temp = new Data();
	int size=data.features.size();
	
	for(int i=0; i<size;i++)
	{
		for(int j=i+1; j<size; j++)
		{
				if(data.features.get(i).attributes.dbANAME.equalsIgnoreCase(data.features.get(j).attributes.dbANAME)&&
						(data.features.get(i).attributes.ServiceTyp.equalsIgnoreCase("Mobile")&&
								data.features.get(j).attributes.ServiceTyp.equalsIgnoreCase("Mobile")))
				{
				
					    a = Integer.valueOf(data.features.get(i).attributes.maxADDOWN);
					    b = Integer.valueOf(data.features.get(j).attributes.maxADDOWN);
					    
					if(a>b)
					{
						temp.features.add(data.features.get(i));
					}
					else if (b>a)
					{
						System.out.print("J is at "+j+" out of "+size);
						//temp.features.add(data.features.get(j));
					}
					else
					{
						temp.features.add(data.features.get(i));
					}
				
				}
		}
			
	}
	
	return temp;
}
}