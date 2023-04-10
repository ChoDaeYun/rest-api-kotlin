package com.daeyun.kotlinjava.config

import com.daeyun.kotlinjava.config.security.CorsFilter
import com.daeyun.kotlinjava.config.security.CustomAccessDeniedHandler
import com.daeyun.kotlinjava.config.security.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.header.writers.StaticHeadersWriter
import org.springframework.security.web.session.SessionManagementFilter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter(){


    override fun configure(http: HttpSecurity) {

        http
            .httpBasic().disable()
            .headers()
            .addHeaderWriter(
                StaticHeadersWriter("Access-Control-Allow-Origin", "*")
            )
            .and().addFilterBefore(corsFilter(),SessionManagementFilter::class.java)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token으로 인증할것이므로 세션필요없으므로 생성안함.
            .and().authorizeHttpRequests()
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .antMatchers("/user/**").hasRole("USER")
            .anyRequest().hasRole("USER")
            .and().exceptionHandling().accessDeniedHandler(CustomAccessDeniedHandler())
            .and().exceptionHandling().authenticationEntryPoint(CustomAuthenticationEntryPoint())

    }


    @Bean
    fun corsFilter(): CorsFilter? {
        return CorsFilter()
    }
}
