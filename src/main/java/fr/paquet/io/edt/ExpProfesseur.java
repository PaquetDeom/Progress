package fr.paquet.io.edt;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.paquet.etablissement.ProfesseurFactory;
import fr.paquet.io.siecle.XMLFileIntegration;

public class ExpProfesseur extends XMLFileIntegration {

	public ExpProfesseur(Document doc) {
		super(doc);
	}

	@Override
	public void integre(String rne) {
		for (Element elt : getElements("Professeurs")) {
			ProfesseurFactory.getInstance().Load(elt, rne);
		}
	}
}
