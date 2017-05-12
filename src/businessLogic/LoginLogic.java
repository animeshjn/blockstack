package businessLogic;

import persistence.LoginData;

/**
 * LoginLogic contains the business logic foR LOG IN.   
 * @author animesh jain
 */
public class LoginLogic {
	/**
	 * To check validity for the login 
	 * @param userName
	 * @param password
	 * @return true if credentials are valid
	 */
	public static boolean isValid(String userName, String password){
		LoginData.initLoginData();
		if(LoginData.credentials.containsKey(userName) && LoginData.credentials.get(userName).equals(password))
		{
			return true;
		}
		return false;
	}
	
	
}
