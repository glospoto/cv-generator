package it.lospoto.model.section.impl;

import it.lospoto.model.entry.SectionEntry;
import it.lospoto.model.entry.impl.PrivacyEntry;
import it.lospoto.model.entry.impl.SkillEntry;
import it.lospoto.model.section.GenericSection;
import it.lospoto.model.template.Template;
import it.lospoto.util.Constant;
import it.lospoto.util.FileSystem;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * This class implements the skill sections.
 */

public class PrivacySection extends GenericSection {
    /* The template filename */
    protected static final String TEMPLATE_FILENAME = "privacy.vm";
    /* The output filename, which will contain the rendered template and will be stored into result/include folder */
    private static final String LATEX_FILENAME = "privacy.tex";

    /* The logger */
    private Logger logger;
    /* The file system */
    private FileSystem fs;
    /* The section content */
    private List<Map<String, String>> sectionContent;

    /**
     * Constructor
     * @param sectionTitle The section title
     * @param language The section language
     */

    public PrivacySection(String sectionTitle, String language) {
        /* Parent constructor */
        super(sectionTitle, language);

        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(PrivacySection.class.getName());
        this.logger.finer("Log for PrivacySection class successfully created");

        /* Get the file system */
        this.fs = FileSystem.getInstance();
        this.logger.finer("File system successfully retrieved");
    }

    /**
     * Return a string representation of this object
     * @return String The string representation
     */
    public String toString() { return PrivacySection.class.getName(); }

    /**
     * Return the title of a section entry, in case it's a subsection
     *
     * @return String The subsection entry
     */
    @Override
    public String getTitle() {
        return this.sectionTitle;
    }

    /**
     * Add a new entry to this object
     *
     * @param sectionEntry The new section entry to add
     */
    @Override
    public void add(SectionEntry sectionEntry) {
        this.subsections.add(sectionEntry);
    }

    /**
     * remove a new entry to this object
     *
     * @param sectionEntry The section entry to remove
     */
    @Override
    public void remove(SectionEntry sectionEntry) {
        this.subsections.remove(sectionEntry);
    }

    /**
     * Set the content for this section
     *
     * @param content The content
     */
    @Override
    public void setContent(List<Map<String, String>> content) {
        this.sectionContent = content;
    }

    /**
     * Build this section
     */
    @Override
    public void build() {
        /* Start scanning it to build the section */
        this.sectionContent.forEach((entry) -> {
            this.logger.finer("Start scanning " + entry);
            /* Browse the map and create the section entry object */
            SectionEntry sectionEntry = new PrivacyEntry(
                    entry.get(SkillEntry.CONTENT)
            );

            this.logger.finer("Section entry " + sectionEntry + "successfully created");
            /* Add to the section entry list */
            this.entries.add(sectionEntry);
            this.logger.finer("Privacy section entry successfully added to the section list");
        });

        this.logger.finer("Start building subsections; there are: " + this.subsections.size());
        /* Start building all the subsections */
        this.subsections.forEach((subsection) -> {
            this.logger.finer("Start building subsection " + subsection.getTitle());
            subsection.build();
            this.logger.finer("Subsection " + subsection.getTitle() + " successfully built");
        });
    }

    /**
     * Render this section
     */
    @Override
    public void render() {
        /* Get the template */
        Template template = Template.getInstance();
        this.logger.finer("Template successfully retrieved");
        /* Load the template */
        template.loadTemplate(TEMPLATE_FILENAME);
        this.logger.finer("Template " + TEMPLATE_FILENAME + " successfully loaded");

        /* Add this section to the context */
        template.addToContext(Constant.CTX_SECTION_PRIVACY, this);
        this.logger.finer("Section privacy successfully added to the context");

        /* Render the section */
        template.render(new File(this.fs.getTemplateResultIncludeSectionsDir(), LATEX_FILENAME));
        this.logger.finer("Section privacy successfully rendered");
    }
}
