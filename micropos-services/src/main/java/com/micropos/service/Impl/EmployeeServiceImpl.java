package com.micropos.service.Impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micropos.dao.EmployeeDao;
import com.micropos.dao.PastSalesDao;
import com.micropos.dao.SalesDao;
import com.micropos.dao.UserDao;
import com.micropos.dao.UserPermissionDao;
import com.micropos.domain.Employee;
import com.micropos.domain.Sales;
import com.micropos.domain.SoldItems;
import com.micropos.domain.User;
import com.micropos.domain.UserPermission;
import com.micropos.dto.EmployeeDto;
import com.micropos.dto.EmployeeDtoList;
import com.micropos.dto.PastSalesReportDataDto;
import com.micropos.dto.SalesEmployeeDto;
import com.micropos.dto.UserDetailDto;
import com.micropos.email.dto.EmailMetaData;
import com.micropos.email.publisher.EventPublisher;
import com.micropos.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	UserDao userDao;

	@Autowired
	SalesDao salesDao;

	@Autowired
	PastSalesDao pastSalesDao;

	@Autowired
	UserPermissionDao userPermissionDao;

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	EventPublisher eventPublisher;

	@Value("${employee.email.template}")
	private String employeeTemplateFile;

	@Value("${employee.email.subject}")
	private String employeeEmailSubject;

	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	DateFormat ndf = new SimpleDateFormat("dd-MM-yyyy");

	@Transactional
	public String createOrUpdate(String name, String userName, String password,String email) {
		User user = userDao.readUserByName(userName);
		UserPermission userPermission =userPermissionDao.read(3);

		if(user!=null) {
			return "User already exist with username";
		}
		user=new User();
		user.setUserPermission(userPermission);
		user.setName(name);
		user.setLastModified(new Date());
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		userDao.save(user);
		return "Successs";
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List<UserDetailDto> searchUser(String name, String selectOption) {
		List<UserDetailDto> userDetailDtoList = new ArrayList<UserDetailDto>();
		List<User>  userList = userDao.getuserbyname(name, selectOption);
		if (userList != null) {

			Iterator rItr = userList.iterator();

			while (rItr.hasNext()) {
				User user = (User) rItr.next();

				final UserDetailDto dto = new UserDetailDto();
				dto.setUsername(user.getUserName());
				dto.setName(user.getName());
				dto.setCreatedPW(user.getPassword());
				dto.setUserId(user.getId().longValue());
				dto.setEmail(user.getEmail());
				userDetailDtoList.add(dto);
			}

			return userDetailDtoList;

		} else
			return null;
	}

	private EmailMetaData createEmployeeEmailMetaData(final String email, final String name, final String invNo ,final String typeTot ,
			final String emailTot ,final String total, final String date) {

		EmailMetaData metaData = new EmailMetaData();
		metaData.setToEmailAddresses(email);
		metaData.setVmFile(employeeTemplateFile);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("name", name);
		data.put("typeTot", typeTot);
		data.put("emailTot", emailTot);
		data.put("total", total);
		data.put("date", date);
		data.put("invNo", invNo);
		metaData.setData(data);

		metaData.setSubject(employeeEmailSubject);
		return metaData;
	}

	@Transactional
	public List<UserDetailDto> getAll() {
		List<UserDetailDto> userDetailDtoList = new ArrayList<UserDetailDto>();
		List<User>  userList = userDao.getAll();
		if (userList != null) {

			Iterator rItr = userList.iterator();

			while (rItr.hasNext()) {
				User user = (User) rItr.next();

				final UserDetailDto dto = new UserDetailDto();
				dto.setUsername(user.getUserName());
				dto.setName(user.getName());
				dto.setCreatedPW(user.getPassword());
				dto.setUserId(user.getId().longValue());
				dto.setEmail(user.getEmail());
				userDetailDtoList.add(dto);
			}

			return userDetailDtoList;

		} else
			return null;
	}


	@Transactional
	public void AddEmployeePortion(Integer empNo, Integer prevInv) {
		User emp=userDao.getuserbyid(empNo);
		Sales sale=salesDao.getById(prevInv);

		if(sale.getIsEmp()==false) {
			throw new RuntimeException("Already Added the Employee's Portion");
		}


		if(sale.getIsDelete()==true) {
			throw new RuntimeException("Sale has been deleted");
		}

		List<SoldItems> soldItems=pastSalesDao.getAllPastSalesHistorySaleId(prevInv);

		Boolean isType=false;
		Boolean isEmail=false;
		Double typeTot=0.0;
		Double emailTot=0.0;

		for(SoldItems soldItem:soldItems) {
			if(soldItem.getItems().getId()==1) {
				isType=true;
				typeTot+=soldItem.getTotal();
			}
				if(soldItem.getItems().getId()==8) {
				isEmail=true;
				emailTot+=soldItem.getTotal();
			}

		}

		if(isType==false && isEmail==false) {
			throw new RuntimeException("No Type Settings or Email for the sale");

		}

		if(emp.getId()==3) {
			typeTot=typeTot*0.50;

		}
		else {
			typeTot=typeTot*0.40;

		}
		emailTot=emailTot*0.1;

		Employee employee=new Employee();
		employee.setUser(emp);
		employee.setSale(sale);
		employee.setType(typeTot);
		employee.setEmail(emailTot);
		employee.setTotal(typeTot+emailTot);
		employee.setDate(new Date());
		Double total=typeTot+emailTot;

		String date=df.format(employee.getDate());

		eventPublisher.proceedEmailEvent(createEmployeeEmailMetaData(emp.getEmail(),emp.getName(), sale.getInvoiceNo(),	String.format("%.2f", typeTot)
,String.format("%.2f", emailTot),String.format("%.2f", total),date));

		employeeDao.save(employee);
		sale.setIsEmp(false);

	}

	@Transactional
	public List<EmployeeDtoList> employeeRecors(Integer empId) {
		List<Employee> employeeRecords=employeeDao.employeeRecords(empId);
		Map<String,Double> map=new HashMap<String,Double>();
		for(Employee employeeRecord:employeeRecords) {
			String date=ndf.format(employeeRecord.getDate());
			//Double init=300.0;

			Double value=map.get(date);

			if(value==null) {
				/*if(employeeRecord.getUser().getId()==3) {
					map.put(date, (employeeRecord.getTotal()));
				}
				else {*/
					map.put(date, (/*init+*/employeeRecord.getTotal()));
//				}
			}
			else {
				map.put(date, (value+employeeRecord.getTotal()));
			}

		}

		Map<String,Double> treeMap = new TreeMap<String,Double>(map);
		Map<String,List<EmployeeDto>> mapMonth=new HashMap<String, List<EmployeeDto>>();

		for(Map.Entry<String,Double> entry : treeMap.entrySet()){
			Double total=entry.getValue();
			String date=entry.getKey();
			String[] month = date.split("-", 2);
			EmployeeDto emp=new EmployeeDto();
			emp.setDate(date);
			emp.setTotal(total);


			List<EmployeeDto> employeesRecs=mapMonth.get(month[1]);
			if(employeesRecs==null) {
				employeesRecs=new ArrayList<EmployeeDto>();
				employeesRecs.add(emp);
				mapMonth.put(month[1], employeesRecs);
			}
			else {
				employeesRecs.add(emp);
				mapMonth.put(month[1], employeesRecs);
			}
		}
		Map<String, List<EmployeeDto>> mapGroupTree = new TreeMap(Collections.reverseOrder());
		mapGroupTree.putAll(mapMonth);
		List<EmployeeDtoList> output=new ArrayList<EmployeeDtoList>();
		for(Map.Entry<String,List<EmployeeDto>> entre : mapGroupTree.entrySet()){

			EmployeeDtoList temp=new EmployeeDtoList();
			temp.setMonth(entre.getKey());
			temp.setRecordList(entre.getValue());
			Double tot=0.00;
			for(EmployeeDto x:entre.getValue()) {
				tot+=x.getTotal();
			}
			temp.setTot(tot);
			output.add(temp);
			}


		return output;

	}


	@Transactional
	public List<SalesEmployeeDto> getLastTenSalesEmployee() {
		List<Sales> sales=salesDao.lastTenEmployeeRecords();
		List<SalesEmployeeDto> salesEmployeeDtos=new ArrayList<SalesEmployeeDto>();

		for(Sales sale:sales) {
			SalesEmployeeDto salesEmployeeDto=new SalesEmployeeDto();
			List<Employee> employees=employeeDao.getEmployeeBySale(sale.getInvoiceNo());
			if(employees.isEmpty()) {
				continue;
			}
			Employee employee=employees.get(0);
			salesEmployeeDto.setInvoiceNo(sale.getInvoiceNo());
			salesEmployeeDto.setEmployeeName(employee.getUser().getName());
			String date=df.format(employee.getDate());
			salesEmployeeDto.setDate(date);
			salesEmployeeDtos.add(salesEmployeeDto);

		}
		return salesEmployeeDtos;
	}

}
