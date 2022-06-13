package com.example.geektrust.entities;

import java.util.Objects;

import com.example.geektrust.dtos.RegistrationDto;

public class RegistrationEntity {
	private String courseRegistrationId;
	private String emailId;
	private String courseOfferingId;
	
	public RegistrationEntity(RegistrationDto registrationDto) {
		this.emailId = registrationDto.getEmailId();
		this.courseOfferingId = registrationDto.getCourseOfferingId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseOfferingId, emailId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationEntity other = (RegistrationEntity) obj;
		return Objects.equals(courseOfferingId, other.courseOfferingId) && Objects.equals(emailId, other.emailId);
	}
	
	public String getCourseRegistrationId() {
		return courseRegistrationId;
	}
	public void setCourseRegistrationId(String courseRegistrationId) {
		this.courseRegistrationId = courseRegistrationId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCourseOfferingId() {
		return courseOfferingId;
	}
	public void setCourseOfferingId(String courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
	}
	
	
}
