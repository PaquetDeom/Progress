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
			
			String exp0 = "NUMERO";
			String numero = elt.getElementsByTagName(exp0).item(0).getTextContent();
			
			String exp1 = "FORMATION";
			String formation = elt.getElementsByTagName(exp1).item(0).getTextContent();
			Classe cla = new ClasseFactory(Connect.getEmf().createEntityManager()).findClasseByFormation(formation, numero);
			
			String exp2 = "NOM";
			String name = elt.getElementsByTagName(exp2).item(0).getTextContent().trim().toUpperCase();
			
			String exp3 = "PRENOM";
			String firstName = elt.getElementsByTagName(exp3).item(0).getTextContent().trim().toUpperCase();
												
			new EleveFactory(Connect.getEmf().createEntityManager()).AffectClasse(name, firstName, cla);
			
			

		}

	}



}
