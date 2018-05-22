package com.ns.mongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ns.mongo.entity.Employee;
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
