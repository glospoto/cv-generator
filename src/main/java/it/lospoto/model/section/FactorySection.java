package it.lospoto.model.section;

import it.lospoto.model.section.impl.PersonalInfoSection;
import it.lospoto.model.section.impl.PublicationSection;
import it.lospoto.util.Constant;
import it.lospoto.util.ContentLoader;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class implements a factory for building Section objects. Each section is created by a dedicated method in this
 * class. This is a singleton.
 */

public class FactorySection {
    /* Singleton instance */
    private static FactorySection instance = null;

    /* The logger */
    private Logger logger;

    /* The content loader */
    private ContentLoader loader;

    /* Map containing all the filenames storing the section content */
    private Map<String, String> sectionClass;

    /* Map containing all the filenames storing the section content */
    private Map<String, String> sectionContentFilename;

    /* The map of all the already created sections */
    Map<String, Section> createdSections;
    /* The language to set to all the sections */
    private String language;

    /**
     * Constructor
     */
    private FactorySection() {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(FactorySection.class.getName());
        this.logger.finer("Log for FactorySection class successfully created");

        /* Create the content loader */
        this.loader = ContentLoader.getInstance();
        this.logger.finer("Content loader successfully created");

        /* The map of all the section class filename */
        this.sectionClass = new HashMap<String, String>();
        this.logger.finer("Map containing the classes' filenames successfully created");
        this.initializeSectionClassMap();
        this.logger.finer("Classes' filenames map successfully initialized");

        /* The map of all the section content filenames */
        this.sectionContentFilename = new HashMap<String, String>();
        this.logger.finer("Map containing the sections' XML filenames successfully created");
        this.initializeSectionContentFilenameMap();
        this.logger.finer("Sections' XML filenames map successfully initialized");

        /* The map of all the already created sections */
        this.createdSections = new HashMap<String, Section>();
        this.logger.finer("Map containing the created sections successfully created");
    }

    /**
     * Initialize the map containing the association between each section and the corresponding class name
     */
    private void initializeSectionClassMap() {
        /* For each section (except PersonalInfoSection), set the filename into the map */
        this.sectionClass.put(Constant.SECTION_EDUCATION, "it.lospoto.model.section.impl.EducationSection");
        this.sectionClass.put(Constant.SECTION_WORKS, "it.lospoto.model.section.impl.WorkSection");
        this.sectionClass.put(Constant.SECTION_PROJECTS, "it.lospoto.model.section.impl.ProjectSection");
        this.sectionClass.put(Constant.SECTION_SKILLS, "it.lospoto.model.section.impl.SkillSection");
        this.sectionClass.put(Constant.SECTION_LANGUAGES, "it.lospoto.model.section.impl.LanguageSection");
        this.sectionClass.put(Constant.SECTION_PUBLICATIONS, "it.lospoto.model.section.impl.PublicationSection");
        this.sectionClass.put(Constant.SECTION_PRIVACY, "it.lospoto.model.section.impl.PrivacySection");
    }

    /**
     * Initialize the map containing the association between each section and the corresponding XML file
     */
    private void initializeSectionContentFilenameMap() {
        /* For each section, set the filename into the map */
        this.sectionContentFilename.put(Constant.SECTION_PERSONAL_INFO, "personal-info.xml");
        this.sectionContentFilename.put(Constant.SECTION_EDUCATION, "education.xml");
        this.sectionContentFilename.put(Constant.SECTION_WORKS, "works.xml");
        this.sectionContentFilename.put(Constant.SECTION_PROJECTS, "projects.xml");
        this.sectionContentFilename.put(Constant.SECTION_SKILLS, "skills.xml");
        this.sectionContentFilename.put(Constant.SECTION_LANGUAGES, "languages.xml");
        this.sectionContentFilename.put(Constant.SECTION_PUBLICATIONS, "publications.xml");
        this.sectionContentFilename.put(Constant.SECTION_PRIVACY, "privacy.xml");
    }

