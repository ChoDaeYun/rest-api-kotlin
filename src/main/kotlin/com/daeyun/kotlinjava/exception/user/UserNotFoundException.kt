package com.daeyun.kotlinjava.exception.user

class UserNotFoundException (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}

