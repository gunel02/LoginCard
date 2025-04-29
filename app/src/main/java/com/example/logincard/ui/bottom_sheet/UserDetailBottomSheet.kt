package com.example.logincard.ui.bottom_sheet

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.example.logincard.R
import com.example.logincard.databinding.BottomSheetUserDetailBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserDetailBottomSheet : BottomSheetDialogFragment() {
    enum class SelectedStatus {
        PROGRESS,
        ACTIVE,
        INACTIVE
    }

    private var selectedStatus: SelectedStatus = SelectedStatus.PROGRESS
    private var _binding: BottomSheetUserDetailBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetUserDetailBinding.inflate(inflater, container, false)

        initListener()

        binding.root.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val focusedView = dialog?.currentFocus
                if (focusedView != null) {
                    val imm =
                        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
                    focusedView.clearFocus()
                }
            }
            false
        }
        return binding.root
    }

    private fun initListener() {
        binding.closeButton.setOnClickListener {
            dismiss()
        }

        binding.sortButton.setOnClickListener {
            showPopup(binding.sortButton)
        }
    }

    private fun showPopup(anchorView: View) {
        val popupView = layoutInflater.inflate(R.layout.layout_popup_menu_in_progress, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        popupWindow.isFocusable = false
        popupWindow.isOutsideTouchable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.elevation = 10f
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val layouts = listOf(
            popupView.findViewById<LinearLayout>(R.id.option_in_progress),
            popupView.findViewById<LinearLayout>(R.id.option_active),
            popupView.findViewById<LinearLayout>(R.id.option_in_active)
        )

        val icons = listOf(
            popupView.findViewById<ImageView>(R.id.icon_check),
            popupView.findViewById<ImageView>(R.id.icon_check2),
            popupView.findViewById<ImageView>(R.id.icon_check3)
        )

        val statusTextList = listOf("In Progress", "Active", "Inactive")

        val defaultIndex = when (selectedStatus) {
            SelectedStatus.PROGRESS -> 0
            SelectedStatus.ACTIVE -> 1
            SelectedStatus.INACTIVE -> 2

        }

        fun updateSelection(selectedIndex: Int) {
            icons.forEachIndexed { index, icon ->
                icon.visibility = if (index == selectedIndex) View.VISIBLE else View.GONE
            }
        }

        updateSelection(defaultIndex)

        layouts.forEachIndexed { index, layout ->
            layout.setOnClickListener {
                updateSelection(index)

                selectedStatus = when (index) {
                    0 -> SelectedStatus.PROGRESS
                    1 -> SelectedStatus.ACTIVE
                    else -> SelectedStatus.INACTIVE
                }

                binding.textStatus.text = statusTextList[index]

                popupWindow.dismiss()
            }
        }
        popupWindow.showAsDropDown(anchorView, 0, 10)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheet = (dialog as? BottomSheetDialog)?.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            ) as FrameLayout?
            bottomSheet?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
