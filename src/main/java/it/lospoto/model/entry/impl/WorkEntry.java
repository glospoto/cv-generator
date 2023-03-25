package it.lospoto.model.entry.impl;

import it.lospoto.model.entry.SectionEntry;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.util.List;

/**
 * This class models a work entry, which has the following attributes:
 *  - company
 *  - year
 *  - role
 *  - city
 *  - content
 *  Constants are used to encode tag names used in the XML file containing the actual information; they are public and
 *  used by the section object to perform queries from the content loader object.
 */

public class WorkEntry implements SectionEntry {
    /* Constants used as parameter for the content loader */
    public static final String COMPANY = "company";
    public static final String YEAR = "year";
    public static final String ROLE = "role";
    public static final String CITY = "city";
    public static final String CONTENT = "content";

    /* The logger */
    private Logger logger;

    /* Class attributes */
    private String company, year, role, city, content;

    /**
     * Constructor
     * @param company The company name
     * @param year The year
     * @param role The role
     * @param city The city
     * @param content The content
     */
    public WorkEntry(String company, String year, String role, String city, String content) {
        /* The logger */
        this.logger = FactoryLogger.getInstance().createLogger(WorkEntry.class.getName());
        this.logger.finest("Log for WorkEntry class successfully created");

        /* Set the attributes */
        this.company = company;
        this.logger.finest("Company set to " + company);
        this.year = year;
        this.logger.finest("Year set to " + year);
        this.role = role;
        this.logger.finest("Role set to " + role);
        this.city = city;
        this.logger.finest("City set to " + city);
        this.content = content;
        this.logger.finest("Content set");

        this.logger.finest("WorkEntry successfully created");
    }

    /**
     * Return the name of the company
     * @return String The name of the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Return the year
     * @return String The year
     */
    public String getYear() {
        return year;
    }

    /**
     * Return the role
     * @return String the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Return the city
     * @return String The city
     */
    public String getCity() {
        return city;
    }

    /**
     * Return the content
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
        return String.format("WorkEntry[Company: %s; Year: %s; Role: %s; City: %s]",
                this.company, this.year, this.role, this.city);
    }

    /**
     * Return the title of a section entry, in case it's a subsection
     *
     * @return String The subsection entry
     */
    @Override
    public String getTitle() {
        this.logger.warning("Operation not supported: WorkEntry has no title");
        return null;
    }

    /**
     * Return the list of all the entries, in case the entry is a section
     *
     * @return List<SectionEntry> The list of entries
     */
    @Override
    public List<SectionEntry> getEntries() {
        this.logger.warning("Operation not supported: WorkEntry has no entries");
        return null;
    }

    /**
     * Return the list of all the subsections, in case the entry is a section
     *
     * @return List<SectionEntry> The list of subsections
     */
    @Override
    public List<SectionEntry> getSubsections() {
        this.logger.warning("Operation not supported: WorkEntry has no subsections");
        return null;
    }

    /**
     * Add a new entry to this object
     *
     * @param sectionEntry The new section entry to add
     */
    @Override
    public void add(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot add anything to WorkEntry");
    }

    /**
     * remove a new entry to this object
     *
     * @param sectionEntry The section entry to remove
     */
    @Override
    public void remove(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot remove anything from WorkEntry");
    }

    /**
     * Build the section entry
     */
    @Override
    public void build() {
        this.logger.warning("WorkEntry has nothing to build");
    }

    /**
     * Render the section entry
     */
    @Override
    public void render() {
        this.logger.warning("WorkEntry has nothing to render");
    }
}
