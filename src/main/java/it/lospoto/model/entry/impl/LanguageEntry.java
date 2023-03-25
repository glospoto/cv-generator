package it.lospoto.model.entry.impl;

import it.lospoto.model.entry.SectionEntry;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.util.List;

/**
 * This class models a language entry, which has the following attributes:
 *  - language
 *  - content
 *  Constants are used to encode tag names used in the XML file containing the actual information; they are public and
 *  used by the section object to perform queries from the content loader object.
 */

public class LanguageEntry implements SectionEntry {
    /* Constants used as parameter for the content loader */
    public static final String LANGUAGE = "language";
    public static final String CONTENT = "content";

    /* The logger */
    private Logger logger;

    /* Class attributes */
    private String language, content;

    /**
     * Constructor
     * @param language The language
     * @param content The content
     */

    public LanguageEntry(String language, String content) {
        /* The logger */
        this.logger = FactoryLogger.getInstance().createLogger(LanguageEntry.class.getName());
        this.logger.finest("Log for LanguageEntry class successfully created");

        /* Set the attributes */
        this.language = language;
        this.logger.finest("Language set to " + language);
        this.content = content;
        this.logger.finest("Content set");

        this.logger.finest("LanguageEntry successfully created");
    }

    /**
     * Return the language
     * @return String The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Return the content
     * @return String the skill content
     */
    public String getContent() {
        return content;
    }

    /**
     * A string representation of this object
     * @return String The string representation
     */
    public String toString() {
        return String.format("LanguageEntry[Language: %s]", this.language);
    }

    /**
     * Return the title of a section entry, in case it's a subsection
     *
     * @return String The subsection entry
     */
    @Override
    public String getTitle() {
        this.logger.warning("Operation not supported: LanguageEntry has no title");
        return null;
    }

    /**
     * Return the list of all the entries, in case the entry is a section
     *
     * @return List<SectionEntry> The list of entries
     */
    @Override
    public List<SectionEntry> getEntries() {
        this.logger.warning("Operation not supported: LanguageEntry has no entries");
        return null;
    }

    /**
     * Return the list of all the subsections, in case the entry is a section
     *
     * @return List<SectionEntry> The list of subsections
     */
    @Override
    public List<SectionEntry> getSubsections() {
        this.logger.warning("Operation not supported: LanguageEntry has no subsections");
        return null;
    }

    /**
     * Add a new entry to this object
     *
     * @param sectionEntry The new section entry to add
     */
    @Override
    public void add(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot add anything to LanguageEntry");
    }

    /**
     * remove a new entry to this object
     *
     * @param sectionEntry The section entry to remove
     */
    @Override
    public void remove(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot remove anything from LanguageEntry");
    }

    /**
     * Build the section entry
     */
    @Override
    public void build() {
        this.logger.warning("LanguageEntry has nothing to build");
    }

    /**
     * Render the section entry
     */
    @Override
    public void render() {
        this.logger.warning("LanguageEntry has nothing to render");
    }
}
