package com.example.geektrust.dtos;

public class CourseDto {
	private String courseName;
	private String instructor;
	private String date; //in the format ddmmyyy
	private String minNoOfEmployees;
	private String maxNoOfEmployees;
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMinNoOfEmployees() {
		return minNoOfEmployees;
	}
	public void setMinNoOfEmployees(String minNoOfEmployees) {
		this.minNoOfEmployees = minNoOfEmployees;
	}
	public String getMaxNoOfEmployees() {
		return maxNoOfEmployees;
	}
	public void setMaxNoOfEmployees(String maxNoOfEmployees) {
		this.maxNoOfEmployees = maxNoOfEmployees;
	}
	
}
