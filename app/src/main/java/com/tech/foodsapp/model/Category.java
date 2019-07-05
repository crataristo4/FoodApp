package com.tech.foodsapp.model;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class Category extends BaseObservable implements Serializable {

    @SerializedName("idCategory")
    @Expose
    private String idCategory;

    @SerializedName("strCategory")
    @Expose
    private String strCategory;

    @SerializedName("strCategoryThumb")
    @Expose
    private String strCategoryThumb;
    @SerializedName("strCategoryDescription")
    @Expose
    private String strCategoryDescription;


    public Category() {
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    @Bindable
    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
        notifyPropertyChanged(BR.category);
    }

    @Bindable
    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
        notifyPropertyChanged(BR.strCategoryThumb);
    }

    @Bindable
    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
        notifyPropertyChanged(BR.strCategoryDescription);
    }

    /*@BindingAdapter("categoryUrl")
    public static void loadImages(ImageView imageView, String thumbnailUrl) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(thumbnailUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }*/

}
