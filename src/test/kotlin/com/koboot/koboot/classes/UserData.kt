package com.koboot.koboot.classes

class UserData(val id: Long = 0, val name: String, val email: String, val age: Int) {

    override fun toString(): String {
        return "UserData(name='$name', email='$email', age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserData

        if (name != other.name) return false
        if (email != other.email) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + age
        return result
    }
}