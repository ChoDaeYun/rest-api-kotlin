package com.daeyun.kotlinjava.config.security

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse
import java.io.IOException


class CorsFilter : Filter {

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig?) {
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilter(req: ServletRequest?, res: ServletResponse, chain: FilterChain) {
        val response: HttpServletResponse = res as HttpServletResponse
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH")
        response.setHeader(
            "Access-Control-Allow-Headers",
            "X-AUTH-TOKEN, Content-Type, Authorization, Content-Length, X-Requested-With, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization"
        )
        chain.doFilter(req, res)
    }
    override fun destroy() {}
}