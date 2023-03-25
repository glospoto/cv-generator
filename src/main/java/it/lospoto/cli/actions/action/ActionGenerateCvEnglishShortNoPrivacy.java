package it.lospoto.cli.actions.action;

import it.lospoto.cli.actions.Action;
import it.lospoto.model.Controller;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

/**
 * Action to generate a CV with the following characteristics:
 *  - language = en
 *  - cv-type = short
 *  - privacy = no
 */

public class ActionGenerateCvEnglishShortNoPrivacy implements Action {
    /* The logger */
    private Logger logger;

    /* The controller */
    private Controller controller;

    public ActionGenerateCvEnglishShortNoPrivacy() {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(ActionGenerateCvEnglishShortNoPrivacy.class.getName());
        this.logger.finer("Log for ActionGenerateCvEnglishShortNoPrivacy class successfully created");

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
        this.controller.generateCvEnglishShortNoPrivacy();
        this.logger.finer("Demanded the CV generation to the controller");
    }
}
