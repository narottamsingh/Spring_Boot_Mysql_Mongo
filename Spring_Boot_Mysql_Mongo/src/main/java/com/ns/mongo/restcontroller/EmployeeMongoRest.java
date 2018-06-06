package com.ns.mongo.restcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ns.mongo.entity.Employee;
import com.ns.mongo.repository.EmployeeRepository;

@RestController
@RequestMapping("/mongo")
public class EmployeeMongoRest {

	@Autowired
	private EmployeeRepository mongoEmployeeRepository;
	
	@RequestMapping(value="/fetch")
	public ResponseEntity<List<Employee>> findAllDB1Emp()  {
		List<Employee> db1Emp = mongoEmployeeRepository.findAll();
		if (db1Emp == null) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<List<Employee>>(db1Emp, HttpStatus.OK);
	}
	
	@PostConstruct
	private void init() throws IOException {
		List<Employee> employeesL = new ArrayList<Employee>();
		ClassPathResource cpr = new ClassPathResource("/data.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(cpr.getInputStream()));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.getProperty("line.separator"));
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    String lines[] = everything.split("\\r?\\n");
		    for (String data : lines) {
		    	String[] dataS = data.split(":");
		    	if (dataS.length >0) {
		    		Employee dbEmployee = new Employee();
		    		dbEmployee.setId(dataS[0]);
		    		dbEmployee.setEmpName(dataS[1]);
		    		dbEmployee.setEmpId(dataS[2]);
		    		dbEmployee.setStatus(true);
		    		employeesL.add(dbEmployee);
				}
			}
		} finally {
		    br.close();
		}
		mongoEmployeeRepository.saveAll(employeesL);
	}
}
