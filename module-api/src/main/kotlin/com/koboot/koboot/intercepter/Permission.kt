package com.koboot.koboot.intercepter

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class Permission(val role: Role = Role.USER) {
    enum class Role {
        USER, ADMIN
    }
}
