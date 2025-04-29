package com.example.logincard.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logincard.databinding.FragmentAssignTransactionBinding
import com.example.logincard.ui.adapter.AdapterAssignTransaction
import com.example.logincard.ui.bottom_sheet.AddCashBottomSheet
import com.example.logincard.ui.bottom_sheet.CashOutBottomSheet

class AssignTransactionFragment : Fragment() {

    private var binding: FragmentAssignTransactionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssignTransactionBinding.inflate(inflater, container, false)

        initListener()

        setAdapter()

        return binding?.root
    }

    private fun initListener() {
        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding?.addCashButton?.setOnClickListener {
            val bottomSheet = AddCashBottomSheet()
            bottomSheet.show(requireActivity().supportFragmentManager, "bottomSheet")
        }

        binding?.cashOutButton?.setOnClickListener {
            val bottomSheet = CashOutBottomSheet()
            bottomSheet.show(requireActivity().supportFragmentManager, "bottomSheet")
        }
    }

    private fun setAdapter() {
        binding?.assignTransactionRv?.layoutManager = LinearLayoutManager(context)
        binding?.assignTransactionRv?.adapter = AdapterAssignTransaction(this)
    }
}