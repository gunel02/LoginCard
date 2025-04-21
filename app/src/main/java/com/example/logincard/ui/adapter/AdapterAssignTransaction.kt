package com.example.logincard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.R
import com.example.logincard.databinding.AssignTransactionsRvBinding
import com.example.logincard.ui.fragment.AssignTransactionFragment
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil

class AdapterAssignTransaction(val fragment: AssignTransactionFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_FOOTER = 1
    }

    private val itemCountExcludingFooter = 10

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCountExcludingFooter) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding = AssignTransactionsRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            ItemViewHolder(binding)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_summary_footer, parent, false)
            FooterViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.clickButton.setOnClickListener {
                    MainNavigationUtil.navigateToUserDetailFragment(holder.binding.root.context)
                }
            }

            is FooterViewHolder -> {
            }
        }
    }

    override fun getItemCount(): Int = itemCountExcludingFooter + 1

    fun setItems() {
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: AssignTransactionsRvBinding) :
        RecyclerView.ViewHolder(binding.root)

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

