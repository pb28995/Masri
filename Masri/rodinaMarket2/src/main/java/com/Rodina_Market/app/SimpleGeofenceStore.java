/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.Rodina_Market.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Storage for geofence values, implemented in SharedPreferences.
 */
public class SimpleGeofenceStore {

	
	public String ProductID="";
	public String  ProductName="";
	public Boolean status;
	
	public String  latitude="";
	public String  longitude="";
	public String  radios="";
	Context mContext;
	
    private final SharedPreferences mPrefs;
    private static final String SHARED_PREFERENCES = "SharedPreferences";

    
   
    public SimpleGeofenceStore(Context context) {
        mPrefs = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        mContext=context;
    }

  
    public SimpleGeofenceStore getGeofence(String id) {
       
    	SimpleGeofenceStore retval=new SimpleGeofenceStore(mContext);
    	
    	retval.latitude=mPrefs.getFloat(getGeofenceFieldKey(id, "KEY_LATITUDE"),0)+"";
    	retval.longitude=mPrefs.getFloat(getGeofenceFieldKey(id, "KEY_LONGITUDE"), 0)+"";
    	retval.radios=mPrefs.getFloat(getGeofenceFieldKey(id, "KEY_RADIUS"), 0)+"";
    	retval.status=mPrefs.getBoolean(getGeofenceFieldKey(id, "KEY_STATUS"), false);
    	retval.ProductName=mPrefs.getString(getGeofenceFieldKey(id, "ProductName"), "");
    	retval.ProductID=mPrefs.getString(getGeofenceFieldKey(id, "productID"),"");
    	return retval;
    }

    /**
     * Save a geofence.
     * @param geofence The SimpleGeofence with the values you want to save in SharedPreferences.
     */
    public void setGeofence(String id, String ProductName,Boolean status,String longi,String lati,String rads) {
        
    	SharedPreferences.Editor prefs = mPrefs.edit();
       
        prefs.putFloat(getGeofenceFieldKey(id, "KEY_LATITUDE"), Float.parseFloat(lati));
        prefs.putFloat(getGeofenceFieldKey(id, "KEY_LONGITUDE"), Float.parseFloat(longi));
        prefs.putFloat(getGeofenceFieldKey(id, "KEY_RADIUS"), Float.parseFloat(rads));
        prefs.putBoolean(getGeofenceFieldKey(id, "KEY_STATUS"), status);
        prefs.putString(getGeofenceFieldKey(id, "ProductName"), ProductName);
        prefs.putString(getGeofenceFieldKey(id, "productID"), id);
       
        prefs.commit();
    }

    /**
     * Remove a flattened geofence object from storage by removing all of its keys.
     */
    public void clearGeofence(String id) {
       
    	SharedPreferences.Editor prefs = mPrefs.edit();
    	prefs.putBoolean(getGeofenceFieldKey(id, "KEY_STATUS"), true);
        prefs.commit();
    }

    
 public void deleteall(){
    	
	 SharedPreferences.Editor prefs = mPrefs.edit();
 	prefs.clear();
     prefs.commit();
    }
    /**
     * Given a Geofence object's ID and the name of a field (for example, KEY_LATITUDE), return
     * the key name of the object's values in SharedPreferences.
     * @param id The ID of a Geofence object.
     * @param fieldName The field represented by the key.
     * @return The full key name of a value in SharedPreferences.
     */
    private String getGeofenceFieldKey(String id, String fieldName) {
        return "mx" + "_" + id + "_" + fieldName;
    }

}
