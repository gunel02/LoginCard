package com.example.logincard.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.logincard.databinding.FragmentAddNewAssignBinding

class AddNewAssignFragment : Fragment() {

    private var binding: FragmentAddNewAssignBinding? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNewAssignBinding.inflate(inflater, container, false)

        initListener()

        binding?.root?.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val currentFocus = requireActivity().currentFocus
                if (currentFocus != null && currentFocus is EditText) {
                    val imm =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                    currentFocus.clearFocus()
                }
            }
            false
        }
        return binding?.root
    }

    private fun initListener() {
        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
