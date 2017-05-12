package persistence;



import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The file bean to store the files relating to the student
 * One to Many with respect to the Student and File
 * 
 * @author Animesh Jain
 *
 */

@SessionScoped
@NamedQueries(value = { @NamedQuery(name = "selectallfile", query = "select f from FileBean f") })
@Entity
@Table
public class FileBean{

	int fileid;
	String studentid;
	byte[] file;
	String fileName;
	String fileType;
	String transactionHash;
	
	/**
	 * @return fileid
	 */
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE) 
	@Column
	public int getFileid() {
		return fileid;
	}

	/**
	 * @param fileid
	 */
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	/**
	 * @return studentId
	 */
	@Column
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentid2) {
		this.studentid = studentid2;
	}

	/**
	 * @return fileBytes
	 */
	@Column(columnDefinition="LONGBLOB")
	public byte[] getFile() {
		return file;
	}

	/**
	 * @param file
	 */
	public void setFile(byte[] file) {
		this.file = file;
	}

	/**
	 * @return fileName
	 */
	@Column
	public String getFileName() {
		return fileName;
	}

	/**
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return type of file
	 */
	@Column
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return transacctionHash
	 */
	@Column(columnDefinition="LONGBLOB")
	public String getTransactionHash() {
		return transactionHash;
	}

	/**
	 * @param transactionHash
	 */
	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}
	
}
