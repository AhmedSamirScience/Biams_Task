package com.samir.baims.common.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.Visibility

/**
 * Abstract class to handle enabling and disabling all views in a fragment.
 */
abstract class ViewHandlerFragment  : Fragment() {

    //region disable and enable All views
    private lateinit var allViews: List<View>
    protected abstract fun getAllViews(): List<View>

    private lateinit var allViewsForVisibility: List<View>
    protected abstract fun getAllViewsForVisibility(): List<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allViews = getAllViews()
        allViewsForVisibility = getAllViewsForVisibility()
    }

    fun disableAllViews() {
        for (item in allViews) {
            item.isEnabled = false
        }
    }

    fun enableAllViews() {
        for (item in allViews) {
            item.isEnabled = true
        }
    }

    fun visibleAllViews() {
        for (item in allViewsForVisibility) {
            item.visibility = View.VISIBLE
        }
    }

    fun goneAllViews() {
        for (item in allViewsForVisibility) {
            item.visibility = View.GONE
        }
    }
    //endregion
}
