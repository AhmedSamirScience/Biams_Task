package com.samir.baims.common.base

import androidx.lifecycle.ViewModel

/**
 * Base class for all ViewModels in the application.
 * This class provides common functionality such as start and stop methods.
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * Stops any ongoing operations or processes.
     * Subclasses must override this method to perform cleanup tasks.
     */
    abstract fun stop()

    /**
     * Starts any necessary operations or processes.
     * Subclasses must override this method to perform startup tasks.
     */
    abstract fun start()


}
