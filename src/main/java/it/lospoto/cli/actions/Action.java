package it.lospoto.cli.actions;

/**
 * Interface modeling an action that represents the user input. An action consists in generating a CV based on one of
 * the following four combinations:
 *  1. language IT, CV type LONG
 *  2. language IT, CV type SHORT
 *  3. language EN, CV type LONG
 *  4. language EN, CV type SHORT
 */

public interface Action {

    /**
     * Execute this action
     */
    public void execute();
}
