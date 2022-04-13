package com.koboot.koboot.intercepter


import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import com.koboot.koboot.errorHandler.CustomException
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class PermissionInterceptor : HandlerInterceptor {
    private fun getFirebaseAuthFromHeader(authorizationHeader: String): FirebaseToken {
        try {
            val idToken = authorizationHeader.substring(7)
            return FirebaseAuth.getInstance().verifyIdToken(idToken)
        } catch (e: FirebaseException) {
            log.error("AUTH-001 : firebaseAuth Unauthorized")
            throw CustomException(HttpStatus.UNAUTHORIZED, "AUTH-001", "Unauthorized Token")
        }
    }

    @Throws(IOException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val tokenString = request.getHeader("Authorization")

        if (tokenString == null) {
            log.error("AUTH-001 : Token is not available.")
            throw CustomException(HttpStatus.UNAUTHORIZED, "AUTH-001", "Token is not available.")
        }

        val firebaseAuth = getFirebaseAuthFromHeader(request.getHeader("Authorization"))
        request.setAttribute("uid", firebaseAuth.uid);

        return true
    }

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        log.info("===== after(interceptor) =====")
    }

    @Throws(Exception::class)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        log.info("===== afterCompletion =====")
    }

    companion object {
        private val log: Logger = LogManager.getLogger(this.javaClass.name)
    }
}