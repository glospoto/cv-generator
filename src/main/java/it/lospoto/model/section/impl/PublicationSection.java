package it.lospoto.model.section.impl;

import it.lospoto.model.entry.SectionEntry;
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
 * This class models a section containing all the publications, This is a very special section becuase there are no
 * section entries for this section. Only the section title must be added to the template context.
 */

public class PublicationSection extends Section {
    /* The template filename */
    protected static final String TEMPLATE_FILENAME = "publication.vm";
    /* The output filename, which will contain the rendered template and will be stored into result/include folder */
    private static final String LATEX_FILENAME = "publication.tex";

    /* The logger */
    private Logger logger;
    /* The file system */
    private FileSystem fs;

    /**
     * Constructor
     */
    public PublicationSection(String sectionTitle, String language) {
        /* Parent constructor */
        super(sectionTitle, language);

        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(PublicationSection.class.getName());
        this.logger.finer("Log for PublicationSection class successfully created");

        /* Get the file system */
        this.fs = FileSystem.getInstance();
        this.logger.finer("File system successfully retrieved");
    }
    /**
     * Set the content for this section
     *
     * @param content The content
     */
    @Override
    public void setContent(List<Map<String, String>> content) {
        this.logger.warning("PublicationSection has no content");
    }

    /**
     * Build this section
     */
    @Override
    public void build() {
        this.logger.warning("PublicationSection has nothing to build");
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
        template.addToContext(Constant.CTX_PUBLICATION_SECTION_TITLE, this.sectionTitle);
        this.logger.finer("Publication section title successfully set");
        /* Render the section */
        template.render(new File(this.fs.getTemplateResultIncludeSectionsDir(), LATEX_FILENAME));
        this.logger.finer("Personal info section successfully rendered");
    }
}