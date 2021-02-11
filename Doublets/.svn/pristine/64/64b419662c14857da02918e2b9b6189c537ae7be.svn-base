import java.util.Stack;

/**
 * ChainManager implemented by a Stack
 * 
 * @author Derek Grayless and Austin Swatek
 */
public class StackChainManager extends ChainManager {
	private Stack<Chain> chains;

	/**
	 * Initializes an empty Stack of Chains
	 */
	public StackChainManager() {
		this.chains = new Stack<Chain>();
	}

	/**
	 * Adds the given Chain to the Stack
	 * 
	 * @param chain
	 *            - Chain to be added to the stack
	 */
	@Override
	public void add(Chain chain) {
		this.chains.push(chain);
		this.updateMax(chains.size());
	}

	/**
	 * Goes to the next Chain in the stack. Essentially dequeue()
	 */
	@Override
	public Chain next() {
		if (isEmpty())
			return null;
		this.incrementNumNexts();
		return this.chains.pop();
	}

	/**
	 * Returns if the Stack is empty or not.
	 */
	@Override
	public boolean isEmpty() {
		return this.chains.size() == 0;
	}

}