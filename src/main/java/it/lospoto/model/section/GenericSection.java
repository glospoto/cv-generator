package it.lospoto.model.section;

import it.lospoto.model.entry.SectionEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A generic section object represents a section which might includes other sections as child, as well. This class
 * actually implements the Composite pattern.
 */

public abstract class GenericSection extends Section implements SectionEntry {
    /* The subsection list */
    protected List<SectionEntry> subsections;

    /**
     * Constructor
     * @param language The language for this section
     */
    public GenericSection(String sectionTitle, String language) {
        super(sectionTitle, language);

        /* Create the list of subsection */
        this.subsections = new ArrayList<SectionEntry>();
    }

    /**
     * Return the list of all the entries, in case the entry is a section
     *
     * @return List<SectionEntry> The list of entries
     */
    @Override
    public List<SectionEntry> getEntries() {
        return this.entries;
    }

    /**
     * Return the list of all the subsections, in case the entry is a section
     *
     * @return List<SectionEntry> The list of subsections
     */
    @Override
    public List<SectionEntry> getSubsections() {
        return this.subsections;
    }
}
