package com.dmbteam.catalogapp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Rodina_Market.app.R;
import com.androidquery.AQuery;
import com.dmbteam.catalogapp.cmn.Category;
public class GridAdapter extends BaseAdapter {

	
	
	
	 Context context;
	 ArrayList<Category> mMainAdapterData;
	 private static LayoutInflater inflater=null;
	   
	 AQuery aq;
	     public GridAdapter(Activity mainActivity,  ArrayList<Category> MainAdapterData) {
	      
	    	 
	    	 mMainAdapterData=MainAdapterData;
	    	 context=mainActivity;
	    	 
	    	 
	    	 
	    	 
	    	/* String about=context.getResources().getString(R.string.aboutapp) ;
             String stng=context.getResources().getString(R.string.action_settings) ;
             
	    	 int rindex=-1;
	    	 for(int i=0;i<mMainAdapterData.size();i++){
	    		 if(mMainAdapterData.get(i).getTitle().equals(about)){
	    			 rindex=i;
	    		 }
	    	 }
	    	 if(rindex>0){
	    		 mMainAdapterData.remove(rindex);
	    	 }
	    	 
	    	 
	    	 rindex=-1;
	    	 for(int i=0;i<mMainAdapterData.size();i++){
	    		 if(mMainAdapterData.get(i).getTitle().equals(stng)){
	    			 rindex=i;
	    		 }
	    	 }
	    	 if(rindex>0){
	    		 mMainAdapterData.remove(rindex);
	    	 }
	    	 */
	        
	        aq=new AQuery(mainActivity);
	         inflater = ( LayoutInflater )context.
	                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    }

	    @Override
	    public int getCount() {
	        // TODO Auto-generated method stub
	    	//if(mMainAdapterData.size()<2)return 0;
	    			
	        return mMainAdapterData.size();
	    }

	    @Override
	    public Object getItem(int position) {
	        // TODO Auto-generated method stub
	        return mMainAdapterData.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        // TODO Auto-generated method stub
	        return position;
	    }

	    public class Holder
	    {
	        TextView tv;
	        ImageView img;
	    }
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	        // TODO Auto-generated method stub
	        Holder holder=new Holder();
	        
	        View rowView;

	             rowView = inflater.inflate(R.layout.griditems, null);
	             holder.tv=(TextView) rowView.findViewById(R.id.textView1);
	             holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

	             String about=context.getResources().getString(R.string.aboutapp) ;
	             String stng=context.getResources().getString(R.string.action_settings) ;
	             if(mMainAdapterData.get(position).getTitle().equals(about)||mMainAdapterData.get(position).getTitle().endsWith(stng))
	             {
	            	 
	            	 // holder.tv.setText(mMainAdapterData.get(position).getTitle());
	     	          
	             }else{
	             holder.tv.setText(mMainAdapterData.get(position).getTitle()+"("+mMainAdapterData.get(position).getProductCount()+")");
	             }
	         if(mMainAdapterData.get(position).getURL().length()>0)
	         aq.id(holder.img).image(mMainAdapterData.get(position).getURL());

	        return rowView;
	    }

		
}
