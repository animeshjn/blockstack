package businessLogic;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import persistence.Student;

/**
 * Type for the business logic of Student
 * @author animesh jain
 *
 */
public class StudentLogic {

	public boolean uploadFile(){
		return true;
	}
	
	/**
	 * to get Student objects from database
	 * @param studentId
	 * @return List of Students 
	 */
	public static ArrayList<Student> getStudentById(String studentId){
	
		EntityManager em = persistence.Data.getEntityManager();
		 TypedQuery<Student> query =
			      em.createNamedQuery("selectStudentId", Student.class);
		 query.setParameter("studentId",studentId );
		 List<Student> results = query.getResultList();
		return (ArrayList<Student>)results;
	}
}
