package com.dmbteam.catalogapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.Rodina_Market.app.R;
import com.Rodina_Market.app.imageSlider;
import com.dmbteam.catalogapp.cmn.product_image;
import com.dmbteam.catalogapp.util.ImageOptionsBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.Rodina_Market.app.R.id.imageView;
import static com.Rodina_Market.app.R.id.webviewForYoutubeVideo;

public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<product_image> mResources;
    private ImageLoader mImageLoader;
    List<Boolean> clicks;
    Boolean clkk;
    TextView imgCounter;

    public CustomPagerAdapter(Context context, List<product_image> images, Boolean cli, TextView tx) {
        mContext = context;
        mResources = images;
        clkk = cli;
        imgCounter = tx;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mImageLoader = ImageLoader.getInstance();
        clicks = new ArrayList<Boolean>();
        for (product_image product_image : images) {
            clicks.add(false);
        }
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.customimage, container, false);

        final int xPos = position;
        if (imgCounter != null) {

            int g = position;//+1;
            if (g == 0) g = 1;
            //g++;

            imgCounter.setText(String.format(
                    mContext.getString(R.string.selected_page_number), g,
                    getCount()));
        }

        final WebView  webViewForYouTube = (WebView) itemView.findViewById(R.id.webviewForYoutubeVideo);
        final ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        String uril = mResources.get(position).getImage_url();
        if (uril.toLowerCase().contains("youtube")) {
            imageView.setVisibility(View.GONE);
            webViewForYouTube.getSettings().setJavaScriptEnabled(true);

            uril = uril.replace("https://www.youtube.com/watch?v=","");


            webViewForYouTube.loadData("<iframe width=\"854\" height=\"480\" src=\"" +
                    "" +
                    "https://www.youtube.com/embed/" +
                    uril +
                    "\" frameborder=\"0\" allowfullscreen></iframe>", "text/html", "UTF-8");

            webViewForYouTube.setVisibility(View.VISIBLE);
            container.addView(itemView);


        } else {
            webViewForYouTube.setVisibility(View.GONE);
            mImageLoader.displayImage(uril,
                    imageView, ImageOptionsBuilder
                            .buildGeneralImageOptions(false, R.drawable.home_nexus9));


            imageView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (clkk) {
                        imageSlider.images = mResources;
                        imageSlider.startItems = xPos;
                        Intent ix = new Intent(mContext, imageSlider.class);
                        mContext.startActivity(ix);
                    }
                    //final Dialog nagDialog = new Dialog(mContext);
                    //nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
               /* nagDialog.setCancelable(true);
	            nagDialog.setContentView(R.layout.ccustomimage);
	            	            ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.imageView);

	            	            mImageLoader.displayImage(mResources.get(position).getImage_url(),
	            	            		ivPreview, ImageOptionsBuilder
	            	    				.buildGeneralImageOptions(false, R.drawable.home_nexus9));
	           // ivPreview.setBackgroundDrawable(imageView.getBackground());
	            nagDialog.show();*/

                }
            });
            // imageView.setImageResource(mResources[position]);

            container.addView(itemView);

        }


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}