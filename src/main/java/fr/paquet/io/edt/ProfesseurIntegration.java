package fr.paquet.io.edt;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.ProfesseurFactory;
import fr.paquet.io.RecursiveNodes;

public class ProfesseurIntegration extends EdtIntegration {

	public static void CreateProfesseur() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			RecursiveNodes.getNodes(getProfesseurDocument().getDocumentElement(), list, "Professeurs");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new ProfesseurFactory(elt);

		}

	}

}
