package it.lospoto.cli.actions.action;

import it.lospoto.cli.actions.Action;
import it.lospoto.model.Controller;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

/**
 * Action to generate a CV with the following characteristics:
 *  - language = it
 *  - cv-type = long
 *  - privacy = no
 */

public class ActionGenerateCvItalianLongNoPrivacy implements Action {
    /* The logger */
    private Logger logger;

    /* The controller */
    private Controller controller;

    public ActionGenerateCvItalianLongNoPrivacy() {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(ActionGenerateCvItalianLongNoPrivacy.class.getName());
        this.logger.finer("Log for ActionGenerateCvItalianLongNoPrivacy class successfully created");

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
        this.logger.finer("Demand the CV generation to the controller");
        this.controller.generateCvItalianLongNoPrivacy();
    }
}
