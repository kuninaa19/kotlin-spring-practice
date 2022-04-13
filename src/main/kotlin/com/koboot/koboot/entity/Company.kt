package com.koboot.koboot.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String = "",
    var address: String = "",

    //    cascade : 영속성 전이 설정, 고아객체 설정
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var users: MutableList<User> = mutableListOf()
) {
    fun addUser(anUser: User) {
        anUser.company = (this)
        this.users.add(anUser)
    }

    /** Cascade 옶션으로 회사에서 직원이 분리되면서 삭제된다. */
    fun removeUser(anUser: User) {
        anUser.company = null
        this.users.remove(anUser)
    }

    override fun toString(): String {
        return "Company(id=$id, name='$name', address='$address', users=$users)"
    }

    @PrePersist
    fun prePersist(){
        println("영속성 컨텍스트 관리직전 호출")
    }
}