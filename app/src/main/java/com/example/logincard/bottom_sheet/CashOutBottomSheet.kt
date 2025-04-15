package com.example.logincard.bottom_sheet

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.logincard.R
import com.example.logincard.databinding.BottomSheetCashOutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CashOutBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetCashOutBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetCashOutBinding.inflate(inflater, container, false)

        binding.closeButton.setOnClickListener {
            dismiss()
        }

        binding.clickButton.setOnClickListener {
            binding.numberEditText.requestFocus()
            binding.numberEditText.post {
                val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(binding.numberEditText,InputMethodManager.SHOW_IMPLICIT)
            }
        }

        setupUI(binding.root)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setupUI(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        val view = dialog?.currentFocus ?: View(requireContext())
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }


}

