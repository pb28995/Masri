package com.dmbteam.catalogapp.cart;

import com.dmbteam.catalogapp.cmn.Post;

/**
 * The Class CartItem.
 */
public class CartItem {

	/** The quantity. */
	private int quantity;
	
	/** The product. */
	private Post product;

	/**
	 * Instantiates a new cart item.
	 *
	 * @param product the product
	 */
	public CartItem(Post product) {
		this.product = product;
		this.quantity = 1;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Post getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(Post product) {
		this.product = product;
	}

	/**
	 * Increment quantity.
	 */
	public void incrementQuantity() {
		++quantity;
	}

	/**
	 * Decrement quantity.
	 */
	public void decrementQuantity() {
		if(quantity > 0){
			--quantity;
		}
	}
}
