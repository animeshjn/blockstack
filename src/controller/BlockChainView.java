package controller;

import javax.faces.bean.ManagedBean;
import businessLogic.BlockchainHelper;

/**
 * Type to set the current view of block chain (Managed Bean)
 * @author animesh jain
 */
@ManagedBean
public class BlockChainView {
	
	String height;
	String previous;
	String current;
	/**
	 * Initializes block Chain
	 */
	public Boolean initBlockChain() {
		try{
			BlockchainHelper.doGetBlock();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * @return currentHeight height of blockchain
	 */
	public Integer getHeight() {
		initBlockChain();
		return BlockchainHelper.HEIGHT;
	}
	
	/**
	 * @return previous block in blockchain 
	 */
	public String getPrevious() {
		initBlockChain();
		return BlockchainHelper.PREVIOUS_BLOCK;
	}
	
	/**
	 * Get the current block in the block chain
	 * @return CurrentBlock
	 */
	public String getCurrent() {
		initBlockChain();
		return BlockchainHelper.CURRENT_BLOCK;
	}
}
