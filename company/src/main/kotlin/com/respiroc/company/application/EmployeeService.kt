package com.respiroc.company.application

import com.respiroc.company.domain.model.Employee
import com.respiroc.company.domain.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val companyService: CompanyService
) {
    
    fun findAll(companyId: Long): List<Employee> = employeeRepository.findByCompanyId(companyId)

    fun findById(id: Long): Employee = employeeRepository.findById(id)
        .orElseThrow { NoSuchElementException("Employee not found with id: $id") }

    @Transactional
    fun create(employee: Employee): Employee {
        if (employee.employeeNumber == null) {
            employee.employeeNumber = generateEmployeeNumber()
        }
        return employeeRepository.save(employee)
    }

    @Transactional
    fun createWithCompany(companyId: Long, employee: Employee): Employee {
        val company = companyService.findById(companyId)
        employee.company = company
        if (employee.employeeNumber == null) {
            employee.employeeNumber = generateEmployeeNumber()
        }
        return employeeRepository.save(employee)
    }

    @Transactional
    fun update(id: Long, employee: Employee): Employee {
        val existing = findById(id)
        
        // Update fields while preserving immutable data
        employee.id = existing.id
        employee.employeeNumber = existing.employeeNumber
        employee.createdAt = existing.createdAt
        employee.company = existing.company

        return employeeRepository.save(employee)
    }

    @Transactional
    fun delete(id: Long) {
        employeeRepository.deleteById(id)
    }

    private fun generateEmployeeNumber(): String {
        var employeeNumber: String
        do {
            employeeNumber = String.format("%06d", Random().nextInt(999999))
        } while (employeeRepository.findByEmployeeNumber(employeeNumber) != null)
        return employeeNumber
    }
}