package it.lospoto.cli;

import it.lospoto.util.Constant;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;
import org.apache.commons.cli.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class models a CLI. It is responsible for parsing the user input and create the corresponding action which will
 * be used to generate the right template. Combinations available are:
 *  1. CV > lang = it, type = long (cv-type not set)
 *  2. CV > lang = it, type = short
 *  3. CV > lang = en, type = long (cv-type not set)
 *  4. CV > lang = en, type = short
 * By default (no options by the user), combination 1. will be served. In case only cv-type option is set, then "it"
 * language will be used by default.
 * All the combinations can be enhanced by adding another parameter (privacy) representing the ability of hiding sensible
 * information, such as phone number and born date.
 */

public class CLI {
    /* Instance representing this class */
    private static CLI instance;

    /* Constants used as primary key in the options map to encode the user input when creating the corresponding action */
    private static final String CLI_OPTION_LANGUAGE = "lang";
    private static final String CLI_OPTION_CV_TYPE = "cv-type";
    private static final String CLI_OPTION_PRIVACY = "privacy";

    /* Log for this class */
    private Logger logger;
    /* The parser */
    private CommandLineParser parser;
    /* The helper */
    private HelpFormatter helper;
    /* The options */
    private Options cliOpts;

    /**
     * Constructor
     */
    private CLI() {
        /* Build the logger for this class */
        this.logger = FactoryLogger.getInstance().createLogger(CLI.class.getName());
        this.logger.fine("Log for CLI class successfully created");
        this.logger.fine("Start creating CLI objects");
        /* The parser */
        this.parser = new DefaultParser();
        this.logger.fine("Basic parser successfully created");
        /* The helper */
        this.helper = new HelpFormatter();
        this.logger.fine("Help formatter successfully created");
        /* The options */
        this.cliOpts = new Options();
        this.logger.fine("Options list successfully created");
        this.logger.fine("CLI objects successfully created");
    }

    /**
     * Get an instance of this class
     * @return CLI A CLI instance
     */
    public static CLI getInstance() {
        if (instance == null)
            instance = new CLI();
        return instance;
    }

    /**
     * Build the CLI adding all the options
     */
    public void buildOptions() {
        /* Option language */
        Option language = Option.builder(Constant.OPT_LANGUAGE_SHORT).longOpt(Constant.OPT_LANGUAGE_LONG)
                .argName("language")
                .hasArg()
                .required(false)
                .desc("Set CV language; available values are: " +
                        Constant.ITALIAN + ", " + Constant.ENGLISH).build();
        this.cliOpts.addOption(language);
        this.logger.fine("Language option successfully created");

        /* Option CV type; by default is long; for short version (without project details) use --short */
        Option cvType = Option.builder(Constant.OPT_CV_TYPE_SHORT).longOpt(Constant.OPT_CV_TYPE_LONG)
                .required(false)
                .desc("Generate short CV version").build();
        this.cliOpts.addOption(cvType);
        this.logger.fine("CV type option successfully created");

        /* Option privacy; by default is set to NO, meaning include all the information. If enabled (YES), hide
        * phone number and born date */
        Option privacy = Option.builder(Constant.OPT_PRIVACY_SHORT).longOpt(Constant.OPT_PRIVACY_LONG)
                .required(false)
                .desc("Hide sensible information, such as phone number and born date").build();
        this.cliOpts.addOption(privacy);
        this.logger.fine("Privacy option successfully created");
    }

    /**
     * Print the helper, if needed
     */
    private void showHelp() {
        String appName = "cv-generator";
        String header = "Generate a CV choosing language and format.\n";
        String footer = "Please report issue at https://lospoto.it.";
        this.helper.printHelp(appName, header, this.cliOpts, footer, true);
    }

    /**
     * Parse the CLI options. It returns a map containing language (it or en) and type (long or short)
     */
    public Map<String, String> parse(String[] args) {
        /* The command line */
        CommandLine cli;
        /* The options map built during the parsing phase; each entry has the following form:
        <(language={it,en}), cv-type={long,short}, privacy={yes,no}> */
        Map<String, String> cliOptions = new LinkedHashMap<>();
        try {
            cli = this.parser.parse(this.cliOpts, args);
            this.logger.fine("User input parsed");

            /* Check number of options */
            if (cli.getOptions().length == 0) {  // Default case
                cliOptions.put(CLI_OPTION_LANGUAGE, Constant.ITALIAN);
                cliOptions.put(CLI_OPTION_CV_TYPE, Constant.LONG);
                this.logger.warning("No options set, assuming default values");
            } else if (
                    !cli.hasOption(Constant.OPT_LANGUAGE_SHORT) &&
                            cli.hasOption(Constant.OPT_CV_TYPE_SHORT)) {   // Only CV type set
                this.logger.warning("Option language not set, assuming default value " + Constant.ITALIAN);
                cliOptions.put(CLI_OPTION_LANGUAGE, Constant.ITALIAN);
                cliOptions.put(CLI_OPTION_CV_TYPE, Constant.SHORT);
            } else if (cli.hasOption(Constant.OPT_LANGUAGE_SHORT)) {   // Language specified (case 1 to 4)
                this.logger.fine("Option " + Constant.OPT_LANGUAGE_LONG + " set, get corresponding value");
                String language = cli.getOptionValue(Constant.OPT_LANGUAGE_SHORT);
                this.logger.fine("Option language set with value " + language);
                /* Set language in the option map */
                cliOptions.put(CLI_OPTION_LANGUAGE, language);
                /* Check whether cv-type has been set */
                if (cli.hasOption(Constant.OPT_CV_TYPE_SHORT)) {   // Case 2 or 4
                    this.logger.fine("Option " + Constant.OPT_CV_TYPE_LONG + " set");
                    cliOptions.put(CLI_OPTION_CV_TYPE, Constant.SHORT);
                } else {    // Case 1 or 3
                    this.logger.fine("Option " + Constant.OPT_CV_TYPE_LONG + " not set");
                    cliOptions.put(CLI_OPTION_CV_TYPE, Constant.LONG);
                }
            }
            /* Check privacy */
            if (cli.hasOption(Constant.OPT_PRIVACY_SHORT)) {   // Privacy set
                this.logger.fine("Option privacy set");
                cliOptions.put(CLI_OPTION_PRIVACY, Constant.PRIVACY_ON);
            } else {
                this.logger.fine("Option privacy not set");
                cliOptions.put(CLI_OPTION_PRIVACY, Constant.PRIVACY_OFF);
            }
            this.logger.fine("Options set to " + cliOptions.toString());
        } catch (ParseException e) {
            this.logger.severe(e.getMessage());
            this.showHelp();
            System.exit(1);
        }
        /* Return the options map */
        return cliOptions;
    }
}
