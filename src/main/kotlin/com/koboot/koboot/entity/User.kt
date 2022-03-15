package com.koboot.koboot.entity

import com.koboot.koboot.intercepter.PermissionRole
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false, length = 1000)
    var name: String = "",
    @Column(length = 1000)
    var email: String = "",
    var age: Int = 0,
    @Enumerated(EnumType.STRING)
    var role : PermissionRole? = PermissionRole.PERSON,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: Company? = null
) {

    fun updateCompany(aCompany: Company) {
// 부모 엔티티에 CascadeType.All 설정되어있기때문에 부모로부터 분리시키면 유저 엔티티가 삭제된다.
        if (this.company != null) {
            this.company!!.removeUser(this)
        }
        this.company = aCompany
        aCompany.addUser(this)
    }

    fun leaveCompany() {
        this.company = null
    }

    fun updateRole(role: PermissionRole) {
        this.role = role
    }

//    override fun toString(): String {
//        return "User(id=$id, name='$name', email='$email', age=$age, company=$company)"
//    }
}