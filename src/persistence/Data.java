package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
/*
 * ANIMESH JAIN CLASS OF 2018
 * MS CS | VSE
 * CLASS TO PERSIST DATA USING JPA
 * */
/**
 *
 * @author animesh jain
 * Data module for the interaction with RDS
 */
public class Data {
	
	@PersistenceContext
	public static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("student");
	EntityManager entityManager;
	public static int fileId=201710;
	public static void genNextFileId(){
		//get last id from database
		// generate
		fileId++;
	}
	
	/**
	 * @return EntityManager
	 */
	public static EntityManager getEntityManager()
	{
		EntityManager entityManager = emf.createEntityManager();
		return entityManager;
	}
	public Data() {
		super();
	}
	/**
	 * Stores Student Data in DB
	 * @param student data 
	 */
	public static void storeData(Student student) {
		
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	/**
	 * Stores Data in DB
	 * @param fileBeanInstance
	 */
	public static void storeData(FileBean fileBeanInstance) {
		System.out.println("File Persistence started");
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(fileBeanInstance);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	/**
	 * Stores Data in DB
	 * @param transactionInstance
	 */
	public static void storeData(Transaction transactionInstance) {
		System.out.println("Transaction Persistence started");
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(transactionInstance);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	/**
	 * Stores Data in DB
	 * @param instructorInstance
	 */
	public static void storeData(Instructor instructorInstance) {
		System.out.println("Instructor Persistence started");
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(instructorInstance);
		entityManager.getTransaction().commit();
		entityManager.close();
	}	
}
