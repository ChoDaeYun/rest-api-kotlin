package com.daeyun.kotlinjava.controller.response

open class ResponseService {
    // enum 으로 api 요청 결과에 대한 code, message 를 정의
    enum class CommonResponse(var code: Int, var msg: String) {
        SUCCESS(200, "success.")

    }

    // 단일건 결과를 처리하는 메소드
    fun <T> getSingleResult(data: T): SingleResult<T>? {
        val result: SingleResult<T> = SingleResult()
        result.data = data
        setSuccessResult(result)
        return result
    }

    // 다중건 결과를 처리하는 메소드
    fun <T> getListResult(list: List<T>?): ListResult<T>? {
        val result: ListResult<T> = ListResult()
        result.list = list
        setSuccessResult(result)
        return result
    }

    // 성공 결과만 처리하는 메소드
    fun getSuccessResult(): CommonResult? {
        val result = CommonResult()
        setSuccessResult(result)
        return result
    }

    // 실패 결과만 처리하는 메소드
    fun getFailResult(code: Int, msg: String?): CommonResult {
        val result = CommonResult()
        result.success = false
        result.code = code
        result.msg = msg
        return result
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private fun setSuccessResult(result: CommonResult) {
        result.success = true
        result.code = CommonResponse.SUCCESS.code
        result.msg = CommonResponse.SUCCESS.msg
    }
}