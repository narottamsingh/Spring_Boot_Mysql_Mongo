package com.ns.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ns.db2.entity.DB2Employee;
@Repository
public interface DB2EmployeeRepository extends JpaRepository<DB2Employee, Long> {

}
