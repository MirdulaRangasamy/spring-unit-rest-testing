package com.exterro.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exterro.rest.dao.StudentRepository;
import com.exterro.rest.exception.NoSuchStudentException;
import com.exterro.rest.model.Student;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public Student addStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public Student getStudent(int rollNo) throws NoSuchStudentException {
		return studentRepo.findById(rollNo).orElseThrow(()-> new NoSuchStudentException("Student not available"));
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student updateStudent(Student student) throws NoSuchStudentException {
		if(!studentRepo.existsById(student.getRollNo())) {
			throw new NoSuchStudentException("Student not available");	
		}
		return studentRepo.save(student);	
	}

	@Override
	public String deleteStudent(int rollNo) throws NoSuchStudentException {
		Student stud = studentRepo.findById(rollNo).orElseThrow(()-> new NoSuchStudentException("Student not available"));
		 studentRepo.delete(stud);
		 return "Student Deleted Successfully";
	}

}
