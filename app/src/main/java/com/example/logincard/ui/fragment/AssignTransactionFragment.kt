package com.example.logincard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logincard.R
import com.example.logincard.adapter.AdapterAddItem
import com.example.logincard.adapter.AdapterAssignTransaction
import com.example.logincard.bottom_sheet.CashOutBottomSheet
import com.example.logincard.bottom_sheet.UserDetailBottomSheet
import com.example.logincard.databinding.FragmentAddNewAssignBinding
import com.example.logincard.databinding.FragmentAssignTransactionBinding

class AssignTransactionFragment : Fragment() {

    private var binding: FragmentAssignTransactionBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssignTransactionBinding.inflate(inflater, container, false)

        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding?.cashOutButton?.setOnClickListener {

                val bottomSheet = CashOutBottomSheet()
                bottomSheet.show(requireActivity().supportFragmentManager, "bottomSheet")

        }

        setAdapter()
        return binding?.root
    }

    private fun setAdapter() {
        binding?.assignTransactionRv?.layoutManager = LinearLayoutManager(context)
        binding?.assignTransactionRv?.adapter = AdapterAssignTransaction(this)
    }


}