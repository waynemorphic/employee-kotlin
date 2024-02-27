package com.transformation.employeekotlin

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface EmployeeRepository: CrudRepository<EmployeeEntity, Long> {
    fun findByEmployeeId(employeeId: Long): List<EmployeeEntity>
}