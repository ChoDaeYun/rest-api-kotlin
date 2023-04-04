package com.daeyun.kotlinjava.controller.response


open class CommonResult {
    var success //응답 성공여부 : true/false
            = false
    var code //응답 코드 번호 : > 0 정상, < 0 비정상
            = 0
    var msg //응답 메시지"
            : String? = null
}