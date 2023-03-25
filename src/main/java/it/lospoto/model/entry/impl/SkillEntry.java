package it.lospoto.model.entry.impl;

import it.lospoto.model.entry.SectionEntry;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.util.List;

/**
 * This class models a skill entry, which has the following attributes:
 *  - type
 *  - content
 *  Constants are used to encode tag names used in the XML file containing the actual information; they are public and
 *  used by the section object to perform queries from the content loader object.
 */

public class SkillEntry implements SectionEntry {
    /* Constants used as parameter for the content loader */
    public static final String TYPE = "type";
    public static final String CONTENT = "content";

    /* The logger */
    private Logger logger;

    /* Class attributes */
    private String type, content;

    /**
     * Constructor
     * @param type The skill type
     * @param content The content
     */

    public SkillEntry(String type, String content) {
        /* The logger */
        this.logger = FactoryLogger.getInstance().createLogger(SkillEntry.class.getName());
        this.logger.finest("Log for SkillEntry class successfully created");

        /* Set the attributes */
        this.type = type;
        this.logger.finest("Type set to " + type);
        this.content = content;
        this.logger.finest("Content set");

        this.logger.finest("SkillEntry successfully created");
    }

    /**
     * Return the skill type
     * @return String The skill type
     */
    public String getType() {
        return type;
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
        return String.format("SkillEntry[Type: %s]", this.type);
    }

    /**
     * Return the title of a section entry, in case it's a subsection
     *
     * @return String The subsection entry
     */
    @Override
    public String getTitle() {
        this.logger.warning("Operation not supported: SkillEntry has no title");
        return null;
    }

    /**
     * Return the list of all the entries, in case the entry is a section
     *
     * @return List<SectionEntry> The list of entries
     */
    @Override
    public List<SectionEntry> getEntries() {
        this.logger.warning("Operation not supported: SkillEntry has no entries");
        return null;
    }

    /**
     * Return the list of all the subsections, in case the entry is a section
     *
     * @return List<SectionEntry> The list of subsections
     */
    @Override
    public List<SectionEntry> getSubsections() {
        this.logger.warning("Operation not supported: SkillEntry has no subsections");
        return null;
    }

    /**
     * Add a new entry to this object
     *
     * @param sectionEntry The new section entry to add
     */
    @Override
    public void add(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot add anything to SkillEntry");
    }

    /**
     * remove a new entry to this object
     *
     * @param sectionEntry The section entry to remove
     */
    @Override
    public void remove(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot remove anything from SkillEntry");
    }

    /**
     * Build the section entry
     */
    @Override
    public void build() {
        this.logger.warning("SkillEntry has nothing to build");
    }

    /**
     * Render the section entry
     */
    @Override
    public void render() {
        this.logger.warning("SkillEntry has nothing to render");
    }
}
