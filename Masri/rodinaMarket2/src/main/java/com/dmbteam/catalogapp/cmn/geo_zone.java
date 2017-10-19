package com.dmbteam.catalogapp.cmn;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class geo_zone {

	
	public geo_zone(){
		
	}
	
	@Element
	String zone_id;
	
	@Element
	String zone_name;
	@Element
	String longitude;
	@Element
	String latitude;
	@Element
	String radius_length;
	
	
	public String getID(){
		return zone_id;
	}
	public String getZone_name() {
		return zone_name;
	}
	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getRadius_length() {
		return radius_length;
	}
	public void setRadius_length(String radius_length) {
		this.radius_length = radius_length;
	}

	
	
	
	
	
}
