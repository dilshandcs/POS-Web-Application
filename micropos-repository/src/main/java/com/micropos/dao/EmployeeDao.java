package com.micropos.dao;

import java.util.List;

import com.micropos.domain.Employee;

public interface EmployeeDao {
	void save(Employee employee);

	List<Employee> employeeRecords(Integer empId);

	List<Employee> getEmployeeBySale(String invNo);

}
