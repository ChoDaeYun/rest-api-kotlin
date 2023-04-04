package com.daeyun.kotlinjava.exception

import com.daeyun.kotlinjava.controller.response.CommonResult
import com.daeyun.kotlinjava.controller.response.ResponseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice : ResponseService() {
    enum class ExceptionCode (var status : HttpStatus ,var code: Int, var msg: String,var koMsg : String){
        UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR,9999, "An unknown error has occurred","알수없는 오류"),
        API_COMMON(HttpStatus.OK,1000, "api call error","API 호출 오류 - 기본"),
        ENTRYPOINT(HttpStatus.UNAUTHORIZED,1002,"You do not have permission to access this resource","권한 오류"),
        ACCESS_DENIED(HttpStatus.FORBIDDEN,1003,"A resource that can not be accessed with the privileges it has","권한 오류")
    }

    @ExceptionHandler(value = [Exception::class])
    fun defaultException(e: Exception): ResponseEntity<CommonResult>  {
        if (e.message != null) ExceptionCode.UNKNOWN.msg.plus("( ${e.message} )")
        return ResponseEntity(this.getFailResult(ExceptionCode.UNKNOWN.code,ExceptionCode.UNKNOWN.msg),ExceptionCode.UNKNOWN.status)
    }

    @ExceptionHandler(value = [CustomException::class])
    fun apiDefaultException(e: CustomException): ResponseEntity<CommonResult>  {
        if (e.message != null) ExceptionCode.API_COMMON.msg.plus("( ${e.message} )")
        return ResponseEntity(this.getFailResult(ExceptionCode.API_COMMON.code,ExceptionCode.API_COMMON.msg),ExceptionCode.API_COMMON.status)
    }

}