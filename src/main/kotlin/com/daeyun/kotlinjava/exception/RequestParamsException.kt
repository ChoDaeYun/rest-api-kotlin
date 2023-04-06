package com.daeyun.kotlinjava.exception

class RequestParamsException (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}

