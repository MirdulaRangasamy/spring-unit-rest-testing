package com.exterro.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exterro.rest.exception.NoSuchStudentException;
import com.exterro.rest.model.Student;
import com.exterro.rest.service.StudentService;

@RestController
@RequestMapping("students")
public class StudentController {
	@Autowired
	private StudentService studService;
	
	@PostMapping("addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
	//	return ResponseEntity<Student>(studService.addStudent(student));
		return new ResponseEntity<>(studService.addStudent(student), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		return ResponseEntity.ok(studService.getAllStudents());
	}
	
	@GetMapping("{rollNo}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollNo) throws NoSuchStudentException {
		return ResponseEntity.ok(studService.getStudent(rollNo));
	}
	
	@PutMapping("updateStudent")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) throws NoSuchStudentException {
		return ResponseEntity.ok(studService.updateStudent(student));
	}
	
	@DeleteMapping("deleteStudent/{rollNo}")
	public ResponseEntity<String> deleteStudent(@PathVariable int rollNo) throws NoSuchStudentException {
		return ResponseEntity.ok(studService.deleteStudent(rollNo));
	}
}
