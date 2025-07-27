package com.respiroc.webapp.controller.web

import com.respiroc.company.application.CompanyService
import com.respiroc.company.application.EmployeeService
import com.respiroc.company.domain.model.Employee
import com.respiroc.webapp.controller.BaseController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/companies/{companyId}/employees")
class EmployeeController(private val employeeService: EmployeeService, private val companyService: CompanyService) : BaseController() {

    @GetMapping
    fun list(@PathVariable companyId: Long, model: Model): String {
        addCommonAttributesForCurrentTenant(model, "Employees")
        model.addAttribute("employees", employeeService.findAll(companyId))
        model.addAttribute("companyId", companyId)
        return "company/employees/list"
    }

    @GetMapping("/new")
    fun newEmployee(@PathVariable companyId: Long, model: Model): String {
        addCommonAttributesForCurrentTenant(model, "New Employee")
        val employee = Employee()
        employee.company = companyService.findById(companyId)
        model.addAttribute("employee", employee)
        model.addAttribute("companyId", companyId)
        return "company/employees/form"
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable companyId: Long, @PathVariable id: Long, model: Model): String {
        addCommonAttributesForCurrentTenant(model, "Edit Employee")
        val employee = employeeService.findById(id)
        model.addAttribute("employee", employee)
        model.addAttribute("companyId", companyId)
        return "company/employees/form"
    }

    @PostMapping
    fun create(@PathVariable companyId: Long, @ModelAttribute employee: Employee): String {
        employeeService.createWithCompany(companyId, employee)
        return "redirect:/companies/$companyId/employees"
    }

    @PostMapping("/{id}/delete")
    fun delete(@PathVariable companyId: Long, @PathVariable id: Long): String {
        employeeService.delete(id)
        return "redirect:/companies/$companyId/employees"
    }

    @PostMapping("/{id}/edit")
    fun update(@PathVariable companyId: Long, @PathVariable id: Long, @ModelAttribute employee: Employee): String {
        employeeService.update(id, employee)
        return "redirect:/companies/$companyId/employees"
    }
}