package controller;

import java.io.ByteArrayInputStream;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import businessLogic.FileLogic;
import businessLogic.GenerateTx;

import java.util.List;

import persistence.FileBean;
import persistence.Student;

/**
 * Session Scoped controller for the Instructor module
 * @author animesh jain
 *
 */
@SessionScoped
@ManagedBean
public class InstructorController {

	ArrayList<Student> st = new ArrayList<Student>();
	
	// String st="G01234567";
	StreamedContent download;

	public InstructorController() {
		super();

	}

	public boolean checkLogin() {

		return false;
	}

	/**
	 * To get list of FileBean Objects
	 * @return ArrayList<{@code}FileBean>
	 */
	public ArrayList<FileBean> getSt() {
		EntityManager em = persistence.Data.getEntityManager();
		TypedQuery<FileBean> query = em.createNamedQuery("selectallfile", FileBean.class);
		List<FileBean> results = query.getResultList();
		// ArrayList<Student> students = (ArrayList<Student>)
		// em.createQuery(criteria).getResultList();
		return (ArrayList<FileBean>) results;
	}

	/**
	 * To prepare download
	 * @param FileBean object
	 */
	public void prepDownload(FileBean s) {
		s.getFile();
		System.err.println("PREP DOWNLOAD CALLED");

		String content = FileLogic.retrieveFileContent(s);
		StreamedContent file = new DefaultStreamedContent(new ByteArrayInputStream(content.getBytes()), "text/txt",
				s.getFileName());
		download = file;
		// Crypto.verifySignature(s.getStudentid(), s.getFile());
		GenerateTx.verifySignature(s.getStudentid(), download);
	}

	/**
	 * To Download file
	 * @param FileBean 
	 * @return StreamedContent
	 */
	public StreamedContent download(FileBean s) {
		// System.out.println(s.getStudentid());
		// System.out.println(s.getFileid());
		// s.getFile();
		// String content= FileLogic.retrieveFileContent(s);
		// StreamedContent file=new DefaultStreamedContent(new
		// ByteArrayInputStream(content.getBytes()),"text/txt",s.getFileName());
		s.getFile();
		String content = FileLogic.retrieveFileContent(s);
		StreamedContent file = new DefaultStreamedContent(new ByteArrayInputStream(content.getBytes()), "text/txt",
				s.getFileName());
		download = file;
		System.err.println("CALLED");
		return download;
	}

	/**
	 *To get the current user 
	 * @return Username 
	 */
	public String getCurrentUser() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
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
}
