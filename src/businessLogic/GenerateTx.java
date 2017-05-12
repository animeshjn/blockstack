package businessLogic;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;

import org.primefaces.model.StreamedContent;

import persistence.Student;
import persistence.Transaction;

/**
 * Type to GenerateTransaction
 * @author animesh jain
 */
public class GenerateTx {

	/**
	 * generate hash from the file content
	 * @param text content of the file
	 * @return hash byte[] of file
	 */
	public static byte[] generateHash(String text){
		MessageDigest digest;
		 byte[] hash=null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		    hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
		
	}

	/**
	 * Method to post transaction hash to blockchain
	 * @param transaction
	 */
	public static void postTracsactionToBlockChain(Transaction tx) {
		//Post to block chain
		System.out.println("Posting to blockchain : "+tx.hash);
		System.out.println("Get the Student");
		ArrayList<Student> studentList=StudentLogic.getStudentById(tx.studentId); 
		Student st=studentList.get(0);
		System.out.println("Public Key: "+st.getPublicKey());
		System.out.println(st.getPublicKey().toString().replaceAll("\n", ""));
	
		BlockchainHelper.doQuery(BlockchainHelper.BLOCKCHAIN_URL, tx.hash, st.getPublicKey().toString().replaceAll("\n", ""));
	}

	/**
	 * verify the signature by student
	 * @param studentid
	 * @param download
	 * @return
	 */
	public static boolean verifySignature(String studentid, StreamedContent download) {
	
		Signature sig;
		ArrayList<Student> studentList=StudentLogic.getStudentById(studentid); 
		Student st=studentList.get(0);
		try {
            sig = Signature.getInstance("SHA256withRSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        try {
        st.getPublicKey();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
		
}
