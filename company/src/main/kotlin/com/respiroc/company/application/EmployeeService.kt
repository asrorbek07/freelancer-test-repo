package com.respiroc.company.application

import com.respiroc.company.domain.model.Employee
import com.respiroc.company.domain.model.dto.EmployeeDTO
import com.respiroc.company.domain.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val companyService: CompanyService
) {

    fun findAll(companyId: Long): List<Employee> =
        employeeRepository.findByCompanyId(companyId)

    fun findById(id: Long): Employee =
        employeeRepository.findById(id)
            .orElseThrow { NoSuchElementException("Employee not found with id: $id") }

    @Transactional
    fun createWithCompany(companyId: Long, employeeDTO: EmployeeDTO): Employee {
        val company = companyService.findById(companyId)
        val employee = employeeDTO.toEmployee(company);
        employee.company = company

        return employeeRepository.save(employee)
    }

    @Transactional
    fun update(id: Long, employeeDTO: EmployeeDTO): Employee {
        val existing = findById(id)

        // Preserve immutable fields and update the rest
        existing.fullName = employeeDTO.fullName
        existing.email = employeeDTO.email
        existing.phoneCountryCode = employeeDTO.phoneCountryCode
        existing.phoneMobile = employeeDTO.phoneWork
        existing.phoneWork = employeeDTO.phoneWork
        existing.employmentDate= employeeDTO.employmentDate!!
        existing.address = employeeDTO.address
        existing.fullTimePercentage = employeeDTO.fullTimePercentage

        return employeeRepository.save(existing)
    }

    @Transactional
    fun delete(id: Long) {
        employeeRepository.deleteById(id)
    }
}
