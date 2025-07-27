package com.respiroc.company.domain.model

import com.respiroc.util.domain.address.Address
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "employees")
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @Column(name = "employee_number", unique = true)
    var employeeNumber: String? = null

    @Column(name = "first_name", nullable = false)
    @Size(max = 255)
    lateinit var firstName: String

    @Column(name = "last_name", nullable = false)
    @Size(max = 255)
    lateinit var lastName: String

    @Email
    @Column(name = "email")
    @Size(max = 255)
    var email: String? = null

    @Column(name = "phone_work")
    @Size(max = 20)
    var phoneWork: String? = null

    @Column(name = "phone_mobile")
    @Size(max = 20)
    var phoneMobile: String? = null

    @Column(name = "phone_home")
    @Size(max = 20)
    var phoneHome: String? = null

    @Column(name = "phone_country_code")
    @Size(max = 5)
    var phoneCountryCode: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null

    @Column(name = "employment_date", nullable = false)
    lateinit var employmentDate: LocalDate

    @Column(name = "employee_category")
    @Size(max = 50)
    var employeeCategory: String? = null

    @Column(name = "full_time_percentage")
    @Min(0)
    @Max(100)
    var fullTimePercentage: Int = 100

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    lateinit var company: Company

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    lateinit var createdAt: Instant

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    lateinit var updatedAt: Instant
}