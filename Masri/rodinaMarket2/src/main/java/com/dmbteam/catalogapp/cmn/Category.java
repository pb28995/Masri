package com.dmbteam.catalogapp.cmn;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * The Class Category.
 */
@Root(strict=false)
public class Category implements Comparable<Category> {

	/**
	 * Instantiates a new category.
	 */
	public Category() {
		this.subCategoriesIds = new ArrayList<Integer>();
	}

	/** The sub categories ids. */
	private List<Integer> subCategoriesIds;
	
	//@ElementList
	private List<sub_category>sub_categories;
	
	public List<sub_category> getSub_categories() {
		if(sub_categories==null)return new ArrayList<sub_category>();
		return sub_categories;
	}

	public void setSub_categories(List<sub_category> sub_categories) {
		this.sub_categories = sub_categories;
	}

	/** The tree index. */
	private int treeIndex;

	/** The id. */
	@Element
	private int category_id;

	/** The parent id. */
	@Element
	private int parent_id;


	@Element
	private String category_name_ar;
	@Element
	private int status;
	@Element
	private int category_order;
	@Element
	private  int branch_id;

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	//@Element
	private String thumbnail;


	public String getCategory_name_ar() {
		return category_name_ar;
	}

	public void setCategory_name_ar(String category_name_ar) {
		this.category_name_ar = category_name_ar;
	}



	public int getCategory_order() {
		return category_order;
	}

	public void setCategory_order(int category_order) {
		this.category_order = category_order;
	}


	/** The title. */
	@Element
	private String category_name;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}




	/** The Opened. */
	private boolean mOpened;
	
	/** The Is main. */
	private boolean mIsMain;

	@Element(required=false)
	private int  product_count;
	
	@Element(required=false)
	private String thumbnail_url="";
	
	public String getURL() {
		return thumbnail_url;
	}
	
	public int getProductCount() {
		return product_count;
	}
	
	
	public void setURL(String s) {
		 thumbnail_url=s;
	}
	
	public void setProductCount(int s) {
		 product_count=s;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return category_id;
	}

	public void setId(int x){
		category_id=x;
	}
	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public int getParentId() {
		return parent_id;
	}
	public void setParentId(int x){
		parent_id=x;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return category_name;
	}
	public void setTitle(String x){
		category_name=x;
	}

	/**
	 * Gets the sub categories ids.
	 *
	 * @return the sub categories ids
	 */
	public List<Integer> getSubCategoriesIds() {
		return subCategoriesIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Category anotherCategory) {
		return Integer.valueOf(category_id).compareTo(anotherCategory.category_id);
	}

	/**
	 * Adds the sub category id.
	 *
	 * @param subCategoryId the sub category id
	 */
	public void addSubCategoryId(int subCategoryId) {
		this.subCategoriesIds.add(subCategoryId);
	}

	
	/**
	 * Sets the tree index.
	 *
	 * @param padding the new tree index
	 */
	public void setTreeIndex(int padding){
		this.treeIndex = padding;
	}

	/**
	 * Gets the tree index.
	 *
	 * @return the tree index
	 */
	public int getTreeIndex() {
		return treeIndex;
	}

	/**
	 * Sets the opened.
	 *
	 * @param opened the new opened
	 */
	public void setOpened(boolean opened) {
		mOpened = opened;
	}

	/**
	 * Checks if is opened.
	 *
	 * @return true, if is opened
	 */
	public boolean isOpened() {
		return mOpened;
	}

	/**
	 * Checks if is main.
	 *
	 * @return true, if is main
	 */
	public boolean isMain() {
		return mIsMain;
	}

	/**
	 * Sets the main.
	 *
	 * @param isMain the new main
	 */
	public void setMain(boolean isMain) {
		mIsMain = isMain;
	}

}