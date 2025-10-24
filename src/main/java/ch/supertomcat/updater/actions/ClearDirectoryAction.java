package ch.supertomcat.updater.actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import ch.supertomcat.supertomcatutils.io.DirectoryUtil;
import ch.supertomcat.updaterxml.update.xml.ClearDirectoryActionDefinition;

/**
 * Clear Directory Action
 */
public class ClearDirectoryAction extends UpdateActionBase<ClearDirectoryActionDefinition> {
	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public ClearDirectoryAction(ClearDirectoryActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		Path directory = Paths.get(definition.getDirectory());
		logger.info("Clear directory: {} (recursive: {})", directory, definition.isRecursive());
		if (!Files.exists(directory)) {
			logger.info("Directory does not exist: {}", directory);
			return;
		}
		if (definition.isRecursive() != null && definition.isRecursive()) {
			DirectoryUtil.clearDirectoryRecursive(directory);
		} else {
			try (Stream<Path> stream = Files.list(directory)) {
				List<Path> files = stream.filter(Files::isRegularFile).toList();
				for (Path file : files) {
					Files.deleteIfExists(file);
				}
			}
		}
	}
}
