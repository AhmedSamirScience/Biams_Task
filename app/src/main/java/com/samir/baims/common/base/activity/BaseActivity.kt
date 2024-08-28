package com.samir.baims.common.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import com.samir.baims.common.base.activity.BackPressedHandlerActivity

/**
 * Base class for all activities in the application.
 * This class provides common functionality such as setting up views, etc.
 */
abstract class BaseActivity : BackPressedHandlerActivity() {

    /** ViewDataBinding instance for the layout of the activity */
    protected lateinit var baseViewBinding: ViewDataBinding

    /**
     * Inflates the binding for the activity layout.
     * @param inflater LayoutInflater instance to inflate the binding.
     * @return ViewDataBinding instance for the inflated layout.
     */
    protected abstract fun inflateBinding(inflater: LayoutInflater): ViewDataBinding
    protected abstract fun getContainer(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewBinding = inflateBinding(layoutInflater)
        setContentView(baseViewBinding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(getContainer()) as NavHostFragment
        navController = navHostFragment.navController
        initializeViews()
    }

    /**
     * Initializes the views of the activity.
     * Subclasses must override this method to set up views.
     */
    protected abstract fun initializeViews()
}
