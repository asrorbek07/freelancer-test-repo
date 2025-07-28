package com.respiroc.company.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener::class)

class Employee : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    @Column(name = "full_name", nullable = false)
    @Size(max = 255)
    lateinit var fullName: String

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

    @Column(name = "phone_country_code")
    @Size(min = 1, max = 5) // E.g., "+1", "+44", "+998"
    var phoneCountryCode: String? = null

    @Column(name = "address")
    @Size(max = 200)
    var address: String? = null

    @Column(name = "employment_date", nullable = false)
    lateinit var employmentDate: LocalDate

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