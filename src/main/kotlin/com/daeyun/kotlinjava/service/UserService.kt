package com.daeyun.kotlinjava.service

import com.daeyun.kotlinjava.domain.user.User
import com.daeyun.kotlinjava.domain.user.UserRepository
import com.daeyun.kotlinjava.dto.user.UserCreateReq
import com.daeyun.kotlinjava.dto.user.UserLoginReq
import com.daeyun.kotlinjava.dto.user.UserUpdateReq
import com.daeyun.kotlinjava.exception.RequestParamsException
import com.daeyun.kotlinjava.exception.user.UserExistException
import com.daeyun.kotlinjava.exception.user.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun getUser(idx:Long): User {
        var optional = userRepository.findById(idx)
        if(!optional.isPresent) throw UserNotFoundException()
        return optional.get()
    }

    @Transactional
    fun saveUser(dto: UserCreateReq){
        if(userRepository.existsByUserId(dto.id)){
            throw UserExistException()
        }
        val newUser = User(idx=null,userId = dto.id, userPw = dto.pw, userName = dto.name)
        userRepository.save(newUser)
    }

    @Transactional
    fun updateUser(idx:Long , dto: UserUpdateReq){
        if(!userRepository.existsByIdx(idx)){
            throw UserNotFoundException()
        }
        userRepository.updateUser(idx,dto.name)
    }


    @Transactional(readOnly = true)
    fun loginUser(dto: UserLoginReq):User{
        return userRepository.findByUserIdAndUserPw(dto.id,dto.pw) ?: throw UserNotFoundException()
    }
}