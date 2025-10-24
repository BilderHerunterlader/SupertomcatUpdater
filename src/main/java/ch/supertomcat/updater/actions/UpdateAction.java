package ch.supertomcat.updater.actions;

import ch.supertomcat.updaterxml.update.xml.ActionBaseDefinition;

/**
 * Update Action Interface
 * 
 * @param <T> Update Action Type
 */
public interface UpdateAction<T extends ActionBaseDefinition> {
	/**
	 * Execute
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception;

	/**
	 * Returns the definition
	 * 
	 * @return definition
	 */
	public T getDefinition();

	/**
	 * @return Progress String
	 */
	public String getProgressString();
}
