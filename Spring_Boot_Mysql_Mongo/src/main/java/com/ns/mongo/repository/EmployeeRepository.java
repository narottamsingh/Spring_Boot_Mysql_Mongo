package com.ns.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ns.mongo.entity.Employee;
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
