package persistence;

import java.util.HashMap;

/**
 *Type for the persistence of LoginData
 * @author animesh jain
 */
public class LoginData {

	String userType;
	public static HashMap<String, String> credentials = new HashMap<>();
	static String[] userName = { "G00000000","G11111111", "G22222222", "G33333333", "G44444444", "12345678", "01234567" };
	static String password = "password";

	/**
	 * initialize login data
	 */
	public static void  initLoginData() {
		for (String user : userName) {
			LoginData.credentials.put(user, password);
		}
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}



}