    /**
     * Return an instance of this class
     * @return FactorySection An instance of this class
     */
    public static FactorySection getInstance() {
        if (instance == null)
            instance = new FactorySection();
        return instance;
    }

    /**
     * Set the language used to create all the sections
     * @param language The language used for creating the sections
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /* One method for each section to create */

    /**
     * Create an instance of PersonalInfoSection
     * @param privacy True if privacy is set; false, otherwise
     * @return Section The personal info section
     */
    public Section createPersonalInfoSection(boolean privacy) {
        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_PERSONAL_INFO);
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Create the section */
        Section personalInfo = new PersonalInfoSection(this.language, privacy);
        this.logger.finer(personalInfo + " successfully created");
        /* Set the content */
        personalInfo.setContent(this.loader.getPersonalInfo(this.language));
        this.logger.finer("Content successfully added");
        return personalInfo;
    }

    /**
     * Create a generic section starting from its class name and section title
     * @param className The class name to instantiate
     * @param sectionTitle The section title
     * @return Section The section created by its class name
     */
    private Section createGenericSection(String className, String sectionTitle) {
        /* Create the section starting from its class name */
        Class<?> sectionClass;
        Constructor<?> constructor = null;
        GenericSection section = null;
        try {
            /* The class representation */
            sectionClass = Class.forName(className);
            /* A generic section constructor takes two params as input, both are String */
            constructor = sectionClass.getConstructor(String.class, String.class);
            /* The generic section create by the sectionClass's constructor */
            section = (GenericSection) constructor.newInstance(sectionTitle, this.language);
        } catch (ClassNotFoundException e) {
            this.logger.severe(e.getMessage());
            System.exit(1);
        } catch (InvocationTargetException e) {
            this.logger.severe(e.getMessage());
            System.exit(1);
        } catch (NoSuchMethodException e) {
            this.logger.severe(e.getMessage());
            System.exit(1);
        } catch (InstantiationException e) {
            this.logger.severe(e.getMessage());
            System.exit(1);
        } catch (IllegalAccessException e) {
            this.logger.severe(e.getMessage());
            System.exit(1);
        }

        this.logger.finer(section + " successfully created");
        section.setContent(this.loader.getSectionContent(this.language));
        this.logger.finer("Content successfully added");

        /* Check whether sections has subsections */
        if (this.loader.hasSubsections()) {
            /* Get all subsections */
            List<Map<String, List<Map<String, String>>>> subsectionsList = this.loader.getSubsectionsContent(this.language);
            this.logger.finer(section + " has " + subsectionsList.size() + " subsection(s); start creating...");
            this.logger.finer("Subsections for " + section + " successfully retrieved");

            /* Create a copy of objects to be used inside the lambda expression (needed) */
            Constructor<?> finalConstructor = constructor;
            GenericSection finalSection = section;

            /* Scan subsections */
            subsectionsList.forEach((subsectionEntry) -> {
                /* Get map keys */
                Set<String> keys = subsectionEntry.keySet();
                /* For each key (namely a section), get title and content */
                keys.forEach((key) -> {
                    /* Get the subsection title */
                    String subsectionTitle = key;
                    this.logger.finer("Subsection title (" + subsectionTitle + ") successfully retrieved");
                    List<Map<String, String>> content = subsectionEntry.get(key);
                    this.logger.finer("Subsection content successfully retrieved: " + content);
                    /* Create the new subsection */
                    GenericSection subsection = null;
                    try {
                        subsection = (GenericSection) finalConstructor.newInstance(subsectionTitle, this.language);
                    } catch (InstantiationException e) {
                        this.logger.severe(e.getMessage());
                        System.exit(1);
                    } catch (IllegalAccessException e) {
                        this.logger.severe(e.getMessage());
                        System.exit(1);
                    } catch (InvocationTargetException e) {
                        this.logger.severe(e.getMessage());
                        System.exit(1);
                    }
                    this.logger.finer(subsection + " subsection successfully created");
                    /* Set the content */
                    subsection.setContent(content);
                    this.logger.finer("Subsection content successfully added");
                    /* Add it to the section */
                    finalSection.add(subsection);
                    this.logger.finer("Subsection successfully added to subsections list");
                });
            });
        }

        return section;
    }

    /**
     * Create an instance of EducationSection
     * @return Section The Education section
     */
    public Section createEducationSection() {
        /* Section class name */
        String sectionClassName = this.sectionClass.get(Constant.SECTION_EDUCATION);
        /* Check whether there is a valid mapping */
        if (sectionClassName == null) {
            this.logger.finer("Something went wrong; couldn't get any class name corresponding to " +
                    Constant.SECTION_EDUCATION + ". I do not know which is the section to build; quitting...");
            System.exit(1);
        }
        this.logger.finer("Section class name " + sectionClassName + " successfully retrieved");

        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_EDUCATION);
        /* Check whether there is a valid mapping */
        if (sectionFileName == null) {
            this.logger.finer("Something went wrong; couldn't get any XML filename corresponding to " +
                    Constant.SECTION_EDUCATION + ". I do not know which is the section content to load; quitting...");
            System.exit(1);
        }
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Get section title */
        String sectionTitle = this.loader.getSectionTitle(this.language);
        this.logger.finer("Section title (" + sectionTitle + ") successfully retrieved");

        return this.createGenericSection(sectionClassName, sectionTitle);
    }

    /**
     * Create an instance of WorksSection
     * @return Section The works section
     */
    public Section createWorksSection() {
        /* Section class name */
        String sectionClassName = this.sectionClass.get(Constant.SECTION_WORKS);
        /* Check whether there is a valid mapping */
        if (sectionClassName == null) {
            this.logger.finer("Something went wrong; couldn't get any class name corresponding to " +
                    Constant.SECTION_WORKS + ". I do not know which is the section to build; quitting...");
            System.exit(1);
        }
        this.logger.finer("Section class name " + sectionClassName + " successfully retrieved");

        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_WORKS);
        /* Check whether there is a valid mapping */
        if (sectionFileName == null) {
            this.logger.finer("Something went wrong; couldn't get any XML filename corresponding to " +
                    Constant.SECTION_WORKS + ". I do not know which is the section content to load; quitting...");
            System.exit(1);
        }
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Get section title */
        String sectionTitle = this.loader.getSectionTitle(this.language);
        this.logger.finer("Section title (" + sectionTitle + ") successfully retrieved");

        return this.createGenericSection(sectionClassName, sectionTitle);
    }

    /**
     * Create an instance of ProjectsSection
     * @return Section The projects section
     */
    public Section createProjectsSection() {
        /* Section class name */
        String sectionClassName = this.sectionClass.get(Constant.SECTION_PROJECTS);
        /* Check whether there is a valid mapping */
        if (sectionClassName == null) {
            this.logger.finer("Something went wrong; couldn't get any class name corresponding to " +
                    Constant.SECTION_PROJECTS + ". I do not know which is the section to build; quitting...");
            System.exit(1);
        }
        this.logger.finer("Section class name " + sectionClassName + " successfully retrieved");

        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_PROJECTS);
        /* Check whether there is a valid mapping */
        if (sectionFileName == null) {
            this.logger.finer("Something went wrong; couldn't get any XML filename corresponding to " +
                    Constant.SECTION_PROJECTS + ". I do not know which is the section content to load; quitting...");
            System.exit(1);
        }
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Get section title */
        String sectionTitle = this.loader.getSectionTitle(this.language);
        this.logger.finer("Section title (" + sectionTitle + ") successfully retrieved");

        return this.createGenericSection(sectionClassName, sectionTitle);
    }

    /**
     * Create an instance of SkillsSection
     * @return Section The skills section
     */
    public Section createSkillsSection() {
        /* Section class name */
        String sectionClassName = this.sectionClass.get(Constant.SECTION_SKILLS);
        /* Check whether there is a valid mapping */
        if (sectionClassName == null) {
            this.logger.finer("Something went wrong; couldn't get any class name corresponding to " +
                    Constant.SECTION_SKILLS + ". I do not know which is the section to build; quitting...");
            System.exit(1);
        }
        this.logger.finer("Section class name " + sectionClassName + " successfully retrieved");

        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_SKILLS);
        /* Check whether there is a valid mapping */
        if (sectionFileName == null) {
            this.logger.finer("Something went wrong; couldn't get any XML filename corresponding to " +
                    Constant.SECTION_SKILLS + ". I do not know which is the section content to load; quitting...");
            System.exit(1);
        }
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Get section title */
        String sectionTitle = this.loader.getSectionTitle(this.language);
        this.logger.finer("Section title (" + sectionTitle + ") successfully retrieved");

        return this.createGenericSection(sectionClassName, sectionTitle);
    }

    /**
     * Create an instance of LanguagesSection
     * @return Section The languages section
     */
    public Section createLanguagesSection() {
        /* Section class name */
        String sectionClassName = this.sectionClass.get(Constant.SECTION_LANGUAGES);
        /* Check whether there is a valid mapping */
        if (sectionClassName == null) {
            this.logger.finer("Something went wrong; couldn't get any class name corresponding to " +
                    Constant.SECTION_LANGUAGES + ". I do not know which is the section to build; quitting...");
            System.exit(1);
        }
        this.logger.finer("Section class name " + sectionClassName + " successfully retrieved");

        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_LANGUAGES);
        /* Check whether there is a valid mapping */
        if (sectionFileName == null) {
            this.logger.finer("Something went wrong; couldn't get any XML filename corresponding to " +
                    Constant.SECTION_LANGUAGES + ". I do not know which is the section content to load; quitting...");
            System.exit(1);
        }
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Get section title */
        String sectionTitle = this.loader.getSectionTitle(this.language);
        this.logger.finer("Section title (" + sectionTitle + ") successfully retrieved");

        return this.createGenericSection(sectionClassName, sectionTitle);
    }

    /**
     * Create an instance of PublicationsSection
     * @return Section The publications section
     */
    public Section createPublicationsSection() {
        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_PUBLICATIONS);
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Get section title */
        String sectionTitle = this.loader.getSectionTitle(this.language);
        this.logger.finer("Section title (" + sectionTitle + ") successfully retrieved");
        /* Create the section */
        Section publications = new PublicationSection(sectionTitle, this.language);
        this.logger.finer(publications + " successfully created");
        return publications;
    }

    /**
     * Create an instance of PrivacySection
     * @return Section The privacy section
     */
    public Section createPrivacySection() {
        /* Section class name */
        String sectionClassName = this.sectionClass.get(Constant.SECTION_PRIVACY);
        /* Check whether there is a valid mapping */
        if (sectionClassName == null) {
            this.logger.finer("Something went wrong; couldn't get any class name corresponding to " +
                    Constant.SECTION_PRIVACY + ". I do not know which is the section to build; quitting...");
            System.exit(1);
        }
        this.logger.finer("Section class name " + sectionClassName + " successfully retrieved");

        /* Section file name */
        String sectionFileName = this.sectionContentFilename.get(Constant.SECTION_PRIVACY);
        /* Check whether there is a valid mapping */
        if (sectionFileName == null) {
            this.logger.finer("Something went wrong; couldn't get any XML filename corresponding to " +
                    Constant.SECTION_PRIVACY + ". I do not know which is the section content to load; quitting...");
            System.exit(1);
        }
        /* Load the XML file for this section */
        this.loader.loadSectionFile(sectionFileName);
        this.logger.finer(sectionFileName + " successfully loaded");
        /* Get section title */
        String sectionTitle = this.loader.getSectionTitle(this.language);
        this.logger.finer("Section title (" + sectionTitle + ") successfully retrieved");

        return this.createGenericSection(sectionClassName, sectionTitle);
    }
}
