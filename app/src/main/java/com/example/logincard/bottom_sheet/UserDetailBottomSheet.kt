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
import com.example.logincard.fragment.UserDetailFragment.SelectedStatus
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class UserDetailBottomSheet : BottomSheetDialogFragment() {
    enum class SelectedStatus {
        COMPLETE,
        TODO,
        PROCESSING
    }
    private var selectedStatus: SelectedStatus = SelectedStatus.COMPLETE

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

        binding.sortButton.setOnClickListener{
                showPopup(binding.sortButton)

        }

        return binding.root
    }

//    todo change names of item in xml , remove fixed size of popup menu
    private fun showPopup(anchorView: View) {
        val popupView = layoutInflater.inflate(R.layout.popup_menu, null)
        val popupWindow = PopupWindow(
            popupView,
            dpToPx(250),
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.elevation = 10f
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val layouts = listOf<LinearLayout>(
            popupView.findViewById(R.id.option_complete),
            popupView.findViewById(R.id.option_todo),
            popupView.findViewById(R.id.option_processing)
        )

        val icons = listOf<ImageView>(
            popupView.findViewById(R.id.icon_complete),
            popupView.findViewById(R.id.icon_todo),
            popupView.findViewById(R.id.icon_processing)
        )

        val defaultIndex = when (selectedStatus) {
            SelectedStatus.COMPLETE -> 0
            SelectedStatus.TODO -> 1
            SelectedStatus.PROCESSING -> 2
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
                    0 -> SelectedStatus.COMPLETE
                    1 -> SelectedStatus.TODO
                    else -> SelectedStatus.PROCESSING
                }

                popupWindow.dismiss()
            }
        }

        popupWindow.showAsDropDown(anchorView, 0, 10)
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
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

