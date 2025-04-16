package com.example.logincard.adapter

import com.example.logincard.fragment.CategoryFragment



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.databinding.CategoryItemsRvBinding
import com.example.logincard.utlis.navigation.MainNavigationUtil

class AdapterCategories(val fragment: CategoryFragment) :
    RecyclerView.Adapter<AdapterCategories.AdapterCategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):AdapterCategoryViewHolder {
        val b = CategoryItemsRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterCategoryViewHolder(b)
    }

    override fun onBindViewHolder(
        holder: AdapterCategoryViewHolder,
        position: Int
    ) {
        holder.binding.clickButton.setOnClickListener {
            MainNavigationUtil.navigateToSelectedCategoryFragment(holder.binding.root.context)
        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    fun setItems() {
        notifyDataSetChanged()
    }

    class AdapterCategoryViewHolder(val binding: CategoryItemsRvBinding) :
        RecyclerView.ViewHolder(binding.root)

}
