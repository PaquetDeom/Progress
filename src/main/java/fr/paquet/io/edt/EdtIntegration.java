package fr.paquet.io.edt;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class EdtIntegration {

	/**
	 * @author Nathanaël
	 * 
	 * 
	 *         Class qui gere tous les fichiers Edt<br/>
	 * 
	 * 
	 */
	private static DocumentBuilderFactory factory = null;
	private static DocumentBuilder builder = null;

	private static File EXP_PROFESSEURFile = null;
	private static File EXP_MATIEREFile = null;

	private static Document EXP_PROFESSEURDocument = null;
	private static Document EXP_MATIEREDocument = null;

	private static Element EXP_PROFESSEURRoot = null;
	private static Element EXP_MATIERERoot = null;

	/**
	 * 
	 * @return le Fichier EXP_PROFESSEUR.xml<br/>
	 */
	private static File getProfesseur() {
		if (EXP_PROFESSEURFile == null)
			EXP_PROFESSEURFile = new File("./EDT/0310053P/EXP_PROFESSEUR.xml");
		return EXP_PROFESSEURFile;
	}

	/**
	 * 
	 * @return le Fichier EXP_MATIERE.xml<br/>
	 */
	private static File getMatiere() {
		if (EXP_MATIEREFile == null)
			EXP_MATIEREFile = new File("./EDT/0310053P/EXP_MATIERE.xml");
		return EXP_MATIEREFile;
	}

	/**
	 * 
	 * @return Le document issus de EXP_PROFESSEUR.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getProfesseurDocument() throws SAXException, IOException, ParserConfigurationException {
		if (EXP_PROFESSEURDocument == null)
			EXP_PROFESSEURDocument = getDocumentBuilder().parse(getProfesseur());
		return EXP_PROFESSEURDocument;
	}

	/**
	 * 
	 * @return Le Document issu de EXP_MATIERE.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document getMatiereDocument() throws SAXException, IOException, ParserConfigurationException {
		if (EXP_MATIEREDocument == null)
			EXP_MATIEREDocument = getDocumentBuilder().parse(getMatiere());
		return EXP_MATIEREDocument;
	}

	/**
	 * 
	 * @return L'Element issus de EXP_PROFESSEUR.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getProfesseurRoot() throws SAXException, IOException, ParserConfigurationException {
		if (EXP_PROFESSEURRoot == null)
			EXP_PROFESSEURRoot = getProfesseurDocument().getDocumentElement();
		return EXP_PROFESSEURRoot;
	}

	/**
	 * 
	 * @return L'Element issu de EXP_MATIERE.xml<br/>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Element getMatiereRoot() throws SAXException, IOException, ParserConfigurationException {
		if (EXP_MATIERERoot == null)
			EXP_MATIERERoot = getMatiereDocument().getDocumentElement();
		return EXP_MATIERERoot;
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
