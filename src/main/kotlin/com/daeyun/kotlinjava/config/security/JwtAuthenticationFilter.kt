package com.daeyun.kotlinjava.config.security

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider): OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request :HttpServletRequest,
        response : HttpServletResponse ,
        chain : FilterChain
    ) {
        val header = request.getHeader("Authorization")
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }
        val token = header.replace("Bearer ", "")
        // 유효한 토큰인지 확인합니다.
        if (token != null && jwtTokenProvider.validateToken(token)) {

            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            val authentication = jwtTokenProvider.getAuthentication(token)
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }
}
