package it.lospoto.cli.actions;

import it.lospoto.cli.CLI;
import it.lospoto.util.Constant;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.util.*;

/**
 * This class models a factory to build suitable action based on the user input. It is implemented as singleton.
 */
public class FactoryAction {
    /* Singleton instance */
    private static FactoryAction instance = null;
    /* Constant representing default action package */
    private static final String ACTION_PACKAGE = "it.lospoto.cli.actions.action";
    /* Logger for this class */
    private Logger logger;
    /* Map containing the mapping between options and corresponding action to be executed */
    Map<String, String> actions;

    /**
     * Constructor. It initializes the map containig the association between options and action class name
     */
    private FactoryAction() {
        /* Build the logger for this class */
        this.logger = FactoryLogger.getInstance().createLogger(FactoryAction.class.getName());
        this.logger.fine("Log for CLI class successfully created");

        /* Convention: keys are obtained by concatenating all the possible values for each option. For example, the
        * string it.long.no describes the action of generating a CV in italian, in long version with all the sensible
        * information shown. That string is bind with a class embracing this action. */
        this.actions = new HashMap<>();
        /* Populating actions map */
        this.logger.fine("Actions map successfully created, start populating it");
        /* Combination for italian language */
        this.buildActionForItalianLanguage();
        /* Combination for english language */
        this.buildActionForEnglishLanguage();
        this.logger.fine("Actions map successfully populated");
    }

    /**
     * Helper method for building all the possible combinations for the Italian language
     */
    private void buildActionForItalianLanguage() {
        /* Entry for it.long.no */
        this.actions.put(
                String.join(".", Constant.ITALIAN, Constant.LONG, Constant.PRIVACY_OFF),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvItalianLongNoPrivacy"));
        this.logger.finest("Entry for it.long.no successfully created, start populating it");

        /* Entry for it.long.yes */
        this.actions.put(
                String.join(".", Constant.ITALIAN, Constant.LONG, Constant.PRIVACY_ON),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvItalianLongWithPrivacy"));
        this.logger.finest("Entry for it.long.yes successfully created, start populating it");

        /* Entry for it.short.no */
        this.actions.put(
                String.join(".", Constant.ITALIAN, Constant.SHORT, Constant.PRIVACY_OFF),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvItalianShortNoPrivacy"));
        this.logger.finest("Entry for it.short.no successfully created, start populating it");

        /* Entry for it.short.yes */
        this.actions.put(
                String.join(".", Constant.ITALIAN, Constant.SHORT, Constant.PRIVACY_ON),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvItalianShortWithPrivacy"));
        this.logger.finest("Entry for it.short.yes successfully created, start populating it");
    }

    /**
     * Helper method for building all the possible combinations for the English language
     */
    private void buildActionForEnglishLanguage() {
        /* Entry for it.long.no */
        this.actions.put(
                String.join(".", Constant.ENGLISH, Constant.LONG, Constant.PRIVACY_OFF),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvEnglishLongNoPrivacy"));
        this.logger.finest("Entry for en.long.no successfully created, start populating it");

        /* Entry for it.long.yes */
        this.actions.put(
                String.join(".", Constant.ENGLISH, Constant.LONG, Constant.PRIVACY_ON),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvEnglishLongWithPrivacy"));
        this.logger.finest("Entry for en.long.yes successfully created, start populating it");

        /* Entry for it.short.no */
        this.actions.put(
                String.join(".", Constant.ENGLISH, Constant.SHORT, Constant.PRIVACY_OFF),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvEnglishShortNoPrivacy"));
        this.logger.finest("Entry for it.long.no successfully created, start populating it");

        /* Entry for it.short.yes */
        this.actions.put(
                String.join(".", Constant.ENGLISH, Constant.SHORT, Constant.PRIVACY_ON),
                String.join(".", ACTION_PACKAGE, "ActionGenerateCvEnglishShortWithPrivacy"));
        this.logger.finest("Entry for en.long.yes successfully created, start populating it");
    }

    /**
     * Return a single instance of this class.
     * @return FactoryAction An instance of this class
     */
    public static FactoryAction getInstance() {
        if (instance == null)
            instance = new FactoryAction();
        return instance;
    }

    /**
     * Build an action based on the user input
     * @param options The options required by the user
     * @return Action The action to be executed
     */
    public Action build(Map<String, String> options) {
        /* The action to be returned */
        Action action = null;
        /* Creating the action key starting from the option values */
        String actionKey = String.join(".", options.values());
        this.logger.fine("Action key generated: " + actionKey);
        /* Get the action for the actions map */
        String actionClassName = this.actions.get(actionKey);
        this.logger.fine("Action class to be loaded: " + actionClassName);
        /* Get the class */
        Class actionClass = null;
        try {
            actionClass = Class.forName(actionClassName);
            this.logger.fine("Action class " + actionClassName + " ready to be instantiated");

        } catch (ClassNotFoundException e) {
            this.logger.severe("Action class not found; " + e);
            this.logger.severe("No action available to be executed; quitting...");
            System.exit(1);
        }
        /* Instantiate the action */
        try {
            action = (Action) actionClass.newInstance();
            this.logger.fine("Action " + actionClassName + " successfully instantiated");
        } catch (InstantiationException e) {
            this.logger.severe("Action class cannot be instantiated; " + e);
            this.logger.severe("No action to be instantiated; quitting...");
            System.exit(1);
        } catch (IllegalAccessException e) {
            this.logger.severe("Illegal access exception; " + e);
            this.logger.severe("Illegal access; quitting...");
            System.exit(1);
        }
        return action;
    }
}
