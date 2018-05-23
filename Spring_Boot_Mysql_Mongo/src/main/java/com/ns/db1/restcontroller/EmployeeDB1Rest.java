package com.ns.db1.restcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ns.db1.entity.DB1Employee;
import com.ns.db1.repository.DB1EmployeeRepository;

@RestController
@RequestMapping("/db1")
public class EmployeeDB1Rest {

	@Resource
	private DB1EmployeeRepository db1EmployeeRepository;
	
	@RequestMapping(value="/fetch")
	public ResponseEntity<List<DB1Employee>> findAllDB1Emp() {
		List<DB1Employee> db1Emp = db1EmployeeRepository.findAll();
		if (db1Emp == null) {
			return new ResponseEntity<List<DB1Employee>>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<List<DB1Employee>>(db1Emp, HttpStatus.OK);
	}

	@PostConstruct
	private void init() throws IOException {
		List<DB1Employee> db1EmployeesL = new ArrayList<DB1Employee>();
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
		    for (String agents : lines) {
		    	String[] agent = agents.split(":");
		    	if (agent.length >0) {
		    		DB1Employee db1Employee = new DB1Employee();
		    		db1EmployeesL.add(db1Employee);
				}
			}
		} finally {
		    br.close();
		}
		db1EmployeeRepository.saveAll(db1EmployeesL);
	}
	
}
