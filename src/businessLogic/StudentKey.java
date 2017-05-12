package businessLogic;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * The StudentKey type to generate KeyPair  
 * @author animesh jain
 *
 */
public class StudentKey {

	/**
	 * to generate Key for the 
	 * @return KeyPair for Student 
	 */
	public static KeyPair generateKey(){
		
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(1024, random);
			KeyPair kp=keyGen.generateKeyPair();
			
			return kp;
		
		
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
		
			e.printStackTrace();
		}
		
		return null;
	}
	
}
