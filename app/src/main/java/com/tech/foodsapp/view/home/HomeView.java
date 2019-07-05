package com.tech.foodsapp.view.home;

import com.tech.foodsapp.model.Categories;
import com.tech.foodsapp.model.Category;
import com.tech.foodsapp.model.Meals;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
