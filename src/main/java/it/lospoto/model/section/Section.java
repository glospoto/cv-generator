package it.lospoto.model.section;

import it.lospoto.model.entry.SectionEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Abstract class modeling a CV section. Section implementation is based on the Composite design patter, where a section
 * is a composable object that contains either a SectionEntry or a Section itself (to implement subsections in the CV).
 * There are two main types of sections:
 *  1. PersonalInfoSection which is a "special section" representing general information for the CV
 *     (e.g., mobile phone, born date, ...); this section has its own (non-standard) structure
 *  2. GenericSection which is a section composed by either SectionEntry objects or GenericSection itself, as
 *     subsections; this section has its own standard structure. Generic sections are: Education, Work, Project, Skill,
 *     Language, and Publication,
 *
 * Content of each section follows:
 *  1. Personal info, containing general information (e.g., phone number)
 *  2. Education, containing information about the scholar path
 *  3. Work, containing high level descriptions about work experiences
 *  4. Work projects, containing, detailed descriptions of activities followed during each work experience. Note:
 *     by using the option -s, this section will not be included
 *  5. Skill, containing information about hard and soft skills
 *  6. Language, containing information about spoken and written languages
 *  7. Publication, containing list of all publications
 */

public abstract class Section {
    /* The title of this section */
    protected String sectionTitle;

    /* The section language */
    protected String language;

    /* The entries for this section */
    protected List<SectionEntry> entries;

    /* The section content */
    protected List<Map<String, String>> sectionContent;

    /**
     * Constructor
     * @param  language The language for this section
     */
    public Section(String sectionTitle, String language) {
        /* Set the section title */
        this.sectionTitle = sectionTitle;
        /* Set the language */
        this.language = language;
        /* Create the list of all the entries' section */
        this.entries = new ArrayList<SectionEntry>();
    }

    /**
     * Set the content for this section
     * @param content The content
     */
    public abstract void setContent(List<Map<String, String>> content);

    /**
     * Build this section
     */
    public abstract void build();

    /**
     * Build this section
     */
    public abstract void render();
}
