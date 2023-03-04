# The Personal Info Section
The following attributes model an entry of the personal info section:
 - `mobilePhone`, representing the mobile phone;
 - `email`, representing email;
 - `website`, representing the website, if any;
 - `bornDate`, representing the bord date.

This section has a structure which is slightly different from all the others, because it will not be shown together with all the other sections, since personal info are typically shown in dedicated box in the CV.

The following XML file models the personal info section:

    <?xml version="1.0"?>
    <section id="personalInfo">
        <entry>
            <!-- General information -->
            <mobilePhone>
                +39~555~1234567
            </mobilePhone>
            <email>
                john@doe.com
            </email>
            <website>
                https://doe.com
            </website>
            <!-- The born date is CV's language dependant -->
            <bornDate lang="{it,en}">  <!-- Allowed values are: it (for Italian); en (for English)>
                Born in City (PR), dd/mm/yyyy
            </borDate>
        </entry>
    </section>