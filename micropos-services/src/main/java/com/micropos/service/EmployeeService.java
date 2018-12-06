package com.micropos.service;

import java.util.List;

import com.micropos.dto.EmployeeDtoList;
import com.micropos.dto.SalesEmployeeDto;
import com.micropos.dto.UserDetailDto;

public interface EmployeeService {
	String createOrUpdate(String name, String userName,String password, String email);

	List<UserDetailDto> searchUser(String name, String selectOption);

	List<UserDetailDto> getAll();

	void AddEmployeePortion(Integer empNo,Integer prevInv);

	List<EmployeeDtoList> employeeRecors(Integer empId);

	List<SalesEmployeeDto> getLastTenSalesEmployee();



}
