package ch.supertomcat.updater.selfupdate;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.supertomcat.supertomcatutils.application.ApplicationProperties;

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

		/*
		 * TODO Logging
		 */
		Path exeFile = updaterApplicationFolder.resolve("SupertomcatUpdater.exe");
		Path targetExeFile = targetFolder.resolve("SupertomcatUpdater.exe");
		Files.copy(exeFile, targetExeFile, StandardCopyOption.REPLACE_EXISTING);

		Path jarFile = updaterApplicationFolder.resolve("SupertomcatUpdater.jar");
		Path targetJarFile = targetFolder.resolve("SupertomcatUpdater.jar");
		Files.copy(jarFile, targetJarFile, StandardCopyOption.REPLACE_EXISTING);

		Path libFolder = updaterApplicationFolder.resolve("lib");
		Path targetLibFolder = targetFolder.resolve("lib");
		/*
		 * TODO Clean directory before copy, so that old libraries are deleted
		 */
		copyFolder(libFolder, targetLibFolder);
	}

	/**
	 * Copy folder with content recursively
	 * 
	 * @param sourceFolder Source Folder
	 * @param targetFolder Target Folder
	 * @throws IOException
	 */
	private void copyFolder(final Path sourceFolder, final Path targetFolder) throws IOException {
		/*
		 * TODO Exception Handling? It seems currently exception is thrown out of this method, but it probably continues to copy And we only get one of the
		 * exceptions?. We probably need to terminate
		 * instead of FileVisitResult.CONTINUE? We probably need visitFileFailed and
		 * postVisitDirectory? Then log in them exception. When are these methods called?
		 */
		Files.walkFileTree(sourceFolder, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				Files.createDirectories(targetFolder.resolve(sourceFolder.relativize(dir).toString()));
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.copy(file, targetFolder.resolve(sourceFolder.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
