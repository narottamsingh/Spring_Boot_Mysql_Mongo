package com.ns.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ns.mongo.entity.Employee;
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
