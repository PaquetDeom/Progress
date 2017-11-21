package fr.paquet.io.siecle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	public ArrayList<Hashtable<String, Document>> getDocuments()
			throws SAXException, IOException, ParserConfigurationException {

		ArrayList<Hashtable<String, Document>> list = new ArrayList<Hashtable<String, Document>>();

		for (XMLDocuments doc : EnumSet.allOf(XMLDocuments.class)) {
			Hashtable<String, Document> hash = new Hashtable<String, Document>();
			File file = new File(doc.fileName());
			hash.put(doc.fileName(), getDocumentBuilder().parse(file));
			list.add(hash);
		}

		return list;
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

}
