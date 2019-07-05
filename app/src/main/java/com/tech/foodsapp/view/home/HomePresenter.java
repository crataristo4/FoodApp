/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.tech.foodsapp.view.home;

import android.util.Log;

import androidx.annotation.NonNull;

import com.tech.foodsapp.Utils;
import com.tech.foodsapp.model.Categories;
import com.tech.foodsapp.model.Category;
import com.tech.foodsapp.model.Meals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter {

    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    void getMeals() {

        view.showLoading();

        Call<Meals> mealsCall = Utils.getApi().getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.setMeal(response.body().getMeals());

                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }


    void getCategories() {

        view.showLoading();

        Call<Categories> categoriesCall = Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call,
                                   @NonNull Response<Categories> response) {

                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {

                    view.setCategory(response.body().getCategories());


                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    /*void getAllCategories() {

        view.showLoading();

        Call<List<Category>> categoriesCall = Utils.getApi().getAllCategories();
        categoriesCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call,@NonNull Response<List<Category>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {

                    List<Category> cat = response.body();
                    view.setCat(cat);
                    Log.i("Response ::?? ", cat.toString());

                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });





    }*/
}
