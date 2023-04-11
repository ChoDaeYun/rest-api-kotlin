package com.daeyun.kotlinjava.config.security

import com.daeyun.kotlinjava.dto.usertoken.CustomUserDetails
import com.daeyun.kotlinjava.service.UserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}") private var secretKey: String,
    private val userDetailsService: UserDetailsService
) {

    init {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }
    companion object {
        private const val TOKEN_VALIDITY = 1000L * 60 * 180
        private const val TOKEN_PREFIX = "Bearer "
        private const val TOKEN_HEADER = "Authorization"
    }

    fun createToken(idx:Long): String {
        val now = Date()
        val validity = Date(now.time + TOKEN_VALIDITY)
        val token = userDetailsService.makeToken()
        val claims = Jwts.claims().setSubject(idx.toString())
        claims["password"] = token
        claims["roles"] = listOf("ROLE_USER");
        userDetailsService.updateToken(idx,token)
        return Jwts.builder()
            .setHeaderParam("typ","JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)),SignatureAlgorithm.HS256)
            .compact()
    }

    // Jwt 토큰으로 인증 정보를 조회
    fun getAuthentication(token : String ) : Authentication?{
        try{
            var accessToken: String = getAccessToken(token) ?: return null
            var customUserDetails = userDetailsService.loadUserByUsername(accessToken)
            return UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.authorities)
        }catch (e:Exception){
            return null
        }
    }

    fun getAccessToken(token : String):String?{
        return try{
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .build()
                .parseClaimsJws(token)
                .body
            claims["password"].toString()
        }catch (e:Exception){
            null
        }
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = getClaimsFromToken(token)
            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
    private fun getClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token)
            .body
    }


    fun resolveToken(tokenHeader: String?): String? {
        if (tokenHeader != null && tokenHeader.startsWith(TOKEN_PREFIX)) {
            return tokenHeader.replace(TOKEN_PREFIX, "")
        }
        return null
    }
}
