package it.lospoto.model.section.impl;

import it.lospoto.model.entry.SectionEntry;
import it.lospoto.model.entry.impl.PersonalInfoEntry;
import it.lospoto.model.section.Section;
import it.lospoto.model.template.Template;
import it.lospoto.util.Constant;
import it.lospoto.util.FileSystem;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * This class models a section containing all the personal information
 */

public class PersonalInfoSection extends Section {
    /* The template filename */
    protected static final String TEMPLATE_FILENAME = "info.vm";
    /* The output filename, which will contain the rendered template and will be stored into result/include folder */
    private static final String LATEX_FILENAME = "info.tex";

    /* The logger */
    private Logger logger;
    /* The file system */
    private FileSystem fs;

    /* Flag representing privacy */
    private boolean privacy;

    /**
     * Constructor
     */
    public PersonalInfoSection(String language, boolean privacy) {
        /* Parent constructor */
        super("", language);

        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(PersonalInfoSection.class.getName());
        this.logger.finer("Log for PersonalInfoSection class successfully created");

        /* Get the file system */
        this.fs = FileSystem.getInstance();
        this.logger.finer("File system successfully retrieved");

        /* Set privacy */
        this.privacy = privacy;
        this.logger.finer("Privacy for the section is " + this.privacy);
    }

    /**
     * Set the content for this section
     *
     * @param content The content
     */
    @Override
    public void setContent(List<Map<String, String>> content) {
        /* Set the content */
        this.sectionContent = content;
        this.logger.finer("Section content successfully set");
    }

    /**
     * Build this section
     */
    @Override
    public void build() {
        this.sectionContent.forEach((entry) -> {
            /* Create the entry object */
            PersonalInfoEntry personalInfoEntry = new PersonalInfoEntry(
                    entry.get(PersonalInfoEntry.MOBILE_PHONE),
                    entry.get(PersonalInfoEntry.EMAIL),
                    entry.get(PersonalInfoEntry.WEBSITE),
                    entry.get(PersonalInfoEntry.BORN_DATE));
            this.logger.finer("Personal info entry successfully created: " + personalInfoEntry);
            personalInfoEntry.setPrivacy(this.privacy);
            this.logger.finer("Privacy set to " + this.privacy + " for personal info entry: " + personalInfoEntry);

            /* Add to the entry list */
            this.entries.add(personalInfoEntry);
            this.logger.finer("Personal info entry successfully added to the section list");
        } );

        this.logger.finer("Personal info section successfully built");
    }

    /**
     * Render the entry
     */
    @Override
    public void render() {
        /* Get the template */
        Template template = Template.getInstance();
        this.logger.finer("Template successfully retrieved");
        /* Load the template */
        template.loadTemplate(TEMPLATE_FILENAME);
        this.logger.finer("Template " + TEMPLATE_FILENAME + " successfully loaded");

        /* For each entry, render */
        for (SectionEntry entry: this.entries) {
            template.addToContext(Constant.CTX_SECTION_PERSONAL_INFO, entry);
        }
        /* Render the section */
        template.render(new File(this.fs.getTemplateResultIncludeDir(), LATEX_FILENAME));
        this.logger.finer("Section personal info successfully rendered");
    }

    /**
     * Return a string representation of this object
     * @return String The string representation
     */
    public String toString() { return PersonalInfoSection.class.getName(); }
}
