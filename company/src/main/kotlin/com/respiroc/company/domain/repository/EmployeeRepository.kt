package com.respiroc.company.domain.repository

import com.respiroc.company.domain.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findByCompanyId(companyId: Long): List<Employee>
}