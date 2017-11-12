package fr.paquet.io.siecle;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.ClasseFactory;
import fr.paquet.io.RecursiveNodes;

public class ClasseIntegration extends SiecleIntegration {

	public static void CreateClasse() {
		ArrayList<Element> list = new ArrayList<Element>();
		ArrayList<Element> list1 = new ArrayList<Element>();
		try {
			RecursiveNodes.getNodes(getNomenclatureDocument().getDocumentElement(), list, "MEF");
			RecursiveNodes.getNodes(getNomenclatureDocument().getDocumentElement(), list1, "UAJ");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {

			new ClasseFactory(elt, list1.get(0).getTextContent());
		}
	}
}
