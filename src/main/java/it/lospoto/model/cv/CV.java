package it.lospoto.model.cv;

import it.lospoto.model.section.Section;
import it.lospoto.model.template.Template;
import it.lospoto.util.AppProperties;
import it.lospoto.util.Constant;
import it.lospoto.util.FileSystem;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class modeling a CV. Available CVs are:
 *  1. Italian, long, no privacy
 *  2. Italian, long, with privacy
 *  3. Italian, short, no privacy
 *  4. Italian, short, with privacy
 *  5. English, long, no privacy
 *  6. English, long, with privacy
 *  7. English, short, no privacy
 *  8. English, short, with privacy
 */

public abstract class CV {
    /* Constants representing template and output file name. A CV class is responsible for rendering the main template
    * file, as well as the settings file. Hence, four constants must be used: two for the main template and two for
    * the setting */

    /* The template filename */
    protected static final String TEMPLATE_MAIN_FILENAME = "main.vm";
    /* The setting filename */
    protected static final String TEMPLATE_SETTINGS_FILENAME = "settings.vm";
    /* The publications filename */
    protected static final String TEMPLATE_PUBLICATIONS_FILENAME = "publication.vm";
    /* The Makefile filename */
    protected static final String TEMPLATE_MAKEFILE_FILENAME = "Makefile.vm";

    /* The output filename, which will contain the rendered template and will be stored into result folder */
    protected static final String OUTPUT_MAIN_FILENAME = "main.tex";
    /* The settings output filename */
    protected static final String OUTPUT_SETTINGS_FILENAME = "settings.tex";
    /* The publications output filename */
    protected static final String OUTPUT_PUBLICATIONS_FILENAME = "publications.bib";
    /* The Makefile output filename */
    protected static final String OUTPUT_MAKEFILE_FILENAME = "Makefile";

    /* The logger */
    private Logger logger;

    /* The file system */
    private FileSystem fs;

    /* The template */
    private Template template;

    /* The properties loaded from the app configuration file */
    protected AppProperties props;

    /* The sections */
    protected List<Section> sections;

    /* The CV language */
    private String language;

    /**
     * Constructor
     */
    public CV(String language) {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(CV.class.getName());
        this.logger.fine("Log for CV class successfully created");

        /* Get the file system */
        this.fs = FileSystem.getInstance();

        /* Create the AppProperties object */
        this.props = new AppProperties();
        this.logger.fine("App properties successfully retrieved");

        /* Get the template */
        this.template = Template.getInstance();
        this.logger.fine("Template successfully retrieved");

        /* Set list of sections */
        this.sections = new ArrayList<Section>();
        this.logger.fine("Sections' array successfully created");

        /* Set the CV language */
        this.language = language;
        this.logger.fine("CV langague successfully set to: " + this.language);
    }

    /**
     * Build the CV
     */
    public abstract void build();

    /**
     * Render the template
     */
    public void render() {
        /* Render name, surname, and language */
        this.renderGeneralInformation();

        /* Render sections */
        this.renderSections();

        /* Render LaTeX document settings */
        this.renderSettings();

        /* Render the publications file */
        this.renderPublications();

        /* Render the Makefile */
        this.renderMakefile();

        /* Render the main file */
        this.renderMainFile();
    }

    /**
     * Private method for rendering general information, such as language, name, and surname
     */
    private void renderGeneralInformation() {
        /* Add app properties to the template */

        /* Language */
        template.addToContext(Constant.CTX_CV_LANGUAGE, this.language);
        this.logger.fine("Property " + Constant.CTX_CV_LANGUAGE + " successfully added");
        /* Name */
        template.addToContext(Constant.CTX_CV_NAME, this.props.getName());
        this.logger.fine("Property " + Constant.CTX_CV_NAME + " successfully added");
        /* Surname */
        template.addToContext(Constant.CTX_CV_SURNAME, this.props.getSurname());
        this.logger.fine("Property " + Constant.CTX_CV_SURNAME + " successfully added");
    }

    /**
     * Private method for rendering sections
     */
    private void renderSections() {
        this.logger.fine("Start rendering sections");
        /* For each section, render */
        this.sections.forEach((section) -> {
            section.render();
        });
        this.logger.fine("Sections successfully rendered");
    }

    /* Each template file must be loaded immediately before being rendered, otherwise the engine will render the
     * last loaded template (Template class is a singleton) */

    /**
     * Private method for rendering the settings file
     */
    private void renderSettings() {
        /* Render the template settings*/
        template.loadTemplate(TEMPLATE_SETTINGS_FILENAME);
        this.logger.fine("Template " + TEMPLATE_SETTINGS_FILENAME + " successfully loaded");

        /* Render the template */
        template.render(new File(this.fs.getTemplateResultIncludeDir(), OUTPUT_SETTINGS_FILENAME));
        this.logger.fine("Settings successfully rendered");
    }

    /**
     * Private method for rendering the publications file
     */
    private void renderPublications() {
        /* Render the template publications */
        template.loadTemplate(TEMPLATE_PUBLICATIONS_FILENAME);
        this.logger.fine("Template " + TEMPLATE_PUBLICATIONS_FILENAME + " successfully loaded");

        /* Render the template */
        template.render(new File(this.fs.getTemplateResultDir(), OUTPUT_PUBLICATIONS_FILENAME));
        this.logger.fine("Publications successfully rendered");
    }

    /**
     * Private method for rendering the Makefile
     */
    private void renderMakefile() {
        /* Render the template Makefile */
        template.loadTemplate(TEMPLATE_MAKEFILE_FILENAME);
        this.logger.fine("Template " + TEMPLATE_MAKEFILE_FILENAME + " successfully loaded");

        /* Render the template */
        template.render(new File(this.fs.getTemplateResultDir(), OUTPUT_MAKEFILE_FILENAME));
        this.logger.fine("Makefile successfully rendered");
    }

    /**
     * Private method for rendering the main file
     */
    private void renderMainFile() {/* Load the template */
        template.loadTemplate(TEMPLATE_MAIN_FILENAME);
        this.logger.fine("Template " + TEMPLATE_MAIN_FILENAME + " successfully loaded");

        /* Render the template */
        template.render(new File(this.fs.getTemplateResultDir(), OUTPUT_MAIN_FILENAME));
        this.logger.fine("Template successfully rendered");}
}
