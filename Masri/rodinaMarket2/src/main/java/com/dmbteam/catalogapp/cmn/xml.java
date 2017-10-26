package com.dmbteam.catalogapp.cmn;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * The Class Catalog.
 */
@Root(strict=false)
public class xml {

	/** The categories. */
	//@ElementList
	//private List<Category> categories;

    @Element
    public  Branch branch;

	public Branch getMyBranCh(){

		return  branch;
	}
	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

//	@ElementList
	public List<Branch> branches;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }





	/** The products. */
	//@ElementList
	//private List<Product> products;

	/** The slider. */

	private List<Post> slider;

	// All categories with hierarchy
	/** The Predefines categories. */

	private ArrayList<Category> mPredefinesCategories;

	// All categories for adapter
	/** The Main categories. */
	private ArrayList<Category> mMainCategories;


	public void setsubCatogries(){

		List<Category>ct=new ArrayList<Category>();
		List<Category>temp=new ArrayList<Category>();

		for (Category cat : getMyBranCh().getCategories()){

			for (Category sub_cat : cat.getSub_categories()){

				temp.add(sub_cat);
			}
		}

		for (Category sub : temp) {

			//for (sub_category sub : category.getSub_categories()) {
				Category c=new Category();
				c.setId(sub.getId());
				c.setParentId(sub.getParentId());
				c.setTitle(sub.getTitle());
				c.setURL(sub.getURL());
				c.setProductCount(sub.getProductCount());

				ct.add(c);
			//}
		}
		getMyBranCh().getCategories().addAll(ct);
		slider=new ArrayList<Post>();
		for (Post category : getMyBranCh().getPosts()) {
			if(category.getSlider()==1){
				slider.add(category);
			}
		}

	}
	/**
	 * Make categories hierarchy.
	 *
	 * @param localPredefinesCategories the local predefines categories
	 */
	public void makeCategoriesHierarchy(List<Category> localPredefinesCategories) {

		if (localPredefinesCategories != null
				&& localPredefinesCategories.size() > 0) {
			Category category = localPredefinesCategories.get(0);
			localPredefinesCategories.remove(0);

			if (category.getParentId() == 0) {
				category.setMain(true);
			}

			for (int i = 0; i < localPredefinesCategories.size(); i++) {
				if (localPredefinesCategories.get(i).getParentId() == category
						.getId()) {
					category.getSubCategoriesIds().add(
							localPredefinesCategories.get(i).getId());
					// localPredefinesCategories.set(i, null);
				}

				if (localPredefinesCategories.get(i).getId() == category
						.getParentId()) {
					localPredefinesCategories.get(i).addSubCategoryId(
							category.getId());
				}
			}

			getPredefinesCategories().add(category);

			// localPredefinesCategories.removeAll(Collections.singleton(null));

			makeCategoriesHierarchy(localPredefinesCategories);
		} else {
			System.out.println("");
		}
	}

	// All categories sorted by id
	/**
	 * Gets the all categories.
	 *
	 * @return the all categories
	 */
	public List<Category> getAllCategories() {



		return getMyBranCh().getCategories();
	}

	// All products sorted by id
	/**
	 * Gets the all products.
	 *
	 * @return the all products
	 */
	public List<Post> getAllProducts() {
		return getMyBranCh().getPosts();
	}

	// All categories with hierarchy
	/**
	 * Gets the predefines categories.
	 *
	 * @return the predefines categories
	 */
	public ArrayList<Category> getPredefinesCategories() {

		if (mPredefinesCategories == null) {
			mPredefinesCategories = new ArrayList<Category>();
		}

		return mPredefinesCategories;
	}

	// Initialize first with main categories
	/**
	 * Inits the categories for adapter.
	 */
	public void initCategoriesForAdapter() {
		mMainCategories = new ArrayList<Category>();

		for (int i = 0; i < mPredefinesCategories.size(); i++) {
			if (mPredefinesCategories.get(i).isMain()) {
				mMainCategories.add(mPredefinesCategories.get(i));
			}
		}
	}

	/**
	 * Gets the main categories.
	 *
	 * @return the main categories
	 */
	public ArrayList<Category> getMainCategories() {
		return mMainCategories;
	}

	/**
	 * Gets the slider.
	 *
	 * @return the slider
	 */
	public List<Post> getSlider() {
		return slider;
	}

	/**
	 * Find all products with title containing.
	 *
	 * @param s the s
	 * @return the list
	 */
	public List<Post> findAllProductsWithTitleContaining(CharSequence s) {

		List<Post> products = new ArrayList<Post>();

		for (int i = 0; i < getMyBranCh().getPosts().size(); i++) {
			Post currentProduct = getMyBranCh().getPosts().get(i);

			if (currentProduct.getName().toLowerCase()
					.contains(s.toString().toLowerCase())) {
				products.add(currentProduct);
			}
		}

		for (int i = 0; i < getSlider().size(); i++) {
			Post currentProduct = getSlider().get(i);

			if (currentProduct.getName().toLowerCase()
					.contains(s.toString().toLowerCase())) {
				products.add(currentProduct);
			}
		}

		return products;
	}

}
