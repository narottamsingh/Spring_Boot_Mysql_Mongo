package com.ns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ns.entity.People;

public interface PeopleRepository extends JpaRepository<People, Long>{

}
