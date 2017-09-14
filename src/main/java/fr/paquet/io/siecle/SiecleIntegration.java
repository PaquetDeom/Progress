package fr.paquet.io.siecle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SiecleIntegration {

	/**
	 * @author Nathanaël
	 * 
	 *         La class est mere des class d'integration de donnees dans la
	 *         DB<br/>
	 *         Elle gere tous les fichiers siecle<br/>
	 *         Elle gere les XPath<br/>
	 * 
	 */
	private static DocumentBuilderFactory factory = null;
	private static DocumentBuilder builder = null;

	private static File CommunsFile = null;
	private static File EleveAvecAdresseFile = null;
	private static File EtablissementsFile = null;
	private static File GeographiqueFile = null;
	private static File NomenclatureFile = null;
	private static File ResponsableAvecAdresseFile = null;
	private static File StructuresFile = null;

	private static Document CommunsDocument = null;
	private static Document EleveAvecAdresseDocument = null;
	private static Document EtablissementsDocument = null;
	private static Document GeographiqueDocument = null;
	private static Document NomenclatureDocument = null;
	private static Document ResponsableAvecAdresseDocument = null;
	private static Document StructuresDocument = null;

	private static Element CommunsRoot = null;
	private static Element EleveAvecAdresseRoot = null;
	private static Element EtablissementsRoot = null;
	private static Element GeographiqueRoot = null;
	private static Element NomenclatureRoot = null;
	private static Element ResponsableAvecAdresseRoot = null;
	private static Element StructuresRoot = null;

	/**
	 * 
	 * @return le Fichier Communs.xml<br/>
	 */
	private static File getCommuns() {
		if (CommunsFile == null)
			CommunsFile = new File("./Siecle/0310053P/Communs.xml");
		return CommunsFile;
	}

	/**
	 * 
	 * @return le Fichier EleveAvecAdresse.xml<br/>
	 */
	private static File getEleveAvecAdresse() {
		if (EleveAvecAdresseFile == null)
			EleveAvecAdresseFile = new File("./Siecle/0310053P/ElevesAvecAdresses.xml");
		return EleveAvecAdresseFile;
	}

	/**
	 * 
	 * @return le Fichier Etablissements.xml<br/>
	 */
	private static File getEtablissements() {
		if (EtablissementsFile == null)
			EtablissementsFile = new File("./Siecle/Etablissements.xml");
		return EtablissementsFile;
	}

	/**
	 * 
	 * @return le Fichier Geographique.xml<br/>
	 */
	private static File getGeographique() {
		if (GeographiqueFile == null)
			GeographiqueFile = new File("./Siecle/Geographique.xml");
		return GeographiqueFile;
	}

	/**
	 * 
	 * @return le Fichier Nomenclature.xml<br/>
	 */
	private static File getNomenclature() {
		if (NomenclatureFile == null)
			NomenclatureFile = new File("./Siecle/Nomenclature.xml");
		return NomenclatureFile;
	}

	/**
	 * 
	 * @return le Fichier ResponsableAvecAdresse.xml<br/>
	 */
	private static File getResponsableAvecAdresse() {
		if (ResponsableAvecAdresseFile == null)
			ResponsableAvecAdresseFile = new File("./Siecle/0310053P/ResponsablesAvecAdresses.xml");
		return ResponsableAvecAdresseFile;
	}

	/**
	 * 
	 * @return le Fichier Structures.xml<br/>
	 */
	private static File getStructures() {
		if (StructuresFile == null)
			StructuresFile = new File("./Siecle/0310053P/Structures.xml");
		return StructuresFile;
	}

	/**
	 * 
	 * @return Le document issus de Communs.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getCommunsDocument() throws SAXException, IOException, ParserConfigurationException {
		if (CommunsDocument == null)
			CommunsDocument = getDocumentBuilder().parse(getCommuns());
		return CommunsDocument;
	}

	/**
	 * 
	 * @return Le document issus de EleveAvecAdresse.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getEleveAvecAdresseDocument()
			throws SAXException, IOException, ParserConfigurationException {
		if (EleveAvecAdresseDocument == null)
			EleveAvecAdresseDocument = getDocumentBuilder().parse(getEleveAvecAdresse());
		return EleveAvecAdresseDocument;
	}

	/**
	 * 
	 * @return Le document issus de Etablissements.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document geEtablissementsDocument() throws SAXException, IOException, ParserConfigurationException {
		if (EtablissementsDocument == null)
			EtablissementsDocument = getDocumentBuilder().parse(getEtablissements());
		return EtablissementsDocument;
	}

	/**
	 * 
	 * @return Le document issus de Geographique.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getGeographiqueDocument() throws SAXException, IOException, ParserConfigurationException {
		if (GeographiqueDocument == null)
			GeographiqueDocument = getDocumentBuilder().parse(getGeographique());
		return GeographiqueDocument;
	}

	/**
	 * 
	 * @return Le document issus de Nomenclature.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getNomenclatureDocument() throws SAXException, IOException, ParserConfigurationException {
		if (NomenclatureDocument == null)
			NomenclatureDocument = getDocumentBuilder().parse(getNomenclature());
		return NomenclatureDocument;
	}

	/**
	 * 
	 * @return Le document issus de ResponsableAvecAdresse.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getResponsableAvecAdresseDocument()
			throws SAXException, IOException, ParserConfigurationException {
		if (ResponsableAvecAdresseDocument == null)
			ResponsableAvecAdresseDocument = getDocumentBuilder().parse(getResponsableAvecAdresse());
		return ResponsableAvecAdresseDocument;
	}

	/**
	 * 
	 * @return Le document issus de Structures.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getStructuresDocument() throws SAXException, IOException, ParserConfigurationException {
		if (StructuresDocument == null)
			StructuresDocument = getDocumentBuilder().parse(getStructures());
		return StructuresDocument;
	}

	/**
	 * 
	 * @return L'Element issus de Communs.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getCommunsRoot() throws SAXException, IOException, ParserConfigurationException {
		if (CommunsRoot == null)
			CommunsRoot = getCommunsDocument().getDocumentElement();
		return CommunsRoot;
	}

	/**
	 * 
	 * @return L'Element issus de EleveAvecAdresse.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getEleveAvecAdresseRoot() throws SAXException, IOException, ParserConfigurationException {
		if (EleveAvecAdresseRoot == null)
			EleveAvecAdresseRoot = getEleveAvecAdresseDocument().getDocumentElement();
		return EleveAvecAdresseRoot;
	}

	/**
	 * 
	 * @return L'Element issus de Geographique.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getGeographiqueRoot() throws SAXException, IOException, ParserConfigurationException {
		if (GeographiqueRoot == null)
			GeographiqueRoot = getGeographiqueDocument().getDocumentElement();
		return GeographiqueRoot;
	}

	/**
	 * 
	 * @return L'Element issus de Etablissements.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getEtablissementsRoot() throws SAXException, IOException, ParserConfigurationException {
		if (EtablissementsRoot == null)
			EtablissementsRoot = geEtablissementsDocument().getDocumentElement();
		return EtablissementsRoot;
	}

	/**
	 * 
	 * @return L'Element issus de Communs.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getNomenclatureRoot() throws SAXException, IOException, ParserConfigurationException {
		if (NomenclatureRoot == null)
			NomenclatureRoot = getNomenclatureDocument().getDocumentElement();
		return NomenclatureRoot;
	}

	/**
	 * 
	 * @return L'Element issus de ResponsableAvecAdresse.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getResponsableAvecAdresseRoot()
			throws SAXException, IOException, ParserConfigurationException {
		if (ResponsableAvecAdresseRoot == null)
			ResponsableAvecAdresseRoot = getResponsableAvecAdresseDocument().getDocumentElement();
		return ResponsableAvecAdresseRoot;
	}

	/**
	 * 
	 * @return L'Element issus de Structures.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getStructuresRoot() throws SAXException, IOException, ParserConfigurationException {
		if (StructuresRoot == null)
			StructuresRoot = getStructuresDocument().getDocumentElement();
		return StructuresRoot;
	}

	/**
	 * 
	 * @return le DocumentBuilderFactory<br/>
	 */
	public static DocumentBuilderFactory getDocumentBuilderFactory() {
		if (factory == null)
			factory = DocumentBuilderFactory.newInstance();
		return factory;
	}

	/**
	 * 
	 * @return Le DocumentBuilder<br/>
	 * @throws ParserConfigurationException
	 *             si la source est fausse<br/>
	 */
	public static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		if (builder == null)
			builder = getDocumentBuilderFactory().newDocumentBuilder();
		return builder;
	}

	public static void getNodes(Node n, ArrayList<Element> listElement, String node) {

		if (n instanceof Element) {
			Element element = (Element) n;
			if (n.getNodeName().equals(node))
				listElement.add(element);

			// Nous allons maintenant traiter les nœuds enfants du nœud en cours
			// de traitement
			int nbChild = n.getChildNodes().getLength();
			// Nous récupérons la liste des nœuds enfants
			NodeList list = n.getChildNodes();

			// nous parcourons la liste des nœuds
			for (int i = 0; i < nbChild; i++) {
				Node n2 = list.item(i);

				// si le nœud enfant est un Element, nous le traitons
				if (n2 instanceof Element) {
					// appel récursif à la méthode pour le traitement du nœud et
					// de ses enfants
					getNodes(n2, listElement, node);
				}
			}
		}
	}

}
