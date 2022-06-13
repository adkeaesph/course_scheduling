package com.example.geektrust.database;

import java.util.HashSet;
import java.util.Set;

import com.example.geektrust.entities.CourseEntity;
import com.example.geektrust.entities.RegistrationEntity;
import com.example.geektrust.exceptions.CustomException;
import com.example.geektrust.utils.Constants;

public class Database {
	
	private static Set<CourseEntity> courses = new HashSet<>();
	private static Set<RegistrationEntity> registrations = new HashSet<>();

	public static Set<CourseEntity> getCourses() {
		return courses;
	}
	
	public static Set<RegistrationEntity> getRegistrations() {
		return registrations;
	}

	public static void addCourse(CourseEntity courseEntity) {
		courses.add(courseEntity);
	}

	public static CourseEntity findCourseByCourseOfferingId(String courseOfferingId) throws CustomException {
		CourseEntity dummyCourseEntity = new CourseEntity(courseOfferingId);
		if(!courses.contains(dummyCourseEntity))
			throw new CustomException(Constants.MESSAGE_INPUT_DATA_ERROR);
		
		for(CourseEntity entity: courses) {
			if(entity.getCourseOfferingId().equals(courseOfferingId))
				return entity;
		}
				
		return null;
	}
	
	public static Set<RegistrationEntity> findRegistrationsByCourseOfferingId(String courseOfferingId) {
		Set<RegistrationEntity> registrationEntities = new HashSet<>();
		for(RegistrationEntity registration:registrations) {
			if(registration.getCourseOfferingId().equals(courseOfferingId))
				registrationEntities.add(registration);
		}
		return registrationEntities;
	}

	public static void approveRegistration(RegistrationEntity registrationEntity) {
		registrations.add(registrationEntity);
	}

	public static void confirmCourse(String courseOfferingId) throws CustomException {
		CourseEntity courseEntity = findCourseByCourseOfferingId(courseOfferingId);
		courseEntity.setStatus(Constants.COURSE_STATUS_CONFIRMED);
	}

	public static RegistrationEntity findRegistrationByCourseRegistrationId(String courseRegistrationId) throws CustomException {
		for(RegistrationEntity registration : registrations) {
			if(registration.getCourseRegistrationId().equals(courseRegistrationId)) {
				return registration;
			}
		}
		throw new CustomException(Constants.MESSAGE_INPUT_DATA_ERROR);
	}

	public static void cancelRegistration(RegistrationEntity registrationEntity) {
		registrations.remove(registrationEntity);
	}
	
}
