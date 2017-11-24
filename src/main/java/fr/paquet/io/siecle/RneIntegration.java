package fr.paquet.io.siecle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.GeographieFactory;
import fr.paquet.ihm.Import.RneImport;
import fr.paquet.ihm.Import.XMLDocuments;
import fr.paquet.io.RecursiveNodes;

public class RneIntegration {

	/**
	 * @author Nathanaël
	 * 
	 * 
	 *         Class qui gere tous les fichiers siecle<br/>
	 * 
	 * 
	 */
	private static DocumentBuilderFactory factory = null;
	private static DocumentBuilder builder = null;
	private XMLDocuments doc = null;
	private Path path = null;

	public RneIntegration(XMLDocuments doc, String path) {
		super();
		setDoc(doc);
		setPath(path);
	}

	/**
	 * 
	 * @param file
	 *            *.xml
	 * @return Le code rne du File<br/>
	 * @throws ParserConfigurationException
	 *             si la source est fausse<br/>
	 */
	public static String getCodeRNE(File file) throws Exception {
		Document doc = getDocumentBuilder().parse(file);
		return getCodeRNE(doc);
	}

	/**
	 * 
	 * @param document
	 * @return le code rne du Document<br/>
	 */
	private static String getCodeRNE(Document document) {
		ArrayList<Element> list = new ArrayList<Element>();
		RecursiveNodes.getNodes(document.getDocumentElement(), list, "PARAMETRES");
		String exp1 = "UAJ";
		String rne = list.get(0).getElementsByTagName(exp1).item(0).getTextContent();
		return rne;
	}

	/**
	 * 
	 * @return une ArrayList Hashtable de fileName et Document<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public Document getDocument()
			throws SAXException, IOException, ParserConfigurationException {
			File file = new File(getPath().toString());
			return getDocumentBuilder().parse(file);
	}
	

	/**
	 * 
	 * @return le DocumentBuilderFactory<br/>
	 */
	private static DocumentBuilderFactory getDocumentBuilderFactory() {
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
	private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		if (builder == null)
			builder = getDocumentBuilderFactory().newDocumentBuilder();
		return builder;
	}

	public XMLDocuments getDoc() {
		return doc;
	}

	public void setDoc(XMLDocuments doc) {
		this.doc = doc;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(String path) {
		Path path0 = Paths.get(path);
		this.path = path0;
	}

	public void integre() throws Exception {
		XMLFileIntegration integrator=doc.getIntegrator().newInstance();
		integrator.integre();
	}
}
