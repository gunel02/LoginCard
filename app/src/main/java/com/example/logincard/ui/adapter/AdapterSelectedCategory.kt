package com.example.logincard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.databinding.UsernameOfSelectedCategoryRvBinding
import com.example.logincard.ui.fragment.SelectedCategoryFragment
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil

class AdapterSelectedCategory(val fragment: SelectedCategoryFragment) :
    RecyclerView.Adapter<AdapterSelectedCategory.AdapterSelectedCategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterSelectedCategoryViewHolder {
        val b = UsernameOfSelectedCategoryRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdapterSelectedCategoryViewHolder(b)
    }

    override fun onBindViewHolder(
        holder: AdapterSelectedCategoryViewHolder,
        position: Int
    ) {
        holder.binding.clickButton.setOnClickListener {
            MainNavigationUtil.navigateToUserDetailFragment(holder.binding.root.context)
        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    fun setItems() {
        notifyDataSetChanged()
    }

    class AdapterSelectedCategoryViewHolder(val binding: UsernameOfSelectedCategoryRvBinding) :
        RecyclerView.ViewHolder(binding.root)
}
