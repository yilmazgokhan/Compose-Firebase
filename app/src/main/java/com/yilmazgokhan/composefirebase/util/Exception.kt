package com.yilmazgokhan.composefirebase.util

class CommonException(message: String = "Something went wrong") : Exception(message)

class UserNotFoundException(message: String) : Exception(message)