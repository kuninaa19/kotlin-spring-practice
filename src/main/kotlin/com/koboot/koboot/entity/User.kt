package com.koboot.koboot.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String = ""
    var email: String = ""
    var age: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    var company: Company? = null

    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email', age=$age, company=$company)"
    }
}