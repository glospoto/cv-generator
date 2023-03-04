# The Work Experience Section
The following attributes model an entry of the work experience section:
 - `sectionTitle`, representing the title of the section, example is: Work Experience;
 - `company`, indicating the company in which the candidate worked; an examples is: Roma Tre University;
 - `year`, reporting the time window in which the current work experience has been done; the format is: XXXX -- YYYY. Note double '-' are used because of the LaTeX dash useage;
 - `role`, representing the role inside the company; an example is: Post-doc fellow;
 - `city`, representing the company's city, example is: Rome;
 - `content`, containing the actual content of the entry; here LaTeX commands must be used, since it will be included into the rendered LaTeX template.

In general, a section is composed by more than one entry, actually one entry for each work experience to be shown in the work section. Additionally, subsections might be created. Each subsection is exactly modeled as a section, namely by the same attributes of a section entry; more subsections can be declared.

The following XML file models the work section:

    <?xml version="1.0"?>
    <section id="works">
        <!-- Section title -->
        <sectionTitle lang="{it,en}">Section title</sectionTitle>

        <!-- Entries -->
        <entries>
            <!-- An entry. The attribute "lang" defines the entry's language -->
            <entry lang="{it,en}">  <!-- Allowed values are: it (for Italian); en (for English)>
                <company>Company 1</company>
                <year>XXXX -- YYYY</year>
                <role>Job title</role>
                <city>City</city>
                <content>Description</content>
            </entry>
            
            <!-- Subsections entries -->
            <subsections>

                <!-- Italian subsections -->
                <subsection lang="{it,en}">  <!-- For subsections, the language is defined at subsection level -->

                    <!-- Subsection title -->
                    <sectionTitle>Subsection</sectionTitle>

                    <!-- Subsections entries -->
                    <entries>
                        <entry>
                            <company>Company 1</company>
                            <year>XXXX -- YYYY</year>
                            <role>Job title</role>
                            <city>City</city>
                            <content>Description</content>
                        </entry>
                    </entries>
                </subsection>
            </subsections>
        </entries>
    </section>