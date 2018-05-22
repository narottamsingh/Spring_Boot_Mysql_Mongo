package com.ns.db1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ns.db1.entity.DB1Employee;
public interface DB1EmployeeRepository extends JpaRepository<DB1Employee, Long> {

}
