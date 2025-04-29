package com.example.logincard.ui.utlis.navigation

import android.content.Context
import android.content.ContextWrapper
import com.example.logincard.R
import com.example.logincard.ui.activity.HomeActivity
import com.example.logincard.ui.fragment.AddNewAssignFragment
import com.example.logincard.ui.fragment.AssignTransactionFragment
import com.example.logincard.ui.fragment.CategoryFragment
import com.example.logincard.ui.fragment.SelectedCategoryFragment
import com.example.logincard.ui.fragment.UserDetailFragment

object MainNavigationUtil {

    fun navigateToCategoryFragment(context: Context?) {
        if (context == null) return
        val fragment = CategoryFragment()
        val fragmentName = fragment.javaClass.name

        val activity = unwrapActivity(context)
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.enter,
                R.anim.exit
            )
            .add(R.id.fragment_container, fragment, fragmentName)
            .addToBackStack(fragmentName)
            .commit()
    }

    fun navigateToSelectedCategoryFragment(context: Context?) {
        if (context == null) return
        val fragment = SelectedCategoryFragment()
        val fragmentName = fragment.javaClass.name

        val activity = unwrapActivity(context)
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.enter,
                R.anim.exit
            )
            .add(R.id.fragment_container, fragment, fragmentName)
            .addToBackStack(fragmentName)
            .commit()
    }

    fun navigateToUserDetailFragment(context: Context?) {
        if (context == null) return
        val fragment = UserDetailFragment()
        val fragmentName = fragment.javaClass.name

        val activity = unwrapActivity(context)
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.enter,
                R.anim.exit
            )
            .add(R.id.fragment_container, fragment, fragmentName)
            .addToBackStack(fragmentName)
            .commit()
    }

    fun navigateToAddNewAssignFragment(context: Context?) {
        if (context == null) return
        val fragment = AddNewAssignFragment()
        val fragmentName = fragment.javaClass.name

        val activity = unwrapActivity(context)
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.enter,
                R.anim.exit
            )
            .add(R.id.fragment_container_without_bottom_nav, fragment, fragmentName)
            .addToBackStack(fragmentName)
            .commit()
    }

    fun navigateToAssignTransactionFragment(context: Context?) {
        if (context == null) return
        val fragment = AssignTransactionFragment()
        val fragmentName = fragment.javaClass.name

        val activity = unwrapActivity(context)
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.enter,
                R.anim.exit
            )
            .add(R.id.fragment_container_without_bottom_nav, fragment, fragmentName)
            .addToBackStack(fragmentName)
            .commit()
    }

    private fun unwrapActivity(context: Context): HomeActivity {
        var correctContext: Context? = context
        while (correctContext !is HomeActivity && correctContext is ContextWrapper) {
            correctContext = correctContext.baseContext
        }
        return correctContext as HomeActivity
    }
}