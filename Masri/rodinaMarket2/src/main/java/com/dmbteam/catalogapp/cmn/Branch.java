package com.dmbteam.catalogapp.cmn;

import java.util.ArrayList;
        import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
        import org.simpleframework.xml.Root;

/**
 * Created by Murad on 14/10/2017.
 */
@Root(name = "branch",strict=false)

public class Branch {

    public  Branch(){

    }
    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_name_ar() {
        return branch_name_ar;
    }

    public void setBranch_name_ar(String branch_name_ar) {
        this.branch_name_ar = branch_name_ar;
    }

    public String getBranch_status() {
        return branch_status;
    }

    public void setBranch_status(String branch_status) {
        this.branch_status = branch_status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }



       @Element (required = false)
        private int branch_id;

        @Element (required = false)
        private String branch_name;
        @Element (required = false)
        private String branch_name_ar;
        @Element (required = false)
        private String branch_status;
        @Element (required = false)
        private Double latitude;
        @Element (required = false)
        private Double longitude;
        @Element (required = false)
        private String user_id;
        @Element (required = false)
        private  String parent;
        @Element (required = false)
        private  String date_created;

    public String getBranch_icon() {
        return branch_icon;
    }

    public void setBranch_icon(String branch_icon) {
        this.branch_icon = branch_icon;
    }

    @Element (required = false)
        private  String branch_icon;

        @ElementList (required = false)
        private List<Category> categories;
        @ElementList (required = false)
        private List<Category> sub_categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(List<Category> sub_categories) {
        this.sub_categories = sub_categories;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @ElementList(required = false)
        private List<Post> posts;



}
