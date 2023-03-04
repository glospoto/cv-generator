# Template
CV-Generator relies on [Velocity](https://velocity.apache.org/) as template engine. The template has a rigid structure where there is:
 - a file for each section, except the personal info section;
 - a file describing all the settings (e.g., in terms of packages to use);
 - a dedicated file reporting all the personal information;
 - a bibliography file;
 - a main file pointing to all the abovementioned files.

## Structure
The template structure is the following:

```
.
├── main.vm
├── info.vm
├── settings.vm
├── bibliography.vm
├── sections
    ├── education.vm
    ├── language.vm
    ├── privacy.vm
    ├── project.vm
    ├── publication.vm
    ├── skill.vm
    └── work.vm

```
Whereas a rendered template will be:

```
.
├── include
│   ├── sections
│   │   ├── education.tex
│   │   ├── language.tex
│   │   ├── privacy.tex
│   │   ├── project.tex
│   │   ├── publication.tex
│   │   ├── skill.tex
│   │   └── work.tex
│   ├── info.tex
│   └── settings.tex 
├── bibliography.bib
└── main.tex

```
