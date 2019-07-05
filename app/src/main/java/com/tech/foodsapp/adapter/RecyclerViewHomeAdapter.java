package com.tech.foodsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tech.foodsapp.R;
import com.tech.foodsapp.databinding.ItemRecyclerCategoryBinding;
import com.tech.foodsapp.model.Category;
import com.tech.foodsapp.model.Categories;

import java.util.List;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {

    private List<Categories.Category> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapter(List<Categories.Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemRecyclerCategoryBinding itemRecyclerCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                    R.layout.item_recycler_category,
                    viewGroup, false);
            return new RecyclerViewHolder(itemRecyclerCategoryBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHomeAdapter.RecyclerViewHolder viewHolder, int i) {

           /* String strCategoryThum = categories.get(i).getStrCategoryThumb();
            Picasso.get().load(strCategoryThum).placeholder(R.drawable.ic_circle).into(viewHolder.categoryThumb);
            String strCategoryName = categories.get(i).getStrCategory();
            */
          Categories.Category category = categories.get(i);
            viewHolder.itemRecyclerCategoryBinding.setCategory(category);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemRecyclerCategoryBinding itemRecyclerCategoryBinding;

        RecyclerViewHolder(@NonNull ItemRecyclerCategoryBinding itemRecyclerCategoryBinding) {
            super(itemRecyclerCategoryBinding.getRoot());
            itemRecyclerCategoryBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }



    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
