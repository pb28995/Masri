package com.dmbteam.catalogapp.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.Rodina_Market.app.IVR;
import com.Rodina_Market.app.MainActivity;
import com.Rodina_Market.app.R;
import com.dmbteam.catalogapp.cart.CartManager;
import com.dmbteam.catalogapp.cmn.Post;
import com.dmbteam.catalogapp.cmn.Spec;
import com.dmbteam.catalogapp.cmn.product_spec;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.dmbteam.catalogapp.util.ImageOptionsBuilder;
import com.dmbteam.catalogapp.util.Utils;
import com.dmbteam.catalogapp.xmlparse.CatalogXmlParser;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * The Class ItemsPagerAdapter.
 */
public class ItemsPagerAdapter extends PagerAdapter implements IconPagerAdapter {

	/** The Context. */
	private Context mContext;
	
	/** The Inflater. */
	private LayoutInflater mInflater;
	
	/** The Adapter data. */
	private List<Post> mAdapterData;
	
	/** The Display image options. */
	private DisplayImageOptions mDisplayImageOptions;
	
	/** The Image loader. */
	private ImageLoader mImageLoader;
	
	/** The Floating action button. */
	private FloatingActionButton mFloatingActionButton;

	/**
	 * Instantiates a new items pager adapter.
	 *
	 * @param context the context
	 * @param adapterData the adapter data
	 */
	public ItemsPagerAdapter(Context context, List<Post> adapterData) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(this.mContext);

		this.mAdapterData = adapterData;

		this.mDisplayImageOptions = ImageOptionsBuilder
				.buildGeneralImageOptions(false, R.drawable.home_nexus9);
		this.mImageLoader = ImageLoader.getInstance();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount() {
		return mAdapterData.size();
	}
	
	

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#isViewFromObject(android.view.View, java.lang.Object)
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return (view == object);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#instantiateItem(android.view.ViewGroup, int)
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		final Post currentProduct = mAdapterData.get(position);
 
		final View mainLayout = mInflater.inflate(R.layout.pager_item_product, null);

		
		
