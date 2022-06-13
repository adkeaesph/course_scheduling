package com.example.geektrust.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.example.geektrust.dtos.CourseDto;
import com.example.geektrust.utils.Constants;

public class CourseEntity {
	private String courseOfferingId;
	private String courseName;
	private String instructor;
	private Date date;
	private int minNoOfEmployees;
	private int maxNoOfEmployees;
	private String status;
	
	public CourseEntity(String courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
		status = Constants.COURSE_STATUS_UNALLOTTED;
	}
	
	public CourseEntity(CourseDto courseDto) throws ParseException {
		courseName = courseDto.getCourseName();
		instructor = courseDto.getInstructor();
		String date = courseDto.getDate();
		String day = date.substring(0, 2);
		String month = date.substring(2, 4);
		String year = date.substring(4);
		this.date = new SimpleDateFormat("dd/MM/yyyy").parse(day+"/"+month+"/"+year);
		minNoOfEmployees = Integer.parseInt(courseDto.getMinNoOfEmployees());
		maxNoOfEmployees = Integer.parseInt(courseDto.getMaxNoOfEmployees());
		status = Constants.COURSE_STATUS_UNALLOTTED;
		courseOfferingId = "OFFERING-"+courseName+"-"+instructor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseOfferingId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseEntity other = (CourseEntity) obj;
		return Objects.equals(courseOfferingId, other.courseOfferingId);
	}

	public String getCourseOfferingId() {
		return courseOfferingId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getMinNoOfEmployees() {
		return minNoOfEmployees;
	}
	public void setMinNoOfEmployees(int minNoOfEmployees) {
		this.minNoOfEmployees = minNoOfEmployees;
	}
	public int getMaxNoOfEmployees() {
		return maxNoOfEmployees;
	}
	public void setMaxNoOfEmployees(int maxNoOfEmployees) {
		this.maxNoOfEmployees = maxNoOfEmployees;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
