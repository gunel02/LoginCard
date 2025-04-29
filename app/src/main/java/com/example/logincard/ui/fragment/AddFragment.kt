package com.example.logincard.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logincard.databinding.FragmentAddBinding
import com.example.logincard.ui.adapter.AdapterNamesOfAddAssigns
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil


class AddFragment : Fragment() {

    private var binding: FragmentAddBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBinding.inflate(inflater, container, false)

        setAdapter()

        initListener()
        return binding?.root

    }

    private fun initListener(){
        binding?.addAssignButton?.setOnClickListener {
            MainNavigationUtil.navigateToAddNewAssignFragment(requireContext())
        }
    }

    private fun setAdapter() {
        binding?.addNamesOfAddAssignsRv?.layoutManager = LinearLayoutManager(context)
        binding?.addNamesOfAddAssignsRv?.adapter = AdapterNamesOfAddAssigns(this)
    }
}