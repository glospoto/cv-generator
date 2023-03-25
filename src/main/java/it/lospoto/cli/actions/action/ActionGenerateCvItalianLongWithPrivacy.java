package it.lospoto.cli.actions.action;

import it.lospoto.cli.actions.Action;
import it.lospoto.model.Controller;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

/**
 * Action to generate a CV with the following characteristics:
 *  - language = it
 *  - cv-type = long
 *  - privacy = yes
 */

public class ActionGenerateCvItalianLongWithPrivacy implements Action {
    /* The logger */
    private Logger logger;

    /* The controller */
    private Controller controller;

    public ActionGenerateCvItalianLongWithPrivacy() {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(ActionGenerateCvItalianLongWithPrivacy.class.getName());
        this.logger.finer("Log for ActionGenerateCvItalianLongWithPrivacy class successfully created");

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
        this.controller.generateCvItalianLongWithPrivacy();
        this.logger.finer("Demanded the CV generation to the controller");
    }
}
