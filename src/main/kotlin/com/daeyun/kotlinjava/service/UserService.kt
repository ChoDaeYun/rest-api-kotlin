package com.daeyun.kotlinjava.service

import com.daeyun.kotlinjava.domain.user.User
import com.daeyun.kotlinjava.domain.user.UserRepository
import com.daeyun.kotlinjava.dto.user.UserCreateReq
import com.daeyun.kotlinjava.dto.user.UserLoginReq
import com.daeyun.kotlinjava.exception.UserExistException
import com.daeyun.kotlinjava.exception.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun saveUser(dto: UserCreateReq){
        if(userRepository.existsByUserId(dto.id)){
            throw UserExistException()
        }
        val newUser = User(userId = dto.id, userPw = dto.pw, userName = dto.name, createDate = LocalDateTime.now())
        userRepository.save(newUser)
    }


    @Transactional(readOnly = true)
    fun loginUser(dto: UserLoginReq):User{
        return userRepository.findByUserIdAndUserPw(dto.id,dto.pw) ?: throw UserNotFoundException()
    }
}