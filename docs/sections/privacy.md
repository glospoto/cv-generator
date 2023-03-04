# The Pricavy Section
The following attributes model an entry of the skill section:
 - `sectionTitle`, representing the title of the section, example is: Authorization;
 - `content`, representing the content allowing who read the CV to treat all the personal information reported there.

In general, a section is composed by more than one entry, actually one entry for each authorization to be shown. Additionally, subsections can be created. Each subsection is exactly modeled as a section, namely by the same attributes of a section entry; more subsections can be declared. **Note that for this section, just one entry with no subsections should be enough.**



The following XML file models the privacy section:

    <?xml version="1.0"?>
    <section id="publications">
        <!-- Section title -->
        <sectionTitle lang="{it,en}">Section title</sectionTitle>

        <!-- Entries -->
        <entries>
            <!-- An entry. The attribute "lang" defines the entry's language -->
            <entry lang="{it,en}">  <!-- Allowed values are: it (for Italian); en (for English)>
                <content>Content of the authorization</content>
            </entry>
        </entries>
    </section>