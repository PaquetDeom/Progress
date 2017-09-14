package fr.paquet.io.siecle;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.EleveFactory;


public class EleveIntegration extends SiecleIntegration {

	public static void CreateEleve() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			getNodes(getEleveAvecAdresseDocument().getDocumentElement(), list, "ELEVE");
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new EleveFactory(elt);
			
		}

	
	}

}
