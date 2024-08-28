package com.samir.baims.common.stateHandling.uI

/**
 * Reference:
 * https://medium.com/@summitkumar/unlocking-the-power-of-sealed-classes-in-kotlin-design-patterns-and-better-code-organization-5627d73a4903
 */
sealed class LiveDataResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : LiveDataResource<T>(data)
    class ErrorResponse<T>(message: String) : LiveDataResource<T>(null, message)
    class Error<T>(message: String) : LiveDataResource<T>(null, message)
    class Loading<T> : LiveDataResource<T>()
}