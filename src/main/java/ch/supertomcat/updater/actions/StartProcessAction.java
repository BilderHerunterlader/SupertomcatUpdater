package ch.supertomcat.updater.actions;

import java.io.File;

import ch.supertomcat.updaterxml.update.xml.StartProcessActionDefinition;

/**
 * Start Process Action
 */
public class StartProcessAction extends UpdateActionBase<StartProcessActionDefinition> {
	/**
	 * Constructor
	 * 
	 * @param definition Definition
	 */
	public StartProcessAction(StartProcessActionDefinition definition) {
		super(definition);
	}

	@Override
	public void execute() throws Exception {
		logger.info("Start process: WorkingDirectory: {}, Command: {}", definition.getWorkingDirectory(), definition.getCommand());
		ProcessBuilder processBuilder = new ProcessBuilder(definition.getCommand()).inheritIO();
		if (definition.getWorkingDirectory() != null) {
			processBuilder.directory(new File(definition.getWorkingDirectory()));
		}
		processBuilder.start();
	}

	@Override
	public String getProgressString() {
		return "Start Process: " + String.join(" ", definition.getCommand());
	}
}
