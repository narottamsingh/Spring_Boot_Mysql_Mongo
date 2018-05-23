package com.ns.db2.restcontroller;

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

import com.ns.db2.entity.DB2Employee;
import com.ns.db2.repository.DB2EmployeeRepository;


@RestController
@RequestMapping("/db2")
public class EmployeeDB2Rest {

	@Autowired
	private DB2EmployeeRepository db2EmployeeRepository;
	
	@RequestMapping(value="/fetch")
	public ResponseEntity<List<DB2Employee>> findAllDB1Emp() {
		List<DB2Employee> db1Emp = db2EmployeeRepository.findAll();
		if (db1Emp == null) {
			return new ResponseEntity<List<DB2Employee>>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<List<DB2Employee>>(db1Emp, HttpStatus.OK);
	}
	
	@PostConstruct
	private void init() throws IOException {
		List<DB2Employee> db2EmployeesL = new ArrayList<DB2Employee>();
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
		    		DB2Employee db2Employee = new DB2Employee();
		    		db2EmployeesL.add(db2Employee);
				}
			}
		} finally {
		    br.close();
		}
		db2EmployeeRepository.saveAll(db2EmployeesL);
	}
}
