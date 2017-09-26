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
		ArrayList<Element> list1 = new ArrayList<Element>();
		ArrayList<Element> list2 = new ArrayList<Element>();
		try {
			getNodes(getCommunsDocument().getDocumentElement(), list, "PARAMETRES");
			getNodes(getCommunsDocument().getDocumentElement(), list1, "UAJ");
			
			for(Element elt1 : list1){
				if(elt1 == list.get(0).getElementsByTagName("UAJ").item(0))
					elt1 = null;
				else
					list2.add(elt1);
			}
			

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list2) {

			new ProviseurFactory(elt);

		}

	}

}
