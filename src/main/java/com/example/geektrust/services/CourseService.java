package com.example.geektrust.services;

import java.text.ParseException;
import java.util.List;

import com.example.geektrust.dtos.CourseDto;
import com.example.geektrust.dtos.RegistrationDto;
import com.example.geektrust.exceptions.CustomException;

public interface CourseService {
	
	public String addCourseOffering(CourseDto courseDto) throws CustomException;
	
	public String registerEmployeeForGivenCourse(RegistrationDto registrationDto) throws CustomException, ParseException;
	
	public List<String> allotCourse(String courseOfferingId) throws CustomException;
	
	public String cancelCourse(String courseRegistrationId) throws CustomException;
}
