import com.daeyun.kotlinjava.service.UserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}") private val secretKey: String,
    private val userDetailsService: UserDetailsService
) {

    companion object {
        private const val TOKEN_VALIDITY = 1000L * 60 * 180
        private const val TOKEN_PREFIX = "Bearer "
        private const val TOKEN_HEADER = "Authorization"
    }

    fun createToken(userPk:String): String {
        val now = Date()
        val validity = Date(now.time + TOKEN_VALIDITY)
        val claims = Jwts.claims().setSubject(userPk)
        claims["accessToken"] = userDetailsService.makeToken()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .compact()
    }

    // Jwt 토큰으로 인증 정보를 조회
    fun getAuthentication(token : String ) : Authentication?{
        try{
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .build()
                .parseClaimsJws(token)
                .body

            var accessToken = claims.get("accessToken").toString()
            var customUserDetails = userDetailsService.loadUserByUsername(accessToken)
            return null
        }catch (e:Exception){
            return null
        }
    }

    fun getAuthoritiesFromToken(token: String): List<String> {
        val claims = getClaimsFromToken(token)
        return claims["authorities"] as List<String>
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
