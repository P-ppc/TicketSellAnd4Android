package com.ppc.ticket4Android.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ppc.ticket.R;

public class ChooseContactAdapter extends BaseAdapter{

	 private Context context;  
	 private ArrayList<HashMap<String,Object>> contactlist;
	 private HashMap<String,Boolean> states= new HashMap<String, Boolean>();	;//用于记录每个radioButton的状态，并保证只可选一个
	 
	 public ChooseContactAdapter(Context context,ArrayList<HashMap<String,Object>> contactlist){
		 this.context=context;
		 this.contactlist=contactlist;
	 }
	public Object getStates(){
		return states;
	} 
	 
	@Override
	public int getCount() {
		
		return contactlist.size();
	}

	@Override
	public Object getItem(int position) {
		
		return contactlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.choose_contact_items, null);
			holder =new ViewHolder();
			holder.contactName=(TextView) convertView.findViewById(R.id.choose_contact_name);
			holder.contactId=(TextView) convertView.findViewById(R.id.choose_contact_id);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		final RadioButton radio=(RadioButton) convertView.findViewById(R.id.choose_contact_radio);
		holder.rdBtn=radio;
		
		holder.contactId.setText(contactlist.get(position).get("contactId").toString());
		holder.contactName.setText(contactlist.get(position).get("contactName").toString());
		
	    holder.rdBtn.setOnClickListener(new OnClickListener() {
			
	    
			@Override
			public void onClick(View v) {
				//重置，确保最多只有一项被选中  
				for(String key:states.keySet()){
					states.put(key, false);
				}
				states.put(String.valueOf(position), radio.isChecked());
				ChooseContactAdapter.this.notifyDataSetChanged();
			}
		});
	    
	    boolean res=false;
	    if(states.get(String.valueOf(position))==null || states.get(String.valueOf(position))==false){
	    	res=false;
	    	states.put(String.valueOf(position),false);
	    }else{
	    	res=true;
	    }
	    holder.rdBtn.setChecked(res);
		return convertView;
	}
	
	static class ViewHolder{
		RadioButton rdBtn;  
		TextView contactName;
		TextView contactId;
	}


}

