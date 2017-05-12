package controller;

import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import businessLogic.LoginLogic;
import persistence.Instructor;
import persistence.Student;

/**
 * Managed Bean Controller type to interact with login logic
 * @author animesh jain
 *
 */
@ManagedBean
public class LoginController {

	public Student student= new Student();

public Instructor instructor= new Instructor();
 public String password;
 public String type;
 /**
  * To verify login and redirect
 * @return The next redirection page
 */
public String login(){
	 
	 System.out.println("Login Module started");
	 
	 if(type.equals("student")&&LoginLogic.isValid(student.getStudentId(), password))
	 {
		 Cookie userCookie = new Cookie("user", student.getStudentId());
		 Cookie typeCookie = new Cookie("type", "student");
		HttpServletResponse response=  (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		 response.addCookie(userCookie);
		 response.addCookie(typeCookie);
		 return "/Student.xhtml?faces-redirect=true";
	 }
	 else if(LoginLogic.isValid(instructor.getId(), password)){
		 Cookie userCookie = new Cookie("user", instructor.getId());
		 Cookie typeCookie = new Cookie("type", "instructor");
		HttpServletResponse response=  (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		 response.addCookie(userCookie);
		 response.addCookie(typeCookie);
		 return "/Instructor.xhtml?faces-redirect=true";
	 }
	 else
		 return "Login";
 }
 
 
 /**
 * @return type of user
 */
public String getType() {
	return type;
}


/**
 * sets type of user
 * @param type of user
 */
public void setType(String type) {
	this.type = type;
}


/**
 * @return Student for the current login
 */
public Student getStudent() {
	return student;
}
public void setStudent(Student student) {
	this.student = student;
}
/**
 * @return Instructor for the current login
 */
public Instructor getInstructor() {
	return instructor;
}
/**
 * @param instructor
 */
public void setInstructor(Instructor instructor) {
	this.instructor = instructor;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
 
 



 
}
