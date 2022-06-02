package com.koboot.koboot.intercepter

import com.koboot.koboot.errorHandler.CustomException
import org.springframework.http.HttpStatus


class AuthenticationException(errorDetails: String) :
    CustomException(HttpStatus.UNAUTHORIZED, "AUTH_ERROR", errorDetails) {
}