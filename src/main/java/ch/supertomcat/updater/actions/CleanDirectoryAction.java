package ch.supertomcat.updater.actions;

import java.nio.file.Path;
import java.nio.file.Paths;

import ch.supertomcat.bh.update.xml.CleanDirectoryActionDefinition;
import ch.supertomcat.supertomcatutils.io.DirectoryUtil;

/**
 * Clean Directory Action
 */
public class CleanDirectoryAction extends UpdateActionBase<CleanDirectoryActionDefinition> {
	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public CleanDirectoryAction(CleanDirectoryActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		// TODO Logging
		Path directory = Paths.get(definition.getDirectory());
		DirectoryUtil.clearDirectoryRecursive(directory);
	}
}
