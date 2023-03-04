# The Publican Section
The following attributes model an entry of the skill section:
 - `sectionTitle`, representing the title of the section, example is: Publications;

This is one of easiest sections to create, just having a title, then the actual content is encoded into a bibtex file being part of the template.


The following XML file models the publication section:

    <?xml version="1.0"?>
    <section id="publications">
        <!-- Section title -->
        <sectionTitle lang="{it,en}">Section title</sectionTitle>
    </section>