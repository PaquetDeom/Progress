package fr.paquet.io.siecle;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.paquet.etablissement.CommuneFactory;
import fr.paquet.etablissement.GeographieFactory;

import org.w3c.dom.Element;

public class GeographieIntegration extends SiecleIntegration {

	public static void CreateGeographie() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			getNodes(getGeographiqueDocument().getDocumentElement(), list, "PAYS_NATIONALITE");
			
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

	public static void CreateCommune() {

		ArrayList<Element> list = new ArrayList<Element>();
		
		try {
			getNodes(getGeographiqueDocument().getDocumentElement(), list, "COMMUNE");
			
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