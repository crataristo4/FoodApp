package com.tech.foodsapp.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tech.foodsapp.R;
import com.tech.foodsapp.Utils;
import com.tech.foodsapp.adapter.RecyclerViewMealByCategory;
import com.tech.foodsapp.databinding.FragmentCategoryBinding;
import com.tech.foodsapp.model.Meals;
import com.tech.foodsapp.view.detail.DetailActivity;

import java.util.List;

import static com.tech.foodsapp.view.home.HomeActivity.EXTRA_DETAIL;

public class CategoryFragment extends Fragment implements CategoryView {


    RecyclerView recyclerView;
    private AlertDialog.Builder descDialog;
    private FragmentCategoryBinding fragmentCategoryBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        fragmentCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        return fragmentCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            fragmentCategoryBinding.textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(fragmentCategoryBinding.imageCategory);
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(fragmentCategoryBinding.imageCategoryBg);

            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            CategoryPresenter categoryPresenter = new CategoryPresenter(this);
            categoryPresenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));


        }

        fragmentCategoryBinding.cardCategory.setOnClickListener(view1 -> {
            descDialog.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
            descDialog.show();

        });
    }

    @Override
    public void showLoading() {
        fragmentCategoryBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        fragmentCategoryBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {

        RecyclerViewMealByCategory recyclerViewMealByCategory = new RecyclerViewMealByCategory(getActivity(), meals);
        recyclerView = fragmentCategoryBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(recyclerViewMealByCategory);

        recyclerViewMealByCategory.setOnItemClickListener((view, position) -> {
            TextView txtMealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, txtMealName.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }


}
