package it.lospoto.util;

import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class grants access to the app.properties values (name and surname)
 */

public class AppProperties {
    /* The logger */
    private Logger logger;
    /* The properties */
    private Properties properties;

    /**
     * Constructor
     */
    public AppProperties() {
        /* Get the logger */
        this.logger = FactoryLogger.getInstance().createLogger(AppProperties.class.getName());
        this.logger.finer("Log for AppProperties class successfully created");
        /* Create the properties object */
        this.properties = new Properties();
        this.logger.finer("Properties successfully created");
        /* The input stream for reading the properties */
        try {
            InputStream input = new FileInputStream(FileSystem.getInstance().getAppConfigurationFile());
            this.properties.load(input);
            this.logger.finer("App configuration file successfully loaded");
        } catch (FileNotFoundException e) {
            this.logger.severe("App configuration file not found; relevant information missing. Quitting...");
            System.exit(1);
        } catch (IOException e) {
            this.logger.severe("Problems when parsing app configuration file: " + e + "Quitting...");
            System.exit(1);
        }
    }

    /**
     * Return the name property loaded from the app configuration file
     * @return String The name
     */
    public String getName() { return this.properties.getProperty("name"); }

    /**
     * Return the surname property loaded from the app configuration file
     * @return String The surname
     */
    public String getSurname() { return this.properties.getProperty("surname"); }
}
