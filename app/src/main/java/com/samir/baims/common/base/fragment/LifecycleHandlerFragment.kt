package com.samir.baims.common.base.fragment


/**
 * Abstract class to handle fragment lifecycle methods.
 */
abstract class LifecycleHandlerFragment : ViewHandlerFragment() {

    //region Fragment Lifecycle Callbacks
    override fun onStart() {
        super.onStart()
        start()
    }

    override fun onStop() {
        super.onStop()
        stopAndReset()
    }
    //endregion

    /**
     * Starts any necessary operations or processes.
     * Subclasses must override this method to perform startup tasks.
     */
    protected abstract fun start()

    /**
     * Stops and resets any ongoing operations or processes.
     * Subclasses must override this method to perform cleanup tasks.
     */
    protected abstract fun stopAndReset()
}
