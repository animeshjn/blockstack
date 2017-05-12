package persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * The Student persistence Java Bean. Stores the data of a particular student
 *
 * @author Animesh Jain 2017
 *
 */
@NamedQueries(value = { @NamedQuery(name = "selectall", query = "select s from Student s"),
		@NamedQuery(name = "selectUserName", query = "SELECT s FROM Student s WHERE s.userName=:userName"),
		@NamedQuery(name = "selectStudentId", query = "SELECT s FROM Student s WHERE s.studentId=:studentId"),
		@NamedQuery(name = "selectPublicKey", query = "SELECT s FROM Student s WHERE s.publicKey=:publicKey")
})
@Entity
@Table
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String studentId;
	private String userName;
	private String password;
	private String publicKey;

	/**
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return studentId
	 */
	@Column
	@Id
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @return serial
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return userName
	 */
	@Column
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return Password
	 */
	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return publicKey
	 */
	@Column(columnDefinition = "LONGBLOB")
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
