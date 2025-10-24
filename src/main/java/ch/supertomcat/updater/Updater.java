package ch.supertomcat.updater;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.help.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.supertomcat.supertomcatutils.application.ApplicationMain;
import ch.supertomcat.supertomcatutils.application.ApplicationProperties;
import ch.supertomcat.updater.actions.ClearDirectoryAction;
import ch.supertomcat.updater.actions.CopyDirectoryAction;
import ch.supertomcat.updater.actions.CopyFileAction;
import ch.supertomcat.updater.actions.DeleteFileAction;
import ch.supertomcat.updater.actions.ExtractZipFileAction;
import ch.supertomcat.updater.actions.SelfUpdateAction;
import ch.supertomcat.updater.actions.StartProcessAction;
import ch.supertomcat.updater.actions.UpdateActionBase;
import ch.supertomcat.updaterxml.UpdateXmlIO;
import ch.supertomcat.updaterxml.update.xml.ActionBaseDefinition;
import ch.supertomcat.updaterxml.update.xml.ClearDirectoryActionDefinition;
import ch.supertomcat.updaterxml.update.xml.CopyDirectoryActionDefinition;
import ch.supertomcat.updaterxml.update.xml.CopyFileActionDefinition;
import ch.supertomcat.updaterxml.update.xml.DeleteFileActionDefinition;
import ch.supertomcat.updaterxml.update.xml.ExtractZipFileActionDefinition;
import ch.supertomcat.updaterxml.update.xml.SelfUpdateActionDefinition;
import ch.supertomcat.updaterxml.update.xml.StartProcessActionDefinition;
import ch.supertomcat.updaterxml.update.xml.Update;

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
				logger.info("Started updater with argument: {}", Arrays.toString(args));

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
			SelfUpdateActionDefinition definition = new SelfUpdateActionDefinition();
			definition.setTargetDirectory(targetFolderPath);
			SelfUpdateAction action = new SelfUpdateAction(definition);
			action.execute();
			return 0;
		} catch (Exception e) {
			logger.error("Self Update failed", e);
			return 1;
		}
	}

	/**
	 * Update
	 * 
	 * @param xmlFilePath XML File Path
	 * @return Exit Code
	 */
	private static int update(String xmlFilePath) {
		Logger logger = LoggerFactory.getLogger(Updater.class);
		try {
			/*
			 * Wait some time to give the application, which launched the update some time to exit
			 */
			try {
				Thread.sleep(20 * 1000L);
			} catch (InterruptedException e) {
				logger.error("Sleep was interrupted", e);
			}

			Path xmlFile = Paths.get(xmlFilePath);
			UpdateXmlIO updateXmlIO = new UpdateXmlIO();
			/*
			 * For backward/forward compatibility don't validate
			 */
			Update update = updateXmlIO.readUpdate(xmlFile, false);

			List<UpdateActionBase<? extends ActionBaseDefinition>> actions = update.getActions().stream().map(x -> {
				switch (x) {
					case ClearDirectoryActionDefinition definition:
						return new ClearDirectoryAction(definition);
					case CopyDirectoryActionDefinition definition:
						return new CopyDirectoryAction(definition);
					case CopyFileActionDefinition definition:
						return new CopyFileAction(definition);
					case DeleteFileActionDefinition definition:
						return new DeleteFileAction(definition);
					case ExtractZipFileActionDefinition definition:
						return new ExtractZipFileAction(definition);
					case SelfUpdateActionDefinition definition:
						return new SelfUpdateAction(definition);
					case StartProcessActionDefinition definition:
						return new StartProcessAction(definition);
					default:
						logger.error("Unsupported type: {}", x);
						return null;
				}
			}).toList();

			for (UpdateActionBase<? extends ActionBaseDefinition> action : actions) {
				if (action == null) {
					throw new UpdaterException("Unsupported update action");
				}
				action.execute();
			}
			return 0;
		} catch (Exception e) {
			logger.error("Update failed. XMLFile: {}", xmlFilePath, e);
			return 1;
		}
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
		try {
			HelpFormatter helpFormatter = HelpFormatter.builder().get();
			helpFormatter.printHelp(ApplicationProperties.getProperty(ApplicationMain.APPLICATION_NAME) + " "
					+ ApplicationProperties.getProperty(ApplicationMain.APPLICATION_VERSION), "", options, "", false);
		} catch (IOException e) {
			Logger logger = LoggerFactory.getLogger(Updater.class);
			logger.error("Could not print help", e);
		}
	}
}
