package com.Rodina_Market.app;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.dmbteam.catalogapp.cmn.Branch;
import com.dmbteam.catalogapp.cmn.Category;
import com.dmbteam.catalogapp.cmn.xmlB;
import com.dmbteam.catalogapp.cmn.xmlB;
import com.dmbteam.catalogapp.fragment.PharmacyListFragment;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.dmbteam.catalogapp.util.ThemesManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.dmbteam.catalogapp.xmlparse.CatalogXmlParser.LOG;

public class ParmacyListMainActivity extends ActionBarActivity {

    public xmlB xmlBranches;
    private Persister serializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemesManager.setCorrectTheme(this, AppSettings.CURRENT_THEME);
        setContentView(R.layout.activity_main);

        try {


            serializer = new Persister();
            new BranchesXmlNetworkStremReader(getBaseContext()).execute();


        } catch (Exception e) {
            Log.e(LOG, "Error while parsing xml data" + e.getMessage());
        }

    }

    public void showPharamcyListScreen(List<Branch> list){

        Fragment content=new PharmacyListFragment().newInstance(list);
        FragmentManager fm = getSupportFragmentManager();
        String contentTag="panchood";
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_placeholder_content, content, contentTag);

        ft.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }


    private class BranchesXmlNetworkStremReader extends
            AsyncTask<Void, Void, Boolean>
    {
        /** The context. */
        private Context context;

        /**
         * Instantiates a new catalog xml network strem reader.
         *
         * @param c the c
         */
        public BranchesXmlNetworkStremReader(Context c) {
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

                HttpPost httpPost = new HttpPost("http://ph.togetherpro.com/dev/index.php/api/branches");
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("type", "brancheslist"));
                nameValuePair.add(new BasicNameValuePair("pharmacy_id","13"));

                //Encoding POST data
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }

                try {
                    HttpResponse response = httpClient.execute(httpPost);
                    InputStream reslt = response.getEntity().getContent();
                    //InputStream reslt = this.context.getAssets().open("BranchesList.xml");


                    if (reslt != null) {
                        try {
                            xmlBranches = serializer.read(xmlB.class, reslt);
                            showPharamcyListScreen(xmlBranches.getBranches());

                            Log.d("DEBUG_MURAD",xmlBranches.toString());

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


        }
    }

}
