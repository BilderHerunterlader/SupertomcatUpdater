package ch.supertomcat.updater.actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ch.supertomcat.bh.update.xml.DeleteFileActionDefinition;

/**
 * Delete File Action
 */
public class DeleteFileAction extends UpdateActionBase<DeleteFileActionDefinition> {
	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public DeleteFileAction(DeleteFileActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		// TODO Logging

		Path fileToDelete = Paths.get(definition.getFile());
		Files.delete(fileToDelete);
		// TODO Implement
	}
}
