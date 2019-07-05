package com.tech.foodsapp.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tech.foodsapp.R;
import com.tech.foodsapp.adapter.ViewPagerCategoryAdapter;
import com.tech.foodsapp.databinding.ActivityCategoryBinding;
import com.tech.foodsapp.model.Categories;
import com.tech.foodsapp.view.home.HomeActivity;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ActivityCategoryBinding activityCategoryBinding;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCategoryBinding = DataBindingUtil.setContentView(this,R.layout.activity_category);
       // setContentView(R.layout.activity_category);

       viewPager  = activityCategoryBinding.viewPager;
       tabLayout = activityCategoryBinding.tabLayout;

        initActionBar();

        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION, 0);
        ViewPagerCategoryAdapter viewPagerCategoryAdapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
        viewPager.setAdapter(viewPagerCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position, true);
        viewPagerCategoryAdapter.notifyDataSetChanged();

    }

    private void initActionBar() {
        setSupportActionBar(activityCategoryBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
