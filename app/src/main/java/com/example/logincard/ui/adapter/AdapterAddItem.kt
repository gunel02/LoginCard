package com.example.logincard.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.logincard.R
import com.example.logincard.databinding.AddItemRvBinding
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil

class AdapterAddItem : RecyclerView.Adapter<AdapterAddItem.AddItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AddItemViewHolder {
        val b = AddItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(b)
    }

    override fun onBindViewHolder(
        holder: AddItemViewHolder, position: Int
    ) {
        val backgroundLayout = holder.binding.root.findViewById<LinearLayout>(R.id.click_button)

        if (position == 0) {
            backgroundLayout.setBackgroundResource(R.drawable.button_gradient)
        } else {
            backgroundLayout.setBackgroundResource(R.drawable.button_gradient_first_item)
        }

        holder.binding.clickButton.setOnClickListener {
            MainNavigationUtil.navigateToCategoryFragment(holder.binding.root.context)
        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    fun setItems() {
        notifyDataSetChanged()
    }

    class AddItemViewHolder(val binding: AddItemRvBinding) : RecyclerView.ViewHolder(binding.root)
}
