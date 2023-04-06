package com.daeyun.kotlinjava.exception.user

class UserExistException (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    constructor(cause: Throwable) : this(null, cause)
}
