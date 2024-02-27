package com.transformation.employeekotlin

import org.springframework.stereotype.Service

@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {
    fun postEmployee(employeeEntity: EmployeeEntity) = employeeRepository.save(employeeEntity)

    fun getEmployeeById(employeeId: Long): List<EmployeeEntity>{
        return employeeRepository.findByEmployeeId(employeeId)
    }
}