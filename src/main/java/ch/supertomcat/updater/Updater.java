package ch.supertomcat.updater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.supertomcat.supertomcatutils.application.ApplicationMain;

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
				// TODO Implement
				exit(0);
			}
		};
		applicationMain.start(args);
	}
}
