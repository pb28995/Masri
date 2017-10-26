package com.Rodina_Market.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.dmbteam.catalogapp.fragment.PharmacyListFragment.OnListFragmentInteractionListener;
import com.dmbteam.catalogapp.cmn.Branch;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import static com.Rodina_Market.app.R.id.imageView;
public class MyPharmacyListRecyclerViewAdapter extends  RecyclerView.Adapter<MyPharmacyListRecyclerViewAdapter.ViewHolder> {

    private final List<Branch> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;
    public MyPharmacyListRecyclerViewAdapter(List<Branch> items,Context c, OnListFragmentInteractionListener listener) {
        mValues = items;
        context = c;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pharmacylist2, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getBranch_name());

        Picasso.with(context).load(mValues.get(position).getBranch_icon())
                .placeholder(R.drawable.bosslogo)
                .into(holder.branchImage);




        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,String.valueOf(mValues.get(position).getBranch_name()),3).show();
                Intent i = new Intent(context, MainActivity.class);
                AppSettings.branchID = mValues.get(position).getBranch_id();
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView branchImage;
      //  public final TextView mContentView;
        public Branch mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.textView);
            branchImage = (ImageView) view.findViewById(R.id.imageView2);

         //   mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }


}
