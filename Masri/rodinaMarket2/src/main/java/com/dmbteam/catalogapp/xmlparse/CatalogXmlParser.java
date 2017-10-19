package com.dmbteam.catalogapp.xmlparse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.core.Persister;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.Rodina_Market.app.R;
import com.dmbteam.catalogapp.cmn.Post;
import com.dmbteam.catalogapp.cmn.xml;
import com.Rodina_Market.app.MainActivity;
import com.dmbteam.catalogapp.cmn.Category;
import com.dmbteam.catalogapp.cmn.productxml;
import com.dmbteam.catalogapp.settings.AppSettings;

/**
 * The Class CatalogXmlParser.
 */
public class CatalogXmlParser {

	/** The Constant LOG. */
	public static final String LOG = CatalogXmlParser.class.getSimpleName();

	/** The Catalog. */
	private xml mCatalog;

	/** The serializer. */
	private Persister serializer;

	/** The instance. */
	public static CatalogXmlParser instance;

	/**
	 * Instantiates a new catalog xml parser.
	 */
	private CatalogXmlParser() {

	}

	
	
	/**
	 * Gets the single instance of CatalogXmlParser.
	 *
	 * @return single instance of CatalogXmlParser
	 */
	public static CatalogXmlParser getInstance() {
		if (instance == null) {
			synchronized (CatalogXmlParser.class) {
				if (instance == null) {
					instance = new CatalogXmlParser();
				}
			}
		}

		return instance;
	}

	/**
	 * Parses the data.
	 *
	 * @param c the c
	 */
	public void parseData(Context c) {
		try {
			
			
			serializer = new Persister();
			new CatalogXmlNetworkStremReader(c).execute();
			  
			
				/*InputStream inputStream = c.getAssets().open("catalog.xml");
				mCatalog = serializer.read(xml.class, inputStream);
				mCatalog.setsubCatogries();
				
				mCatalog.makeCategoriesHierarchy(new ArrayList<Category>(
						mCatalog.getAllCategories()));

				mCatalog.initCategoriesForAdapter();

				((MainActivity) c).initMainComponents();*/
			
		} catch (Exception e) { 
			Log.e(LOG, "Error while parsing xml data" + e.getMessage());
		} 

	}

	/**
	 * Gets the catalog.
	 *
	 * @return the catalog
	 */
	public xml getCatalog() {
		return mCatalog;
	}

	/**  
	 * Gets the sub categories.
	 *
	 * @param category the category
	 * @param padding the padding
	 * @return the sub categories
	 */
	public List<Category> getSubCategories(Category category, int padding) {

		List<Category> subCategories = new ArrayList<Category>();

		if (category.getSubCategoriesIds() != null) {
			for (int i = 0; i < category.getSubCategoriesIds().size(); i++) {
				Category findedCategory = findCategoryById(category
						.getSubCategoriesIds().get(i));

				if (findedCategory != null) {
					findedCategory.setOpened(false);
					findedCategory.setTreeIndex(padding);

					subCategories.add(findedCategory);
				}
			}
		}

		return subCategories;
	}

	/**
	 * Find category by id.
	 *
	 * @param id the id
	 * @return the category
	 */
	public Category findCategoryById(int id) {
		for (int i = 0; i < mCatalog.getAllCategories().size(); i++) {
			if (mCatalog.getAllCategories().get(i).getId() == id) {
				return mCatalog.getAllCategories().get(i);
			}
		}

		return null;
	}

	/**
	 * Gets the all products for categories.
	 *
	 * @param categories the categories
	 * @return the all products for categories
	 */
	public List<Post> getAllProductsForCategories(List<Category> categories) {

		List<Post> resultList = new ArrayList<Post>();

		for (int i = 0; i < categories.size(); i++) {
			List<Post> allProductsForCurrentCategory = getAllProductsForCategory(categories
					.get(i));
			resultList.addAll(allProductsForCurrentCategory);
		}

		return resultList;
	}

	/**
	 * Gets the all products for category.
	 *
	 * @param category the category
	 * @return the all products for category
	 */
	public List<Post> getAllProductsForCategory(Category category) {

		List<Post> resultList = new ArrayList<Post>();

		for (int i = 0; i < mCatalog.getAllProducts().size(); i++) {
			if (mCatalog.getAllProducts().get(i).getCategory() == category
					.getId()) {
				resultList.add(mCatalog.getAllProducts().get(i));
			}
		}

		for (int i = 0; i < mCatalog.getSlider().size(); i++) {
			if (mCatalog.getSlider().get(i).getCategory() == category.getId()) {
				resultList.add(mCatalog.getSlider().get(i));
			}
		}

		return resultList;
	}

	/**
	 * The Class CatalogXmlNetworkStremReader.
	 */
	private class CatalogXmlNetworkStremReader extends
			AsyncTask<Void, Void, Boolean> {

		/** The context. */
		private Context context;

		/**
		 * Instantiates a new catalog xml network strem reader.
		 *
		 * @param c the c
		 */
		public CatalogXmlNetworkStremReader(Context c) {
			this.context = c;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		@Override
		protected Boolean doInBackground(Void... params) {

			InputStream inputStream = null;
            InputStream result;
            HttpClient httpClient = new DefaultHttpClient();

            try {

                HttpPost httpPost = new HttpPost(AppSettings.apiEndpoint);
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("type", "pharmacydata"));
                nameValuePair.add(new BasicNameValuePair("pharmacy_id", AppSettings.cid));

                //Encoding POST data
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }

                try {
                    HttpResponse response = httpClient.execute(httpPost);
                   // InputStream reslt = response.getEntity().getContent();

					InputStream reslt = this.context.getAssets().open("pharmacyData.xml");



					if (reslt != null) {
                        try {
                            mCatalog = serializer.read(xml.class, reslt);

							Log.d("DEBUG_MURAD",mCatalog.toString());
							mCatalog.setsubCatogries();
							mCatalog.makeCategoriesHierarchy(new ArrayList<Category>(
									mCatalog.getAllCategories()));

							mCatalog.initCategoriesForAdapter();

							return true;


                        } catch (Exception e) {
                            Log.d("DEBUG_MURAD", e.toString());
                        }
                    }


                    // write response to log
                    // Log.d("Http Post Response:", response.toString());
                } catch (ClientProtocolException e) {
                    // Log exception
                    e.printStackTrace();
                } catch (IOException e) {
                    // Log exception
                    e.printStackTrace();
                }

			} catch (Exception e) {

                Log.d("DEBUG_MURAD", e.getMessage());

                //*****************************************************
                //*****************************************************

            }


			return false;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(final Boolean result) {
			super.onPostExecute(result);

			new Handler().post(new Runnable() {
				
				@Override
				public void run() {
					if (result.booleanValue()) {
						try{
							//addGeoFencing();
						
						
						((MainActivity) context).initMainComponents();
					    	
						
						((MainActivity) context).addGeoFencing(mCatalog.getAllProducts());
						}catch(Exception ex){
							
							int pp=0;
						}
					}
					
				}
			});
			
		}
	}  
}
