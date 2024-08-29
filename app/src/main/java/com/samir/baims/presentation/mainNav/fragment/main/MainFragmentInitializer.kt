package com.samir.baims.presentation.mainNav.fragment.main

import android.view.View

class MainFragmentInitializer {
    fun start() {}
    fun terminate() {}

    /**
     * Sets a click listener to the given view.
     *
     * @param view The view to which the click listener will be attached.
     * @param clickListener The click listener to be set on the view.
     */
    fun setOnClickListeners(views: List<View>, clickListener: View.OnClickListener) {
        for (item in views) {
            item.setOnClickListener(clickListener)
        }
    }
}