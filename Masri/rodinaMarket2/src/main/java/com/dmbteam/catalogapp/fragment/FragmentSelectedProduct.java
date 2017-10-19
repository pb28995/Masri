package com.dmbteam.catalogapp.fragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Rodina_Market.app.MainActivity;
import com.Rodina_Market.app.R;
import com.dmbteam.catalogapp.adapter.ItemsPagerAdapter;
import com.dmbteam.catalogapp.cmn.Post;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.parse.entity.mime.MultipartEntity;

/**
 * The Class FragmentSelectedProduct.
 */
public class FragmentSelectedProduct extends Fragment {

	
	class viewCount extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			
			
			HttpClient httpClient = new DefaultHttpClient();
			String urlString = AppSettings.apiEndpoint;
		    try
		    {
		    	HttpPost httpPost = new HttpPost(urlString);
		    	List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		    	nameValuePair.add(new BasicNameValuePair("type", "pharmacydata"));
		    	nameValuePair.add(new BasicNameValuePair("pharmacy_id", AppSettings.cid));
					nameValuePair.add(new BasicNameValuePair("product_id", updater+""));
		    	
		    	//Encoding POST data
		    	try {
		    	      httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		    	 
		    	} catch (UnsupportedEncodingException e) 
		    	{
		    	     e.printStackTrace();
		    	}
		    	
		    	try {
		    	    HttpResponse response = httpClient.execute(httpPost);
		    	    // write response to log
		    	   // Log.d("Http Post Response:", response.toString());
		    	} catch (ClientProtocolException e) {
		    	    // Log exception
		    	    e.printStackTrace();
		    	} catch (IOException e) {
		    	    // Log exception
		    	    e.printStackTrace();
		    	}
		    	
		       
		    }
		    catch (Exception ex){
		        //Log.e("Debug", "error: " + ex.getMessage(), ex);
		    }
			return null;
		}
		
	}
	
	/** The Constant TAG. */
	public static final String TAG = FragmentSelectedProduct.class
			.getSimpleName();
	
	/** The All products. */
	private List<Post> mAllProducts;
	
	/** The Selected position. */
	private int mSelectedPosition;

	/**
	 * New instance.
	 *
	 * @param allProducts the all products
	 * @param selectedPosition the selected position
	 * @return the fragment selected product
	 */
	public static FragmentSelectedProduct newInstance(
			List<Post> allProducts, int selectedPosition) {

		FragmentSelectedProduct fragment = new FragmentSelectedProduct();

		fragment.mAllProducts = allProducts;
		fragment.mSelectedPosition = selectedPosition;

		return fragment;
	}

	/** The Parent view. */
	private View mParentView;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	public int updater=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mParentView = inflater
				.inflate(R.layout.fragment_selected_product, null);

		ViewPager viewPager = (ViewPager) mParentView
				.findViewById(R.id.list_selected_produt_viewpager);

		final ItemsPagerAdapter itemsPagerAdapter = new ItemsPagerAdapter(
				getActivity(), mAllProducts);

		viewPager.setAdapter(itemsPagerAdapter);

		viewPager.setCurrentItem(mSelectedPosition);

		((MainActivity) getActivity()).setAbTitle(mAllProducts.get(
				mSelectedPosition).getName());
       updater=mAllProducts.get(mSelectedPosition).getPost_id();
		new viewCount().execute();
		//Toast.makeText(getActivity(),mAllProducts.get(mSelectedPosition).getTitle() , 0).show();
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				((MainActivity) getActivity()).setAbTitle(mAllProducts
						.get(arg0).getName());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		return mParentView;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();

		((MainActivity) getActivity()).showCommonAb();
		((MainActivity) getActivity()).refreshCartCounter();
	}
}
