package com.example.logincard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.bottom_sheet.UserDetailBottomSheet
import com.example.logincard.databinding.UserDetailItemRvBinding
import com.example.logincard.databinding.UsernameOfSelectedCategoryRvBinding
import com.example.logincard.fragment.SelectedCategoryFragment
import com.example.logincard.fragment.UserDetailFragment
import com.example.logincard.utlis.navigation.MainNavigationUtil

class AdapterUserDetail(val fragment: UserDetailFragment) :
    RecyclerView.Adapter<AdapterUserDetail.AdapterUserDetailViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):AdapterUserDetailViewHolder {
        val b = UserDetailItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterUserDetailViewHolder(b)
    }

    override fun onBindViewHolder(
        holder: AdapterUserDetailViewHolder,
        position: Int
    ) {

           holder.binding.mainContainer.setOnClickListener {
            val bottomSheet = UserDetailBottomSheet()
            bottomSheet.show(fragment.requireActivity().supportFragmentManager, "bottomSheet")
            }



    }

    override fun getItemCount(): Int {
        return 10
    }

    fun setItems() {
        notifyDataSetChanged()
    }

    class AdapterUserDetailViewHolder(val binding: UserDetailItemRvBinding) :
        RecyclerView.ViewHolder(binding.root)

}
