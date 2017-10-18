package fr.paquet.io.edt;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import fr.paquet.dataBase.Connect;
import fr.paquet.etablissement.Classe;
import fr.paquet.etablissement.ClasseFactory;
import fr.paquet.etablissement.Eleve;
import fr.paquet.etablissement.EleveFactory;
import fr.paquet.io.RecursiveNodes;

public class AffectEleve extends EdtIntegration {

	public static void AffectClasse() {

		ArrayList<Element> list = new ArrayList<Element>();
		try {
			RecursiveNodes.getNodes(getEleveDocument().getDocumentElement(), list, "Eleves");

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		for (Element elt : list) {
			String exp1 = "FORMATION";
			String str1 = elt.getElementsByTagName(exp1).item(0).getTextContent();
			Classe cla = new ClasseFactory(Connect.getEmf().createEntityManager()).findClasseByFormation(str1);
			
			String exp2 = "NOM";
			String name = elt.getElementsByTagName(exp2).item(0).getTextContent();
			
			String exp3 = "PRENOM";
			String firstName = elt.getElementsByTagName(exp3).item(0).getTextContent();
			
			Eleve elv = new EleveFactory(Connect.getEmf().createEntityManager()).FindByNameAndFirstName(name.trim().toUpperCase(), firstName.trim().toUpperCase());
			
			elv.addClasse(cla);
			new EleveFactory(Connect.getEmf().createEntityManager()).AffectClasse(elv);
			
			

		}

	}



}
