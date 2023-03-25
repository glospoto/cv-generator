package it.lospoto.util;

import it.lospoto.util.log.FactoryLogger;
import it.lospoto.util.log.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class models a loader to import content into the model. CV information are stored into XML files, which are
 * parsed to acquire all the needed information.
 * It's implemented as a singleton and created inside the FactorySection class; depending on the section to be parsed,
 * the corresponding XML file will be loaded "on-the-fly".
 */

public class ContentLoader {
    /* Singleton instance */
    private static ContentLoader instance = null;

    /* The logger */
    private Logger logger;
    /* The file system reference */
    private FileSystem fs;
    /* The actual XML file to parse */
    private Document xml;
    /* The filename representing the XML to parse */
    private String currentFilename;

    /**
     * Constructor
     */
    public ContentLoader() {
        /* Create the logger */
        this.logger = FactoryLogger.getInstance().createLogger(ContentLoader.class.getName());
        this.logger.finest("Log for ContentLoader class successfully created");
        /* Load the file system */
        this.fs = FileSystem.getInstance();
        this.logger.finest("FileSystem class successfully retrieved");
    }

    /**
     * Return an instance of this class
     * @return ContentLoader An instance of this class
     */
    public static ContentLoader getInstance() {
        if (instance == null)
            instance = new ContentLoader();
        return instance;
    }

