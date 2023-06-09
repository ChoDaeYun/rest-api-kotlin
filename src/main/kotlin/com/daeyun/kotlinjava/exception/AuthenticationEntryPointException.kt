package com.daeyun.kotlinjava.exception

class AuthenticationEntryPointException (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}

