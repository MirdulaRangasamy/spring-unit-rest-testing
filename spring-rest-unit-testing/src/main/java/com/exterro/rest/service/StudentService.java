package com.exterro.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exterro.rest.exception.NoSuchStudentException;
import com.exterro.rest.model.Student;
@Service
public interface StudentService {
	Student addStudent(Student student);
	Student getStudent(int rollNo)throws NoSuchStudentException;
	List<Student> getAllStudents();
	Student updateStudent(Student student)throws NoSuchStudentException;
	String deleteStudent(int rollNo)throws NoSuchStudentException;
}
