package com.dmbteam.catalogapp.cmn;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class product_image {

	
	public product_image(){
		
	}
	
	@Element(required = false)
	private String image_url;

	public String getImage_url() {
		return image_url.trim();
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
}
