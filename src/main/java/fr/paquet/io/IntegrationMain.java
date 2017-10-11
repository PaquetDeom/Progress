package fr.paquet.io;

import fr.paquet.io.siecle.ClasseIntegration;
import fr.paquet.io.siecle.CoordonneeEleveIntegration;
import fr.paquet.io.siecle.CoordonneeResponsableIntegration;
import fr.paquet.io.siecle.EleveIntegration;
import fr.paquet.io.siecle.EtablissementIntegration;
import fr.paquet.io.siecle.GeographieIntegration;
import fr.paquet.io.siecle.ProviseurIntegration;
import fr.paquet.io.siecle.ResponsableIntegration;

public class IntegrationMain {

	public static void main(String[] args) {
		long start = 0;
		long duree = 0;

		start = System.nanoTime();

		GeographieIntegration.CreateGeographie();
		GeographieIntegration.CreateCommune();
		System.out.println(" ");
		System.out.println("-- Geographique fait --");

		ClasseIntegration.CreateClasse();
		System.out.println(" ");
		System.out.println("-- Nomenclature fait --");
			
		try {
			EtablissementIntegration.CreateEtablissement();
			System.out.println(" ");
			System.out.println("-- Etablissements fait --");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" ");
			System.out.println("-- Etablissements non fait --");
		}
		
		
		ProviseurIntegration.CreateProviseur();
		System.out.println(" ");
		System.out.println("-- Proviseurs fait --");
		
		CoordonneeResponsableIntegration.CreateCoordonnee();
		CoordonneeEleveIntegration.CreateCoordonnee();
		System.out.println(" ");
		System.out.println("-- Coordonnees fait --");

		EleveIntegration.CreateEleve();

		System.out.println(" ");
		System.out.println("-- Eleve fait --");

		ResponsableIntegration.CreateResponsable();

		duree = (System.nanoTime() - start) / 1000000000;

		System.out.println(" ");
		System.out.println(duree + "secondes");
		System.out.println("- Fin -");
	}

}