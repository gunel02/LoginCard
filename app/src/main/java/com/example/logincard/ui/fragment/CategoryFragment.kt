package com.example.logincard.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.logincard.databinding.FragmentCategoryBinding
import com.example.logincard.ui.adapter.AdapterCategories

class CategoryFragment : Fragment() {

    private var binding: FragmentCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        setCategoryAdapter()

        initListener()

        return binding?.root

    }

    private fun initListener() {
        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setCategoryAdapter() {
        binding?.categoryItemRv?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = AdapterCategories(this@CategoryFragment)

        }
    }

}