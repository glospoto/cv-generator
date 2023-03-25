package it.lospoto.model.entry.impl;

import it.lospoto.model.entry.SectionEntry;
import it.lospoto.model.template.Template;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class models an education entry, which has the following attributes:
 *  - title (e.g, PhD, Master degree, ...)
 *  - institution (e.g, Roma Tre University)
 *  - city (e.g., Rome)
 *  - advisor
 *  - content
 *  Constants are used to encode tag names used in the XML file containing the actual information; they are public and
 *  used by the section object to perform queries from the content loader object.
 */

public class EducationEntry implements SectionEntry {
    /* Constants used as parameter for the content loader */
    public static final String NAME = "name";
    public static final String INSTITUTION = "institution";
    public static final String YEAR = "year";
    public static final String CITY = "city";
    public static final String ADVISOR = "advisor";
    public static final String CONTENT = "content";

    /* The logger */
    private Logger logger;

    /* Class attributes */
    private String name, institution, year, city, advisor, content;

    /**
     * Constructor
     * @param name The name of this education entry
     * @param institution The institution where the degree has been achieved
     * @param  year The year in which the title has been achieved
     * @param city The city in which the institution is
     * @param advisor The name of the advisor
     * @param content The actual content of the section (e.g., thesis details)
     */

    public EducationEntry(String name, String institution, String year, String city, String advisor, String content) {
        /* The logger */
        this.logger = FactoryLogger.getInstance().createLogger(EducationEntry.class.getName());
        this.logger.finest("Log for EducationEntry class successfully created");

        /* Set the attributes */
        this.name = name;
        this.logger.finest("Title set to " + name);
        this.institution = institution;
        this.logger.finest("Institution set to " + institution);
        this.year = year;
        this.logger.finest("Year set to " + year);
        this.city = city;
        this.logger.finest("City set to " + city);
        this.advisor = advisor;
        this.logger.finest("Advisor set to " + advisor);
        this.content = content;
        this.logger.finest("Content set");

        this.logger.finest("EducationEntry successfully created");
    }

    /* Getter methods. Even if not used in the code, they are used by velocity to render the template */

    /**
     * Return the name title
     * @return String The title
     */
    public String getName() {
        return name;
    }

    /**
     * Return the entry institution
     * @return String The institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Return the entry year
     * @return String The year
     */
    public String getYear() {
        return year;
    }

    /**
     * Return the entry city
     * @return String The city
     */
    public String getCity() {
        return city;
    }

    /**
     * Return the entry advisor
     * @return String The advisor
     */
    public String getAdvisor() {
        return advisor;
    }

    /**
     * Return the entry content
     * @return String The content
     */
    public String getContent() {
        return content;
    }

    /**
     * A string representation of this object
     * @return String The string representation
     */
    public String toString() {
        return String.format("EducationEntry[Title: %s; Institution: %s; Year: %s, City: %s; Advisor: %s]",
                this.name, this.institution, this.year, this.city, this.advisor);
    }

    /**
     * Return the title of a section entry, in case it's a subsection
     *
     * @return String The subsection entry
     */
    @Override
    public String getTitle() {
        this.logger.warning("Operation not supported: EducationEntry has no title");
        return null;
    }

    /**
     * Return the list of all the entries, in case the entry is a section
     *
     * @return List<SectionEntry> The list of entries
     */
    @Override
    public List<SectionEntry> getEntries() {
        this.logger.warning("Operation not supported: EducationEntry has no entries");
        return null;
    }

    /**
     * Return the list of all the subsections, in case the entry is a section
     *
     * @return List<SectionEntry> The list of subsections
     */
    @Override
    public List<SectionEntry> getSubsections() {
        this.logger.warning("Operation not supported: EducationEntry has no subsections");
        return null;
    }

    /**
     * Add a new entry to this object
     *
     * @param sectionEntry The new section entry to add
     */
    @Override
    public void add(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot add anything to EducationEntry");
    }

    /**
     * remove a new entry to this object
     *
     * @param sectionEntry The section entry to remove
     */
    @Override
    public void remove(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot remove anything from EducationEntry");
    }

    /**
     * Build the section entry
     */
    @Override
    public void build() {
        this.logger.warning("EducationEntry has nothing to build");
    }

    /**
     * Render the section entry
     */
    @Override
    public void render() {
        this.logger.warning("EducationEntry has nothing to render");
    }
}
