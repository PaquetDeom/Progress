package fr.paquet.io.siecle;

import javax.print.Doc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.paquet.etablissement.CommuneFactory;
import fr.paquet.etablissement.EtablissementFactory;
import fr.paquet.etablissement.GeographieFactory;
import fr.paquet.etablissement.ProviseurFactory;

public class CommunIntegration extends XMLFileIntegration {

	public CommunIntegration(Document doc) {
		super(doc);
	}

	@Override
	public void integre() {
		for (Element elt : getElements("DONNEES")) {
			EtablissementFactory.getInstance().Load(elt);
		}
		
		for (Element elt : getElements("PARAMETRES")) {
			ProviseurFactory.getInstance().Load(elt);
		}
		
	}

}
