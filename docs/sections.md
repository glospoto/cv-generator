# Sections
This documentation explains how to add new sections (if needed) to the tool, as well as how each section is modeled by means of suitable XML files.

# XML Content Structure
The actual CV content is encoded into dedicated XML files, one for each section. The following list reports all the available sections, with the corresponding structure explanation.

- [Education section](sections/education.md)
- [Work experiences section](sections/work.md)
- [Projects section](sections/project.md)
- [Skills section](sections/skill.md)
- [Languages section](sections/language.md)
- [Publications section](sections/publication.md)
- [Privacy section](sections/privacy.md)

Additionally, there is a section ([Personal info section](sections/personal-info.md)) which contains all the contact information about the person to provide inside a CV (e.g., phone number).

# Adding a new section
Adding a new section to CV-Generator consists of the following steps:
1. create the XML file containing the section structure in the `content` folder;
2. create the corresponding template file into `tempate/sections` folder;
3. implement the entry model inside package `it.lospoto.model.entry.impl`;
4. implement the section model inside package `it.lospoto.model.section.impl`;
5. implement a method to build the section inside `it.lospoto.model.section.FactorySection`.

Each section is modeled as list of entries; each entry represent a single element of a CV. For example, the education section is a list of (education) entry, each of which represents a degree. For example, an education section might containt the following entries:
 - an entry for the PhD degree;
 - an entry for the Master degree;
 - an entry for the Bachelor degree.
Then, a CV is a collection of sections, each built according to the `cv-generator` input parameters.

Suppose to add a section called `Foo`: next sections explain all the steps, one by one.

## Create the XML file
As first step, the XML file defining the section structure and containing all the section information must be created inside `content` folder. The file can be created according to the section structure just described and will be called `foo.xml`.

## Create the template file
After creating the XML file, the template file must be created and stored into `template/sections` folder; this file will be called `foo.vm` and can be implemented as done for the other files, described in the [template section](template.md).

## Implement the entry model
Inside `it.lospoto.model.entry.impl` create a class called `FooEntry`, as follows:

    public class FooEntry implements SectionEntry {}

Each section entry implements the interface `SectionEntry` which is used to easly create subsections. This interface is implemented according to the _Composite_ pattern.
For all the details about the actual implementation, see classes in that package.

## Implement the section model
Inside `it.lospoto.model.secion.impl` create a class called `FooSection`, as follows:

    public class FooSection extends GenericSection {}
 
Each section extends the class `GenericSection` which is a _special_ section being able of containing other `GenericSection`; basically, it is used to easly create subsections. This implementation has been done according to the _Composite_ pattern.
For all the details about the actual implementation, see classes in that package.

## Implement method in the factory class
As last step, a method to build the section must be implementend in the `it.lospoto.model.section.FactorySection`, which is a factory class to build sections.
First of all, a constant acting as an identifier for the section must be added into `it.lospoto.util.Constant` class.

    public class Constant {
        /* OMISSIS */
        /* Foo section */
        public static final String SECTION_FOO = "foo";

This constant is used in the `FactorySection` class to add two associations:
1. with the actual class implemeting the section;
2. with the XML file containing the section information.

To accomplish step 1., the method `initializeSectionClassMap()` must be updated, by adding line as the following:

    this.sectionClass.put(Constant.SECTION_FOO, "it.lospoto.model.section.impl.FooSection");

Whereas, to accomplish step 2., the method `initializeSectionContentFilenameMap()` must be update, by adding a line as the following:

    this.sectionContentFilename.put(Constant.SECTION_FOO, "foo.xml");

Last step is to implement the method which actually builds the section:

    public Section createFooSection() {}

Then, the section can be used inside one of the classes modeling the CVs (inside `it.lospoto.model.cv.impl`; see one of that class for further details).