package com.koboot.koboot.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
    var name: String =""
    var address : String = ""

//   cascade : 영속성 전이 설정, 고아객체 설정
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "userId")
    var member : MutableList<User> = mutableListOf()

    override fun toString(): String {
        return "Company(id=$id, name='$name', address='$address', member=$member)"
    }
}