package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 * Transaction type to persist the transaction data
 * @author animesh jain
 *
 */
@NamedQueries(value = { @NamedQuery(name = "transactions", query = "select tx from Transaction tx"),
		@NamedQuery(name = "selecttxstudentid", query = "select tx from Transaction tx where tx.studentId = :studentId") })
@Entity
public class Transaction {

public String hash;
public String studentId;
public int txid;
/**
 * @return hash of transaction
 */
@Column(columnDefinition = "LONGBLOB")
public String getHash() {
	return hash;
}

/**
 * @param hash
 */
public void setHash(String hash) {
	this.hash = hash;
}
/**
 * @return studentId
 */
@Column
public String getStudentId() {
	return studentId;
}
/**
 * @param studentId
 */
public void setStudentId(String studentId) {
	this.studentId = studentId;
}

/**
 * @return transactionId
 */
@Id 
@GeneratedValue(strategy=GenerationType.SEQUENCE) 
public int getTxid() {
	return txid;
}

/**
 * @param transacitonId
 */
public void setTxid(int txid) {
	this.txid = txid;
}


}
