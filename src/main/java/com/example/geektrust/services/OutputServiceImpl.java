package com.example.geektrust.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.example.geektrust.dtos.CourseDto;
import com.example.geektrust.dtos.RegistrationDto;
import com.example.geektrust.exceptions.CustomException;
import com.example.geektrust.utils.Constants;

public class OutputServiceImpl implements OutputService {
	
	@Override
	public List<String> produceResultFromSingleCommand(String command) {
		String[] words = command.split("\\s+");
		String operator = words[Constants.INDEX_ZERO];
		List<String> results = new ArrayList<>();
		CourseService courseService = new CourseServiceImpl();
		
		switch (operator) {
			case "ADD-COURSE-OFFERING":
				results.add(addCourseOffering(courseService, words));
				break;
			
			case "REGISTER":
				results.add(registerForCourse(courseService, words)); 
				break;
	
			case "ALLOT":
				results.addAll(allotCourse(courseService, words));
				break;
			case "CANCEL":
				results.add(cancelCourse(courseService, words));
				break;
			default:
				
		}
		return results;
	}

	private String addCourseOffering(CourseService courseService, String[] inputParameters) {
		CourseDto courseDto = new CourseDto();
		String result = "";
		int index = Constants.INDEX_ZERO;
		try {
			courseDto.setCourseName(inputParameters[++index]);
			courseDto.setInstructor(inputParameters[++index]);
			courseDto.setDate(inputParameters[++index]);
			courseDto.setMinNoOfEmployees(inputParameters[++index]);
			courseDto.setMaxNoOfEmployees(inputParameters[++index]);
			result = courseService.addCourseOffering(courseDto);
		} catch(CustomException exception) {
			result = exception.getMessage();
		} catch(IndexOutOfBoundsException exception) {
			result = Constants.MESSAGE_INPUT_DATA_ERROR;
		}
		return result;
	}
	
	private String registerForCourse(CourseService courseService, String[] inputParameters) {
		RegistrationDto registrationDto = new RegistrationDto();
		String result = "";
		int index = Constants.INDEX_ZERO;
		try {
			registrationDto.setEmailId(inputParameters[++index]);
			registrationDto.setCourseOfferingId(inputParameters[++index]);
			result = courseService.registerEmployeeForGivenCourse(registrationDto);
			result+= " ACCEPTED";
		} catch(CustomException exception) {
			result = exception.getMessage();
		} catch(IndexOutOfBoundsException | ParseException exception) {
			result = Constants.MESSAGE_INPUT_DATA_ERROR;
		}
		return result;
	}
	
	private List<String> allotCourse(CourseService courseService, String[] inputParameters) {
		List<String> results = new ArrayList<>();
		int index = Constants.INDEX_ZERO;
		try {
			String courseOfferingId = inputParameters[++index];
			results.addAll(courseService.allotCourse(courseOfferingId));
		} catch(CustomException exception) {
			results.add(exception.getMessage());
		}
		return results;
	}
	
	private String cancelCourse(CourseService courseService, String[] inputParameters) {
		String result = "";
		int index = Constants.INDEX_ZERO;
		try {
			String courseRegistrationId = inputParameters[++index];
			result = courseService.cancelCourse(courseRegistrationId);
		} catch(CustomException exception) {
			result = exception.getMessage();
		}
		return result;
	}
}
