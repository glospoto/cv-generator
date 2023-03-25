package it.lospoto;

import it.lospoto.cli.CLI;
import it.lospoto.cli.actions.Action;
import it.lospoto.cli.actions.FactoryAction;
import it.lospoto.model.template.Template;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.util.Map;

public class CvGenerator {
    /* Log reference */
    private static final Logger logger = FactoryLogger.getInstance().createLogger(CvGenerator.class.getName());

    public static void main (String[] args) {
        logger.info("Welcome to CV Generator");
        /* The map containing the CLI options */
        Map<String, String> cliOptions;

        /* Get CLI instance and parse the input */
        logger.fine("Getting a CLI instance");
        CLI cli = CLI.getInstance();
        logger.fine("CLI instance retrieved");
        logger.fine("Start building all the CLI options");
        cli.buildOptions();
        logger.fine("CLI options successfully created");
        logger.fine("Start parsing user input");
        cliOptions = cli.parse(args);
        logger.fine("User input successfully parsed");
        logger.info("CV options: " + cliOptions);

        /* Create and execute the proper action */
        FactoryAction fa = FactoryAction.getInstance();
        Action action = fa.build(cliOptions);
        action.execute();
        logger.info("CV successfully created");

    }
}
