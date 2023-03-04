# The Projects Section
Similarly to the [Work Experience](work.md) section, the following attributes model an entry of the project section:
 - `sectionTitle`, representing the title of the section, example is: Projects;
 - `project`, representing the project's name, example is: Kathará: a container based network emulation system;
 - `company`, indicating the company in which the candidate worked; an examples is: Roma Tre University;
 - `year`, reporting the time window in which the current work experience has been done; the format is: XXXX -- YYYY. Note double '-' are used because of the LaTeX dash useage. Note that for this section **it might be omitted**;
 - `role`, representing the role inside the company; an example is: Post-doc fellow;
 - `content`, containing the actual content of the entry; here LaTeX commands must be used, since it will be included into the rendered LaTeX template.

In general, a section is composed by more than one entry, actually one entry for each project to be shown in the project section. Additionally, subsections might be created. Each subsection is exactly modeled as a section, namely by the same attributes of a section entry; more subsections can be declared.

The following XML file models the project section:

    <?xml version="1.0"?>
    <section id="projects">
        <!-- Section title -->
        <sectionTitle lang="{it,en}">Section title</sectionTitle>

        <!-- Entries -->
        <entries>
            <!-- An entry. The attribute "lang" defines the entry's language -->
            <entry lang="{it,en}">  <!-- Allowed values are: it (for Italian); en (for English)>
                <project>Project 1</project>
                <company>Company</company>
                <role>Job title</role>
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
                            <project>Project 1</project>
                            <company>Company</company>
                            <year>XXX-YYY</year>
                            <role>Job title</role>
                            <content>Description</content>
                        </entry>
                    </entries>
                </subsection>
            </subsections>
        </entries>
    </section>