		TextView pageview = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_views);
		pageview.setText(currentProduct.getViewCount());
		
		TextView pageInfo = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_page_info);
		
		//pageInfo.setText(String.format(
			//	mContext.getString(R.string.selected_page_number), ++position,
				//mAdapterData.size()));

		
		CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(mainLayout.getContext(),currentProduct.getProduct_images(),true,pageInfo);
		ViewPager mainImageView = (ViewPager) mainLayout.findViewById(R.id.list_item_photo_viewpager);
		mainImageView.setAdapter(mCustomPagerAdapter);
		
		
		
		TextView txtBc=(TextView)mainLayout.findViewById(R.id.pager_item_product_titlebc);
		txtBc.setText(currentProduct.Getcrum());
		//ImageView mainImageView = (ImageView) mainLayout 
			//	.findViewById(R.id.pager_item_product_image);

		/*if (currentProduct.isNetworkResource()) {
		
			String uri=currentProduct.getPhoto(mContext);
			mImageLoader.displayImage(uri,
					mainImageView, mDisplayImageOptions);
		} else {
			try{
			mainImageView.setImageDrawable(mContext.getResources().getDrawable(
					currentProduct.getDrawableId(mContext)));
			}catch(Exception ex){}
		}*/
		
		if(currentProduct.getPrice()==0){
			
			((ImageView)(mainLayout.findViewById(R.id.pager_item_product_oval_discount))).setVisibility(View.GONE);
			//pager_item_product_oval_discount
		}
 
		TextView categoryView = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_category);
		categoryView.setText(CatalogXmlParser.getInstance()
				.findCategoryById(currentProduct.getCategory()).getTitle());

		
		TextView discountInfo = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_discount);
		if (currentProduct.getDiscount() > 0) {
			discountInfo.setVisibility(View.VISIBLE);

			String discountToDisplay = mContext.getResources().getString(
					R.string.discount_format);
			discountToDisplay = String.format(discountToDisplay, ""
					+ (int) currentProduct.getDiscount() + "%");
			discountInfo.setText(discountToDisplay);
		} else {
			discountInfo.setVisibility(View.GONE);
		}

		TextView priceNoDiscount = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_oval_price_no_discount);

		TextView priceUnderlined = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_oval_price);
		TextView priceWithDiscount = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_oval_price_discount);

		String noDiscountPrice = Utils.mFormatter.format(currentProduct
				.getPrice()) +currentProduct.getCurrencySign();
		if (currentProduct.getDiscount() > 0) {
			priceNoDiscount.setVisibility(View.GONE);

			priceUnderlined.setVisibility(View.VISIBLE);
			priceUnderlined.setText("" + noDiscountPrice);
			priceUnderlined.setPaintFlags(priceUnderlined.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG);

			double discountedPriceDouble = currentProduct.getPrice()
					
					* (1 - currentProduct.getDiscount() / 100);
			
			String discountedPrice = Utils.mFormatter
					.format(discountedPriceDouble) + currentProduct.getCurrencySign();
			priceWithDiscount.setText("" +  discountedPrice);
			priceWithDiscount.setVisibility(View.VISIBLE);

		} else {
			priceUnderlined.setVisibility(View.GONE);
			priceWithDiscount.setVisibility(View.GONE);

			priceNoDiscount.setVisibility(View.VISIBLE);

			priceNoDiscount.setText("" + noDiscountPrice);
		}

		
		if(currentProduct.getPrice()==0){
			priceUnderlined.setVisibility(View.GONE);
			priceWithDiscount.setVisibility(View.GONE);
			priceNoDiscount.setVisibility(View.GONE);
			priceUnderlined.setVisibility(View.GONE);
			
		}
		
		
		TextView titlePagerItem = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_title);

		titlePagerItem.setText(currentProduct.getName());

		

		
		//current product
		LinearLayout ll=(LinearLayout)mainLayout.findViewById(R.id.pager_item_product_condition);
		LayoutParams parentLayoutParams = new LinearLayout.LayoutParams(
		        LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		for (Spec product : currentProduct.getProduct_specs()) {
			
			LinearLayout tmp=new LinearLayout(mainImageView.getContext());
			tmp.setWeightSum(1);
			TextView key=new TextView(mainImageView.getContext());
			TextView val=new TextView(mainImageView.getContext());
			key.setText(product.getSpec_name());
			
			
			LayoutParams lp=new LayoutParams(LayoutParams.FILL_PARENT,
		            LayoutParams.WRAP_CONTENT);
			lp.setMargins(23, 0, 0, 0);
			lp.weight=0.5f;
			key.setLayoutParams(lp);
			
			lp=new LayoutParams(LayoutParams.FILL_PARENT,
		            LayoutParams.WRAP_CONTENT);
			
			lp.weight=0.5f;
			val.setLayoutParams(lp);
			val.setText(product.getSpec_value());
			tmp.setLayoutParams(parentLayoutParams);
			key.setTextColor(Color.GRAY);
			val.setTextColor(Color.BLACK);
			key.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			val.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);	
			
			final float scale = mainImageView.getContext().getResources().getDisplayMetrics().density;
			int pixels = (int) (2 * scale + 0.5f);
			lp=new LayoutParams(LayoutParams.FILL_PARENT,pixels
		            );
			View v=new View(mainImageView.getContext());
			v.setBackgroundResource(R.color.selected_product_separator_bg);
			v.setLayoutParams(lp);
			
			tmp.addView(key);
			tmp.addView(val);
			
			ll.addView(tmp);
			ll.addView(v);
		}
		

		
		Button call=(Button)mainLayout.findViewById(R.id.call);
		Button share=(Button)mainLayout.findViewById(R.id.share);
		Button reserve=(Button)mainLayout.findViewById(R.id.res);
		
		call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				if(currentProduct.getIvr_enabled()==1){
					mainLayout.getContext().startActivity(new Intent(mainLayout.getContext(),IVR.class));
					
				}else{
				try{
		  		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + currentProduct.getSales_phone().trim()));
				mContext.startActivity(intent);
				}catch(Exception ex){
					
					try{
						String callNo=currentProduct.getSales_phone().trim().replace("+", "00");
						Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callNo));
						mContext.startActivity(intent);
						  
					}catch(Exception d){
						Toast.makeText(mContext, mContext.getResources().getString(R.string.no_number_available), 0).show();
					}
					
				}
			}
			}
		});
		
		if(currentProduct.getPrice()==0){
			reserve.setVisibility(View.GONE);
			LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1.5f);
			param.setMargins(1, 0, 1, 0);
			share.setLayoutParams(param);
			call.setLayoutParams(param);
			
			mainLayout.findViewById(R.id.tyyyyy).setVisibility(View.GONE);
			mainLayout.findViewById(R.id.pager_item_product_delimiter1).setVisibility(View.GONE);
			
		}
		reserve.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(AppSettings.UseCart==0){
				CartManager.getInstance().clearCart();
				}
				
				
				if (CartManager.getInstance().checkExistance(currentProduct)) {
					CartManager.getInstance().addProductToItems(currentProduct);
					((MainActivity) mContext).showCartFragment(true, false);
				} else{
					((MainActivity) mContext).showCartFragment(true, false);
				}
			}
		});
		
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
					String title=currentProduct.getName();
					String pic="";
			     	if(currentProduct.getPhoto(mContext).length()>0){
			     		pic=currentProduct.getPhoto(mContext);
			     	}
			     	
			     	
					String shareBody = title+"\n"+currentProduct.getDescription()+"\n"+"call now :"+currentProduct.getSales_phone()+"\n\n"+pic;
			        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			        sharingIntent.setType("text/plain");
			        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
			        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
			        mContext.startActivity(Intent.createChooser(sharingIntent, "share offer"));
			}
		});
		
		LinearLayout checkout=(LinearLayout)mainLayout.findViewById(R.id.pager_item_product_color1);
		if(AppSettings.UseCart==0){
			checkout.setVisibility(View.GONE);
		}else{
			checkout.setVisibility(View.VISIBLE);
			Button img=(Button)mainLayout.findViewById(R.id.cartme);
			img.setOnClickListener(new FloatingActionButtonListener(currentProduct));
			if(currentProduct.getPrice()==0){
				img.setVisibility(View.GONE);
				//((TextView)mainLayout.findViewById(R.id.pager_item_product_color_value)).setVisibility(View.GONE);
			}
			
		}
		TextView descriptionTextView = (TextView) mainLayout
				.findViewById(R.id.pager_item_product_description);
		descriptionTextView.setText(currentProduct.getDescription()+"\n\n\n");

		ObservableScrollView observableScrollView = (ObservableScrollView) mainLayout
				.findViewById(R.id.pager_item_ObservableScrollView);

		mFloatingActionButton = (FloatingActionButton) mainLayout
				.findViewById(R.id.fab);
		mFloatingActionButton.attachToScrollView(observableScrollView);

		mFloatingActionButton
				.setOnClickListener(new FloatingActionButtonListener(
						currentProduct));
		mFloatingActionButton.setVisibility(View.GONE);
		
		
		if(AppSettings.UseCart==0){
		mFloatingActionButton.setVisibility(View.GONE);
		}
		container.addView(mainLayout, 0);

		return mainLayout;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#destroyItem(android.view.ViewGroup, int, java.lang.Object)
	 */
	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		collection.removeView((FrameLayout) view);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#setPrimaryItem(android.view.ViewGroup, int, java.lang.Object)
	 */
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#startUpdate(android.view.ViewGroup)
	 */
	public void startUpdate(ViewGroup arg0) {
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#saveState()
	 */
	@Override
	public Parcelable saveState() {
		return null;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#restoreState(android.os.Parcelable, java.lang.ClassLoader)
	 */
	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	/* (non-Javadoc)
	 * @see android.support.v4.view.PagerAdapter#finishUpdate(android.view.ViewGroup)
	 */
	@Override
	public void finishUpdate(ViewGroup arg0) {
	}

	/* (non-Javadoc)
	 * @see com.viewpagerindicator.IconPagerAdapter#getIconResId(int)
	 */
	@Override
	public int getIconResId(int index) {

		return R.drawable.pager_icon_selector;
	}

	/**
	 * The listener interface for receiving floatingActionButton events.
	 * The class that is interested in processing a floatingActionButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addFloatingActionButtonListener<code> method. When
	 * the floatingActionButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see FloatingActionButtonEvent
	 */
	private class FloatingActionButtonListener implements View.OnClickListener {

		/** The selected product. */
		Post selectedProduct;

		/**
		 * Instantiates a new floating action button listener.
		 *
		 * @param selectedProduct the selected product
		 */
		public FloatingActionButtonListener(Post selectedProduct) {
			super();
			this.selectedProduct = selectedProduct;
		}

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {
			
			if(CartManager.getInstance().checkExistance(selectedProduct)){
				CartManager.getInstance().addProductToItems(selectedProduct);

				((MainActivity) mContext).refreshCartCounter();
			}else{
				Toast.makeText(mContext, mContext.getString(R.string.existing_product_in_cart), Toast.LENGTH_LONG).show();
			}
			
		}

	}
}
