package ch.supertomcat.updater.actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import ch.supertomcat.supertomcatutils.application.ApplicationMain;
import ch.supertomcat.supertomcatutils.application.ApplicationProperties;
import ch.supertomcat.updater.UpdaterException;
import ch.supertomcat.updaterxml.update.xml.SelfUpdateActionDefinition;

/**
 * Self Update Action
 */
public class SelfUpdateAction extends UpdateActionBase<SelfUpdateActionDefinition> {
	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public SelfUpdateAction(SelfUpdateActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		Path targetDirectory = Paths.get(definition.getTargetDirectory());

		String updaterApplicationPath = ApplicationProperties.getProperty(ApplicationMain.APPLICATION_PATH);
		Path updaterApplicationFolder = Paths.get(updaterApplicationPath).toAbsolutePath();

		if (updaterApplicationFolder.equals(targetDirectory)) {
			throw new UpdaterException("Self update failed. Can't update to same folder. UpdaterFolder: " + updaterApplicationPath + ", TargetFolder: " + targetDirectory);
		}

		logger.info("Self update from '{}' to '{}'", updaterApplicationFolder, targetDirectory);

		logger.info("Create directory if not exsists: {}", targetDirectory);
		Files.createDirectories(targetDirectory);

		Path exeFile = updaterApplicationFolder.resolve("Updater.exe");
		Path targetExeFile = targetDirectory.resolve("Updater.exe");
		logger.info("Copy '{}' to '{}'", exeFile, targetExeFile);
		Files.copy(exeFile, targetExeFile, StandardCopyOption.REPLACE_EXISTING);

		Path jarFile = updaterApplicationFolder.resolve("Updater.jar");
		Path targetJarFile = targetDirectory.resolve("Updater.jar");
		logger.info("Copy '{}' to '{}'", jarFile, targetJarFile);
		Files.copy(jarFile, targetJarFile, StandardCopyOption.REPLACE_EXISTING);
	}
}
