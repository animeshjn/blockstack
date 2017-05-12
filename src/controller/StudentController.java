package controller;
import java.io.File;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.UploadedFile;
import businessLogic.FileLogic;
import businessLogic.StudentKey;
import persistence.*;

/**
 * Controller to the student module
 * @author animesh jain
 *
 */
@ManagedBean
public class StudentController {
	FileBean fObj= new FileBean();
	Student student=new Student();
	File file;
	Transaction transaction= new Transaction();
	public FileBean getfObj() {
		return fObj;
	}
	public void setfObj(FileBean fObj) {
		this.fObj = fObj;
	}
	
	/**
	 * Upoad file
	 * @param fileUploadEvent
	 * @return redirectPage
	 */
	public String upload(org.primefaces.event.FileUploadEvent var){
		UploadedFile uploaded= var.getFile();
		String fileName= uploaded.getFileName();
		String username="";
		HttpServletRequest request=  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals("user")) {
		     username=cookie.getValue();
		    }
		  }
		}
//		FileLogic.storeFile(fileName,fObj, uploaded,username,transaction);
		try{
		student.setUserName(username);
		student.setStudentId(username);
		student.setPassword("password");
		student.setPublicKey(StudentKey.generateKey().getPublic().toString());
		Data.storeData(student);}catch (Exception e) {
		e.printStackTrace();
		}finally {
			try{FileLogic.storeFile(fileName,fObj, uploaded,username,transaction);}catch (Exception e) {
			e.printStackTrace();
			}
		}
		return "Student";
	}

	/**
	 * Get the current user 
	 * @return current user
	 */
	public String getCurrentUser(){
		HttpServletRequest request=  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals("user")) {
		     return cookie.getValue();
		    }
		  }
		}
		
		return null;
	}
	/**
	 * @return Student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Set the student object
	 * @param student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return File 
	 */
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return current transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	/**
	 * @param transaction object
	 */
	public void setTransaction(Transaction tx) {
		this.transaction = tx;
	}
	
}
