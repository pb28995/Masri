package com.dmbteam.catalogapp.cmn;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Created by Murad on 14/10/2017.
 */
@Root(strict=false)
public class Post {


    private double discountedPrice;
    @ElementList(required = false)
    private List<product_image>product_images;

    public int getPost_id() {
        return post_id;
    }

    public String getViewCount(){

        return "Views : "+impressions;
    }

    public List<Spec> getProduct_specs() {
        if(specs==null)return new ArrayList<Spec>();
        return getSpecs();
    }

    public String Getcrum(){


        return "GetScrum";// city_name+" ---- > "+district_name;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getPost_type() {
        return post_type;
    }

    public void setPost_type(int post_type) {
        this.post_type = post_type;
    }

    public int getPost_code() {
        return post_code;
    }

    public String getPhoto(Context context) {

        return getProduct_images().get(0).getImage_url();
    }
    public static boolean isNetworkResource(String resource) {

        return resource.startsWith("http");
    }

    public List<product_image> getProduct_images() {
        if(product_images==null)return new ArrayList<product_image>();
        return product_images;
    }

    public boolean isNetworkResource() {

        String url=getProduct_images().get(0).getImage_url().trim();
        return url.startsWith("http");
    }
    public  int getDrawableId(Context context) {
        return context.getResources().getIdentifier(getPhoto(context), "drawable",
                context.getPackageName());
    }

    public double getDiscountedPrice() {
        discountedPrice = getPrice() * (1 - price_discount_percentage / 100);

        return discountedPrice;
    }

    public String getCurrencySign(){
        return " "+price_currency_symbol;
    }


    public double getDiscount() {
        return price_discount_percentage;
    }

    public double getPrice() {

        return price_before_discount;
    }

    public static  int getDrawableId(Context context,String str) {
        return context.getResources().getIdentifier(str, "drawable",
                context.getPackageName());
    }

    public void setPost_code(int post_code) {
        this.post_code = post_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSub_category() {
        return sub_category;
    }

    public void setSub_category(int sub_category) {
        this.sub_category = sub_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription_ar() {
        return description_ar;
    }

    public void setDescription_ar(String description_ar) {
        this.description_ar = description_ar;
    }

    public String getSales_phone() {
        return sales_phone;
    }

    public void setSales_phone(String sales_phone) {
        this.sales_phone = sales_phone;
    }

    public int getIvr_enabled() {
        return ivr_enabled;
    }

    public void setIvr_enabled(int ivr_enabled) {
        this.ivr_enabled = ivr_enabled;
    }
    public double GetTax(){
        return tax_percentage/100;
    }
    public int getShow_offline() {
        return show_offline;
    }

    public void setShow_offline(int show_offline) {
        this.show_offline = show_offline;
    }

    public Double getPrice_before_discount() {
        return price_before_discount;
    }

    public void setPrice_before_discount(Double price_before_discount) {
        this.price_before_discount = price_before_discount;
    }

    public Double getPrice_discount_percentage() {
        return price_discount_percentage;
    }

    public void setPrice_discount_percentage(Double price_discount_percentage) {
        this.price_discount_percentage = price_discount_percentage;
    }

    public Double getTax_percentage() {
        return tax_percentage;
    }

    public void setTax_percentage(Double tax_percentage) {
        this.tax_percentage = tax_percentage;
    }

    public int getDelivery_cost() {
        return delivery_cost;
    }

    public void setDelivery_cost(int delivery_cost) {
        this.delivery_cost = delivery_cost;
    }

    public String getPrice_currency_symbol() {
        return price_currency_symbol;
    }

    public void setPrice_currency_symbol(String price_currency_symbol) {
        this.price_currency_symbol = price_currency_symbol;
    }


    public int getSlider(){

        return 0;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getZero_stock() {
        return zero_stock;
    }

    public void setZero_stock(int zero_stock) {
        this.zero_stock = zero_stock;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }

    public int getButton_order_now() {
        return button_order_now;
    }

    public void setButton_order_now(int button_order_now) {
        this.button_order_now = button_order_now;
    }

    public int getButton_reserve() {
        return button_reserve;
    }

    public void setButton_reserve(int button_reserve) {
        this.button_reserve = button_reserve;
    }

    public int getButton_contact_us() {
        return button_contact_us;
    }

    public void setButton_contact_us(int button_contact_us) {
        this.button_contact_us = button_contact_us;
    }

    public int getButton_call() {
        return button_call;
    }

    public void setButton_call(int button_call) {
        this.button_call = button_call;
    }

    public int getButton_share() {
        return button_share;
    }

    public void setButton_share(int button_share) {
        this.button_share = button_share;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getParent_category_name() {
        return parent_category_name;
    }

    public void setParent_category_name(String parent_category_name) {
        this.parent_category_name = parent_category_name;
    }

    public String getParent_category_name_ar() {
        return parent_category_name_ar;
    }

    public void setParent_category_name_ar(String parent_category_name_ar) {
        this.parent_category_name_ar = parent_category_name_ar;
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

    @Element
    private int post_id;
    @Element
    private int post_type;
    @Element
    private int post_code;
    @Element
    private String name;
    @Element
    private String name_ar;
    @Element
    private int category;
    @Element
    private int sub_category;
    @Element
    private String description;
    @Element
    private String description_ar;
    @Element
    private String sales_phone;
    @Element
    private int ivr_enabled;

    @Element
    private int show_offline;
    @Element
    private Double price_before_discount;
    @Element
    private Double price_discount_percentage;
    @Element
    private Double tax_percentage;
    @Element
    private int delivery_cost;
    @Element
    private String price_currency_symbol;
    @Element
    private int quantity;


    @Element
    private int zero_stock;
    @Element
    private String youtube_url;
    @Element
    private int button_order_now;
    @Element
    private int button_reserve;
    @Element
    private int button_contact_us;
    @Element
    private int button_call;
    @Element
    private int button_share;

    @Element
    private int order;
    @Element
    private int status;
    @Element
    private int impressions;
    @Element
    private String creation_date;
    @Element
    private String parent_category_name;
    @Element
    private String parent_category_name_ar;

    @ElementList
    private List<Spec> specs;




}
