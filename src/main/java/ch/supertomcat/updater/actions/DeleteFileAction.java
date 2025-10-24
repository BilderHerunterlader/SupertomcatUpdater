package ch.supertomcat.updater.actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ch.supertomcat.updaterxml.update.xml.DeleteFileActionDefinition;

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
		Path fileToDelete = Paths.get(definition.getFile());
		logger.info("Delete file: {}", fileToDelete);
		Files.deleteIfExists(fileToDelete);
	}

	@Override
	public String getProgressString() {
		return "Delete File: " + definition.getFile();
	}
}
