package com.dmbteam.catalogapp.adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Rodina_Market.app.MainActivity;
import com.Rodina_Market.app.R;
import com.dmbteam.catalogapp.cart.CartItem;
import com.dmbteam.catalogapp.cart.CartManager;
import com.dmbteam.catalogapp.cmn.Post;
import com.dmbteam.catalogapp.fragment.FragmentCartPreview;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.dmbteam.catalogapp.util.Utils;
import com.parse.entity.mime.HttpMultipartMode;
import com.parse.entity.mime.MultipartEntity;
import com.parse.entity.mime.content.FileBody;
import com.parse.entity.mime.content.StringBody;

/**
 * The Class CheckoutPreviewAdapter.
 */
public class CheckoutPreviewAdapter extends
		RecyclerView.Adapter<CheckoutPreviewAdapter.ViewHolder> {

	public static ImageView img;
	public static Bitmap img_bitmap;
	public static Uri img_bitmap_uri;
	/**
	 * The Class ViewHolder.
	 */
	
	class PostMessage extends AsyncTask{

		
		public String email="";
		public String phone="";
		public String notes="";
		public String address="";
		public String sendername="";
		public String ordertotal="";
		public String orderSummry="";
		public String orderfile="";
		public File image=null;
		HttpResponse response;
		ProgressDialog pd;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd=new ProgressDialog(mContext);
			pd.setTitle("please wait");
			pd.setCancelable(false);
			pd.setTitle("sending message");
			pd.show();
		}
		
		@Override
		protected Object doInBackground(Object... params) {
			
			try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(AppSettings.apiEndpoint);
			
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,null,Charset.forName("UTF-8"));
			//MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,null,"");
			entity.addPart("type",new StringBody("postmessage",Charset.forName("UTF-8")));
			entity.addPart("user_id",new StringBody(AppSettings.cid));
			entity.addPart("email",new StringBody(email,Charset.forName("UTF-8")));
			entity.addPart("phone",new StringBody(phone,Charset.forName("UTF-8")));
			entity.addPart("notes",new StringBody(notes,Charset.forName("UTF-8")));
			entity.addPart("address",new StringBody(address,Charset.forName("UTF-8")));
			entity.addPart("sender_name",new StringBody(sendername,Charset.forName("UTF-8")));
			entity.addPart("order_total",new StringBody(ordertotal.replaceAll(",", ""),Charset.forName("UTF-8")));
			
			entity.addPart("order_summary",new StringBody(orderSummry,Charset.forName("UTF-8")));
			
			//entity.addPart("order_summary",new StringBody(orderSummry,Charset.forName("UTF-8")));
			if(image!=null)
			entity.addPart("image",new FileBody(image,"image/jpeg"));
			
			
			httpPost.setEntity(entity);
		    response = httpClient.execute(httpPost);
			int x=00000;
			String code=response.getStatusLine().getReasonPhrase();
	    	String codexx=response.getStatusLine().getStatusCode()+"";
			
			
			x=1;
			}catch(Exception ex){
				
				int c=0;
			}
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			pd.dismiss();
			if(response!=null&&response.getStatusLine().getStatusCode()==200){
				Toast.makeText(mContext,mContext.getResources().getString(R.string.ordersent), 0).show();
				
				CartManager.getInstance().getAllItems().clear();
				((MainActivity)mContext).refreshCartCounter();
				((MainActivity)mContext).setUpAdapter();
				//howMainFragment(false, true, ((MainActivity)mContext).mAdapterData, ((MainActivity)mContext).sliderProducts.size() > 0);
				
			}else{
				
				Toast.makeText(mContext, mContext.getResources().getString(R.string.ordernotsent), 0).show();
			//	Toast.makeText(mContext, "order not sent\nnetwork issue", 0).show();
			}
			
			img_bitmap_uri=null;
			img_bitmap_uri=null;
		}
	}
	public static class ViewHolder extends RecyclerView.ViewHolder {

		/** The product container. */
		private View productContainer;
		
		/** The title. */
		private TextView title;
		
		/** The qty. */
		private TextView qty;
		
		/** The price. */
		private TextView price;

		/** The cart preview container. */
		private View cartPreviewContainer;
		
		/** The cart preview delimiter. */
		private View cartPreviewDelimiter;
		
		/** The cart preview total. */
		private TextView cartPreviewTotal;
		
		/** The cart preview name. */
		private EditText cartPreviewName;
		
		/** The cart preview mail. */
		private EditText cartPreviewMail;
		
		/** The cart preview phone. */
		private EditText cartPreviewPhone;
		
		/** The cart preview comment. */
		private EditText cartPreviewComment;
		
		private EditText cartPreviewAdress;
		
		/** The cart preview sent. */
		private TextView cartPreviewSent;

		/**
		 * Instantiates a new view holder.
		 *
		 * @param itemView the item view
		 */
		public ViewHolder(View itemView) {
			super(itemView);

			productContainer = itemView
					.findViewById(R.id.list_item_filter_product_container);
			title = (TextView) itemView
					.findViewById(R.id.list_item_filter_title);
			qty = (TextView) itemView.findViewById(R.id.list_item_filter_qty);
			price = (TextView) itemView
					.findViewById(R.id.list_item_filter_price);

			cartPreviewContainer = itemView
					.findViewById(R.id.list_item_filter_cart_preview);
			cartPreviewDelimiter = itemView
					.findViewById(R.id.list_item_filter_cart_delimiter);
			cartPreviewTotal = (TextView) itemView
					.findViewById(R.id.list_item_filter_total_value);
			cartPreviewName = (EditText) itemView
					.findViewById(R.id.list_item_filter_full_name);
			cartPreviewMail = (EditText) itemView
					.findViewById(R.id.list_item_filter_mail);
			cartPreviewPhone = (EditText) itemView
					.findViewById(R.id.list_item_filter_phone);
			cartPreviewComment = (EditText) itemView
					.findViewById(R.id.list_item_filter_comment);
			
			cartPreviewAdress = (EditText) itemView
					.findViewById(R.id.list_item_filter_adress);
			cartPreviewSent = (TextView) itemView
					.findViewById(R.id.list_item_filter_sent);
		}
	}

	/** The Context. */
	private Context mContext;
	
	/** The Inflater. */
	private LayoutInflater mInflater;
	
	/** The Adapter data. */
	private List<CartItem> mAdapterData;
	
	/** The is in cart context. */
	private boolean isInCartContext;

	/**
	 * Instantiates a new checkout preview adapter.
	 *
	 * @param context the context
	 * @param isInCartContext the is in cart context
	 */
	public CheckoutPreviewAdapter(Context context, boolean isInCartContext) {
		this(context);
		this.isInCartContext = isInCartContext;
		// this.mAdapterData.add(new CartItem(null));
	}

	/**
	 * Instantiates a new checkout preview adapter.
	 *
	 * @param context the context
	 */
	public CheckoutPreviewAdapter(Context context) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(this.mContext);
		this.mAdapterData = CartManager.getInstance().getAllItems();
	}

	/* (non-Javadoc)
	 * @see android.support.v7.widget.RecyclerView.Adapter#getItemCount()
	 */
	@Override
	public int getItemCount() {

		return mAdapterData.size();
	}

	/* (non-Javadoc)
	 * @see android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(android.view.ViewGroup, int)
	 */
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View convertView = mInflater.inflate(
				R.layout.list_item_checkout_preview, parent, false);
		ViewHolder holder = new ViewHolder(convertView);

		 img=(ImageView)convertView.findViewById(R.id.imageView1);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 String fileName = "Camera_Example.jpg";
				 ContentValues values = new ContentValues();
                 values.put(MediaStore.Images.Media.TITLE, fileName);
	             values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
	             img_bitmap_uri = mContext.getContentResolver().insert(
	                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
	             
	             
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, img_bitmap_uri);
				cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				((MainActivity) mContext).startActivityForResult(cameraIntent, 69);
				
			}
		});
		
		return holder;
	}

	/* (non-Javadoc)
	 * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)
	 */
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		CartItem currentCartItem = mAdapterData.get(position);
		Post currentProduct = mAdapterData.get(position).getProduct();

		holder.cartPreviewContainer.setVisibility(View.GONE);

		holder.title.setText(currentCartItem.getProduct().getName());
		holder.qty.setText(currentCartItem.getQuantity() + "x");

		double price = 0.0;

		if (currentProduct.getDiscount() > 0.0) {
			price = (currentProduct
					.getDiscountedPrice());
		} else {
			price = (currentProduct.getPrice());
		}

		holder.price.setText(Utils.mFormatter.format(price)
				+ currentProduct.getCurrencySign());

		if (isInCartContext && position == getItemCount() - 1) {
			holder.cartPreviewTotal.setText(CartManager.getInstance()
					.getTotal() + currentProduct.getCurrencySign());

			holder.cartPreviewContainer.setVisibility(View.VISIBLE);

			int abHeight = ((MainActivity) mContext).getSupportActionBar()
					.getHeight();

			int displayHeight = mContext.getResources().getDisplayMetrics().heightPixels
					- abHeight;

			int cartContainerHeight = Utils.dipsToPixels(mContext, 470);
			int itemsHeight = Utils.dipsToPixels(mContext, 31) * getItemCount();

			int marginDelimiter = displayHeight
					- (cartContainerHeight + itemsHeight);

			if (marginDelimiter > 0) {
				holder.cartPreviewDelimiter
						.setLayoutParams(new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,
								marginDelimiter));
			}

			holder.cartPreviewSent.setOnClickListener(new SentClickListener(
					holder));

			((MainActivity) mContext).getAbSent().setOnClickListener(
					new SentClickListener(holder));
		}

		
	
	}

	/**
	 * The listener interface for receiving sentClick events.
	 * The class that is interested in processing a sentClick
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSentClickListener<code> method. When
	 * the sentClick event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SentClickEvent
	 */
	
	
	private class SentClickListener implements OnClickListener {

		/** The holder. */
		ViewHolder holder;

		/**
		 * Instantiates a new sent click listener.
		 *
		 * @param holder the holder
		 */
		public SentClickListener(ViewHolder holder) {
			super();
			this.holder = holder;
		}

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		
		private File persistImage(Bitmap bitmap, String name) {
			  File filesDir = mContext.getFilesDir();
			  File imageFile = new File(filesDir, name + ".jpg");
			  
			  	String ret=imageFile.getAbsolutePath();
			  OutputStream os;
			  try {
			    os = new FileOutputStream(imageFile);
			    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
			    
			    os.flush();
			    os.close();
			  } catch (Exception e) {
			    //Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
			  }
			  return imageFile;
			}
		
		@Override
		public void onClick(View v) {

			boolean result = false;

			if (holder.cartPreviewName.getText().toString().trim().isEmpty()) {
				result = true;
				holder.cartPreviewName
						.setBackgroundResource(R.drawable.cart_preview_et_bg_orange);
				holder.cartPreviewName.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_fail, 0);
			} else {
				holder.cartPreviewName
						.setBackgroundResource(R.drawable.cart_preview_et_bg_green);
				holder.cartPreviewName.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_ok, 0);
			}

			/*if (holder.cartPreviewMail.getText().toString().trim().isEmpty()) {
				result = true;
				holder.cartPreviewMail
						.setBackgroundResource(R.drawable.cart_preview_et_bg_orange);
				holder.cartPreviewMail.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_fail, 0);
			} else {
				holder.cartPreviewMail
						.setBackgroundResource(R.drawable.cart_preview_et_bg_green);
				holder.cartPreviewMail.setCompoundDrawablesWithIntrinsicBounds(
						0, 0, R.drawable.ic_checkout_input_ok, 0);
			}*/

			if (holder.cartPreviewPhone.getText().toString().trim().isEmpty()) {
				result = true;
				holder.cartPreviewPhone
						.setBackgroundResource(R.drawable.cart_preview_et_bg_orange);
				holder.cartPreviewPhone
						.setCompoundDrawablesWithIntrinsicBounds(0, 0,
								R.drawable.ic_checkout_input_fail, 0);
			} else {
				holder.cartPreviewPhone
						.setBackgroundResource(R.drawable.cart_preview_et_bg_green);
				holder.cartPreviewPhone
						.setCompoundDrawablesWithIntrinsicBounds(0, 0,
								R.drawable.ic_checkout_input_ok, 0);

			}

			
			
			// post order xxx
			if (result) {
				Toast.makeText(mContext,
						mContext.getString(R.string.mandatory_fields),
						Toast.LENGTH_LONG).show();

			} else {
				
				PostMessage pm=new PostMessage();
				pm.email=holder.cartPreviewMail.getText().toString();
				pm.phone=holder.cartPreviewPhone.getText().toString();
				pm.notes=holder.cartPreviewComment.getText().toString();
				pm.sendername=holder.cartPreviewName.getText().toString();
				pm.ordertotal=CartManager.getInstance().getTotal();
				pm.address=holder.cartPreviewAdress.getText().toString();
				pm.orderSummry=CartManager.getInstance().getSummry();
				
				
				try{
				if(img!=null&&img_bitmap!=null){ 
				Bitmap bmp=img_bitmap;//((BitmapDrawable)img.getDrawable()).getBitmap();
				pm.image=persistImage(bmp,"tmp_x");
				
				}
				}catch(Exception ex){}
				pm.execute();

			}

		}

	}

}
