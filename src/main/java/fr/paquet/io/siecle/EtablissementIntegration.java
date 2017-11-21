package fr.paquet.io.siecle;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.etablissement.EtablissementFactory;
import fr.paquet.io.RecursiveNodes;

public class EtablissementIntegration extends SiecleIntegration {

	public static void CreateEtablissement(Path path) throws Exception {

		ArrayList<Element> list = new ArrayList<Element>();
		ArrayList<Element> list1 = new ArrayList<Element>();

		try {
			RecursiveNodes.getNodes(getCommunsDocument(path).getDocumentElement(), list, "PARAMETRES");
			RecursiveNodes.getNodes(getCommunsDocument(path).getDocumentElement(), list1, "DONNEES");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		if (list.size() == 1 && list1.size() == 1)
			new EtablissementFactory(list.get(0), list1.get(0));
		else
			new Exception("Les list sont trop longues");

	}
}
