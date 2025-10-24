package ch.supertomcat.updater.actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import ch.supertomcat.updaterxml.update.xml.CopyFileActionDefinition;

/**
 * Copy File Action
 */
public class CopyFileAction extends UpdateActionBase<CopyFileActionDefinition> {
	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public CopyFileAction(CopyFileActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		Path sourceFile = Paths.get(definition.getSourceFile());
		Path targetDirectory = Paths.get(definition.getTargetDirectory());
		Path targetFile = targetDirectory.resolve(sourceFile.getFileName().toString());
		logger.info("Create directory if not exsists: {}", targetDirectory);
		Files.createDirectories(targetDirectory);
		logger.info("Copy file from '{}' to '{}'", sourceFile, targetFile);
		Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
	}
}
