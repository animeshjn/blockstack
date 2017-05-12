package businessLogic;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.primefaces.model.UploadedFile;
import persistence.Data;
import persistence.FileBean;
import persistence.Transaction;

/**
 * 
 * Business logic to store the file
 * @author Animesh Jain
 *
 */
public class FileLogic {
	
	/**
	 * @param String to be converted to hex
	 * @return hex representation of String
	 */
	public static String toHex(String arg) {
		return String.format("%040x", new BigInteger(1, arg.getBytes(StandardCharsets.UTF_8)));
	}
	
	/**
	 * @param hex value
	 * @return Converted string from hex
	 */
	public static String fromHex(String hex) {
	    hex = hex.replaceAll("^(00)+", "");
	    byte[] bytes = new byte[hex.length() / 2];
	    for (int i = 0; i < hex.length(); i += 2) {
	        bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
	    }
	    return new String(bytes);
	}
	
	/**
	 * To encode, compress and store file in databse
	 * @param fileName
	 * @param fileBean Object
	 * @param uploaded
	 * @param studentid
	 * @param tx 
	 */
	public static void storeFile(String fileName, FileBean fObj, UploadedFile uploaded, String studentid,Transaction tx){
		byte[] hash=null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(uploaded.getInputstream(),StandardCharsets.UTF_8));
			String readLine;
			StringBuffer fileData= new StringBuffer();
			while ((readLine = reader.readLine()) != null) {
				fileData.append(readLine);
				fileData.append("\n");
			}
			hash=GenerateTx.generateHash(fileData.toString());
			System.out.println(hash);
			//SHA256 Digest of file
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] sha256hash = sha256.digest(fileData.toString().getBytes(StandardCharsets.UTF_8));
			System.out.println(sha256hash);
			String hexValue= toHex(fileData.toString());
			tx.setHash(toHex(fileData.toString()));
			fObj.setFileName(fileName);
			fObj.setStudentid(studentid);
			fObj.setFile(hexValue.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		fObj.setStudentid(studentid);
		//tx= new Transaction();
		tx.setStudentId(studentid);
		fObj.setTransactionHash(tx.getHash());
		System.out.println("Persisting transaction: "+tx.getHash());
		Data.storeData(tx);
		Data.storeData(fObj);
		GenerateTx.postTracsactionToBlockChain(tx);
	}
	
	
/**
 * Decode the file from the file object
 * @param fileBean the object of fileBean
 * @return String content of the file bean
 */
public static String retrieveFileContent(FileBean fObj){
	
	String hex=new String(fObj.getFile());
	try {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		System.out.println(digest);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}

	String content=fromHex(hex);
	System.out.println(content);
	return content;
  	
}

}
