package fr.paquet.io.siecle;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.ResponsableEleveFactory;
import fr.paquet.io.RecursiveNodes;

public class ResponsableIntegration extends SiecleIntegration {

	public static void CreateResponsable() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			RecursiveNodes.getNodes(getResponsableAvecAdresseDocument().getDocumentElement(), list, "PERSONNE");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new ResponsableEleveFactory(elt);

		}

	}

}
