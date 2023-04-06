package com.daeyun.kotlinjava.exception

import com.daeyun.kotlinjava.controller.response.CommonResult
import com.daeyun.kotlinjava.controller.response.ResponseService
import com.daeyun.kotlinjava.exception.user.UserException
import com.daeyun.kotlinjava.exception.user.UserExistException
import com.daeyun.kotlinjava.exception.user.UserNotFoundException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class ExceptionAdvice : ResponseService() {
    enum class ExceptionCode (var status : HttpStatus ,var code: Int, var msg: String,var koMsg : String){
        SYSTEM(HttpStatus.INTERNAL_SERVER_ERROR,9999, "An unknown error has occurred","기본 오류"),
        NOT_FOUND(HttpStatus.OK,404, "404 Not Found ","페이지를 찾을 수 없습니다"),
        REQUEST_PARAMS(HttpStatus.OK,1000,"bad request parameters","입력 값 오류 "),
        USER(HttpStatus.OK,2000, "user service error ","사용자 서비스 오류 - User"),
        USER_EXIST(HttpStatus.OK,2001, "already existing user ","이미 존재하는 회원 - User"),
        USER_NOT_FOUND(HttpStatus.OK,2002, "user not found ","사용자를 찾을수 없습니다 - User"),
        ENTRYPOINT(HttpStatus.UNAUTHORIZED,1002,"You do not have permission to access this resource","권한 오류"),
        ACCESS_DENIED(HttpStatus.FORBIDDEN,1003,"A resource that can not be accessed with the privileges it has","권한 오류")
    }

    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception): ResponseEntity<CommonResult>  {
        return ResponseEntity(this.getFailResult(ExceptionCode.SYSTEM.code,ExceptionCode.SYSTEM.msg),ExceptionCode.SYSTEM.status)
    }

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun httpMessageNotReadableException(e:HttpMessageNotReadableException):ResponseEntity<CommonResult>{
        return ResponseEntity(this.getFailResult(ExceptionCode.REQUEST_PARAMS.code,ExceptionCode.REQUEST_PARAMS.msg),ExceptionCode.REQUEST_PARAMS.status)
    }

    @ExceptionHandler(value = [NoHandlerFoundException::class])
    fun noHandlerFoundException(e:NoHandlerFoundException):ResponseEntity<CommonResult>{
        return ResponseEntity(this.getFailResult(ExceptionCode.NOT_FOUND.code,ExceptionCode.NOT_FOUND.msg),ExceptionCode.NOT_FOUND.status)
    }

    @ExceptionHandler(value = [RequestParamsException::class])
    fun requestParamsException(e: RequestParamsException): ResponseEntity<CommonResult>  {
        return ResponseEntity(this.getFailResult(ExceptionCode.REQUEST_PARAMS.code,ExceptionCode.REQUEST_PARAMS.msg),ExceptionCode.REQUEST_PARAMS.status)
    }


    @ExceptionHandler(value = [UserException::class])
    fun userException(e: UserException): ResponseEntity<CommonResult>  {
        return ResponseEntity(this.getFailResult(ExceptionCode.USER.code,ExceptionCode.USER.msg),ExceptionCode.USER.status)
    }

    @ExceptionHandler(value = [UserExistException::class])
    fun userException(e: UserExistException): ResponseEntity<CommonResult>  {
        return ResponseEntity(this.getFailResult(ExceptionCode.USER_EXIST.code,ExceptionCode.USER_EXIST.msg),ExceptionCode.USER_EXIST.status)
    }

    @ExceptionHandler(value = [UserNotFoundException::class])
    fun userException(e: UserNotFoundException): ResponseEntity<CommonResult>  {
        return ResponseEntity(this.getFailResult(ExceptionCode.USER_NOT_FOUND.code,ExceptionCode.USER_NOT_FOUND.msg),ExceptionCode.USER_NOT_FOUND.status)
    }

}