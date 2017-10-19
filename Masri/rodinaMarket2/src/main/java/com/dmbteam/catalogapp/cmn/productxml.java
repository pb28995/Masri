package com.dmbteam.catalogapp.cmn;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.dmbteam.catalogapp.settings.AppSettings;

/**
 * The Class Catalog.
 */
@Root(strict=false ,name="xml")

public class productxml {

	
	@Element(required=false )
	private String telephone="";

	@Element(required=false )
	private String email="";
	
	@Element(required=false )
	private String website="";
	
	
	@Element(required=false )
	private String fb_link="";
	
	
	@Element(required=false )
	private int activate_shopping_cart=0;
	
	
	@Element(required=false )
	private int push_notification=0;
	
	
	@Element(required=false )
	private int geo_fencing=0;
	
	
	@Element(required=false )
	private int delivery_cost=0;
	
	@Element(required=false )
	private String contact_name="";
	
	@Element(required=false )
	private String app_name="";
	
	
	@Element(required=false )
	private int user_status=0;
	
	@Element(required=false )
	private int category_home_screen=0;
	
	
	public void SetSettings(){
		
		AppSettings.UseCart=activate_shopping_cart;
		AppSettings.CATALOG_NAME = app_name;
		//AppSettings.delivertyCost=delivery_cost;
		AppSettings.isCatogryGrid=category_home_screen;
		
		AppSettings.MAIL = email;
		AppSettings.PHONE = telephone;
		AppSettings.SKYPE = telephone;
		AppSettings.FACEBOOK = website;
	}
}
