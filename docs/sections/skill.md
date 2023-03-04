# The Skill Section
The following attributes model an entry of the skill section:
 - `sectionTitle`, representing the title of the section, example is: Skills;
 - `type`, representing type of the skill, example is: Programming;
 - `content`, containing the actual content of the entry; here LaTeX commands must be used, since it will be included into the rendered LaTeX template.

In general, a section is composed by more than one entry, actually one entry for each skill (or group of skills) to be shown in the skill section. Additionally, subsections might be created. Each subsection is exactly modeled as a section, namely by the same attributes of a section entry; more subsections can be declared. **Note that for this section, the choice of only having subsections grouping skills has been done. Nothing prevents to do differently, the model is general enough to arrange the section in entries plus subsections, if needed.**

The following XML file models the skill section:

    <?xml version="1.0"?>
    <section id="skills">
        <!-- Section title -->
        <sectionTitle lang="{it,en}">Section title</sectionTitle>

        <!-- Entries -->
        <entries>
            
            <!-- Subsections entries -->
            <subsections>

                <!-- Italian subsections -->
                <subsection lang="{it,en}">  <!-- For subsections, the language is defined at subsection level -->

                    <!-- Subsection title -->
                    <sectionTitle>Subsection</sectionTitle>

                    <!-- Subsections entries -->
                    <entries>
                        <entry>
                            <type>Skill 2.1</type>
                            <content>Description 2.1</content>
                        </entry>
                        <entry>
                            <type>Skill 2.2</type>
                            <content>Description 2.2</content>
                        </entry>
                    </entries>
                </subsection>
            </subsections>
        </entries>
    </section>