package fr.paquet.io.siecle;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.paquet.etablissement.CommuneFactory;
import fr.paquet.etablissement.GeographieFactory;
import fr.paquet.ihm.Import.RneImport;
import fr.paquet.io.RecursiveNodes;

import org.w3c.dom.Element;

public class GeographieIntegration extends RneIntegration {

	public GeographieIntegration(RneImport rne) throws SAXException, IOException, ParserConfigurationException {
		super();
		
	}

	private static void CreateGeographie() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			RecursiveNodes.getNodes(getGeographiqueDocument(path).getDocumentElement(), list, "PAYS_NATIONALITE");
			

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new GeographieFactory(elt);

		}

	}

	public static void CreateCommune(Path path) {

		ArrayList<Element> list = new ArrayList<Element>();

		try {
			RecursiveNodes.getNodes(getGeographiqueDocument(path).getDocumentElement(), list, "COMMUNE");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new CommuneFactory(elt);

		}

	}

}