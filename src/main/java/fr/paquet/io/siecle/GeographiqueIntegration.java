package fr.paquet.io.siecle;

import org.w3c.dom.*;

import fr.paquet.etablissement.CommuneFactory;
import fr.paquet.etablissement.GeographieFactory;
import fr.paquet.io.siecle.XMLFileIntegration;

public class GeographiqueIntegration extends XMLFileIntegration {

	public GeographiqueIntegration(Document doc) {
		super(doc);
	}

	@Override
	public void integre() {
		for(Element elt:getElements("PAYS_NATIONALITE")) {
			GeographieFactory.getInstance().Load(elt);
		}
		for(Element elt: getElements("COMMUNE")) {
			CommuneFactory.getInstance().Load(elt);
		}
			
	}
}
