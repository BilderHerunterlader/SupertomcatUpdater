package ch.supertomcat.updater.actions;

import ch.supertomcat.bh.update.xml.ActionBaseDefinition;

/**
 * Base class for update actions
 * 
 * @param <T> Update Action Type
 */
public abstract class UpdateActionBase<T extends ActionBaseDefinition> implements UpdateAction {
	/**
	 * Definition
	 */
	protected final T definition;

	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public UpdateActionBase(T definition) {
		this.definition = definition;
	}
}
