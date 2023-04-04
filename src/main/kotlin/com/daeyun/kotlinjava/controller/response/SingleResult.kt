package com.daeyun.kotlinjava.controller.response

class SingleResult<T> : CommonResult() {
    var data: T? = null
}