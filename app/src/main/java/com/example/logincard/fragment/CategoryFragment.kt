package com.example.logincard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.logincard.R
import com.example.logincard.adapter.AdapterCategories
import com.example.logincard.databinding.FragmentCategoryBinding
import com.example.logincard.databinding.FragmentHomeBinding

class CategoryFragment : Fragment() {

    private var binding: FragmentCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)


        setCategoryAdapter()

        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return binding?.root

    }

    private fun setCategoryAdapter() {

        binding?.categoryItemRv?.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = AdapterCategories(this@CategoryFragment)

        }
    }

}