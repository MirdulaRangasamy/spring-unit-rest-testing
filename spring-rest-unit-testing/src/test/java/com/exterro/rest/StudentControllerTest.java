package com.exterro.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.exterro.rest.controller.StudentController;
import com.exterro.rest.model.Student;
import com.exterro.rest.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
	
	@Autowired private MockMvc mvc;
     
	@Autowired private ObjectMapper mapper;
	     
	@MockBean private StudentService svc;
	
	@Test
	public void getAllStudentAPI() throws Exception 
	{
		List<Student> resultList = Arrays.asList(new Student(101,"S1","IT",6.7f),new Student(102,"S2","CSE",6.9f));
		when(svc.getAllStudents()).thenReturn(resultList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students");

		MvcResult result = mvc.perform(requestBuilder).andDo(print()).andReturn();
		
		String expected = mapper.writeValueAsString(resultList);
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void createEmployeeAPI() throws Exception 
	{
		Student studentBean = new Student(888,"Keerthi","CSE",8.9f);
		
		when(svc.addStudent(studentBean)).thenReturn(studentBean);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students/addStudent")
				.accept(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(studentBean))
				.contentType(MediaType.APPLICATION_JSON);
				
		
		MvcResult result = mvc.perform(requestBuilder).andDo(print()).andReturn();
		System.out.println(result);
		
		 MockHttpServletResponse response = result.getResponse();
		 System.out.println(response);
		  assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	        assertEquals("http://localhost:8080/students/addStudent",
	                response.getHeader(HttpHeaders.LOCATION));
//		System.out.println("&&&&&&&&&&&&"+result.getResponse().getContentAsString());
//		String expected = mapper.writeValueAsString(studentBean);
//		
//		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	/*	Course mockCourse = new Course("1", "Smallest Number", "1",
                Arrays.asList("1", "2", "3", "4"));

        // studentService.addCourse to respond back with mockCourse
        Mockito.when(studentService.addCourse(Mockito.anyString(), Mockito.any(Course.class))).thenReturn(mockCourse);

        // Send course as body to /students/Student1/courses
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/students/Student1/courses")
                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/students/Student1/courses/1",
                response.getHeader(HttpHeaders.LOCATION));*/
	}
	
	@Test
	@Ignore
	public void testAddShouldReturn400BadRequest() throws Exception {
		
        Student stud = new Student(888,"Keerthi","CSE",8.9f);
        when(svc.addStudent(stud)).thenReturn(stud);
        String requestBody = mapper.writeValueAsString(stud);
 
        mvc.perform(post("/students/addStudent").contentType("application/json")
                .content(requestBody))
                .andExpect(status().is(405))
                .andDo(print())
        ;
    }
	@Test
	@Ignore
	public void testGetAll() {
		Student stud = new Student(888,"Keerthi","CSE",8.9f);

		List<Student> allEmployees = Arrays.asList(stud);

		when(svc.getAllStudents()).thenReturn(allEmployees);

//    mvc.perform(get("/api/employees")
//      .contentType(MediaType.APPLICATION_JSON))
//      .andExpect(status().isOk())
//      .andExpect(jsonPath("$", hasSize(1)))
//      .andExpect(jsonPath("$[0].name", is(alex.getName())));
	}

	private Object hasSize(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
