# The Language Section
The following attributes model an entry of the skill section:
 - `sectionTitle`, representing the title of the section, example is: Languages;
 - `language`, representing language, example is: Italian;
 - `content`, containing the actual content of the entry; here LaTeX commands must be used, since it will be included into the rendered LaTeX template.

In general, a section is composed by more than one entry, actually one entry for each skill (or group of skills) to be shown in the skill section. Additionally, subsections might be created. Each subsection is exactly modeled as a section, namely by the same attributes of a section entry; more subsections can be declared. **Note that this section should not have any subsections; anyway, nothing is preventing to create them, one or more.**

The following XML file models the language section:

    <?xml version="1.0"?>
    <section id="languages">
        <!-- Section title -->
        <sectionTitle lang="{it,en}">Section title</sectionTitle>

        <!-- Entries -->
        <entries>
            
            <!-- An entry. The attribute "lang" defines the entry's language -->
            <entry lang="{it,en}">  <!-- Allowed values are: it (for Italian); en (for English)>
                <language>Language 1</language>
                <content>Description</content>
            </entry>
        </entries>
    </section>