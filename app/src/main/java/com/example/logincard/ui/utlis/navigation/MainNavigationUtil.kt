package com.example.logincard.utlis.navigation

import android.content.Context
import android.content.ContextWrapper
import com.example.logincard.activity.HomeActivity
import com.example.logincard.R
import com.example.logincard.fragment.AddNewAssignFragment
import com.example.logincard.fragment.AssignTransactionFragment
import com.example.logincard.fragment.CategoryFragment
import com.example.logincard.fragment.SelectedCategoryFragment
import com.example.logincard.fragment.UserDetailFragment

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

//    test
//    fun navigateToAddNewAssignFragment(context: Context?) {
//        if (context == null) return
//        val fragment = AddNewAssignFragment()
//        val fragmentName = fragment.javaClass.name
//
//        val activity = unwrapActivity(context)
//
//        // Hide fragment container with bottom navigation and show container without bottom nav
//        val fragmentContainer = activity.findViewById<FrameLayout>(R.id.fragment_container)
//        val fragmentContainerWithoutBottomNav = activity.findViewById<FrameLayout>(R.id.fragment_container_without_bottom_nav)
//
//        fragmentContainer.visibility = View.GONE
//        fragmentContainerWithoutBottomNav.visibility = View.VISIBLE
//
//        activity.supportFragmentManager.beginTransaction()
//            .setCustomAnimations(
//                R.anim.enter,
//                R.anim.exit,
//                R.anim.enter,
//                R.anim.exit
//            )
//            .replace(R.id.fragment_container_without_bottom_nav, fragment, fragmentName)
//            .addToBackStack(fragmentName)
//            .commit()
//    }

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