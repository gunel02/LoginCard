package com.example.logincard.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logincard.databinding.FragmentSelectedCategoryBinding
import com.example.logincard.ui.adapter.AdapterSelectedCategory
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil

class SelectedCategoryFragment : Fragment() {
    private var binding: FragmentSelectedCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedCategoryBinding.inflate(inflater, container, false)

        setSelectedCategoryAdapter()

        initListener()

        return binding?.root
    }

    private fun initListener() {
        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding?.addButton?.setOnClickListener {
            MainNavigationUtil.navigateToAddNewAssignFragment(requireContext())
        }
    }

    private fun setSelectedCategoryAdapter() {
        binding?.usernameOfSelectedCategoryItemRv?.layoutManager = LinearLayoutManager(context)
        binding?.usernameOfSelectedCategoryItemRv?.adapter = AdapterSelectedCategory(this)
    }

}

