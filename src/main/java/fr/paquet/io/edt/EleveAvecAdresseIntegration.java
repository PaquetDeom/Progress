package fr.paquet.io.edt;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.paquet.etablissement.EleveFactory;
import fr.paquet.io.siecle.XMLFileIntegration;

public class EleveAvecAdresseIntegration extends XMLFileIntegration {

	public EleveAvecAdresseIntegration(Document doc) {
		super(doc);

	}

	@Override
	public void integre() {
		for (Element elt : getElements("ELEVE")) {
			EleveFactory.getInstance().Load(elt);
		}

	}

}
