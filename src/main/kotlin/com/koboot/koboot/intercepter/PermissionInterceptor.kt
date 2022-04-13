package com.koboot.koboot.intercepter


import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import com.koboot.koboot.intercepter.Permission.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class PermissionInterceptor : HandlerInterceptor {
    private fun getFirebaseTokenFromAuthorizationHeader(authorizationHeader: String): FirebaseToken {
        try {
            val idToken = authorizationHeader.substring(7)
            return FirebaseAuth.getInstance().verifyIdToken(idToken)
        } catch (e: FirebaseException) {
            log.error("AUTH-100 : 파이어베이스 토큰 인증 오류 : $e")
            throw AuthenticationException("firebaseAuth Unauthorized")
        } catch (e: StringIndexOutOfBoundsException) {
            log.error("AUTH-100 :헤더에 데이터가 없어요. : $e")
            throw AuthenticationException("firebaseAuth Unauthorized")
        }
    }

    @Throws(IOException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val auth: Permission = handler.getMethodAnnotation(Permission::class.java) ?: return true

        val tokenString =
            request.getHeader("Authorization") ?: throw AuthenticationException("firebaseAuth Unauthorized")

        val firebaseToken = getFirebaseTokenFromAuthorizationHeader(tokenString)
        request.setAttribute("uid", firebaseToken.uid);

        if (auth.role == Role.USER) {
            log.info("유저 API 사용 : [ 컨트롤러 : ${handler.method.declaringClass} 메소드 : ${handler.method.name} ] \n 유저 정보 : [ uid : ${firebaseToken.uid}, 이름 : ${firebaseToken.name}, 이메일 : ${firebaseToken.email} ]")
            return true
        }

        if (auth.role == Role.ADMIN) {
            log.info("어드민 API 조회 : [ uid : ${firebaseToken.uid}, 이름 : ${firebaseToken.name}, 이메일 : ${firebaseToken.email} ]")
            return true
        }

        throw AuthenticationException("Unauthorized user")
    }

    companion object {
        private val log: Logger = LogManager.getLogger(this.javaClass.name)
    }
}