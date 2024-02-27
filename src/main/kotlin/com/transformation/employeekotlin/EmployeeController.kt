package com.transformation.employeekotlin

import com.transformation.employeekotlin.api.EmployeeApi
import com.transformation.employeekotlin.model.EmployeeData
import com.transformation.employeekotlin.model.HttpCreatedResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger{}

@Controller
class EmployeeController(private val employeeService: EmployeeService): EmployeeApi {
    // POST Employee
    override fun createEmployee(employeeData: EmployeeData?): ResponseEntity<HttpCreatedResponse> {
        employeeService.postEmployee(toEmployeeEntity(employeeData))
        return ResponseEntity<HttpCreatedResponse>(HttpStatus.CREATED)
    }

    override fun getEmployeeById(employeeId: String): ResponseEntity<MutableList<EmployeeData>> { // a.
        val list: List<EmployeeEntity> = employeeService.getEmployeeById(employeeId.toLong())
        val listData = list.mapNotNull{ data: EmployeeEntity? ->
            data?.let { toEmployeeData(it) } // b.
        }.toMutableList()
        // Required MultiValueMap, List provided
        logger.info { "===========> ${listData}"}

        return ResponseEntity<MutableList<EmployeeData>>(listData, HttpStatus.OK)
    }

    fun toEmployeeEntity(employeeData: EmployeeData?): EmployeeEntity {
        val employeeEntity = EmployeeEntity( // c.
            // Elvis operator for checking nullable references of employeeData
            employeeId = employeeData?.employeeId?.toLong(), // d.
            firstname = employeeData?.firstName?.toString(),
            lastName = employeeData?.lastName?.toString()
        )

        return employeeEntity
    }

    fun toEmployeeData(employeeEntity: EmployeeEntity): EmployeeData {
        val employeeData = EmployeeData()
        employeeData.employeeId = employeeEntity.employeeId?.toInt()
        employeeData.firstName = employeeEntity.firstname
        employeeData.lastName = employeeEntity.lastName

        return employeeData
    }
}