package com.dmbteam.catalogapp.settings;

import static com.dmbteam.catalogapp.util.ThemesManager.APP_THEMES.*;

import com.Rodina_Market.app.Global;
import com.dmbteam.catalogapp.util.ThemesManager.APP_THEMES;

/**
 * The Class AppSettings.
 */
public class AppSettings {

    /**
     * Theme of the app
     * <p>
     * Replace the value with any of these:
     * <p>
     * ThemeGreen, ThemeBlue, ThemeOrange, ThemePurple, ThemeDarkBlue, ThemeNeutral, ThemePink
     */

    public static  int branchID = 0;
    public static final APP_THEMES CURRENT_THEME = ThemeGreen;
    public static final boolean useCommaForPrices = false;
    public static final String applicationId = "396WguSXHPPKCP2jBsUKw7b8qkPAyxhOsjDcJ2u6";
    public static final String clientKey = "6Jp7iYWDl4VypF0sCyXaOPa9lBKk48PB9PsbeUoW";
    public static int UseCart = 2;  //1 if need cart no then use 0
    public static String CATALOG_NAME = "Unknown";//app name here
    //public static int delivertyCost=1;
    public static int isCatogryGrid = 0;
    public static String MAIL = "Unknown";
    public static String PHONE = "Unknown";
    public static String SKYPE = "Unknown";
    public static String FACEBOOK = "Unknown";


    //Change following things only

    public static String apiEndpoint = "http://ph.togetherpro.com/dev/index.php/api/main";//replace api link here
    public static String cid = "24";  //client id
    public static int Geo_Fencing = 0;  //enable  geo fencing =1 else 0
    //Following are numbers again IVR
    public static String[] IVRNUM = {

            "01027017660", // sale team
            "01000457547", //customer care
            "01000457547", //Technical Suport
            "01222233151", //Billing
            "01277223326"     //Operator
    };

}
