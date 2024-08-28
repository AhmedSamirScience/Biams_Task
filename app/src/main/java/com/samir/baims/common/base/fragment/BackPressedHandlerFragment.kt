package com.samir.baims.common.base.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback

/**
 * Abstract class to handle back pressed events in fragments.
 */
abstract class BackPressedHandlerFragment : LifecycleHandlerFragment() {

    //region handling onBackPressedCallback
    protected val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Handle back press event here
            // You can add your custom logic
            // For example:
            if (shouldHandleBackPressed()) {
                Log.e("BackPressedx", "Handled in fragment")

                // If you want to handle back press in fragment
                handleBackPressed()
            } else {
                Log.e("BackPressedx", "Handled in activity")

                // If you want to let the activity handle back press
                isEnabled = false
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    // Override this method in subclasses to provide custom back press handling logic
    protected open fun shouldHandleBackPressed(): Boolean {
        return false
    }

    protected open fun handleBackPressed() {
        // Default implementation does nothing
    }
    //endregion
}
