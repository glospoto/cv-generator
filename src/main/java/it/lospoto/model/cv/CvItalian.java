package it.lospoto.model.cv;

/**
 * Abstract class modeling all the Italian based CVs (long/short, no/with privacy).
 */

public abstract class CvItalian extends CV {
    /* Constant representing the language (it will be used in the template for the \setlanguage{} tag) */
    protected final static String LANGUAGE = "italian";

    /**
     * Constructor
     */
    public CvItalian() {
        super(LANGUAGE);
    }
}
