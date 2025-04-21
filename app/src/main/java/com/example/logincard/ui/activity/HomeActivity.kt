package com.example.logincard.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.logincard.R
import com.example.logincard.databinding.ActivityHomeBinding
import com.example.logincard.ui.fragment.AddFragment
import com.example.logincard.ui.fragment.HomeFragment
import com.example.logincard.ui.fragment.ProfileFragment
import com.example.logincard.ui.utlis.Func
import com.example.logincard.ui.utlis.StaticVariables

class HomeActivity : AppCompatActivity() {

    private var binding: ActivityHomeBinding? = null

    var backPress = false
    private var activeFragment: Fragment? = null
    private var selectedFragment: Int = R.id.home
    private val TAG_HOME = "home"
    private val TAG_ADD = "add"
    private val TAG_PROFILE = "profile"
    val CURRENT_FRAGMENT = "current_fragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        StaticVariables.globalHomeActivity = this@HomeActivity

        addFragments(savedInstanceState)

        binding?.bottomNav?.setOnItemSelectedListener {
            val indicator = binding?.navIndicator
            val menuSize = binding?.bottomNav?.menu?.size() ?: 1
            val itemWidth = binding?.bottomNav?.width?.div(menuSize) ?: 1

            val selectedIndex = when (it.itemId) {
                R.id.home -> 0
                R.id.add -> 1
                R.id.profile -> 2
                else -> 0
            }

            indicator?.post {
                val targetX = (selectedIndex * itemWidth) + (itemWidth / 2) - (indicator.width / 2)
                indicator.animate().x(targetX.toFloat()).setDuration(250).start()
            }

            setFragment(it.itemId)

        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (selectedFragment != R.id.home) {
                    supportFragmentManager.beginTransaction().hide(activeFragment!!)
                        .show(homeFragment).commit()
                    binding?.bottomNav?.selectedItemId = R.id.home
                    activeFragment = homeFragment
                } else {
                    if (backPress) {
                        finish()
                    } else {
                        backPress = true
                        Func.showToast(this@HomeActivity, getString(R.string.press_to_exit))
                        Handler(Looper.getMainLooper()).postDelayed({
                            backPress = false
                        }, 2000)

                    }
                }
            }
        })
    }

    private val homeFragment: HomeFragment by lazy {
        val fr = supportFragmentManager.findFragmentByTag(TAG_HOME)
        if (fr != null) fr as HomeFragment
        else HomeFragment()
    }
    private val addFragment: AddFragment by lazy {
        val fr = supportFragmentManager.findFragmentByTag(TAG_ADD)
        if (fr != null) fr as AddFragment
        else AddFragment()
    }
    private val profileFragment: ProfileFragment by lazy {
        val fr = supportFragmentManager.findFragmentByTag(TAG_PROFILE)
        if (fr != null) fr as ProfileFragment
        else ProfileFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("active_fragment", activeFragment?.tag)
    }

    private fun addFragments(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            selectedFragment = it.getInt(CURRENT_FRAGMENT, R.id.home)
        }
        val activeFragmentTag = savedInstanceState?.getString("active_fragment")

        activeFragment = if (activeFragmentTag.isNullOrEmpty()) {
            homeFragment
        } else {
            when (activeFragmentTag) {
                TAG_HOME -> homeFragment
                TAG_ADD -> addFragment
                TAG_PROFILE -> profileFragment
                else -> homeFragment
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, homeFragment, TAG_HOME).hide(homeFragment)
                .add(R.id.fragment_container, addFragment, TAG_ADD).hide(addFragment)
                .add(R.id.fragment_container, profileFragment, TAG_PROFILE).hide(profileFragment)
                .show(activeFragment!!).commit()
        }

        binding?.bottomNav?.setOnItemSelectedListener {
            setFragment(it.itemId)
        }
    }

    private fun setFragment(itemId: Int): Boolean {
        selectedFragment = itemId
        when (itemId) {
            R.id.home -> {
                if (activeFragment is HomeFragment) {
                    return false
                }

                activeFragment?.let {
                    supportFragmentManager.beginTransaction().hide(it).show(homeFragment)
                        .commit()
                }

                activeFragment = homeFragment
            }

            R.id.add -> {
                if (activeFragment is AddFragment) {
                    return false
                }
                activeFragment?.let {
                    supportFragmentManager.beginTransaction().hide(it)
                        .show(addFragment).commit()
                }
                activeFragment = addFragment
            }

            R.id.profile -> {
                if (activeFragment is ProfileFragment) {
                    return false
                }
                activeFragment?.let {
                    supportFragmentManager.beginTransaction().hide(it)
                        .show(profileFragment).commit()
                }
                activeFragment = profileFragment
            }
        }
        return true

    }
}
