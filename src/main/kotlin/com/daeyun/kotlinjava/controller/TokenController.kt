package com.daeyun.kotlinjava.controller

import com.daeyun.kotlinjava.config.security.JwtTokenProvider
import com.daeyun.kotlinjava.controller.response.ResponseService
import com.daeyun.kotlinjava.controller.response.SingleResult
import com.daeyun.kotlinjava.dto.usertoken.CustomUserDetails
import com.daeyun.kotlinjava.service.UserDetailsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@RestController
class TokenController(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
) : ResponseService() {

    /**
     * 10분 마다 jwt 및 accesstoken 키값 새로 발급
     */
    @GetMapping("/token/validation")
    fun userGet(
        request: HttpServletRequest
    ): SingleResult<CustomUserDetails>? {
        val header = request.getHeader("Authorization")
        val token = jwtTokenProvider.resolveToken(header);
        var jwtString:String? = null
        var customUserDetails : CustomUserDetails? = null
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            val accessToken = jwtTokenProvider.getAccessToken(token) ?: return null
            customUserDetails = userDetailsService.loadUserByUsername(accessToken)
            if(customUserDetails.createDate.plusMinutes(10) < LocalDateTime.now()){
                //새로 발급
                jwtString = jwtTokenProvider.createToken(customUserDetails.getIdx())
            }
        }
        val returnData: SingleResult<CustomUserDetails> = SingleResult()
        returnData.success=true
        returnData.code=200
        returnData.msg=jwtString
        returnData.data=customUserDetails
        return returnData
    }
}