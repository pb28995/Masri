package com.dmbteam.catalogapp.cart;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.dmbteam.catalogapp.cmn.Post;

import com.dmbteam.catalogapp.settings.AppSettings;
import com.dmbteam.catalogapp.util.AutoIncrementGenerator;
import com.dmbteam.catalogapp.util.Utils;

/**
 * The Class CartManager.
 */
public class CartManager {

	/** The instance. */
	private static CartManager instance;

	/** The All items. */
	private List<CartItem> mAllItems;

	/**
	 * Gets the single instance of CartManager.
	 * 
	 * @return single instance of CartManager
	 */
	public static CartManager getInstance() {

		if (instance == null) {
			synchronized (CartManager.class) {
				if (instance == null) {
					instance = new CartManager();
				}
			}
		}

		return instance;
	}

	/**
	 * Instantiates a new cart manager.
	 */
	private CartManager() {
		mAllItems = new ArrayList<CartItem>();
	}

	/**
	 * Adds the product to items.
	 * 
	 * @param product
	 *            the product
	 */
	public void addProductToItems(Post product) {
		this.mAllItems.add(new CartItem(product));
	}

	/**
	 * Clear cart.
	 */
	public void clearCart() {
		this.mAllItems.clear();
	}

	/**
	 * Check existance.
	 * 
	 * @param productToCheck
	 *            the product to check
	 * @return true, if successful
	 */
	public boolean checkExistance(Post productToCheck) {
		for (int i = 0; i < mAllItems.size(); i++) {
			if (mAllItems.get(i).getProduct().getPost_id() == productToCheck.getPost_id()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Gets the all items.
	 * 
	 * @return the all items
	 */
	public List<CartItem> getAllItems() {
		return mAllItems;
	}

	/**
	 * Increment quantity of product.
	 * 
	 * @param productId
	 *            the product id
	 */
	public void incrementQuantityOfProduct(int productId) {
		for (int i = 0; i < getAllItems().size(); i++) {
			if (getAllItems().get(i).getProduct().getPost_id() == productId) {
				getAllItems().get(i).incrementQuantity();
				break;
			}
		}
	}

	/**
	 * Decrement quantity of product.
	 * 
	 * @param productId
	 *            the product id
	 */
	public void decrementQuantityOfProduct(int productId) {
		for (int i = 0; i < getAllItems().size(); i++) {
			if (getAllItems().get(i).getProduct().getPost_id() == productId) {
				getAllItems().get(i).decrementQuantity();
				break;
			}
		}
	}

	/**
	 * Gets the saved value.
	 * 
	 * @return the saved value
	 */
	public String getSavedValue() {
		double value = 0;

		for (int i = 0; i < getAllItems().size(); i++) {
			CartItem currentCartItem = getAllItems().get(i);
			Post currentProduct = currentCartItem.getProduct();

			if (currentProduct.getDiscount() > 0) {
				value += currentCartItem.getQuantity()
						* (currentProduct.getPrice() - currentProduct
								.getDiscountedPrice());
			}
		}

		return Utils.mFormatter.format(value);
	}

	/**
	 * Gets the sub total.
	 * 
	 * @return the sub total
	 */
	public String getSubTotalString() {
		double value = getSubTotalDouble();
		return Utils.mFormatter.format(value);
	}

	private double getSubTotalDouble() {
		double value = 0;

		for (int i = 0; i < getAllItems().size(); i++) {
			CartItem currentCartItem = getAllItems().get(i);
			Post currentProduct = currentCartItem.getProduct();

			if (currentProduct.getDiscount() > 0) {
				value += currentCartItem.getQuantity()
						* currentProduct.getDiscountedPrice();
			} else {
				value += currentCartItem.getQuantity()
						* currentProduct.getPrice();
			}
			//value = value / (1 + currentProduct.GetTax());
		}
		
		return value;
	}
	
	public String getSummry() {
		String summry ="[";

		for (int i = 0; i < getAllItems().size(); i++) {
			
			CartItem currentCartItem = getAllItems().get(i);
			Post currentProduct = currentCartItem.getProduct();
			String local="{ "+'"'+ "product"+'"'+" : "+currentProduct.getPost_id()+" ,"+'"'+"quantity"+'"'+" : "+currentCartItem.getQuantity()+"}";
			
			if(summry.endsWith("[")){
				summry=summry+local;
			}else{
				summry=summry+","+local;
			}
			//summry=summry+"\n"+currentProduct.getTitle()+" --- "+currentCartItem.getQuantity();
			
		}
		
		summry=summry+"]";
		//summry=summry+"\n\n"+"Total :"+getTotal();
		return summry;
	}

	/**
	 * Gets the vat.
	 * 
	 * @return the vat
	 */
	
public double getVat() {
		
		double value = 0;
		for (int i = 0; i < getAllItems().size(); i++) {
			
			double value2 = 0;
			CartItem currentCartItem = getAllItems().get(i);
			 
			Post currentProduct = currentCartItem.getProduct();
			if (currentProduct.getDiscount() > 0) {
				value2 += currentCartItem.getQuantity()
						* currentProduct.getDiscountedPrice();
			} else {
				value2 += currentCartItem.getQuantity()
						* currentProduct.getPrice();
			}
			value+=value2*currentProduct.GetTax();
		}
		return value;
	}

	public String getVatString() {
		
		double value = 0;
		for (int i = 0; i < getAllItems().size(); i++) {
			
			double value2 = 0;
			CartItem currentCartItem = getAllItems().get(i);
			 
			Post currentProduct = currentCartItem.getProduct();
			if (currentProduct.getDiscount() > 0) {
				value2 += currentCartItem.getQuantity()
						* currentProduct.getDiscountedPrice();
			} else {
				value2 += currentCartItem.getQuantity()
						* currentProduct.getPrice();
			}
			value+=value2*currentProduct.GetTax();
		}
		return Utils.mFormatter.format(value);
	}
	
	public String getdeliveryString() {
		
		double value = 0;
		for (int i = 0; i < getAllItems().size(); i++) {
			
			CartItem currentCartItem = getAllItems().get(i);
			 
			Post currentProduct = currentCartItem.getProduct();
			
			if(value<currentProduct.getDelivery_cost())
				value=currentProduct.getDelivery_cost();
		}
		return Utils.mFormatter.format(value);
	}
	
	public double getdeliverydouble() {
		
		double value = 0;
		for (int i = 0; i < getAllItems().size(); i++) {
			
			
			CartItem currentCartItem = getAllItems().get(i);
			 
			Post currentProduct = currentCartItem.getProduct();
			if(value<currentProduct.getDelivery_cost())
			 value=currentProduct.getDelivery_cost();
		}
		return value;
	}
	public double getVatDouble() {
		double value = 0;
		for (int i = 0; i < getAllItems().size(); i++) {
			
			double value2 = 0;
			CartItem currentCartItem = getAllItems().get(i);
			 
			Post currentProduct = currentCartItem.getProduct();
			if (currentProduct.getDiscount() > 0) {
				value2 += currentCartItem.getQuantity()
						* currentProduct.getDiscountedPrice();
			} else {
				value2 += currentCartItem.getQuantity()
						* currentProduct.getPrice();
			}
			value+=value2*currentProduct.GetTax();
		}
		return value;
		//return Utils.mFormatter.format(value);
	}

	/**
	 * Gets the total.
	 * 
	 * @return the total
	 */
	public String getTotal() {
		return Utils.mFormatter.format(getSubTotalDouble()
				+ getVatDouble()+getdeliverydouble());
	}

	/**
	 * Removes the item.
	 * 
	 * @param position
	 *            the position
	 */
	public void removeItem(int position) {
		getAllItems().remove(position);
	}

	/**
	 * Generate string for mail.
	 * 
	 * @param name
	 *            the name
	 * @param mail
	 *            the mail
	 * @param phone
	 *            the phone
	 * @param comment
	 *            the comment
	 * @return the string
	 */
	public String generateStringForMail(Context context, String name,
			String mail, String phone, String comment) {
		String result = "";

		result += "Order Number: "
				+ AutoIncrementGenerator.getIncrementValue(context) + "\n\n";

		result += "Name: ";
		result += name;
		result += "\n";

		result += "E-mail: ";
		result += mail;
		result += "\n";

		result += "Phone: ";
		result += phone;
		result += "\n";

		if (!comment.isEmpty()) {
			result += "Comment: ";
			result += comment;
			result += "\n";
		}

		result += "\n";
		result += "\n";

		for (int i = 0; i < getAllItems().size(); i++) {
			CartItem currentCartItem = getAllItems().get(i);
			Post currentProduct = currentCartItem.getProduct();

			result += currentProduct.getName();
			result += "\t\t\t\t\t";
			result += currentCartItem.getQuantity();
			result += "x";
			result += "\t\t\t";

			String price = "";
			if (currentProduct.getDiscount() > 0) {
				price = Utils.mFormatter.format(currentProduct
						.getDiscountedPrice());
			} else {
				price = Utils.mFormatter.format(currentProduct.getPrice());
			}

			result += price;
			result += "\n";

		}

		result += "\n";
		result += "\n";

		result += "Total: " + getTotal();

		return result;
	}
}