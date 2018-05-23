package com.ns.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ns.db1.entity.DB1Employee;

@Repository
public interface DB1EmployeeRepository extends JpaRepository<DB1Employee, Long> {

}
