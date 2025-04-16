package com.example.logincard.bottom_sheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.example.logincard.R
import com.example.logincard.databinding.BottomSheetUserDetailBinding
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetUserDetailBinding.inflate(inflater, container, false)

        binding.closeButton.setOnClickListener {
            dismiss()
        }

        binding.sortButton.setOnClickListener {
            showPopup(binding.sortButton)
        }

        return binding.root
    }

    private fun showPopup(anchorView: View) {
        val popupView = layoutInflater.inflate(R.layout.layout_popup_menu_in_progress, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
