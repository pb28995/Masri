package com.Rodina_Market.app;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import android.R.integer;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;   
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.LayoutInflater;  
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmbteam.catalogapp.adapter.CheckoutPreviewAdapter;
import com.dmbteam.catalogapp.adapter.GridAdapter;
import com.dmbteam.catalogapp.adapter.SlidingAdapter;
import com.dmbteam.catalogapp.cart.CartManager;
import com.dmbteam.catalogapp.cmn.Category;
import com.dmbteam.catalogapp.cmn.Post;
import com.dmbteam.catalogapp.cmn.geo_zone;
import com.dmbteam.catalogapp.fragment.FragmentCart;
import com.dmbteam.catalogapp.fragment.FragmentCartPreview;
import com.dmbteam.catalogapp.fragment.FragmentFilter;
import com.dmbteam.catalogapp.fragment.FragmentMain;
import com.dmbteam.catalogapp.fragment.FragmentSelectedProduct;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.dmbteam.catalogapp.util.ImageOptionsBuilder;
import com.dmbteam.catalogapp.util.ThemesManager;
import com.dmbteam.catalogapp.xmlparse.CatalogXmlParser;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * The Class MainActivity.
 */
public class MainActivity extends ActionBarActivity implements
		OnBackStackChangedListener {
  

	private PendingIntent mGeofencePendingIntent;
	
	List<Category> GridCatogry=null;
	

	
	void connectAPi(){

		/*if (mGoogleApiClient == null) {
		    mGoogleApiClient = new GoogleApiClient.Builder(this)
		        .addConnectionCallbacks(this)
		        .addOnConnectionFailedListener(this)
		        .addApi(LocationServices.API)
		        .build();
		}*/
	}
	
	public void addGeoFencing(List<Post>product){
		// getGeofencingRequest();
		
		/*CreateFancing(product);
		if(mGeofenceList.size()>0){
		LocationServices.GeofencingApi.addGeofences(
                mGoogleApiClient,
               mGeofenceList,
                getGeofencePendingIntent()
        ).setResultCallback(new ResultCallback<Result>() {

			@Override
			public void onResult(Result result) {
				
				if(result.getStatus().getStatusCode()!=CommonStatusCodes.SUCCESS){
					
					SimpleGeofenceStore x=new SimpleGeofenceStore(MainActivity.this);
					x.deleteall();
				}
			//	Print(result.getStatus().toString());
			}
		});
		}*/
	}


	/*private GeofencingRequest getGeofencingRequest() {
		CreateFancing();
	    GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
	    builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
	    builder.addGeofences(mGeofenceList);
	    return builder.build();
	}*/
	void CreateFancing(List<Post> products){
		
/*
		SimpleGeofenceStore geoStoreSample=new SimpleGeofenceStore(this);
		mGeofenceList=new ArrayList<Geofence>();
	 
		for (Product product : products) {
			
			List<geo_zone>GeoZones=product.getGeo_zones();
			
			for (geo_zone geo_zone : GeoZones) {
				
			String id="prod_"+product.getId()+"_geo_"+geo_zone.getID();
			SimpleGeofenceStore sample=geoStoreSample.getGeofence(id);	
			if(sample.status==true||sample.ProductName.length()>0)continue;
			
			geoStoreSample.setGeofence(id, product.getTitle(), false, geo_zone.getLongitude()+"", 
					geo_zone.getLatitude(), geo_zone.getRadius_length());
			mGeofenceList.add(new Geofence.Builder()
		    .setRequestId(id)
		    .setCircularRegion(
		    		Float.parseFloat(geo_zone.getLatitude()),
		    		Float.parseFloat(geo_zone.getLongitude()),
		    		Float.parseFloat(geo_zone.getRadius_length())*1000
		    )
		    .setExpirationDuration(Geofence.NEVER_EXPIRE)
		    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
		            Geofence.GEOFENCE_TRANSITION_EXIT)
		    .build());
	
			}
	}
*/
	 	/*mGeofenceList.add(new Geofence.Builder()
		   
	    .setRequestId("2")

	    .setCircularRegion(
	    		31.4484915,
	    		74.30581,
	            220
	    )
	    
	    .setExpirationDuration(Geofence.NEVER_EXPIRE)
	    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
	            Geofence.GEOFENCE_TRANSITION_EXIT)
	    .build());*/
		
	}
	     
 @Override
protected void onStart() {
	// TODO Auto-generated method stub

	super.onStart();
}
	
	

	
	class ViewDetails extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			//http://mobile.togetherpro.com/insertview.php?name=ali
			String name ="";
			try {
				 name = URLEncoder.encode(offer, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(name.length()==0)return null;
			//String url=Global.server+"/insertview.php?name="+name;
			
			return null;
		}
		
		
	}
	
	String phoneStr="";
	

	/** The Sliding list view. */
	private ListView mSlidingListView;

	/** The Main adapter data. */
	private ArrayList<Category> mMainAdapterData;

	/** The Sliding adapter. */
	private SlidingAdapter mSlidingAdapter;

	/** The catalog xml parser. */
	private CatalogXmlParser catalogXmlParser;

	/** The Drawer toggle. */
	private ActionBarDrawerToggle mDrawerToggle;

	/** The Drawer layout. */
	private DrawerLayout mDrawerLayout;

	/** The Toolbar. */
	private Toolbar mToolbar;

	/** The Ab cart counter. */
	private TextView mAbCartCounter;

	/** The Ab title. */
	private TextView mAbTitle;

	/** The Ab search. */
	private View mAbSearch;

	/** The Ab checkout. */
	public View mAbCheckout;

	/** The Ab cart image view. */
	public View mAbCartImageView;

	/** The Ab clear filter. */
	private View mAbClearFilter;

	/** The Ab search et. */
	private EditText mAbSearchEt;

	/** The Ab cancel search. */
	private View mAbCancelSearch;

	/** The Search text watcher. */
	private SearchTextWatcher mSearchTextWatcher;

	String offer="";
	/** The Ab sent. */
	private View mAbSent;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
	 */
	

	 private boolean validPhone(String phone) {
	        Pattern pattern = Patterns.PHONE;
	        return pattern.matcher(phone).matches();
	    }
	 
	 private boolean validEmail(String phone) {
	        Pattern pattern = Patterns.EMAIL_ADDRESS;
	        return pattern.matcher(phone).matches();
	    }
	 
	String GetPhoneNumber(){
		
		String phone="";
		String email="";
		AccountManager am = AccountManager.get(this);
		   Account[] accounts = am.getAccounts();
		      for (Account ac : accounts) 
		       {
		         if(validPhone(ac.name)){
		        	 if(phone.length()==0){
		        	 phone=ac.name.trim();}else{
		        		 phone=phone+" , "+ac.name.trim();
		        	 }
		         }
		    	  if(validEmail(ac.name)){
		    		 if(email.length()==0){
		    		  email=ac.name.trim();}else{
		    		  	  email=email+" ,"+ac.name.trim();
		    		  }
		    	  }
		       }
		    
		  //  Toast.makeText(getApplicationContext(), "Phone : "+phone+"\nEmail : "+email, 1).show();
		
		// displaySmsLog();
		 return phone+"::"+email;
		//TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
	//	String mPhoneNumber = tMgr.getSimSerialNumber();
	//	return mPhoneNumber;
	}
	
	
	void UpdateLocals(String lang){
		
		Locale  myLocale = new Locale(lang); 
	    Resources res = getResources(); 
	    DisplayMetrics dm = res.getDisplayMetrics(); 
	    Configuration conf = res.getConfiguration(); 
	    conf.locale = myLocale; 
	    res.updateConfiguration(conf, dm);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		ThemesManager.setCorrectTheme(this, AppSettings.CURRENT_THEME);

		super.onCreate(savedInstanceState);

		
		SharedPreferences sharePrefrence=this.getSharedPreferences(
			      getPackageName(), Context.MODE_PRIVATE);
		
		SharedPreferences myPref =  PreferenceManager.getDefaultSharedPreferences(this);
		if(myPref.getString("lang", "hhh").endsWith("1")){
			UpdateLocals("en_US");
		}
		if(myPref.getString("lang", "hhh").endsWith("2")){
			UpdateLocals("ar");
		}
		
		//Toast.makeText(getApplicationContext(), myPref.getString("lang", "hhh")+"", 0).show();
		//locationPrompt();
		connectAPi();

		setContentView(R.layout.activity_main);

		//ParseAnalytics.trackAppOpened(getIntent());
		
		int isemail=sharePrefrence.getInt("ph", 0);
		if(isemail==0){
			
			phoneStr=GetPhoneNumber();
			//new signupTask().execute();
		}
		
		try{  
			
			Intent ix=getIntent();
			offer=ix.getStringExtra("o");
			
			NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			mNotificationManager.cancel(Global.NotificationCode);
		//	if(		offer!=null&&	offer.length()>0)
			//new ViewDetails().execute();
		}catch(Exception ex){}
		
		
		
		
		mSearchTextWatcher = new SearchTextWatcher();

		ImageLoaderConfiguration imageLoaderConfiguration = ImageOptionsBuilder
				.createImageLoaderConfiguration(this);
		ImageLoader.getInstance().init(imageLoaderConfiguration);

		catalogXmlParser = CatalogXmlParser.getInstance();

		catalogXmlParser.parseData(this);
		//Toast.makeText(getApplicationContext(),catalogXmlParser.getCatalog().toString(),Toast.LENGTH_LONG).show();

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		initActionbar();
	}

	/**
	 * Inits the main components.
	 */
	
	
	void MiniCreate(){

		ThemesManager.setCorrectTheme(this, AppSettings.CURRENT_THEME);
		mSearchTextWatcher = new SearchTextWatcher();
		ImageLoaderConfiguration imageLoaderConfiguration = ImageOptionsBuilder
				.createImageLoaderConfiguration(this);
		ImageLoader.getInstance().init(imageLoaderConfiguration);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		initActionbar();
	}
	GridView view;
	public void initMainComponents() {

		
	      if(AppSettings.isCatogryGrid==1){
	    	
	    	setContentView(R.layout.simplegridview);
	        MiniCreate();
	        initLeftSlidingMenu();
	        showCommonAb();
	        refreshCartCounter();
	        mDrawerToggle.syncState();
	      
			if(GridCatogry==null)
				GridCatogry=new ArrayList<Category>();
			
			mMainAdapterData = catalogXmlParser.getCatalog().getMainCategories();
			
			
			String abouttxt=getResources().getString(R.string.aboutapp);
			String seString=getResources().getString(R.string.action_settings);
			
			Category bmi=new Category();
			bmi.setTitle(abouttxt);
			
			Category bmi2=new Category();
			bmi2.setTitle(seString);
			
			Boolean is=false;
			for (Category ci : mMainAdapterData) {
				if(ci.getTitle().endsWith(seString)){
					is=true;
				}
			}
			if(is==false){
			//mMainAdapterData.add(bmi2);
			}
			
			 is=false;
				for (Category ci : mMainAdapterData) {
					if(ci.getTitle().endsWith(abouttxt)){
						is=true;
					}
				}
				if(is==false){
				//mMainAdapterData.add(bmi);
				}
		
			GridAdapter gridadpater=new GridAdapter(this, mMainAdapterData);
			view =(GridView)findViewById(R.id.gridView1);
			view.setAdapter(gridadpater);
			
			view.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View viewx,
						int position, long id) {
					
					//Category catogry=mMainAdapterData.get(position);
					Category catogry = (Category)parent.getAdapter().getItem(position);
					
					if(GridCatogry==null)GridCatogry=new ArrayList<Category>();
					GridCatogry.add(catogry);
					if(catogry.getSubCategoriesIds().size()>0){
					
						
						initiatecatogryView(catogry);
						
					}else{
						
						
						String abouttxt=getResources().getString(R.string.aboutapp).toLowerCase();
						String sting=getResources().getString(R.string.action_settings).toLowerCase();
						
						if(catogry.getTitle().toString().toLowerCase().equals(abouttxt)){
							
							startActivity(new Intent(MainActivity.this,aboutusActivity.class));
							return;
						}
						if(catogry.getTitle().toString().toLowerCase().equals(sting)){
							
							startActivity(new Intent(MainActivity.this,com.Rodina_Market.app.Settings.class));
							return;
						}
						
						List<Post> sliderProducts = catalogXmlParser.getCatalog()
								.getSlider();
						List<Post> allProducts = catalogXmlParser.getCatalog()
								.getAllProducts();

						List<Post> _sliderProducts=new  ArrayList<Post>();
						List<Post> _allProducts=new  ArrayList<Post>();

						for (Post product : sliderProducts) {
							if(product.getCategory()==catogry.getId()){
								_sliderProducts.add(product);
							}
						}


                                for (Post product : allProducts) {
                                    if(product.getCategory()==catogry.getId()){
                                        _allProducts.add(product);
							}
						}
						
						setContentView(R.layout.activity_main);
						MiniCreate();
						initLeftSlidingMenu();
						setUpAdapter(_sliderProducts, _allProducts);
						 showCommonAb();
						 mDrawerToggle.syncState();
						// showSearchAb();
						//product home
						//Toast.makeText(getApplicationContext(), "PPPP", 1).show();
					}
					
				}
			});
			
			
			
		}else{
		initLeftSlidingMenu();
		setUpAdapter();

		}
	}

	void initiatecatogryView(Category catogry){
		
		 refreshCartCounter();
		ArrayList<Category>categories= new ArrayList<Category>();
		
		if(catogry==null){
			
			categories= catalogXmlParser.getCatalog().getMainCategories();
		}else{
	
		List<Integer>ids=catogry.getSubCategoriesIds();
		for (Integer integer : ids) {
			for (Category ca : catalogXmlParser.getCatalog().getAllCategories()) {
				
				if(ca.getId()==integer){
					categories.add(ca);
				}
			}
			
		}
	}
		GridAdapter gridadpater=new GridAdapter(MainActivity.this, categories);
		view.setAdapter(gridadpater);
	}
	
	/*
	 * 
	 *
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	
	
	 public static String convertImageUriToFile ( Uri imageUri, Activity activity )  {
	      
         Cursor cursor = null;
         int imageID = 0;
          
         try {
          
             /*********** Which columns values want to get *******/
             String [] proj={
                              MediaStore.Images.Media.DATA,
                              MediaStore.Images.Media._ID,
                              MediaStore.Images.Thumbnails._ID,
                              MediaStore.Images.ImageColumns.ORIENTATION
                            };
              
             cursor = activity.managedQuery(
                      
                             imageUri,         //  Get data for specific image URI
                             proj,             //  Which columns to return
                             null,             //  WHERE clause; which rows to return (all rows)
                             null,             //  WHERE clause selection arguments (none)
                             null              //  Order-by clause (ascending by name)
                              
                          );
                                
             //  Get Query Data
              
             int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
             int columnIndexThumb = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
             int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
              
             //int orientation_ColumnIndex = cursor.
             //    getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION);
              
             int size = cursor.getCount();
              
             /*******  If size is 0, there are no images on the SD Card. *****/
              
             if (size == 0) {


                 //imageDetails.setText("No Image");
             }
             else
             {
             
                 int thumbID = 0;
                 if (cursor.moveToFirst()) {
                      
                     /**************** Captured image details ************/
                      
                     /*****  Used to show image on view in LoadImagesFromSDCard class ******/
                     imageID     = cursor.getInt(columnIndex);
                      
                     thumbID     = cursor.getInt(columnIndexThumb);
                      
                     String Path = cursor.getString(file_ColumnIndex);
                      
                     //String orientation =  cursor.getString(orientation_ColumnIndex);
                      
                     String CapturedImageDetails = " CapturedImageDetails : \n\n"
                                                       +" ImageID :"+imageID+"\n"
                                                       +" ThumbID :"+thumbID+"\n"
                                                       +" Path :"+Path+"\n";
                      
                     // Show Captured Image detail on activity
                     //imageDetails.setText( CapturedImageDetails );
                      
                 }
             }    
         } finally {
             if (cursor != null) {
                 cursor.close();
             }
         }
          
         // Return Captured Image ImageID ( By this ImageID Image will load from sdcard )
          
         return ""+imageID;
     }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 69) {
			
			try{
				if ( resultCode == RESULT_OK) {
			
				   CheckoutPreviewAdapter.img_bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(), CheckoutPreviewAdapter.img_bitmap_uri);
				   
				   int lenth=(int) (CheckoutPreviewAdapter.img_bitmap.getHeight()*0.65);
				   int width=(int) (CheckoutPreviewAdapter.img_bitmap.getWidth()*0.65);
				   CheckoutPreviewAdapter.img_bitmap=Bitmap.createScaledBitmap(CheckoutPreviewAdapter.img_bitmap, width, lenth, false);
				   CheckoutPreviewAdapter.img.setImageBitmap(CheckoutPreviewAdapter.img_bitmap);
				}
				
				}catch(Exception ex){}
			
			
			}
		if (requestCode == FragmentCartPreview.MAIL_REQUEST_CODE) {
			setUpAdapter();

			CartManager.getInstance().getAllItems().clear();

			refreshCartCounter();
		}
	}

	/**
	 * Sets the up adapter.
	 */
	public void setUpAdapter() {
		
		//ali farzan tahaa
		//override
		List<Post> sliderProducts = catalogXmlParser.getCatalog()
				.getSlider();
   
		List<Post> allProducts = catalogXmlParser.getCatalog()
				.getAllProducts();

		if(offer!=null&&offer.length()>=0){
			
			sliderProducts=new ArrayList<Post>();
			allProducts=catalogXmlParser.getCatalog()
							.findAllProductsWithTitleContaining(offer);
		}
       	setUpAdapter(sliderProducts, allProducts);
	
	}

	/**
	 * Sets the up adapter.
	 * 
	 * @param sliderProducts
	 *            the slider products
	 * @param allProducts
	 *            the all products
	 */
	private void setUpAdapter(List<Post> sliderProducts,
			List<Post> allProducts) {
		List<List<Post>> mAdapterData = new ArrayList<List<Post>>();

		if (sliderProducts.size() > 0) {
			mAdapterData.add(sliderProducts);
		}

		if (allProducts.size() > 0) {

			while (allProducts.size() > 0) {
				int randInt =1;// Utils.randInt(1, 3);

				List<Post> currentSelectedion = allProducts.subList(0,
						randInt);
				mAdapterData.add(currentSelectedion);

				allProducts = new ArrayList<Post>(allProducts.subList(
						randInt, allProducts.size()));
			}
  
			if (allProducts.size() == 1) {

				List<Post> singleProduct = new ArrayList<Post>();

				singleProduct.add(allProducts.get(0));

				mAdapterData.add(singleProduct);
			} /*else if (allProducts.size() == 2) {
				List<Product> twoProducts = new ArrayList<Product>();

				twoProducts.add(allProducts.get(0));
				twoProducts.add(allProducts.get(1));

				mAdapterData.add(twoProducts);
			} else if (allProducts.size() == 3) {
				List<Product> threeProducts = new ArrayList<Product>();

				threeProducts.add(allProducts.get(0));
				threeProducts.add(allProducts.get(1));
				threeProducts.add(allProducts.get(2));

				mAdapterData.add(threeProducts);
			}*/
		}

		showMainFragment(false, true, mAdapterData, sliderProducts.size() > 0);

	}

	/**
	 * Inits the left sliding menu.
	 */
	public void initLeftSlidingMenu() {
		mSlidingListView = (ListView) findViewById(R.id.sliding_layout_listview);

		TextView metadatTitle = (TextView) findViewById(R.id.sliding_main_title);
		metadatTitle.setText(AppSettings.CATALOG_NAME);

		TextView metadatSubtitle = (TextView) findViewById(R.id.sliding_main_subtitle);
		metadatSubtitle.setText(AppSettings.MAIL);

		TextView metadatPhone = (TextView) findViewById(R.id.left_drawer_metadata_phone);
		String phoneString = getString(R.string.metadata_phone);
		metadatPhone.setText(String.format(phoneString, AppSettings.PHONE));

		TextView metadatSkype = (TextView) findViewById(R.id.left_drawer_metadata_skype);
		String skypeString = getString(R.string.metadata_skype);
		metadatSkype.setText(String.format(skypeString, AppSettings.SKYPE));

		TextView metadatFacebook = (TextView) findViewById(R.id.left_drawer_metadata_facebook);
		String facebookString = getString(R.string.metadata_facebook);
		metadatFacebook.setText(String.format(facebookString,
				AppSettings.FACEBOOK));

		mMainAdapterData = catalogXmlParser.getCatalog().getMainCategories();
      
		String abouttxt=getResources().getString(R.string.aboutapp);
        String settings=getResources().getString(R.string.action_settings);
        
        Category bmi=new Category();
		bmi.setTitle(abouttxt);
		
		Category bmi2=new Category();
		bmi2.setTitle(settings);
		
		Boolean is=false;
		for (Category ci : mMainAdapterData) {
			if(ci.getTitle().endsWith(settings)){
				is=true;
			}
		}
		if(is==false){
		//mMainAdapterData.add(bmi2);
		}
		
		is=false;
		for (Category ci : mMainAdapterData) {
			if(ci.getTitle().endsWith(abouttxt)){
				is=true;
			}
		}
		if(is==false){
		//mMainAdapterData.add(bmi);
		}
		
		mSlidingAdapter = new SlidingAdapter(this, 0, mMainAdapterData);

		mSlidingListView.setAdapter(mSlidingAdapter);

		final LinearLayout metadataLayout = (LinearLayout) findViewById(R.id.left_drawer_metadata_ll);

		final Animation myAnimation1 = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.fade_in);
		final Animation myAnimation2 = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.fade_out);

		View rightArrow = findViewById(R.id.sliding_right_arrow);

		rightArrow.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (metadataLayout.getVisibility() == View.VISIBLE) {
					metadataLayout.startAnimation(myAnimation2);

					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							metadataLayout.setVisibility(View.GONE);

						}
					}, 800);

				} else {
					metadataLayout.setVisibility(View.VISIBLE);
					metadataLayout.startAnimation(myAnimation1);

				}

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	/**
	 * Show main fragment.
	 * 
	 * @param addToBackStack
	 *            the add to back stack
	 * @param clearBackStack
	 *            the clear back stack
	 * @param adapterData
	 *            the adapter data
	 * @param isHaveSlider
	 *            the is have slider
	 */
	public void showMainFragment(boolean addToBackStack,
			boolean clearBackStack, List<List<Post>> adapterData,
			boolean isHaveSlider) {
		FragmentMain fragmentMain = FragmentMain.newInstance(adapterData);
		fragmentMain.setHaveSlider(isHaveSlider);

		showScreen(fragmentMain, FragmentMain.TAG, addToBackStack,
				clearBackStack);
	}

	/**
	 * Show selected product fragment.
	 * 
	 * @param addToBackStack
	 *            the add to back stack
	 * @param clearBackStack
	 *            the clear back stack
	 * @param adapterData
	 *            the adapter data
	 * @param selectedProductId
	 *            the selected product id
	 */
	@SuppressLint("NewApi")
	public void showSelectedProductFragment(boolean addToBackStack,
			boolean clearBackStack, List<Post> adapterData,
			int selectedProductId) {

		FragmentMain fraMain = (FragmentMain) getSupportFragmentManager()
				.findFragmentByTag(FragmentMain.TAG);

		FragmentFilter fraFilter = (FragmentFilter) getSupportFragmentManager()
				.findFragmentByTag(FragmentFilter.TAG);

		FragmentSelectedProduct fragmentSelectedProduct = FragmentSelectedProduct
				.newInstance(adapterData, selectedProductId);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
				&& (fraFilter != null || fraMain != null)) {

			Transition trExplode = TransitionInflater.from(this)
					.inflateTransition(android.R.transition.slide_bottom);

			Transition trExplode1 = TransitionInflater.from(this)
					.inflateTransition(android.R.transition.slide_top);

			if (fraMain != null) {
				fraMain.setSharedElementReturnTransition(trExplode);
				fraMain.setExitTransition(trExplode);
			} else {
				fraFilter.setSharedElementReturnTransition(trExplode);
				fraFilter.setExitTransition(trExplode);
			}

			fragmentSelectedProduct.setSharedElementEnterTransition(trExplode);
			fragmentSelectedProduct.setEnterTransition(trExplode1);

			// Our shared element (in Fragment A)
			View listItemMainLayout = LayoutInflater.from(this).inflate(
					R.layout.list_item_main, null);
			ImageView mProductImage = (ImageView) listItemMainLayout
					.findViewById(R.id.list_item_container_1_image);

			showScreen(fragmentSelectedProduct, FragmentSelectedProduct.TAG,
					addToBackStack, clearBackStack, mProductImage);

		} else {
			showScreen(fragmentSelectedProduct, FragmentSelectedProduct.TAG,
					addToBackStack, clearBackStack);
		}
	}

	/**
	 * Show cart fragment.
	 * 
	 * @param addToBackStack
	 *            the add to back stack
	 * @param clearBackStack
	 *            the clear back stack
	 */
	public void showCartFragment(boolean addToBackStack, boolean clearBackStack) {
		
		if (CartManager.getInstance().getAllItems().size() == 0) {

			Toast.makeText(this, getString(R.string.no_cart_items),
					Toast.LENGTH_LONG).show();

			return;
		}

		FragmentCart fragmentCart = FragmentCart.newInstance();

		showScreen(fragmentCart, FragmentCart.TAG, addToBackStack,
				clearBackStack);
	}

	/**
	 * Show cart fragment preview.
	 * 
	 * @param addToBackStack
	 *            the add to back stack
	 * @param clearBackStack
	 *            the clear back stack
	 */
	public void showCartFragmentPreview(boolean addToBackStack,
			boolean clearBackStack) {

		if (CartManager.getInstance().getAllItems().size() == 0) {

			Toast.makeText(this, getString(R.string.no_cart_items),
					Toast.LENGTH_LONG).show();

			return;
		}

		FragmentCartPreview fragmentCart = FragmentCartPreview.newInstance();

		showScreen(fragmentCart, FragmentCartPreview.TAG, addToBackStack,
				clearBackStack);
	}

	/**
	 * Show filtered fragment.
	 * 
	 * @param filteredProducts
	 *            the filtered products
	 */
	private void showFilteredFragment(List<Post> filteredProducts) {
		FragmentFilter fragmentFilter = FragmentFilter
				.newInstance(filteredProducts);

		// if (getSupportFragmentManager().findFragmentByTag(FragmentFilter.TAG)
		// == null) {
		showScreen(fragmentFilter, FragmentFilter.TAG, false, true);
		// } else {
		// ((FragmentFilter) getSupportFragmentManager().findFragmentByTag(
		// FragmentFilter.TAG)).notifyChange(filteredProducts);
		// }

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentManager.OnBackStackChangedListener#
	 * onBackStackChanged()
	 */
	@Override
	public void onBackStackChanged() {
		mDrawerToggle.setDrawerIndicatorEnabled(getSupportFragmentManager()
				.getBackStackEntryCount() == 0);
		getSupportActionBar().setDisplayHomeAsUpEnabled(
				getSupportFragmentManager().getBackStackEntryCount() > 0);
		mDrawerToggle.syncState();
	}

	/**
	 * Open category in slider.
	 * 
	 * @param category
	 *            the category
	 * @param position
	 *            the position
	 * @param padding
	 *            the padding
	 */
	public void openCategoryInSlider(Category category, int position,
			int padding) {
		for (int i = 0; i < mMainAdapterData.size(); i++) {
			if (mMainAdapterData.get(i).getId() == category.getId()) {

				List<Category> subCategories = catalogXmlParser
						.getSubCategories(category, padding);

				if (subCategories.size() > 0) {
					mMainAdapterData.addAll(++position, subCategories);

					mSlidingAdapter.notifyDataSetChanged();
				}

				break;
			}
		}
	}


	
	void CLOCK(Category catogry){
		if(catogry.getSubCategoriesIds().size()>0){
			
			
			initiatecatogryView(catogry);
			
		}else{
			
			
			String abouttxt=getResources().getString(R.string.aboutapp).toLowerCase();
			String sting=getResources().getString(R.string.action_settings).toLowerCase();
			
			if(catogry.getTitle().toString().toLowerCase().equals(abouttxt)){
				
				startActivity(new Intent(MainActivity.this,aboutusActivity.class));
				return;
			}
			if(catogry.getTitle().toString().toLowerCase().equals(sting)){
				
				startActivity(new Intent(MainActivity.this,com.Rodina_Market.app.Settings.class));
				return;
			}
			
			List<Post> sliderProducts = catalogXmlParser.getCatalog()
					.getSlider();
			List<Post> allProducts = catalogXmlParser.getCatalog()
					.getAllProducts();

			List<Post> _sliderProducts=new  ArrayList<Post>();
			List<Post> _allProducts=new  ArrayList<Post>();
			
			for (Post product : sliderProducts) {
				if(product.getCategory()==catogry.getId()){
					_sliderProducts.add(product);
				}
			}
			
			
			for (Post product : allProducts) {
				if(product.getCategory()==catogry.getId()){
					_allProducts.add(product);
				}
			}
			
			setContentView(R.layout.activity_main);
			MiniCreate();
			initLeftSlidingMenu();

			setUpAdapter(_sliderProducts, _allProducts);
			 showCommonAb();
			 mDrawerToggle.syncState();
			// showSearchAb();
			//product home
			//Toast.makeText(getApplicationContext(), "PPPP", 1).show();
		}
	}
	public void loadProductForSelectedCategory(final Category category,
			int position, final int padding) {

		// Close sliding menu
		mDrawerLayout.closeDrawer(GravityCompat.START);

		//sdsdsdsd
		
		if(AppSettings.isCatogryGrid==1)
		{
			CLOCK(category);
			//initiatecatogryView(category);
		}else{
		//setContentView(R.layout.activity_main);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < mMainAdapterData.size(); i++) {
					if (mMainAdapterData.get(i).getId() == category.getId()) {

						List<Category> subCategories = catalogXmlParser
								.getSubCategories(category, padding);

						// 3 dimension hieararchy
						Integer size = new Integer(subCategories.size());
						for (int j = 0; j < size; j++) {

							Category currentCategory = subCategories.get(j);

							List<Category> currentSubCategories = catalogXmlParser
									.getSubCategories(currentCategory, padding);

							subCategories.addAll(currentSubCategories);

						}

						subCategories.add(category);

						List<Post> allProductsForCategories = catalogXmlParser
								.getAllProductsForCategories(subCategories);

						if (allProductsForCategories.size() > 3) {
							List<Post> productsForSlider = allProductsForCategories
									.subList(0, 3);
							List<Post> productsForAdapter = allProductsForCategories
									.subList(3, allProductsForCategories.size());

							setUpAdapter(productsForSlider, productsForAdapter);

						} else {
							setUpAdapter(allProductsForCategories,
									new ArrayList<Post>());
						}

						showFilteredAb(category.getTitle());

						if (allProductsForCategories.size() == 0) {
							Toast.makeText(MainActivity.this,
									getString(R.string.no_result_finded),
									Toast.LENGTH_LONG).show();
						}

						break;
					}
				}
			}
		}, 300);
		}
	}

	/**
	 * Close category.
	 * 
	 * @param category
	 *            the category
	 * @param position
	 *            the position
	 */
	public void closeCategory(Category category, int position) {

		for (int i = position + 1; i < mMainAdapterData.size(); i++) {
			if (mMainAdapterData.get(i).getTreeIndex() > category
					.getTreeIndex()) {
				mMainAdapterData.set(i, null);
			} else {
				break;
			}
		}

		mMainAdapterData.removeAll(Collections.singleton(null));
		mSlidingAdapter.notifyDataSetChanged();
	}

	/**
	 * Refresh cart counter.
	 */
	public void refreshCartCounter() {
		this.mAbCartCounter.setText(""
				+ CartManager.getInstance().getAllItems().size());
	}

	/**
	 * Sets the ab title.
	 * 
	 * @param title
	 *            the new ab title
	 */
	public void setAbTitle(String title) {
		mAbTitle.setText(title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v7.app.ActionBarActivity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		
		
		
		checkForBackStackCounter();
		int c=getSupportFragmentManager().getBackStackEntryCount();
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		}
		else if(GridCatogry!=null &&c==0){
			
			if(GridCatogry.size()>0)
			GridCatogry.remove(GridCatogry.size()-1);
		
			if(GridCatogry.size()==0){
				initMainComponents();	
				GridCatogry=null;
				
			}else{
				initMainComponents();
				initiatecatogryView(GridCatogry.get(GridCatogry.size()-1));
			}
			return;
		}
		
		else {
			super.onBackPressed();
		}
	}

	/**
	 * Check for back stack counter.
	 */
	public void checkForBackStackCounter() {
		if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
			setAbTitle("");
		}
	}

	/**
	 * Show checkout ab.
	 */
	public void showCheckoutAb() {
		setAbTitle(getString(R.string.shopping_cart));

		mAbSearch.setVisibility(View.GONE);
		mAbCartImageView.setVisibility(View.GONE);
		mAbCartCounter.setVisibility(View.GONE);
		mAbSent.setVisibility(View.GONE);
		mAbCheckout.setVisibility(View.VISIBLE);
		if(AppSettings.UseCart==1){
			//mAbCheckout.setVisibility(View.VISIBLE);
			//mAbCartImageView.setVisibility(View.VISIBLE);
		}else{
			//mAbCheckout.setVisibility(View.GONE);
			mAbCartImageView.setVisibility(View.GONE);
		}
	}

	/**
	 * Show common ab.
	 */
	public void showCommonAb() {
		mAbSearch.setVisibility(View.VISIBLE);
		mAbCartImageView.setVisibility(View.VISIBLE);
		mAbCartCounter.setVisibility(View.VISIBLE);

		mAbCheckout.setVisibility(View.GONE);
		mAbClearFilter.setVisibility(View.GONE);
		mAbSearchEt.setVisibility(View.GONE);
		mAbCancelSearch.setVisibility(View.GONE);
		mAbSent.setVisibility(View.GONE);

		if(AppSettings.UseCart==1){
			//mAbCheckout.setVisibility(View.VISIBLE);
			mAbCartImageView.setVisibility(View.VISIBLE);
		}else{
			mAbCheckout.setVisibility(View.GONE);
			mAbCartImageView.setVisibility(View.GONE);
		}
	}

	/**
	 * Show search ab.
	 */
	public void showSearchAb() {
		mAbSearch.setVisibility(View.GONE);
		mAbCartImageView.setVisibility(View.GONE);
		mAbCartCounter.setVisibility(View.GONE);
		mAbCheckout.setVisibility(View.GONE);
		mAbClearFilter.setVisibility(View.GONE);
		mAbSent.setVisibility(View.GONE);

		mAbSearchEt.setVisibility(View.VISIBLE);
		mAbCancelSearch.setVisibility(View.VISIBLE);
	}

	/**
	 * Show filtered ab.
	 * 
	 * @param title
	 *            the title
	 */
	public void showFilteredAb(String title) {
		mAbSearch.setVisibility(View.GONE);
		mAbCartImageView.setVisibility(View.GONE);
		mAbCartCounter.setVisibility(View.GONE);
		mAbSent.setVisibility(View.GONE);

		mAbCheckout.setVisibility(View.GONE);
		mAbClearFilter.setVisibility(View.VISIBLE);
		mAbTitle.setText(title);
	}

	/**
	 * Show order summary ab.
	 */
	public void showOrderSummaryAb() {
		mAbCheckout.setVisibility(View.GONE);

		setAbTitle(getString(R.string.order_summary));
		mAbSent.setVisibility(View.VISIBLE);
	}

	/**
	 * Gets the ab sent.
	 * 
	 * @return the ab sent
	 */
	public View getAbSent() {
		return mAbSent;
	}

	/**
	 * Gets the ab search et.
	 * 
	 * @return the ab search et
	 */
	public EditText getAbSearchEt() {
		return mAbSearchEt;
	}

	/**
	 * Show screen.
	 * 
	 * @param content
	 *            the content
	 * @param contentTag
	 *            the content tag
	 * @param addToBackStack
	 *            the add to back stack
	 * @param clearBackStack
	 *            the clear back stack
	 */
	private void showScreen(Fragment content, String contentTag,
			boolean addToBackStack, boolean clearBackStack) {
		FragmentManager fm = getSupportFragmentManager();
		fm.addOnBackStackChangedListener(this);
		FragmentTransaction ft = fm.beginTransaction();

		ft.replace(R.id.main_placeholder_content, content, contentTag);

		if (clearBackStack) {
			
			try{
			fm.popBackStackImmediate(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			}catch(Exception ex){}
		}

		if (addToBackStack) {
			ft.addToBackStack(String.valueOf(System.identityHashCode(content)));
		}

		ft.commitAllowingStateLoss();
		fm.executePendingTransactions();
	}

	/**
	 * Show screen.
	 * 
	 * @param content
	 *            the content
	 * @param contentTag
	 *            the content tag
	 * @param addToBackStack
	 *            the add to back stack
	 * @param clearBackStack
	 *            the clear back stack
	 * @param imageView
	 *            the image view
	 */
	private void showScreen(Fragment content, String contentTag,
			boolean addToBackStack, boolean clearBackStack, ImageView imageView) {
		FragmentManager fm = getSupportFragmentManager();
		fm.addOnBackStackChangedListener(this);
		FragmentTransaction ft = fm.beginTransaction();
		ft.addSharedElement(imageView, getString(R.string.transition_cover));

		ft.replace(R.id.main_placeholder_content, content, contentTag);

		if (clearBackStack) {
			fm.popBackStackImmediate(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}

		if (addToBackStack) {
			ft.addToBackStack(String.valueOf(System.identityHashCode(content)));
		}

		ft.commitAllowingStateLoss();
		fm.executePendingTransactions();
	}

	/**
	 * Inits the actionbar.
	 */
	private void initActionbar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		

		setSupportActionBar(mToolbar);

		LayoutInflater inflater = LayoutInflater.from(this);
		View toolbarLayout = (RelativeLayout) inflater.inflate(
				R.layout.ab_custom, null);
		
		mAbCartCounter = (TextView) toolbarLayout
				.findViewById(R.id.ab_custom_card_counter);
		mAbTitle = (TextView) toolbarLayout.findViewById(R.id.ab_custom_title);
		mAbSearch = toolbarLayout.findViewById(R.id.ab_custom_search);
		mAbCheckout = toolbarLayout.findViewById(R.id.ab_custom_checkout);
		
		
		
		mAbClearFilter = toolbarLayout
				.findViewById(R.id.ab_custom_clear_result);
		mAbSearchEt = (EditText) toolbarLayout
				.findViewById(R.id.ab_custom_search_et);
		
		mAbCancelSearch = toolbarLayout
				.findViewById(R.id.ab_custom_cancel_search);
		mAbCartImageView = toolbarLayout.findViewById(R.id.ab_custom_card);
		mAbSent = toolbarLayout.findViewById(R.id.ab_custom_sent);

		Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		mToolbar.addView(toolbarLayout, layoutParams);
		mToolbar.bringToFront();

		
		if(AppSettings.UseCart==1){
			//mAbCheckout.setVisibility(View.VISIBLE);
			mAbCartImageView.setVisibility(View.VISIBLE);
		}else{
			mAbCheckout.setVisibility(View.GONE);
			mAbCartImageView.setVisibility(View.GONE);
		}
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				mToolbar, R.string.drawer_open, R.string.drawer_close) {

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				// TODO Auto-generated method stub
				super.onDrawerSlide(drawerView, slideOffset);
			}

			/** Called when a drawer has settled in a completely closed state. */
			@Override
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(getString(R.string.app_name));
			}

			/** Called when a drawer has settled in a completely open state. */
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(
						getString(R.string.choose_category));
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		mDrawerToggle
				.setToolbarNavigationClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						getSupportFragmentManager().popBackStack();

						checkForBackStackCounter();
					}
				});

		mAbCartImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showCartFragment(true, false);
			}
		});

		mAbClearFilter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mAbTitle.setText("");
				setUpAdapter();
			}
		});

		mAbSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showSearchAb();

				mAbSearchEt.requestFocus();

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(mAbSearchEt, InputMethodManager.SHOW_IMPLICIT);

				mAbSearchEt.addTextChangedListener(mSearchTextWatcher);
			}
		});

		mAbCancelSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showCommonAb();
				setUpAdapter();

				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mAbSearchEt.getWindowToken(), 0);

				mAbSearchEt.removeTextChangedListener(mSearchTextWatcher);

				mAbSearchEt.setText("");
			}
		});

		mAbCheckout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				showCartFragmentPreview(true, false);
			}
		});
	}

	/**
	 * The Class SearchTextWatcher.
	 */
	private class SearchTextWatcher implements TextWatcher {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence,
		 * int, int, int)
		 */
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (s.length() > 2) {

				
			 view.setVisibility(View.GONE);
				List<Post> filteredProduct = catalogXmlParser.getCatalog()
						.findAllProductsWithTitleContaining(s);

				showFilteredFragment(filteredProduct);
			} else if (s.length() == 0) {
				view.setVisibility(view.VISIBLE);
				mAbCancelSearch.performClick();
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence,
		 * int, int, int)
		 */
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
		 */
		@Override
		public void afterTextChanged(Editable s) {

		}
	}

	
	
	public void  Print(String msg){
		
		Toast.makeText(getApplicationContext(), msg, 0).show();
	}


}
