package com.transformation.employeekotlin

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="employee")
class EmployeeEntity (
    @Id internal var employeeId: Long?,
    internal var firstname: String?,
    internal var lastName: String?
)
