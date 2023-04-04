package com.daeyun.kotlinjava.controller.response

class ListResult<T> : CommonResult() {
    var list: List<T>? = null
}