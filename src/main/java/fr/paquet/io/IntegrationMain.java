package fr.paquet.io;

import java.nio.file.Path;

import fr.paquet.ihm.Import.RneImport;
import fr.paquet.io.edt.AffectEleve;
import fr.paquet.io.edt.ProfesseurIntegration;
import fr.paquet.io.siecle.ClasseIntegration;
import fr.paquet.io.siecle.EleveIntegration;
import fr.paquet.io.siecle.EtablissementIntegration;
import fr.paquet.io.siecle.GeographieIntegration;
import fr.paquet.io.siecle.ProviseurIntegration;

public class IntegrationMain {

	long start = 0;
	long duree = 0;

	public IntegrationMain(RneImport rne) {
		super();
		try {
			integration(rne.getPathFolderImport());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void integration(Path path) {
		start = System.nanoTime();

		GeographieIntegration.CreateGeographie(path);
		GeographieIntegration.CreateCommune(path);
		System.out.println(" ");
		System.out.println("-- Geographique fait --");

		try

		{
			EtablissementIntegration.CreateEtablissement(path);
			System.out.println(" ");
			System.out.println("-- Etablissements fait --");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" ");
			System.out.println("-- Etablissements non fait --");
		}

		ProviseurIntegration.CreateProviseur(path);
		System.out.println(" ");
		System.out.println("-- Proviseurs fait --");

		ClasseIntegration.CreateClasse(path);
		System.out.println(" ");
		System.out.println("-- Nomenclature fait --");

		EleveIntegration.CreateEleve(path);
		AffectEleve.AffectClasse(path);
		System.out.println(" ");
		System.out.println("-- Eleve fait --");

		ProfesseurIntegration.CreateProfesseur(path);
		System.out.println(" ");
		System.out.println("-- Professeur fait --");

		duree = (System.nanoTime() - start) / 1000000000;

		System.out.println(" ");
		System.out.println("-- Siècle fait --");
		System.out.println("-- EDT fait --");
		System.out.println(duree + "secondes");
		System.out.println("- Fin -");
	}

}
