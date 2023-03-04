# The Education Section
The following attributes model an entry of the education section:
 - `sectionTitle`, representing the title of the section, example is: Education;
 - `name`, indicating the type of degree; examples are: PhD, Master Degree, Bachelor Degree, ...;
 - `year`, reporting the time window in which the current degree has been achieved; the format is: XXXX -- YYYY. Note double '-' are used because of the LaTeX dash useage;
 - `institution`, representing the institution name in which the current degree has been achieved; an example is: Roma Tre University;
 - `city`, representing the institution's city, example is: Rome;
 - `advisor`, reporting the advisor's name; example is: Prof. Mario Rossi;
 - `content`, containing the actual content of the entry; here LaTeX commands must be used, since it will be included into the rendered LaTeX template.

In general, a section is composed by more than one entry, actually one entry for each degree to be shown in the education section. Additionally, subsections can be created. Each subsection is exactly modeled as a section, namely by the same attributes of a section entry; more subsections can be declared.

The following XML file models the education section:

    <?xml version="1.0"?>
    <section id="education">
        <!-- Section title -->
        <sectionTitle lang="{it,en}">Section title</sectionTitle>

        <!-- Entries -->
        <entries>
            <!-- An entry. The attribute "lang" defines the entry's language -->
            <entry lang="{it,en}">  <!-- Allowed values are: it (for Italian); en (for English)>
                <name>Degree name</name>
                <year>XXXX -- YYYY</year>
                <institution>Institution name</institution>
                <city>Institution city</city>
                <advisor>Advisor</advisor>
                <content>The actual content in LaTeX style</content>
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
                            <name>Degree name</name>
                            <year>XXXX -- YYYY</year>
                            <institution>Institution name</institution>
                            <city>Institution city</city>
                            <advisor>Advisor</advisor>
                            <content>The actual content in LaTeX style</content>
                        </entry>
                    </entries>
                </subsection>
            </subsections>
        </entries>
    </section>