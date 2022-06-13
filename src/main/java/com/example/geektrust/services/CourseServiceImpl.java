package com.example.geektrust.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.geektrust.database.Database;
import com.example.geektrust.dtos.CourseDto;
import com.example.geektrust.dtos.RegistrationDto;
import com.example.geektrust.entities.CourseEntity;
import com.example.geektrust.entities.RegistrationEntity;
import com.example.geektrust.exceptions.CustomException;
import com.example.geektrust.utils.Constants;

public class CourseServiceImpl implements CourseService{

	@Override
	public String addCourseOffering(CourseDto courseDto) throws CustomException {
		try {
			CourseEntity courseEntity = new CourseEntity(courseDto);
			Database.addCourse(courseEntity);
			return courseEntity.getCourseOfferingId();
		} catch(ParseException parseException) {
			throw new CustomException(Constants.MESSAGE_INPUT_DATA_ERROR);
		}
	}

	@Override
	public String registerEmployeeForGivenCourse(RegistrationDto registrationDto) throws CustomException, ParseException {
		try {
			String courseOfferingId = registrationDto.getCourseOfferingId();
			CourseEntity courseEntity = Database.findCourseByCourseOfferingId(courseOfferingId);
			Set<RegistrationEntity> registrationEntities = Database.findRegistrationsByCourseOfferingId(courseOfferingId);
			if(registrationEntities.size() < courseEntity.getMinNoOfEmployees()) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH_DELIMETER);
				String today = now.format(formatter);
				Date todaysDate = new SimpleDateFormat(Constants.DATE_FORMAT_DD_MM_YYYY_WITH_SLASH_DELIMETER).parse(today);
				Date lastDate = courseEntity.getDate();
				if(todaysDate.compareTo(lastDate) > 0)
					throw new CustomException(Constants.COURSE_STATUS_CANCELED);
			}
			if(registrationEntities.size() == courseEntity.getMaxNoOfEmployees())
				throw new CustomException(Constants.MESSAGE_COURSE_FULL_ERROR);
			String courseName = courseEntity.getCourseName();
			String employeeName = registrationDto.getEmailId().split("@")[0];
			RegistrationEntity registrationEntity = new RegistrationEntity(registrationDto);
			registrationEntity.setCourseRegistrationId("REG-COURSE-"+employeeName+"-"+courseName);
			Database.approveRegistration(registrationEntity);
			return registrationEntity.getCourseRegistrationId();
		} catch(CustomException exception) {
			throw new CustomException(exception.getMessage());
		} 
	}

	@Override
	public List<String> allotCourse(String courseOfferingId) throws CustomException {
		CourseEntity courseEntity = Database.findCourseByCourseOfferingId(courseOfferingId);
		if(courseEntity.getStatus().equals(Constants.COURSE_STATUS_CONFIRMED))
			throw new CustomException(Constants.MESSAGE_COURSE_ALREADY_CONFIRMED);
		
		String courseStatus = Constants.COURSE_STATUS_CONFIRMED;
		Set<RegistrationEntity> registrationEntities = Database.findRegistrationsByCourseOfferingId(courseOfferingId);
		if(registrationEntities.size() < courseEntity.getMinNoOfEmployees())
			courseStatus = Constants.COURSE_STATUS_CANCELED;
		
		List<RegistrationEntity> registrationEntitiesList = new ArrayList<>(registrationEntities);
		Collections.sort(registrationEntitiesList, new Comparator<RegistrationEntity>() {
			@Override
			public int compare(RegistrationEntity entity1, RegistrationEntity entity2) {
				return entity1.getCourseRegistrationId().compareTo(entity2.getCourseRegistrationId());
			}
		});
		
		List<String> results = new ArrayList<>();
		String result;
		String resultSuffix = String.format("%s %s %s %s %s", courseEntity.getCourseOfferingId(), 
				courseEntity.getCourseName(),courseEntity.getInstructor(), 
				new SimpleDateFormat(Constants.DATE_FORMAT_DD_MM_YYYY_WITH_NO_DELIMETER).format(courseEntity.getDate()), courseStatus);
		for(RegistrationEntity entity : registrationEntitiesList) {
			result = String.format("%s %s %s", entity.getCourseRegistrationId(), entity.getEmailId(), resultSuffix);
			results.add(result);
		}
		return results;
	}

	@Override
	public String cancelCourse(String courseRegistrationId) throws CustomException {
		RegistrationEntity registrationEntity = Database.findRegistrationByCourseRegistrationId(courseRegistrationId);
		String courseOfferingId = registrationEntity.getCourseOfferingId();
		CourseEntity courseEntity = Database.findCourseByCourseOfferingId(courseOfferingId);
		if(courseEntity.getStatus().equals(Constants.COURSE_STATUS_CONFIRMED))
			return String.format("%s %s", courseRegistrationId, Constants.REGISTRATION_CANCELLATION_REJECTED);
		
		Database.cancelRegistration(registrationEntity);
		return String.format("%s %s", courseRegistrationId, Constants.REGISTRATION_CANCELLATION_ACCEPTED);
	}
}
