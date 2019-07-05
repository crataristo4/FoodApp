package com.tech.foodsapp.api;

import com.tech.foodsapp.model.CatList;
import com.tech.foodsapp.model.Categories;
import com.tech.foodsapp.model.Category;
import com.tech.foodsapp.model.Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("latest.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

    @GET("categories.php")
    Call<CatList> getAllCategories();

    @GET("filter.php")
    Call<Meals> getMealByCategory(@Query("c") String categoryName);

    @GET("search.php")
    Call<Meals> getMealByName(@Query("s") String mealName);


}
