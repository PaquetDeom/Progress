package fr.paquet.io.siecle;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.EtablissementFactory;

public class EtablissementIntegration extends SiecleIntegration {

	public static void CreateEtablissement() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			getNodes(geEtablissementsDocument().getDocumentElement(), list, "ETABLISSEMENT");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new EtablissementFactory(elt);

		}

	}

}
