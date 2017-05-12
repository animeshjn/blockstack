package persistence;

import java.util.ArrayList;

/**
 * module for Persistence of Instructor data 
 * @author animesh jain
 */
public class Instructor {
public String id;
public ArrayList<String> courses;
public String instructorName;

/**
 * @return id
 */
public String getId() {
	return id;
}

/**
 * @param id
 */
public void setId(String id) {
	this.id = id;
}

/**
 * @return courseList taken by instructor  
 */
public ArrayList<String> getCourses() {
	return courses;
}

public void setCourses(ArrayList<String> courses) {
	this.courses = courses;
}

/**
 * @return instructorName
 */
public String getInstructorName() {
	return instructorName;
}

/**
 * @param instructorName
 */
public void setInstructorName(String instructorName) {
	this.instructorName = instructorName;
}

}
