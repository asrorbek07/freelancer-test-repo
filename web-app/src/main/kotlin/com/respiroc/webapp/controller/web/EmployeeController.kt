package com.respiroc.webapp.controller.web

import com.respiroc.company.application.EmployeeService
import com.respiroc.company.domain.model.dto.EmployeeDTO
import com.respiroc.webapp.controller.BaseController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@Controller
@RequestMapping("/company/employee")
class EmployeeController(
    private val employeeService: EmployeeService,
) : BaseController() {

    @GetMapping("")
    fun findAll(model: Model): String {
        val companyId = tenantId()
        addCommonAttributesForCurrentTenant(model, "Employees")
        val employees = employeeService.findAll(companyId)
        model.addAttribute("employees", employees)
        return "company/employee-list"
    }

    @GetMapping("/new")
    fun register(model: Model): String {
        val companyId = tenantId()
        val employeeDTO = EmployeeDTO(
            fullName = "",
            email = "",
            phoneCountryCode = "",
            phoneMobile = "",
            phoneWork = "",
            employmentDate = LocalDate.now(),
            address = "",
            fullTimePercentage = 100,
            companyId = companyId
        )
        addCommonAttributesForCurrentTenant(model, "New Employee")
        model.addAttribute("employeeDTO", employeeDTO) // Empty request for form binding
        model.addAttribute("formAction", "/company/employee") // <- Add this
        return "company/employee-form"
    }

    @GetMapping("/{id}/edit")
    fun modify(@PathVariable id: Long, model: Model): String {
        val employee = employeeService.findById(id)
        val employeeDTO = EmployeeDTO.fromEmployee(employee)

        addCommonAttributesForCurrentTenant(model, "Edit Employee")
        model.addAttribute("employeeDTO", employeeDTO)
        model.addAttribute("formAction", "/company/employee/${id}/edit")
        return "company/employee-form"
    }

    @PostMapping
    fun create(@ModelAttribute employeeDTO: EmployeeDTO): String {
        val companyId = tenantId()
        employeeService.createWithCompany(companyId, employeeDTO)
        return "redirect:/company/employee"
    }

    @PostMapping("/{id}/edit")
    fun update(@PathVariable id: Long, @ModelAttribute employeeDTO: EmployeeDTO): String {
        val existing = employeeService.findById(id)
        employeeService.update(id, employeeDTO)
        return "redirect:/company/employee"
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun delete(@PathVariable id: Long) {
        employeeService.delete(id)
    }
}
