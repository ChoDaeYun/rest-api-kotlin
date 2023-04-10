package com.daeyun.kotlinjava.controller

import com.daeyun.kotlinjava.config.security.JwtTokenProvider
import com.daeyun.kotlinjava.controller.response.CommonResult
import com.daeyun.kotlinjava.controller.response.ResponseService
import com.daeyun.kotlinjava.controller.response.SingleResult
import com.daeyun.kotlinjava.domain.user.User
import com.daeyun.kotlinjava.dto.user.UserCreateReq
import com.daeyun.kotlinjava.dto.user.UserLoginReq
import com.daeyun.kotlinjava.dto.user.UserUpdateReq
import com.daeyun.kotlinjava.dto.usertoken.CustomUserDetails
import com.daeyun.kotlinjava.exception.RequestParamsException
import com.daeyun.kotlinjava.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider
) : ResponseService() {

    @GetMapping("/user/get")
    fun userGet(
        @AuthenticationPrincipal customUserDetails: CustomUserDetails
    ): SingleResult<User>? {
        return this.getSingleResult(userService.getUser(customUserDetails.getIdx()))
    }

    @PostMapping("/user/create")
    fun userCreate(
        @RequestBody dto: UserCreateReq
    ):CommonResult?{
        if(dto.name == "" || dto.id == "" || dto.pw == ""){
            throw RequestParamsException()
        }
        userService.saveUser(dto)
        return this.getSuccessResult()
    }

    @PostMapping("/user/login")
    fun userLogin(
        @RequestBody dto : UserLoginReq
    ): SingleResult<String?>? {
        var user:User = userService.loginUser(dto)
        user.idx?.let {
            var jwtString = jwtTokenProvider.createToken(it)
            return this.getSingleResult(jwtString)
        }
        throw RequestParamsException()
    }

    @PutMapping("/user/update")
    fun userModify(
        @AuthenticationPrincipal customUserDetails: CustomUserDetails
        ,@RequestBody dto : UserUpdateReq
    ): CommonResult? {
        if(dto.name == ""){
            throw RequestParamsException()
        }
        userService.updateUser(customUserDetails.getIdx(),dto)
        return this.getSuccessResult()
    }
}