    /**
     * Load section file based on its name
     * @param filename The section's filename to be loaded
     */
    public void loadSectionFile(String filename) {
        /* Set filename as current */
        this.currentFilename = filename;
        /* The file to be loaded */
        File content = new File(this.fs.getContentDir(), this.currentFilename);
        this.logger.finest("File object successfully created for filename " + this.currentFilename);

        /* Start creating objects to parse the XML file */
        /* Instantiate the Factory */
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        this.logger.finest("Document builder factory successfully created");
        /* Process XML securely, avoid attacks like XML External Entities (XXE); optional, but recommended */
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            this.logger.finest("Document builder successfully created");

            this.xml = db.parse(content);
            this.logger.finest("XML file successfully loaded");

            /* Optional, but recommended, as reported in the following Stackoverflow discussion:
            http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work */
            this.xml.getDocumentElement().normalize();
            this.logger.finest("XML file successfully normalized");
        } catch (ParserConfigurationException e) {
            this.logger.severe("Parsing error: " + e + "Quitting...");
            System.exit(1);
        } catch (SAXException | IOException e) {
            this.logger.severe("Error when handling the file: " + e + "Quitting...");
            System.exit(1);
        }
    }

    /**
     * Return a mamp containing all the information related to the personal info section. Since this section has a
     * different structure compared to all the other sections, it has its own method to retrieve the content.
     * @param language The value of the attribute used for the selection
     * @return
     */
    public List<Map<String, String>> getPersonalInfo(String language) {
        /* The list containing all the section content as a sequence of maps */
        List<Map<String, String>> entries = new ArrayList<Map<String, String>>();
        this.logger.finest("Entries' list successfully created");
        /* The map representing an entry */
        Map<String, String> entry;

        /* Get the element root */
        NodeList sectionEntries = this.xml.getElementsByTagName(Constant.ENTRY_NODE_NAME);
        this.logger.finest("Section entries (<entry>) nodes successfully retrieved");

        /* Scanning all the nodes means scanning the section entries */
        for (int i = 0; i < sectionEntries.getLength(); i++) {
            /* The node representing a section entry, namely info enclosed in a single <entry> node */
            Node sectionEntry = sectionEntries.item(i);
            this.logger.finest("Scanning section (<entry>) node");
            /* Creating the map for this entry */
            entry = new HashMap<String, String>();
            this.logger.finest("Entry map successfully created");
            if (sectionEntry.getNodeType() == Node.ELEMENT_NODE) {
                /* NodeList representing all the nodes inside <entry> */
                NodeList entryNodes = sectionEntry.getChildNodes();
                this.logger.finest("Section entry nodes (inside <entry>) successfully retrieved");
                /* Scanning all the nodes means scanning the current section entry */
                for (int j = 0; j < entryNodes.getLength(); j++) {
                    /* The node representing the specific element under <entry> */
                    Node entryNode = entryNodes.item(j);
                    if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element entryElement = (Element) entryNode;
                        String entryNodeName = entryNode.getNodeName();
                        String entryNodeValue = null;
                        if (!entryElement.hasAttributes()) {
                            entryNodeValue = entryElement.getTextContent().trim();
                            this.logger.finest("Node " + entryNodeName + " successfully retrieved");
                            /* Populating the entry map: <node_name, node_value> */
                            entry.put(entryNodeName, entryNodeValue);
                            this.logger.finest(
                                    "<" + entryNodeName + ", " + entryNodeValue + "> successfully added");
                        } else {
                            /* Get the attribute */
                            String entryLanguage = entryElement.getAttribute(Constant.LANGUAGE_ATTRIBUTE_NAME);
                            if (entryLanguage.equals(language)) {
                                entryNodeValue = entryElement.getTextContent().trim();
                                this.logger.finest("Node " + entryNodeName + " successfully retrieved");
                                /* Populating the entry map: <node_name, node_value> */
                                entry.put(entryNodeName, entryNodeValue);
                                this.logger.finest(
                                        "<" + entryNodeName + ", " + entryNodeValue + "> successfully added");
                            }
                        }
                    }
                }
            }
            /* Add the entry to the list of all the entries */
            entries.add(entry);
            this.logger.finest("Entry map successfully added to the list");
        }
        return entries;
    }

    /**
     * Return a NodeList object containing nodes identified by xpath expression given as parameter
     * @param expression The xpaht expression
     * @return NodeList The list of nodes to be returned
     */
    private NodeList query(String expression) {
        /* To perform this selection, XPath will be used */
        this.logger.finest("Expression to be executed: " + expression);
        /* Get the factory */
        XPathFactory xPathfactory = XPathFactory.newInstance();
        this.logger.finest("XPath factory successfully retrieved");
        XPath xpath = xPathfactory.newXPath();
        this.logger.finest("XPath successfully created");
        /* The XPath expression object */
        XPathExpression expr = null;
        /* The node list returned after evaluating the expression */
        NodeList nodes = null;
        try {
            expr = xpath.compile(expression);
            this.logger.finest("XPath expression successfully compiled");
            nodes = (NodeList) expr.evaluate(this.xml, XPathConstants.NODESET);
            this.logger.finest("XPath expression successfully evaluated; node(s) found: " + nodes.getLength());
        } catch (XPathExpressionException e) {
            this.logger.severe("Problems when evaluating XPath expression: " + e + "Quitting...");
        }
        return nodes;
    }

    /**
     * Private method to get a map representation of a section entry.
     * In the map, the association <key,value> has the following form: <node_name,node_value>.
     * For example, considering the following structure:
     *
     * <title id="phd">
     *   PhD in XXX
     * </title>
     * <institution>
     *   Univeristy
     * </institution>
     * <city>
     *   City
     * </city>
     * <advisor>
     *   Advisor
     * </advisor>
     * <content>
     *   Description
     * </content>
     *
     * taken from education.xml,
     * @param sectionEntry The section entry (<entry>) node
     * @return Map<String, String> The map representation of the section entry
     */
    private Map<String, String> entryToMap(Node sectionEntry) {
        this.logger.finest("Scanning section (<entry>) node");
        /* Creating the map for this entry */
        Map<String, String> entry = new HashMap<String, String>();
        this.logger.finest("Entry map successfully created");
        if (sectionEntry.getNodeType() == Node.ELEMENT_NODE) {
            /* NodeList representing all the nodes inside <entry> */
            NodeList entryNodes = sectionEntry.getChildNodes();
            this.logger.finest("Section entry nodes (inside <entry>) successfully retrieved");
            /* Scanning all the nodes means scanning the current section entry */
            for (int j = 0; j < entryNodes.getLength(); j++) {
                /* The node representing the specific element under <entry> */
                Node entryNode = entryNodes.item(j);
                if (entryNode.getNodeType() == Node.ELEMENT_NODE) {
                    String entryNodeName = entryNode.getNodeName();
                    String entryNodeValues = entryNode.getTextContent().trim();
                    this.logger.finest("Node " + entryNodeName + " successfully retrieved");
                    /* Populating the entry map: <node_name, node_value> */
                    entry.put(entryNodeName, entryNodeValues);
                    this.logger.finest("<" + entryNodeName + ", " + entryNodeValues + "> successfully added");
                }
            }
        }
        return entry;
    }

    /**
     * Private method used to make general the action of retrieving content for either sections or subsections:
     * depending on the expression variable, this method will return section or subsection content. Possible values for
     * expression are:
     *  - /section/entries/entry -> get content for sections
     *  - /section/entries/subsections/subsection/entries/entry -> get content for subsections
     * A (sub)section content is a list of map. Each map contains values to build a SectionEntry model object; since a
     * section might contain multiple entries, thus multiple maps, that are collected all together in a list.
     * The returning object will have the following shape:
     * List[Map(<"title", "PhD in XXX">, <"institution", "University">, <"city", "City">, ...), Map(...), ...]
     * The map object is retrieved by the entryToMap(Node n) private method.
     *
     * @param expression The xpath expression to execute
     * @return List<Map<String, String>> The list of all the entries for the section
     */

    private List<Map<String, String>> getContent(String expression) {
        /* The list containing all the section content as a sequence of maps */
        List<Map<String, String>> entries = new ArrayList<Map<String, String>>();
        this.logger.finest("Entries' list successfully created");

        /* Get all the entries whose language is the language represented by attributeValue */
        NodeList sectionEntries = this.query(expression);
        this.logger.finest("Section entries (<entry>) nodes successfully retrieved");
        /* Scanning all the nodes means scanning the section entries */
        for (int i = 0; i < sectionEntries.getLength(); i++) {
            /* The node representing a section entry, namely info enclosed in a single <entry> node */
            Node sectionEntry = sectionEntries.item(i);
            /* Get all the entry elements as a map and add it to the list of all the entries */
            entries.add(this.entryToMap(sectionEntry));
            this.logger.finest("Entry map successfully added to the list");
        }
        return entries;
    }

    /**
     * Return the section title based on the language
     * @param language The language for the section title
     * @return String The section title
     */
    public String getSectionTitle(String language) {
        String title = null;
        /* Start building the expression; section title is /section/sectionTitle[lang="language"] */
        String expression = String.format("/%s/%s[@%s='%s']",
                Constant.SECTION_NODE_NAME,
                Constant.SECTION_TITLE_NODE_NAME,
                Constant.LANGUAGE_ATTRIBUTE_NAME,
                language);
        this.logger.finest("Expression successfully built");
        /* Get the node list. It must contain only one node; otherwise, error and exit */
        NodeList sectionTitle = this.query(expression);
        if (sectionTitle.getLength() == 0) {
            this.logger.warning("Section title not set; I do not know how to title a specific section. " +
                    "Check " + this.currentFilename + "; quitting...");
            System.exit(1);
        } else if (sectionTitle.getLength() > 1) {
            this.logger.warning("More than 1 section title set; I do not know which one to use. Check " +
                    this.currentFilename + "; quitting...");
            System.exit(1);
        } else if (sectionTitle.getLength() == 1)
            title = sectionTitle.item(0).getTextContent().trim();
        return title;
    }

    /**
     * Return the content of the current section (given its XML file)
     * @param language The language of the section
     * @return List<Map<String, String>> The content as list of maps
     */
    public List<Map<String,String>> getSectionContent(String language) {
        /* Start building the expression; section title is /section/sectionTitle[lang="language"] */
        String expression = String.format("/%s/%s/%s[@%s='%s']",
                Constant.SECTION_NODE_NAME,
                Constant.ENTRIES_NODE_NAME,
                Constant.ENTRY_NODE_NAME,
                Constant.LANGUAGE_ATTRIBUTE_NAME,
                language);
        this.logger.finest("Expression successfully built: " + expression);
        return this.getContent(expression);
    }

    /**
     * Check whether the current section (given its XML file) has subsections
     * @return boolean True if there is at least one subsection; false, otherwise
     */
    public boolean hasSubsections() {
        /* By default, section has no subsections */
        boolean hasSubsection = false;
        /* Check whether the current xml file has <subsections> node */
        NodeList subsections = this.xml.getElementsByTagName(Constant.SUBSECTIONS_NODE_NAME);
        if (subsections.getLength() > 1) {
            this.logger.warning("More than 1 <subsections> tag found; not allowed. Check " +
                    this.currentFilename + "; quitting...");
            System.exit(1);
        } else if (subsections.getLength() == 1)
            /* Subsection found */
            hasSubsection = true;
        return hasSubsection;
    }

    /**
     * Return the content of the subsections of the current section (given its XML file)
     * @param language The language of the section
     * @return List<Map<String, String>> The content as list of maps
     */
    public List<Map<String, List<Map<String, String>>>> getSubsectionsContent(String language) {
        /* The list of subsections */
        List<Map<String, List<Map<String, String>>>> subsectionsList =
                new ArrayList<Map<String, List<Map<String, String>>>>();
        this.logger.finest("Subsections entries' list successfully created");

        /* Subsection entry map <subsectionTitle, subsectionContent> */
        Map<String, List<Map<String, String>>> subsectionsMap;

        /* Start building the expression; subsections is  */
        String expressionForSubsections = String.format("/%s/%s/%s/%s[@%s='%s']",
                Constant.SECTION_NODE_NAME,
                Constant.ENTRIES_NODE_NAME,
                Constant.SUBSECTIONS_NODE_NAME,
                Constant.SUBSECTION_NODE_NAME,
                Constant.LANGUAGE_ATTRIBUTE_NAME,
                language);
        this.logger.finest("Expression for subsections successfully built");

        /* Get all the subsections */
        NodeList subsections = this.query(expressionForSubsections);
        this.logger.finest("Subsections successfully retrieved");
        /* Scanning all the nodes means scanning the subsection entries */
        for (int cSubsections = 0; cSubsections < subsections.getLength(); cSubsections++) {
            this.logger.finest("Scanning subsection (<subsection>) node");
            /* Create the subsectionsMap for this subsection */
            subsectionsMap = new HashMap<String, List<Map<String, String>>>();
            this.logger.finest("Subsections entry map successfully created");
            /* The node representing a section entry, namely info enclosed in a single <subsection> node */
            Node subsectionEntry = subsections.item(cSubsections);
            if (subsectionEntry.getNodeType() == Node.ELEMENT_NODE) {
                /* NodeList representing all the nodes inside <subsection> */
                NodeList subsectionEntryNodes = subsectionEntry.getChildNodes();
                this.logger.finest("Subsections nodes (inside <subsection>) successfully retrieved");
                /* The subsection title */
                String subsectionTitle = null;
                /* The list of all the subsection content */
                List<Map<String, String>> subsectionContentList = new ArrayList<Map<String, String>>();
                this.logger.finest("Subsections content list successfully created");
                /* The subsection content map */
                Map<String, String> subsectionContentMap = null;
                /* Scan all the nodes and only take the sectionTitle node */
                for (int cEntriesSubsections = 0; cEntriesSubsections < subsectionEntryNodes.getLength(); cEntriesSubsections++) {
                    Node subsectionEntryNode = subsectionEntryNodes.item(cEntriesSubsections);
                    this.logger.finest("Start scanning nodes inside <subsection>");
                    /* Nodes here can be either <sectionTitle> or <entries> */
                    if (subsectionEntryNode.getNodeType() == Node.ELEMENT_NODE) {
                        /* Check whether node is section title */
                        if (subsectionEntryNode.getNodeName().equals(Constant.SECTION_TITLE_NODE_NAME)) {
                            /* The node is <sectionTitle> */
                            this.logger.finest("Node " + subsectionEntryNode.getNodeName());
                            /* Get subsection title */
                            subsectionTitle = subsectionEntryNode.getTextContent().trim();
                            this.logger.finest("Subsection title successfully retrieved: " + subsectionTitle);
                        } else if (subsectionEntryNode.getNodeName().equals(Constant.ENTRIES_NODE_NAME)) {
                            /* The node is <entries> */
                            this.logger.finest("Start scanning nodes inside subsection <entries>");
                            /* Get content inside entries */
                            NodeList subsectionEntriesNodes = subsectionEntryNode.getChildNodes();
                            this.logger.finest(
                                    "Subsections entries nodes (inside <entries>) successfully retrieved");
                            /* Scan all the entry nodes */
                            for (int cEntries = 0; cEntries < subsectionEntriesNodes.getLength(); cEntries++) {
                                Node subsectionContentEntriesNode = subsectionEntriesNodes.item(cEntries);
                                if (subsectionContentEntriesNode.getNodeType() == Node.ELEMENT_NODE) {
                                    /* The node is <entry> */
                                    this.logger.finest("Node " + subsectionContentEntriesNode.getNodeName());
                                    /* Convert <entry> node into a map */
                                    subsectionContentMap = this.entryToMap(subsectionContentEntriesNode);
                                    this.logger.finest("Subsection content: " + subsectionContentMap);
                                    /* Add to the subsectionContentList */
                                    subsectionContentList.add(subsectionContentMap);
                                    this.logger.finest("Add map to the content list: " + subsectionContentList);
                                }
                            }
                        }
                        this.logger.finest("Subsection successfully retrieved; " +
                                "subsection title is " + subsectionTitle + "; content is " + subsectionContentList);
                        /* Add to the map */
                        subsectionsMap.put(subsectionTitle, subsectionContentList);
                        this.logger.finest("Subsection content successfully added to the subsection map: " +
                                subsectionsMap);
                    }
                }
                /* Add it to the list */
                subsectionsList.add(subsectionsMap);
                this.logger.finest("Subsection map added to the subsections list: " + subsectionsList);
            }
        }
        return subsectionsList;
    }
}
