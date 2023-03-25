package it.lospoto.cli.actions.action;

import it.lospoto.cli.actions.Action;
import it.lospoto.model.Controller;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

/**
 * Action to generate a CV with the following characteristics:
 *  - language = it
 *  - cv-type = short
 *  - privacy = yes
 */

public class ActionGenerateCvItalianShortWithPrivacy implements Action {
    /* The logger */
    private Logger logger;

    /* The controller */
    private Controller controller;

    public ActionGenerateCvItalianShortWithPrivacy() {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(ActionGenerateCvItalianShortWithPrivacy.class.getName());
        this.logger.finer("Log for ActionGenerateCvItalianShortWithPrivacy class successfully created");

        /* Create the controller */
        this.controller = Controller.getInstance();
        this.logger.finer("Controller successfully created");
    }

    /**
     * Execute this action
     */
    @Override
    public void execute() {
        /* Ask the controller to generate the CV */
        this.controller.generateCvItalianShortWithPrivacy();
        this.logger.finer("Demanded the CV generation to the controller");
    }
}
