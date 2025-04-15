package com.example.logincard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logincard.adapter.AdapterSelectedCategory
import com.example.logincard.databinding.FragmentSelectedCategoryBinding
import com.example.utlis.navigation.MainNavigationUtil

class SelectedCategoryFragment : Fragment() {
    private var binding: FragmentSelectedCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedCategoryBinding.inflate(inflater, container, false)

        setSelectedCategoryAdapter()

        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding?.addButton?.setOnClickListener{
            MainNavigationUtil.navigateToAddNewAssignFragment(requireContext())
        }

        return binding?.root
    }

    private fun setSelectedCategoryAdapter() {
        binding?.usernameOfSelectedCategoryItemRv?.layoutManager = LinearLayoutManager(context)
        binding?.usernameOfSelectedCategoryItemRv?.adapter = AdapterSelectedCategory(this)
    }

}

