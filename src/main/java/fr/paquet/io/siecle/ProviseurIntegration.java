package fr.paquet.io.siecle;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.ProviseurFactory;

public class ProviseurIntegration extends SiecleIntegration {

	public static void CreateProviseur() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			getNodes(getCommunsDocument().getDocumentElement(), list, "UAJ");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new ProviseurFactory(elt);

		}

	}

}
