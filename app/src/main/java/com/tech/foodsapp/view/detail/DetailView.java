package com.tech.foodsapp.view.detail;

import com.tech.foodsapp.model.Meals;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meals);
    void onErrorLoading(String message);

}
