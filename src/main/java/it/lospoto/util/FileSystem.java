package it.lospoto.util;

import java.io.File;

/**
 * This class wrap the file system structure used by the application. It's a singleton.
 */
public class FileSystem {
    /* Singleton instance */
    private static FileSystem instance = null;

    /* Separator */
    private static final String SEPARATOR = System.getProperty("file.separator");

    /* Name of the working directory */
    private static final String WORKING_FOLDER = System.getProperty("user.dir");

    /* Name of the directory containing all the configuration files */
    private static final String CONFIG_FOLDER = "config";

    /* Log configuration file name */
    private static final String LOG_CONFIGURATION_FILE_NAME = "log.properties";
    /* Log configuration file name */
    private static final String APP_CONFIGURATION_FILE_NAME = "app.properties";

    /* Name of the directory containing all the log files */
    private static final String LOG_FOLDER = "log";

    /* Name of the directory containing the all the contents (one file for each section) */
    private static final String CONTENT_FOLDER = "content";

    /* Name of the directory containing the template */
    private static final String CV_TEMPLATE_FOLDER = "template";

    /* Name of the directory containing the section templates under template folder */
    private static final String CV_TEMPLATE_SECTION_FOLDER = "sections";

    /* Output template structure directories' names */
    /* Name of the root directory containing the rendered template */
    private static final String TEMPLATE_RESULT_FOLDER = "result";
    /* Name of the include directory */
    private static final String TEMPLATE_INCLUDE_FOLDER = "include";
    /* Name of the sections directory */
    private static final String TEMPLATE_SECTIONS_FOLDER = "sections";

    /* The resource directory */
    private File resourceDir;

    /* The configuration file directory */
    private File configDir;

    /* The configuration file directory */
    private File logDir;

    /* The log configuration file */
    private File logConfigurationFile;

    /* The app configuration file */
    private File appConfigurationFile;

    /* The content directory */
    private File contentDir;

    /* The CV template directory */
    private File cvTemplateDir;

    /* The section template directory */
    private File cvTemplateSectionDir;

    /**
     * The result folder has the following structure:
     * + result
     *   + include
     *     + sections
     *       - education.tex
     *       - language.tex
     *       - privacy.tex
     *       - publication.tex
     *       - skill.tex
     *       - work.tex
     *       - project.tex
     *     - settings.tex
     *     - info.tex
     *   - main.tex
     */

    /* The rendered template directory */
    private File templateResultDir;
    /* The include directory */
    private File templateResultIncludeDir;
    /* The sections directory */
    private File templateResultIncludeSectionsDir;

    /**
     * Default constructor
     */
    private FileSystem() {
        /* Initialize resource directory */
        this.resourceDir = new File(this.getClass().getResource("/").getFile());

        /* Create file object representing the config directory */
        this.configDir = new File(this.resourceDir, CONFIG_FOLDER);
        /* Check whether config folder exists; if not, exit with error */
        if (!this.configDir.exists()) {
            System.err.println("Cannot find configuration folder: I do not know how I'm made. Quitting...");
            System.exit(1);
        }
        /* Create file object representing the log configuration file */
        this.logConfigurationFile = new File(this.configDir, LOG_CONFIGURATION_FILE_NAME);

        /* Create file object representing the app configuration file */
        this.appConfigurationFile = new File(this.configDir, APP_CONFIGURATION_FILE_NAME);

        /* Create file object representing the log directory */
        this.logDir = new File(String.join(WORKING_FOLDER, LOG_FOLDER));
        /* Check whether log folder exists; if not, create it */
        if (!this.logDir.exists())
            this.logDir.mkdir();

        /* Create file object representing the content directory */
        this.contentDir = new File(this.resourceDir, CONTENT_FOLDER);
        /* Check whether content folder exists; if not, exit with error */
        if (!this.contentDir.exists()) {
            System.err.println("Cannot find content folder: I do not know where to load contents from. Quitting...");
            System.exit(1);
        }

        /* Create file object representing the CV template directory */
        this.cvTemplateDir = new File(this.resourceDir, CV_TEMPLATE_FOLDER);
        /* Check whether config folder exists; if not, exit with error */
        if (!this.cvTemplateDir.exists()) {
            System.err.println("Cannot find CV template folder: I do not know how to generate CVs. Quitting...");
            System.exit(1);
        }

        /* Create file object representing the section template directory */
        this.cvTemplateSectionDir = new File(this.cvTemplateDir, CV_TEMPLATE_SECTION_FOLDER);
        /* Check whether config folder exists; if not, exit with error */
        if (!this.cvTemplateSectionDir.exists()) {
            System.err.println("Cannot find section template folder: I do not know how to generate CVs. Quitting...");
            System.exit(1);
        }

        /* Create file object representing the result (rendered template) directory */
        this.templateResultDir = new File(this.resourceDir, TEMPLATE_RESULT_FOLDER);
        /* Check whether result folder exists; if not, create it */
        if (!this.templateResultDir.exists())
            this.templateResultDir.mkdir();

        /* Create file object representing the include (rendered template) directory */
        this.templateResultIncludeDir = new File(this.templateResultDir, TEMPLATE_INCLUDE_FOLDER);
        /* Check whether result folder exists; if not, create it */
        if (!this.templateResultIncludeDir.exists())
            this.templateResultIncludeDir.mkdir();

        /* Create file object representing the sections (rendered template) directory */
        this.templateResultIncludeSectionsDir = new File(this.templateResultIncludeDir, TEMPLATE_SECTIONS_FOLDER);
        /* Check whether result folder exists; if not, create it */
        if (!this.templateResultIncludeSectionsDir.exists())
            this.templateResultIncludeSectionsDir.mkdir();
    }

    /**
     * Return an instance of this class
     * @return FileSystem An instance of this class
     */
    public static FileSystem getInstance() {
        if (instance == null)
            instance = new FileSystem();
        return instance;
    }

    /**
     * Return the log configuration file
     * @return File The log configuration file
     */
    public File getLogConfigurationFile() {
        return this.logConfigurationFile;
    }

    /**
     * Return the app configuration file
     * @return File The app configuration file
     */
    public File getAppConfigurationFile() {
        return this.appConfigurationFile;
    }

    /**
     * Return the content folder
     * @return File The content folder
     */
    public File getContentDir() { return this.contentDir; }

    /**
     * Return the CV template folder
     * @return File The CV template folder
     */
    public File getCvTemplateDir() { return this.cvTemplateDir; }

    /**
     * Return the section template folder
     * @return File The section template folder
     */
    public File getCvTemplateSectionDir() { return this.cvTemplateSectionDir; }

    /**
     * Return the result folder containing the rendered template
     * @return File The result folder
     */
    public File getTemplateResultDir() { return this.templateResultDir; }

    /**
     * Return the include folder containing the rendered template
     * @return File The result folder
     */
    public File getTemplateResultIncludeDir() { return this.templateResultIncludeDir; }

    /**
     * Return the sections folder containing the rendered template
     * @return File The result folder
     */
    public File getTemplateResultIncludeSectionsDir() { return this.templateResultIncludeSectionsDir; }
}
