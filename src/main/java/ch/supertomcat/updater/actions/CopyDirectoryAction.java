package ch.supertomcat.updater.actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import ch.supertomcat.supertomcatutils.io.DirectoryUtil;
import ch.supertomcat.updaterxml.update.xml.CopyDirectoryActionDefinition;

/**
 * Copy Directory Action
 */
public class CopyDirectoryAction extends UpdateActionBase<CopyDirectoryActionDefinition> {
	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public CopyDirectoryAction(CopyDirectoryActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		Path sourceDirectory = Paths.get(definition.getSourceDirectory());
		Path targetDirectory = Paths.get(definition.getTargetDirectory());
		logger.info("Copy directory {} to {} (recursive: {})", sourceDirectory, targetDirectory, definition.isRecursive());

		if (definition.isRecursive() != null && definition.isRecursive()) {
			DirectoryUtil.copyDirectoryRecursive(sourceDirectory, targetDirectory);
		} else {
			try (Stream<Path> stream = Files.list(sourceDirectory)) {
				List<Path> sourceFiles = stream.filter(Files::isRegularFile).toList();
				for (Path sourceFile : sourceFiles) {
					Path currentDirectory = sourceFile.toAbsolutePath().getParent();
					logger.info("Create directory if not exsists: {}", currentDirectory);
					Files.createDirectories(currentDirectory);

					Path targetFile = targetDirectory.resolve(sourceFile.getFileName().toString());
					logger.info("Copy file from '{}' to '{}'", sourceFile, targetFile);
					Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}
}
