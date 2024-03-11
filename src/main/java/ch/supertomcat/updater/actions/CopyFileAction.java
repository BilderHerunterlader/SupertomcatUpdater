package ch.supertomcat.updater.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ch.supertomcat.bh.update.xml.CopyFileActionDefinition;
import ch.supertomcat.supertomcatutils.io.CopyUtil;

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
		// TODO Logging
		Path sourceFile = Paths.get(definition.getSourceFile());
		Path targetDirectory = Paths.get(definition.getTargetDirectory());
		Path targetFile = targetDirectory.resolve(sourceFile.getFileName().toString());

		// TODO Added method to CopyUtil to allow to pass Path
		try (InputStream in = Files.newInputStream(sourceFile); OutputStream out = Files.newOutputStream(targetFile)) {
			CopyUtil.copy(in, out);
		}
	}
}
