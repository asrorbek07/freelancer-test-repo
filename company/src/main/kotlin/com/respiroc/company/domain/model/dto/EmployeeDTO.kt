package com.respiroc.company.domain.model.dto

import com.respiroc.company.domain.model.Company
import com.respiroc.company.domain.model.Employee
import java.time.LocalDate

data class EmployeeDTO(
    val fullName: String,
    val email: String?,
    val phoneCountryCode: String?,
    val phoneWork: String?,
    val phoneMobile: String?,
    val address: String?,
    val employmentDate: LocalDate?,
    val fullTimePercentage: Int = 100,
    val companyId: Long? = null
) {
    fun toEmployee(company: Company): Employee {
        return Employee().apply {
            this.fullName = this@EmployeeDTO.fullName
            this.email = this@EmployeeDTO.email
            this.phoneCountryCode = this@EmployeeDTO.phoneCountryCode
            this.phoneWork = this@EmployeeDTO.phoneWork
            this.phoneMobile = this@EmployeeDTO.phoneMobile
            this.address = this@EmployeeDTO.address
            this.employmentDate = this@EmployeeDTO.employmentDate!!
            this.fullTimePercentage = this@EmployeeDTO.fullTimePercentage
            this.company = company
        }
    }

    companion object {
        fun fromEmployee(employee: Employee): EmployeeDTO {
            return EmployeeDTO(
                fullName = employee.fullName,
                email = employee.email ?: "",
                phoneCountryCode = employee.phoneCountryCode ?: "",
                phoneMobile = employee.phoneMobile ?: "",
                phoneWork = employee.phoneWork ?: "",
                employmentDate = employee.employmentDate,
                address = employee.address ?: "",
                fullTimePercentage = employee.fullTimePercentage,
                companyId = employee.company.id
            )
        }
    }
}
