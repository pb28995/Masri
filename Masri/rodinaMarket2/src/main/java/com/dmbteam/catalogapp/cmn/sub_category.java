package com.dmbteam.catalogapp.cmn;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;



@Root(strict=false)
public class sub_category {

	public sub_category(){
		
	}

	@Element
	private String category_name_ar;
	//@Element
	private String thumbnail;
	@Element
	private int category_order;
	@Element
	private int status;

	public String getCategory_name_ar() {
		return category_name_ar;
	}

	public void setCategory_name_ar(String category_name_ar) {
		this.category_name_ar = category_name_ar;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getCategory_order() {
		return category_order;
	}

	public void setCategory_order(int category_order) {
		this.category_order = category_order;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}






	@Element(required = false)
	private int category_id;
	@Element
	private String category_name;
	@Element(required = false)
	private int parent_id;
	
	@Element(required = false)
	private String thumbnail_url;
	
	@Element(required = false)
	private int product_count;
	
	public String getURL() {
		return thumbnail_url;
	}
	
	public int getProductCount() {
		return product_count;
	}
	
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

}
