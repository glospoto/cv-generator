package it.lospoto.model.cv.impl;

import it.lospoto.model.cv.CvItalian;
import it.lospoto.model.section.FactorySection;
import it.lospoto.model.section.Section;
import it.lospoto.util.Constant;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

/**
 * This class models a CV with the following parameters:
 *  - lang = it
 *  - cv-type = long
 *  - privacy = no
 */

public class CvItalianLongNoPrivacy extends CvItalian {
    /* The logger */
    private Logger logger;

    /**
     * Constructor
     */
    public CvItalianLongNoPrivacy() {
        /* Invoke parent class constructor */
        super();

        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(CvItalianLongNoPrivacy.class.getName());
        this.logger.fine("Log for CvItalianLongNoPrivacy class successfully created");
    }

    /**
     * Return a string representation of this object
     * @return String The string representation
     */
    public String toString() { return CvItalianLongNoPrivacy.class.getName(); }

    /**
     * Build the CV
     */
    @Override
    public void build() {
        /* Building a CV means building all its sections */
        /* The section factory object */
        FactorySection factorySection = FactorySection.getInstance();
        this.logger.fine("FactorySection successfully created");
        /* Set language to ITALIAN */
        factorySection.setLanguage(Constant.ITALIAN);
        this.logger.fine("Language successfully set to " + Constant.ITALIAN + " for all the sections");

        /* Personal info section */
        Section personalInfo = factorySection.createPersonalInfoSection(false);
        this.logger.fine(personalInfo + " successfully created");
        personalInfo.build();
        this.logger.fine(personalInfo + " successfully built");
        this.sections.add(personalInfo);
        this.logger.fine(personalInfo + " successfully added to the section list");

        /* Education section */
        Section education = factorySection.createEducationSection();
        this.logger.fine(education + " successfully created");
        education.build();
        this.logger.fine(education + " successfully built");
        this.sections.add(education);
        this.logger.fine(education + " successfully added to the section list");

        /* Work section */
        Section works = factorySection.createWorksSection();
        this.logger.fine(works + " successfully created");
        works.build();
        this.logger.fine(works + " successfully built");
        this.sections.add(works);
        this.logger.fine(works + " successfully added to the section list");

        /* Project section */
        Section projects = factorySection.createProjectsSection();
        this.logger.fine(projects + " successfully created");
        projects.build();
        this.logger.fine(projects + " successfully built");
        this.sections.add(projects);
        this.logger.fine(projects + " successfully added to the section list");

        /* Skill section */
        Section skills = factorySection.createSkillsSection();
        this.logger.fine(skills + " successfully created");
        skills.build();
        this.logger.fine(skills + " successfully built");
        this.sections.add(skills);
        this.logger.fine(skills + " successfully added to the section list");

        /* Language section */
        Section languages = factorySection.createLanguagesSection();
        this.logger.fine(languages + " successfully created");
        languages.build();
        this.logger.fine(languages + " successfully built");
        this.sections.add(languages);
        this.logger.fine(languages + " successfully added to the section list");

        /* Publication section */
        Section publications = factorySection.createPublicationsSection();
        this.logger.fine(publications + " successfully created");
        this.sections.add(publications);
        this.logger.fine(publications + " successfully added to the section list");

        /* Privacy section */
        Section privacy = factorySection.createPrivacySection();
        this.logger.fine(privacy + " successfully created");
        privacy.build();
        this.logger.fine(privacy + " successfully built");
        this.sections.add(privacy);
        this.logger.fine(privacy + " successfully added to the section list");
    }
}
