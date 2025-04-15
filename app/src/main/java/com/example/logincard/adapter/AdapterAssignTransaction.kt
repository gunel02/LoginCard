package com.example.logincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.databinding.AssignTransactionsRvBinding
import com.example.logincard.databinding.CategoryItemsRvBinding
import com.example.logincard.databinding.UsernameOfSelectedCategoryRvBinding
import com.example.logincard.fragment.AssignTransactionFragment
import com.example.logincard.fragment.SelectedCategoryFragment
import com.example.utlis.navigation.MainNavigationUtil

class AdapterAssignTransaction(val fragment: AssignTransactionFragment) :
    RecyclerView.Adapter<AdapterAssignTransaction.AdapterAssignTransactionViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):AdapterAssignTransactionViewHolder {
        val b = AssignTransactionsRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterAssignTransactionViewHolder(b)
    }

    override fun onBindViewHolder(
        holder: AdapterAssignTransactionViewHolder,
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

    class AdapterAssignTransactionViewHolder(val binding: AssignTransactionsRvBinding ) :
        RecyclerView.ViewHolder(binding.root)

}
