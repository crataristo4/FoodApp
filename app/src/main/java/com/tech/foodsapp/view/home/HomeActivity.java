package com.tech.foodsapp.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.tech.foodsapp.R;
import com.tech.foodsapp.Utils;
import com.tech.foodsapp.adapter.RecyclerViewHomeAdapter;
import com.tech.foodsapp.adapter.ViewPagerHeaderAdapter;
import com.tech.foodsapp.databinding.ActivityHomeBinding;
import com.tech.foodsapp.model.Categories;
import com.tech.foodsapp.model.Meals;
import com.tech.foodsapp.view.category.CategoryActivity;
import com.tech.foodsapp.view.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "details";
    ActivityHomeBinding activityHomeBinding;

    ViewPager viewPagerMeal;
    RecyclerView recyclerViewCategory;

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewPagerMeal = activityHomeBinding.viewPagerHeader;
        recyclerViewCategory = activityHomeBinding.recyclerCategories;

        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    @Override
    public void showLoading() {
        activityHomeBinding.shimmerMeal.setVisibility(View.VISIBLE);
        activityHomeBinding.shimmerCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        activityHomeBinding.shimmerMeal.setVisibility(View.GONE);
        activityHomeBinding.shimmerCategory.setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            TextView txtMealName = v.findViewById(R.id.mealName);
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, txtMealName.getText().toString());
            startActivity(intent);

        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        recyclerViewCategory.setLayoutManager(new GridLayoutManager(this,3, VERTICAL,false));
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }




    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

}
