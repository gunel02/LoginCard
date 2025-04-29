package com.example.logincard.ui.adapter

import com.example.logincard.ui.fragment.CategoryFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.R
import com.example.logincard.databinding.CategoryItemsRvBinding
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil

//class AdapterCategories(val fragment: CategoryFragment) :
//    RecyclerView.Adapter<AdapterCategories.AdapterCategoryViewHolder>() {
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): AdapterCategoryViewHolder {
//        val b = CategoryItemsRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return AdapterCategoryViewHolder(b)
//    }
//
//    override fun onBindViewHolder(
//        holder: AdapterCategoryViewHolder,
//        position: Int
//    ) {
//        holder.binding.clickButton.setOnClickListener {
//            MainNavigationUtil.navigateToSelectedCategoryFragment(holder.binding.root.context)
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return 10
//    }
//
//    fun setItems() {
//        notifyDataSetChanged()
//    }
//
//    class AdapterCategoryViewHolder(val binding: CategoryItemsRvBinding) :
//        RecyclerView.ViewHolder(binding.root)
//
//}

// todo - set to the up to right
class AdapterCategories( val fragment: CategoryFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_NORMAL = 0
        private const val VIEW_TYPE_RIGHT_LAYOUT = 1
    }

    override fun getItemCount(): Int = 10

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) VIEW_TYPE_RIGHT_LAYOUT else VIEW_TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NORMAL) {
            val b = CategoryItemsRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            NormalViewHolder(b)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bank_in_category, parent, false)
            RightViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NormalViewHolder) {
            holder.binding.clickButton.setOnClickListener {
                MainNavigationUtil.navigateToSelectedCategoryFragment(holder.binding.root.context)
            }
        }

    }


    inner class NormalViewHolder(val binding: CategoryItemsRvBinding) : RecyclerView.ViewHolder(binding.root)

    inner class RightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

