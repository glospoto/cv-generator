package it.lospoto.model.entry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface modeling a section entry. This interface is created according to the Composite pattern: a section entry
 * might be either an entry or a section (GenericSection) itself.
 * Each section (see Section class) has its own specific entry. Hence, available entries are:
 *  1. Personal info entries
 *  2. Education entries
 *  3. Work entries
 *  4. Work project entries
 *  5. Skill entries
 *  6. Language entries
 *  7. Publication entries
 *
 *  Each entry object models specific content.
 */

public interface SectionEntry {

    /* Methods. Each SectionEntry has two main methods: build() and render(); in case of leaf objects (according
    * to the Composite pattern), those operations do nothing (just log messages in the actual implementation).
    * Also, standard methods for Composite pattern compliance are implemented (add and remove component). Even in this
    * case, if leaf objects, just log a message (operation not supported) */

    /**
     * Return the title of a section entry, in case it's a subsection
     * @return String The subsection entry
     */
    public String getTitle();

    /**
     * Return the list of all the entries, in case the entry is a section
     * @return List<SectionEntry> The list of entries
     */
    public List<SectionEntry> getEntries();

    /**
     * Return the list of all the subsections, in case the entry is a section
     * @return List<SectionEntry> The list of subsections
     */
    public List<SectionEntry> getSubsections();

    /**
     * Add a new entry to this object
     * @param sectionEntry The new section entry to add
     */
    public void add(SectionEntry sectionEntry);

    /**
     * remove a new entry to this object
     * @param sectionEntry The section entry to remove
     */

    public void remove(SectionEntry sectionEntry);

    /**
     * Build the section entry
     */
    public void build();

    /**
     * Render the section entry
     */
    public void render();
}
