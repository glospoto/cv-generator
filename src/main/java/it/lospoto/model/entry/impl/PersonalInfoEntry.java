package it.lospoto.model.entry.impl;

import it.lospoto.model.entry.SectionEntry;
import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;

import java.util.List;

/**
 * This class models a personal info entry. The personal info entry has the following attributes:
 *  - mobilePhone
 *  - email
 *  - website
 *  - bornDate
 *  Constants are used to encode tag names used in the XML file containing the actual information; they are public and
 *  used by the section object to perform queries from the content loader object.
 */

public class PersonalInfoEntry implements SectionEntry {
    /* Constants used as parameter for the content loader */
    public static final String MOBILE_PHONE = "mobilePhone";
    public static final String EMAIL = "email";
    public static final String WEBSITE = "website";
    public static final String BORN_DATE = "bornDate";

    /* The logger */
    private Logger logger;

    /* Class attributes */
    private String mobilePhone, email, website, bornDate;
    /* Privacy flag; by default, privacy is disabled */
    private boolean privacy = false;

    /**
     * Create a new entry for the personal info
     * @param mobilePhone The mobile phone to show
     * @param email The email to show
     * @param website The website to show
     * @param bornDate The born date to show
     */
    public PersonalInfoEntry(String mobilePhone, String email, String website, String bornDate) {
        /* The logger */
        this.logger = FactoryLogger.getInstance().createLogger(PersonalInfoEntry.class.getName());
        this.logger.finest("Log for PersonalInfoEntry class successfully created");

        /* Set the attributes */
        this.mobilePhone = mobilePhone;
        this.logger.finest("Mobile phone set to " + mobilePhone);
        this.email = email;
        this.logger.finest("Email set to " + email);
        this.website = website;
        this.logger.finest("Website set to " + website);
        this.bornDate = bornDate;
        this.logger.finest("Born date set to " + bornDate);

        this.logger.finest("PersonalInfoEntry successfully created");
    }

    /* Getter methods. Even if not used in the code, they are used by velocity to render the template */

    /**
     * Return the mobile phone
     * @return String the Mobile phone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Return the email
     * @return String The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Return the website
     * @return String The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Return the bord date
     * @return String The born date
     */
    public String getBornDate() {
        return bornDate;
    }

    /**
     * Return true if privacy is set; false, otherwise
     * @return boolean True if privacy is set; false, otherwise
     */
    public boolean hasPrivacy() {
        return this.privacy;
    }

    /**
     * Set privacy
     * @param privacy The privacy flag
     */
    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
        this.logger.finest("Privacy set to " + privacy);
    }

    /**
     * A string representation of this object
     * @return String The string representation
     */
    public String toString() {
        return String.format("PersonalInfoEntry[Mobile phone: %s; Email: %s; Website: %s; Born date: %s; Privacy: %s]",
                this.mobilePhone, this.email, this.website, this.bornDate, this.privacy);
    }

    /**
     * Return the title of a section entry, in case it's a subsection
     *
     * @return String The subsection entry
     */
    @Override
    public String getTitle() {
        this.logger.warning("Operation not supported: PersonalInfoEntry has no title");
        return null;
    }

    /**
     * Return the list of all the entries, in case the entry is a section
     *
     * @return List<SectionEntry> The list of entries
     */
    @Override
    public List<SectionEntry> getEntries() {
        this.logger.warning("Operation not supported: PersonalInfoEntry has no entries");
        return null;
    }

    /**
     * Return the list of all the subsections, in case the entry is a section
     *
     * @return List<SectionEntry> The list of subsections
     */
    @Override
    public List<SectionEntry> getSubsections() {
        this.logger.warning("Operation not supported: PersonalInfoEntry has no subsections");
        return null;
    }

    /**
     * Add a new entry to this object
     *
     * @param sectionEntry The new section entry to add
     */
    @Override
    public void add(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot add anything to PersonalInfoEntry");
    }

    /**
     * remove a new entry to this object
     *
     * @param sectionEntry The section entry to remove
     */
    @Override
    public void remove(SectionEntry sectionEntry) {
        this.logger.warning("Operation not supported: cannot remove anything from PersonalInfoEntry");
    }

    /**
     * Build the section entry
     */
    @Override
    public void build() {
        this.logger.warning("PersonalInfoEntry has nothing to build");
    }

    /**
     * Render the section entry
     */
    @Override
    public void render() {
        this.logger.warning("PersonalInfoEntry has nothing to render");
    }
}
