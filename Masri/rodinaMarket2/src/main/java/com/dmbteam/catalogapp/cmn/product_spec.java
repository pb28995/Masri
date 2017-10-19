package com.dmbteam.catalogapp.cmn;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import android.hardware.Camera.FaceDetectionListener;

@Root(strict=false)
public class product_spec {

	
	@Element(required = false)
	String spec_value;
	@Element(required = false)
	String spec_name;
	
	
	public product_spec(){
		
	}
	
	
	public String getSpec_value() {
		return spec_value;
	}
	public void setSpec_value(String spec_value) {
		this.spec_value = spec_value;
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	
	
}
