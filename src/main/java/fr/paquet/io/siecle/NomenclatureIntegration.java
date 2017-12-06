package fr.paquet.io.siecle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.paquet.etablissement.ClasseFactory;
import fr.paquet.etablissement.EtablissementFactory;

public class NomenclatureIntegration extends XMLFileIntegration {

	public NomenclatureIntegration(Document doc) {
		super(doc);
		
	}

	@Override
	public void integre(String rne) throws Exception {
		for (Element elt : getElements("MEF")) {
			ClasseFactory.getInstance().Load(elt, EtablissementFactory.getInstance().findEtablissementByRne(rne));
		}
		
	}

}
