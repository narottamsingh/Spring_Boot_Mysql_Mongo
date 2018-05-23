package com.ns.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ns.db2.entity.DB2Employee;
public interface DB2EmployeeRepository extends JpaRepository<DB2Employee, Long> {

}
