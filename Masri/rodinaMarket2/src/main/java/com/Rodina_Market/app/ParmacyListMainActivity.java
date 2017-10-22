package com.Rodina_Market.app;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.dmbteam.catalogapp.cmn.Branch;
import com.dmbteam.catalogapp.cmn.xmlB;
import com.dmbteam.catalogapp.cmn.xmlB;
import com.dmbteam.catalogapp.fragment.PharmacyListFragment;
import com.dmbteam.catalogapp.settings.AppSettings;
import com.dmbteam.catalogapp.util.ThemesManager;

import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
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


        } catch (Exception e) {
            Log.e(LOG, "Error while parsing xml data" + e.getMessage());
        }




        InputStream reslt = null;
        try {
            reslt = this.getAssets().open("BranchesList.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (reslt != null) {
            try {
                xmlBranches = serializer.read(xmlB.class, reslt);
                showPharamcyListScreen(xmlBranches.getBranches());

            }catch (Exception e){
                Log.d("Murad",e.toString());
            }
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


}
