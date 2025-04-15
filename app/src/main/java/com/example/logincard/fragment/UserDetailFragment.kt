package com.example.logincard.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logincard.R
import com.example.logincard.adapter.AdapterUserDetail
import com.example.logincard.databinding.FragmentUserDetailBinding
import com.example.utlis.navigation.MainNavigationUtil

class UserDetailFragment : Fragment() {

    enum class SelectedStatus {
        COMPLETE,
        TODO,
        PROCESSING
    }

    private var binding: FragmentUserDetailBinding? = null
    private var selectedStatus: SelectedStatus = SelectedStatus.COMPLETE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        setAdapter()

        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding?.addButton?.setOnClickListener {
            MainNavigationUtil.navigateToAssignTransactionFragment(requireContext())
        }

        binding?.listButton?.setOnClickListener {
            showPopup(binding?.listButton!!)
        }


        binding?.calendarButton?.setOnClickListener {
        }


        return binding?.root
    }

    private fun setAdapter() {
        binding?.userDetailItemRv?.layoutManager = LinearLayoutManager(context)
        binding?.userDetailItemRv?.adapter = AdapterUserDetail(this)
    }

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


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
