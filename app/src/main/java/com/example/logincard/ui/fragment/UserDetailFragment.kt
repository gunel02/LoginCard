package com.example.logincard.ui.fragment

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
import com.example.logincard.databinding.FragmentUserDetailBinding
import com.example.logincard.ui.adapter.AdapterUserDetail
import com.example.logincard.ui.utlis.navigation.MainNavigationUtil
import java.util.Calendar

class UserDetailFragment : Fragment() {

    private var calendarPopup: PopupWindow? = null
    private var selectedDateInMillis: Long = Calendar.getInstance().timeInMillis

    enum class SelectedStatus {
        COMPLETE, TODO, PROCESSING
    }

    private var binding: FragmentUserDetailBinding? = null
    private var selectedStatus: SelectedStatus = SelectedStatus.COMPLETE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        setAdapter()

        initListener()

        return binding?.root
    }

    private fun initListener() {
        binding?.calendarButton?.setOnClickListener {
            showCalendarPopup(it)
        }

        binding?.backBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding?.addButton?.setOnClickListener {
            MainNavigationUtil.navigateToAssignTransactionFragment(requireContext())
        }

        binding?.listButton?.setOnClickListener {
            showPopup(binding?.listButton!!)
        }
    }

    private fun setAdapter() {
        binding?.userDetailItemRv?.layoutManager = LinearLayoutManager(context)
        binding?.userDetailItemRv?.adapter = AdapterUserDetail(this)
    }

    private fun showPopup(anchorView: View) {
        val popupView = layoutInflater.inflate(R.layout.popup_menu, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
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


    //    todo - calendar must be custom ,
    private fun showCalendarPopup(anchor: View) {
        val view = layoutInflater.inflate(R.layout.popup_calendar, null)
        val calendarView = view.findViewById<CalendarView>(R.id.calendar_view)

        calendarView.date = selectedDateInMillis

        calendarPopup = PopupWindow(
            view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        ).apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            elevation = 8f
            isOutsideTouchable = true
            showAsDropDown(anchor, 0, 10)
        }

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            selectedDateInMillis = calendar.timeInMillis

            val dateStr = "$dayOfMonth/${month + 1}/$year"
            binding?.testDate?.text = dateStr

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
