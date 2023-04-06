package com.daeyun.kotlinjava.exception.user

class UserException (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}

