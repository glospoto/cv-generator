package it.lospoto.model;

import it.lospoto.model.cv.CV;
import it.lospoto.model.cv.impl.*;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

/**
 * This class implements a controller. A controller intercepts all the requests coming from the action classes, loads the
 * proper model class, and eventually requires the generation of the CV from the template. It's a singleton instance.
 */

public class Controller {
    /* Singleton instance */
    private static Controller instance = null;

    /* The logger */
    private final Logger logger;

    /**
     * Constructor
     */
    private Controller() {
        /* Build the logger */
        this.logger = FactoryLogger.getInstance().createLogger(Controller.class.getName());
        this.logger.fine("Log for Controller class successfully created");
    }

    /**
     * Create an instance of this class
     * @return Controller The instance of this class
     */
    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    /* Class interface: one method for each possible action */

    /**
     * Generate a CV with the following characteristics:
     *  - lang = it
     *  - cv-type = long
     *  - privacy = no
     */
    public void generateCvItalianLongNoPrivacy() {
        CV cv = new CvItalianLongNoPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }

    /**
     * Generate a CV with the following characteristics:
     *  - lang = it
     *  - cv-type = long
     *  - privacy = yes
     */
    public void generateCvItalianLongWithPrivacy() {
        CV cv = new CvItalianLongWithPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }

    /**
     * Generate a CV with the following characteristics:
     *  - lang = it
     *  - cv-type = short
     *  - privacy = no
     */
    public void generateCvItalianShortNoPrivacy() {
        CV cv = new CvItalianShortNoPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }

    /**
     * Generate a CV with the following characteristics:
     *  - lang = it
     *  - cv-type = short
     *  - privacy = yes
     */
    public void generateCvItalianShortWithPrivacy() {
        CV cv = new CvItalianShortWithPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }

    /**
     * Generate a CV with the following characteristics:
     *  - lang = en
     *  - cv-type = long
     *  - privacy = no
     */
    public void generateCvEnglishLongNoPrivacy() {
        CV cv = new CvEnglishLongNoPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }

    /**
     * Generate a CV with the following characteristics:
     *  - lang = en
     *  - cv-type = long
     *  - privacy = yes
     */
    public void generateCvEnglishLongWithPrivacy() {
        CV cv = new CvEnglishLongWithPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }

    /**
     * Generate a CV with the following characteristics:
     *  - lang = en
     *  - cv-type = short
     *  - privacy = no
     */
    public void generateCvEnglishShortNoPrivacy() {
        CV cv = new CvEnglishShortNoPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }

    /**
     * Generate a CV with the following characteristics:
     *  - lang = en
     *  - cv-type = short
     *  - privacy = yes
     */
    public void generateCvEnglishShortWithPrivacy() {
        CV cv = new CvEnglishShortWithPrivacy();
        this.logger.info(cv + " successfully created");
        cv.build();
        this.logger.info(cv + " successfully built");
        cv.render();
        this.logger.info(cv + " successfully rendered");
    }
}
