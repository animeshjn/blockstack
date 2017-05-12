package businessLogic;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.primefaces.json.JSONObject;

/**
 *Type that interfaces the interaction with blockchain 
 *
 */
public class BlockchainHelper {

	/**
	 * BLOCKCHAIN_URL URL to post method 
	 * */
	public static final String BLOCKCHAIN_URL = "https://e3553fe8ebed412295f55359922a281d-vp0.us.blockchain.ibm.com:5001/chaincode";
	
	/**
	 * BLOCKCHAIN_GET URL to blockchian information 
	 * */
	public static final String BLOCKCHAIN_GET = "https://e3553fe8ebed412295f55359922a281d-vp0.us.blockchain.ibm.com:5001/chain";
	
	/**
	 * CHAINCODE_ID in IBM Bluemix 
	 * */
	public static final String CHAINCODE_ID = "3add0da3e9c029051aa2df777eebb7bb9a56940271781ff647199fb2b623ecfe8b898a4bf1bce9388ff59102df10d6edd6208ef45422eb5dcbd6b8fbf2367efd";
	
	public static String CURRENT_BLOCK;
	
	public static String PREVIOUS_BLOCK;
	
	public static Integer HEIGHT;


	/**
	 * To post transaction to blockchain 
	 * @param url to the post method 
	 * @param txHash the transaction hash 
	 * @param studID the studentid or public key
	 */
	public static void doQuery(String url, String txHash, String studID) {

		try {
			String queryString = "{"
					+ " \"jsonrpc\": \"2.0\",\"method\": \"invoke\",\"params\": {\"type\": 1, \"chaincodeID\": {\"name\": \""
					+ CHAINCODE_ID + "\"},\"ctorMsg\": {\"function\": \"postSubmittedHomework\",\"args\": [\"" + txHash
					+ "\",\"" + studID + "\"]},\"secureContext\": \"user_type1_0\"},\"id\": 1}";
			System.out.println("Query String");
			System.out.println(queryString);
			URL obj = new URL(url);
			HttpURLConnection con;
			con = (HttpURLConnection) obj.openConnection();

			// Setting basic post request
			con.setRequestMethod("POST");

			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/json");

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(queryString);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("nSending 'POST' request to URL : " + url);
			System.out.println("Post Data : " + queryString);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();

			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();

			// printing result from response
			System.out.println(response.toString());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * To post transaction to blockchain 
	 **/
	public static void doGetBlock() {
		try {
			URL url = new URL(BLOCKCHAIN_GET);
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			// Setting basic post request
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);

			int responseCode = con.getResponseCode();
			System.out.println("Sending 'GET' request to URL : " + url.toString());
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();
			System.out.println(response.toString());
			JSONObject blockChainJSON = new JSONObject(response.toString());
			CURRENT_BLOCK = (String) blockChainJSON.get("currentBlockHash");
			PREVIOUS_BLOCK = (String) blockChainJSON.get("previousBlockHash");
			HEIGHT = (Integer)blockChainJSON.get("height");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}