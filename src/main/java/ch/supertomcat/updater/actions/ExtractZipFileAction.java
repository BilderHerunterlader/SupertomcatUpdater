package ch.supertomcat.updater.actions;

import java.nio.file.Path;
import java.nio.file.Paths;

import ch.supertomcat.supertomcatutils.io.ZipUtil;
import ch.supertomcat.updaterxml.update.xml.ExtractZipFileActionDefinition;

/**
 * Extract ZIP File Action
 */
public class ExtractZipFileAction extends UpdateActionBase<ExtractZipFileActionDefinition> {

	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public ExtractZipFileAction(ExtractZipFileActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		Path zipFile = Paths.get(definition.getFile());
		Path targetDirectory = Paths.get(definition.getTargetDirectory());
		ZipUtil.extractZipFile(zipFile, targetDirectory);
	}
}
