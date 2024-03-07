package ch.supertomcat.updater.selfupdate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.supertomcat.supertomcatutils.application.ApplicationProperties;
import ch.supertomcat.supertomcatutils.io.DirectoryUtil;

/**
 * Self Update
 */
public class SelfUpdate {
	/**
	 * Logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Target Folder
	 */
	private final Path targetFolder;

	/**
	 * Constructor
	 * 
	 * @param targetFolder Target Folder
	 */
	public SelfUpdate(Path targetFolder) {
		this.targetFolder = targetFolder;
	}

	/**
	 * Execute
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		String updaterApplicationPath = ApplicationProperties.getProperty("ApplicationPath");
		Path updaterApplicationFolder = Paths.get(updaterApplicationPath);
		logger.info("Self update from '{}' to '{}'", updaterApplicationFolder, targetFolder);

		Path exeFile = updaterApplicationFolder.resolve("SupertomcatUpdater.exe");
		Path targetExeFile = targetFolder.resolve("SupertomcatUpdater.exe");
		logger.info("Copy '{}' to '{}'", exeFile, targetExeFile);
		Files.copy(exeFile, targetExeFile, StandardCopyOption.REPLACE_EXISTING);

		Path jarFile = updaterApplicationFolder.resolve("SupertomcatUpdater.jar");
		Path targetJarFile = targetFolder.resolve("SupertomcatUpdater.jar");
		logger.info("Copy '{}' to '{}'", jarFile, targetJarFile);
		Files.copy(jarFile, targetJarFile, StandardCopyOption.REPLACE_EXISTING);

		Path libFolder = updaterApplicationFolder.resolve("lib");
		Path targetLibFolder = targetFolder.resolve("lib");
		logger.info("Clean Directory: {}", targetLibFolder);
		DirectoryUtil.clearDirectoryRecursive(targetLibFolder);
		logger.info("Copy '{}' to '{}'", libFolder, targetLibFolder);
		DirectoryUtil.copyDirectoryRecursive(libFolder, targetLibFolder);
	}
}
