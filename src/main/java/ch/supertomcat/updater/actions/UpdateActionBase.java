package ch.supertomcat.updater.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.supertomcat.updaterxml.update.xml.ActionBaseDefinition;

/**
 * Base class for update actions
 * 
 * @param <T> Update Action Type
 */
public abstract class UpdateActionBase<T extends ActionBaseDefinition> implements UpdateAction<T> {
	/**
	 * Logger
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

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

	@Override
	public T getDefinition() {
		return definition;
	}
}
