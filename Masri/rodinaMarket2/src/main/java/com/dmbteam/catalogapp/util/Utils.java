package com.dmbteam.catalogapp.util;

import android.content.Context;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Rodina_Market.app.R;
import com.dmbteam.catalogapp.adapter.MainProductAdapter;
import com.dmbteam.catalogapp.cmn.Post;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

/**
 * The Class Utils.
 */
public class Utils {
	/** The Formatter. */
	public static NumberFormat mFormatter;// = new DecimalFormat("#0.00");

	static{

//		Locale fmtLocale = Locale.getDefault();
		mFormatter = NumberFormat.getInstance(Locale.ENGLISH);
		mFormatter.setMaximumFractionDigits(0);
		mFormatter.setMinimumFractionDigits(0);
	}


	/**
	 * Dips to pixels.
	 *
	 * @param context the context
	 * @param dips the dips
	 * @return the int
	 */
	public static int dipsToPixels(Context context, float dips) {
		if (context != null) {
			return Math.round(TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, dips, context.getResources()
							.getDisplayMetrics()));
		} else {
			return Math.round(dips);
		}
	}

	/**
	 * Construct main cell view.
	 *
	 * @param mContext the context
	 * @param currentProduct the current product
	 * @param holder the holder
	 * @param mainImageView the main image view
	 * @param mDisplayImageOptions the display image options
	 * @param mainTitleView the main title view
	 * @param mainSubtitleView the main subtitle view
	 * @param mainViewDiscount the main view discount
	 * @param discountedView the discounted view
	 * @param priceView the price view
	 */
	public static void constructMainCellView(Context mContext,
											 Post currentProduct, MainProductAdapter.ViewHolder holder,
											 ImageView mainImageView, DisplayImageOptions mDisplayImageOptions,
											 TextView mainTitleView, TextView mainSubtitleView,
											 TextView mainViewDiscount, TextView discountedView,
											 TextView priceView) {

		mainTitleView.setText(currentProduct.getName());

		if (mainSubtitleView != null) {
			mainSubtitleView.setText(currentProduct.getDescription());
		}

		if (currentProduct.isNetworkResource()){
			ImageLoader.getInstance().displayImage(
					currentProduct.getPhoto(mContext), mainImageView,
					mDisplayImageOptions);
		} else {
			mainImageView.setImageDrawable(mContext.getResources().getDrawable(
					currentProduct.getDrawableId(mContext)));
		}

		String discountedPrice = "";
		if (currentProduct.getDiscount() > 0) {

			String discountToDisplay = mContext.getResources().getString(
					R.string.discount_format);
			discountToDisplay = String.format(discountToDisplay, ""
					+ (int) currentProduct.getDiscount() + "%");

			mainViewDiscount.setText(discountToDisplay);

			String realPrice = mFormatter.format(currentProduct.getPrice())
					+ currentProduct.getCurrencySign();
			priceView.setPaintFlags(priceView.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG);
			priceView.setText(realPrice);

			mainViewDiscount.setVisibility(View.VISIBLE);
			priceView.setVisibility(View.VISIBLE);

			discountedPrice = mFormatter.format(currentProduct
					.getDiscountedPrice()) +currentProduct.getCurrencySign();
		} else {
			mainViewDiscount.setVisibility(View.GONE);
			priceView.setVisibility(View.GONE);
			discountedPrice = mFormatter.format(currentProduct.getPrice())
					+ currentProduct.getCurrencySign();
		}
		
		if(currentProduct.getPrice()==0){
			priceView.setVisibility(View.GONE);
			discountedView.setVisibility(View.GONE);
		}
		discountedView.setText(discountedPrice);
	}

	/**
	 * Rand int.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
