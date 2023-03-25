package it.lospoto.model.template;

import it.lospoto.util.FileSystem;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.*;
import java.util.Properties;

/**
 * This class implements a template engine, by wrapping the Apache Velocity library (https://velocity.apache.org/engine/2.3/).
 * It's implemented as singleton.
 */

public class Template {
    /* Singleton instance */
    private static Template instance = null;

    /* The logger */
    private Logger logger;

    /* The velocity template */
    private org.apache.velocity.Template template = null;

    /* Current context; each time a new template must be rendered, current context is initialized with the context
    * containing all the information needed to render the template */
    private VelocityContext currentContext;

    /**
     * Constructor
     */
    private Template() {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(Template.class.getName());
        /* The file system */
        FileSystem fs = FileSystem.getInstance();
        /* The property to use when initializing Velocity. This property contains the template path */
        Properties velocityProperties = new Properties();
        velocityProperties.setProperty(
                RuntimeConstants.FILE_RESOURCE_LOADER_PATH,
                fs.getCvTemplateDir().getAbsolutePath() + "," + fs.getCvTemplateSectionDir().getAbsolutePath());
        this.logger.finer("Property " + RuntimeConstants.FILE_RESOURCE_LOADER_PATH + " successfully set to " +
                velocityProperties.getProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH));
        this.logger.finer("Start initializing Velocity");

        /* Create a basic template configuration object; for any further details, refer to the official documentation:
        https://velocity.apache.org/engine/2.3/developer-guide.html#to-singleton-or-not-to-singleton and
        https://velocity.apache.org/engine/2.3/developer-guide.html#miscellaneous-details */
        Velocity.init(velocityProperties);
        this.logger.finer("Velocity successfully initialized");
        /* The template context */
        this.currentContext = new VelocityContext();
        this.logger.finer("Template context successfully created");
    }

    /**
     * Return an instance of this class
     * @return Template An instance of this class
     */
    public static Template getInstance() {
        if (instance == null)
            instance = new Template();
        return instance;
    }

    /**
     * Load a template based on its file name
     * @param templateFilename The template's filename to load
     */
    public void loadTemplate(String templateFilename) {
        this.template = Velocity.getTemplate(templateFilename);
        this.logger.finer("Template " + templateFilename + " successfully loaded");

    }

    /**
     * Set value to the context
     * @param key The key
     * @param value The information to render
     */
    public void addToContext(String key, Object value) {
        this.currentContext.put(key, value);
        this.logger.finer("<" + key + "," + value + "> successfully added to the context");
    }

    /**
     * Store the rendered template in a file
     * @param outputFile The file in which storing the rendered template
     */
    public void render(File outputFile) {
        /* The string containing the rendered template */
        StringWriter w = new StringWriter();
        this.logger.finer("String containing the rendered template successfully created");
        /* Render the template */
        this.template.merge(this.currentContext, w);
        this.logger.finer("Template " + this.template.getName() + " rendered; going to store it");

        /* The writer */
        try {
            Writer writer = new FileWriter(outputFile);
            this.logger.finer("Writer successfully created");
            /* Writes string to the file */
            writer.write(String.valueOf(w));
            this.logger.finer("Rendered template successfully stored in: " + outputFile.getAbsolutePath());
            /* Close the writer */
            writer.close();
            this.logger.finer("Writer successfully closed");
        } catch (IOException e) {
            this.logger.severe("Problems when writing the rendered template: " + e + "Quitting...");
            System.exit(1);
        }
    }
}
