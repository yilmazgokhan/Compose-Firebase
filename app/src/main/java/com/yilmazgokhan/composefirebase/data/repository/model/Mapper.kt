package com.yilmazgokhan.composefirebase.data.repository.model

import com.yilmazgokhan.composefirebase.data.datasource.entity.UserDTO

fun UserDTO.mapModel(): User {
    return User(
        username = username,
        name = name,
        phone = phone,
        mail = mail,
        address = address,
        gender = gender
    )
}