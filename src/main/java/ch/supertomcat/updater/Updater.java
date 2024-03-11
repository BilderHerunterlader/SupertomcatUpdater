package ch.supertomcat.updater;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.supertomcat.supertomcatutils.application.ApplicationMain;
import ch.supertomcat.supertomcatutils.application.ApplicationProperties;
import ch.supertomcat.updater.selfupdate.SelfUpdate;

/**
 * Class which contains the main-Method
 */
public final class Updater {

	/**
	 * Main-Method
	 * 
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		ApplicationMain applicationMain = new ApplicationMain("SupertomcatUpdater", null, true, false, Updater.class) {
			@Override
			protected void main(String[] args) {
				Logger logger = LoggerFactory.getLogger(Updater.class);
				logger.info("Test");

				Options options = createCommandLineOptions();
				CommandLineParser parser = new DefaultParser();
				CommandLine cmd;
				try {
					cmd = parser.parse(options, args);
				} catch (ParseException e) {
					logger.error("Could not parse command line arguments", e);
					printHelp(options);
					System.exit(1);
					return;
				}

				if (cmd.getOptions().length == 0) {
					logger.error("No arguments provided");
					printHelp(options);
					System.exit(1);
					return;
				}

				if (cmd.hasOption("selfUpdate")) {
					String targetFolderPath = cmd.getOptionValue("selfUpdate");
					logger.info("Self Update to: {}", targetFolderPath);
					int exitCode = selfUpdate(targetFolderPath);
					System.exit(exitCode);
					return;
				}

				if (cmd.hasOption("update")) {
					String xmlFilePath = cmd.getOptionValue("update");
					logger.info("Update using XML-File: {}", xmlFilePath);
					int exitCode = update(xmlFilePath);
					System.exit(exitCode);
					return;
				}

				if (cmd.hasOption("help")) {
					printHelp(options);
					System.exit(0);
					return;
				}

				logger.error("Unexpected state: No supported arguments detected");
				printHelp(options);
				System.exit(1);
			}
		};
		applicationMain.start(args);
	}

	/**
	 * Self Update
	 * 
	 * @param targetFolderPath Target Folder Path
	 * @return Exit Code
	 */
	private static int selfUpdate(String targetFolderPath) {
		Logger logger = LoggerFactory.getLogger(Updater.class);
		try {
			// String programData = System.getenv("ProgramData");
			// Path programDataFolder = Paths.get(programData, "BilderHerunterlader");
			// Files.createDirectories(programDataFolder);
			//
			String programFilesFolder = System.getenv("ProgramFiles(x86)");
			Path programFolder = Paths.get(programFilesFolder, "BilderHerunterladerTest");
			SelfUpdate selfUpdate = new SelfUpdate(programFolder);
			selfUpdate.execute();
		} catch (Exception e) {
			logger.error("TestError", e);
			System.exit(1);
		}

		return 0;
	}

	/**
	 * Update
	 * 
	 * @param xmlFilePath XML File Path
	 * @return Exit Code
	 */
	private static int update(String xmlFilePath) {
		Logger logger = LoggerFactory.getLogger(Updater.class);
		// TODO Read XML File with JAXB
		// TODO Create Wrapper Objects for all actions
		// TODO Execute Actions
		return 0;
	}

	/**
	 * Create Command Line Options
	 * 
	 * @return Command Line Options
	 */
	private static Options createCommandLineOptions() {
		Options options = new Options();

		Option selfUpdateOption = new Option("selfUpdate", true, "Updates this updater in folder in parameter");
		options.addOption(selfUpdateOption);

		Option updateOption = new Option("update", true, "Executes update instructions provided in XML-File in parameter");
		options.addOption(updateOption);

		Option helpOption = new Option("help", false, "Show this Help");
		options.addOption(helpOption);

		return options;
	}

	/**
	 * Print Help
	 * 
	 * @param options Command Line Options
	 */
	private static void printHelp(Options options) {
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp(ApplicationProperties.getProperty("ApplicationName") + " " + ApplicationProperties.getProperty("ApplicationVersion"), options);
	}
}
