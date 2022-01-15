package com.koboot.koboot.services

import com.koboot.koboot.dto.CompanyReqDTO
import com.koboot.koboot.dto.CompanyResDTO
import com.koboot.koboot.entity.Company
import com.koboot.koboot.repository.CompanyRepository
import com.koboot.koboot.repository.UserRepository
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class CompanyService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val companyRepository: CompanyRepository,
    @Autowired private val modelMapper: ModelMapper
) {
    fun getCompanies(): List<CompanyResDTO> {
        val companies = companyRepository.findAll()
        if (companies.isEmpty()) {
            log.error("전체 회사가 조회가 안됩니다")
            throw Exception("에러요")
        }
        log.info("회사 정보좀 알려주세용 $companies")

        val result = companies.stream().map { modelMapper.map(it, CompanyResDTO::class.java) }.toList()
        log.info(result.toString())
        return result
    }

    fun getCompany(id: Long): CompanyResDTO {
        val aCompany = companyRepository.findById(id).orElse(null)
        if (aCompany != null) {
            log.info("회사정보좀 알려주세요 $aCompany")

            val result = modelMapper.map(aCompany, CompanyResDTO::class.java)

            log.info("회사정보 입니다 $result")
            return result
        }else{
            throw Exception("에러발생")
        }
    }

    fun createCompany(companyReqDto: CompanyReqDTO): CompanyResDTO {
        val aCompany = companyRepository.save(modelMapper.map(companyReqDto, Company::class.java))

        val result = modelMapper.map(aCompany, CompanyResDTO::class.java)
        log.info("회사가 생성됐다. $aCompany")

        return result

    }

    companion object {
        private val log: Logger = LogManager.getLogger(this.javaClass.name)
    }
}