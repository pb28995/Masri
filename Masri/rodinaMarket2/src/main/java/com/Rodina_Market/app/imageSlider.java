package com.Rodina_Market.app;

import java.util.List;



import com.dmbteam.catalogapp.cmn.product_image;
import com.dmbteam.catalogapp.adapter.*;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class imageSlider extends Activity {
	
	public static int startItems;
	public static List<product_image>images;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageswipecustomimage);
		
		try{
			getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		}catch(Exception ex){}
		CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this,images,false,null);
		ViewPager mainImageView = (ViewPager)findViewById(R.id.list_item_photo_viewpager);
		mainImageView.setAdapter(mCustomPagerAdapter);
		mainImageView.setCurrentItem(startItems);
		
		
	}

}
