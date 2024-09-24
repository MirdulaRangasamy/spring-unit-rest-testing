package com.exterro.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exterro.rest.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
