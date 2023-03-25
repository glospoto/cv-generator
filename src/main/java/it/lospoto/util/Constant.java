package it.lospoto.util;

/**
 * This class models constants used by the applications. At the moment constants are used for:
 *  1. languages (it and/or en)
 *  2. type of resume (long, including project details, and/or short, not including project details).
 */

public class Constant {
    /* String representing CLI options in both short and long form */
    /* Names (short and long) for language option */
    public static final String OPT_LANGUAGE_SHORT = "l";
    public static final String OPT_LANGUAGE_LONG = "lang";
    /* Names (short and long) for cv-type option */
    public static final String OPT_CV_TYPE_SHORT = "s";
    public static final String OPT_CV_TYPE_LONG = "short";
    /* Names (short and long) for privacy option */
    public static final String OPT_PRIVACY_SHORT = "p";
    public static final String OPT_PRIVACY_LONG = "privacy";

    /* String representing possible values for options */
    /* Values for language option */
    public static final String ITALIAN = "it";
    public static final String ENGLISH = "en";
    /* Values for cv-type option */
    public static final String LONG = "long";
    public static final String SHORT = "short";
    /* Values for privacy option */
    public static final String PRIVACY_OFF = "no";
    public static final String PRIVACY_ON = "yes";

    /* String representing section names; they are used in the FactorySection as key of the map containing all the
    * created sections, as well as when rendering the template as key of the context map */
    /* Personal info section */
    public static final String SECTION_PERSONAL_INFO = "personalInfo";
    /* Education section */
    public static final String SECTION_EDUCATION = "education";
    /* Work experience section */
    public static final String SECTION_WORKS = "works";
    /* Project details section */
    public static final String SECTION_PROJECTS = "projects";
    /* Skills section */
    public static final String SECTION_SKILLS = "skills";
    /* Languages section */
    public static final String SECTION_LANGUAGES = "languages";
    /* Publications section */
    public static final String SECTION_PUBLICATIONS = "publications";
    /* Privacy section */
    public static final String SECTION_PRIVACY = "privacy";

    /* String representing fixed values when parsing the XML files storing the actual CV contents */
    /* Name of the node <section> (root of the XML tree) */
    public static final String SECTION_NODE_NAME = "section";
    /* Name of the node <sectionTitle> */
    public static final String SECTION_TITLE_NODE_NAME = "sectionTitle";
    /* Name of the node <entries> */
    public static final String ENTRIES_NODE_NAME = "entries";
    /* Name of the node <entry> */
    public static final String ENTRY_NODE_NAME = "entry";
    /* Name of the node <subsections> */
    public static final String SUBSECTIONS_NODE_NAME = "subsections";
    /* Name of the node <subsection> */
    public static final String SUBSECTION_NODE_NAME = "subsection";
    /* Name of the attribute "lang" */
    public static final String LANGUAGE_ATTRIBUTE_NAME = "lang";

    /* String representing fixed values used to pass values to Velocity via context */
    /* CV language (used by babel package) */
    public static final String CTX_CV_LANGUAGE = "cvLanguage";
    /* Person name */
    public static final String CTX_CV_NAME = "name";
    /* Person surname */
    public static final String CTX_CV_SURNAME = "surname";
    /* Section personal info */
    public static final String CTX_SECTION_PERSONAL_INFO = "personalInfo";
    /* Section education */
    public static final String CTX_SECTION_EDUCATION = "education";
    /* Section work */
    public static final String CTX_SECTION_WORK = "work";
    /* Section project */
    public static final String CTX_SECTION_PROJECT = "project";
    /* Section skill */
    public static final String CTX_SECTION_SKILL = "skill";
    /* Section language */
    public static final String CTX_SECTION_LANGUAGE = "language";
    /* Publication section title */
    public static final String CTX_PUBLICATION_SECTION_TITLE = "publicationSectionTitle";
    /* Section privacy */
    public static final String CTX_SECTION_PRIVACY = "privacy";
}
