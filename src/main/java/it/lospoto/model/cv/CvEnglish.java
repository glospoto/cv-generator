package it.lospoto.model.cv;

/**
 * Abstract class modeling all the English based CVs (long/short, no/with privacy).
 */

public abstract class CvEnglish extends CV {
    /* Constant representing the language (it will be used in the template for the \setlanguage{} tag) */
    protected final static String LANGUAGE = "english";

    /**
     * Constructor
     */
    public CvEnglish() {
        super(LANGUAGE);
    }
}
