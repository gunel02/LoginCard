package com.example.logincard.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.databinding.NamesOfAddAssignsRvBinding
import com.example.logincard.databinding.UsernameOfSelectedCategoryRvBinding
import com.example.logincard.ui.fragment.AddFragment
import com.example.logincard.ui.fragment.SelectedCategoryFragment
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil

class AdapterNamesOfAddAssigns(val fragment: AddFragment) :
    RecyclerView.Adapter<AdapterNamesOfAddAssigns.AdapterNamesOfAddAssignsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterNamesOfAddAssignsViewHolder {
        val b = NamesOfAddAssignsRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdapterNamesOfAddAssignsViewHolder(b)
    }

    override fun onBindViewHolder(
        holder: AdapterNamesOfAddAssignsViewHolder,
        position: Int
    ) {
        holder.binding.clickButton.setOnClickListener {
            MainNavigationUtil.navigateToAddNewAssignFragment(holder.binding.root.context)
        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    fun setItems() {
        notifyDataSetChanged()
    }

    class AdapterNamesOfAddAssignsViewHolder(val binding: NamesOfAddAssignsRvBinding) :
        RecyclerView.ViewHolder(binding.root)
}
