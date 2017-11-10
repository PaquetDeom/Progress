package fr.paquet.ihm.Import;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.vaadin.server.VaadinService;

public class RneImport {

	/**
	 * @author Nathanaël
	 * 
	 *         Classe qui gere la creation des Files par code Rne
	 *         D'etablissement<br/>
	 */

	private static List<File> files = new ArrayList<File>();
	private static boolean communs = false;
	private static boolean eleveAvecAdresse = false;
	private static boolean etablissements = false;
	private static boolean geographique = false;
	private static boolean nomenclature = false;
	private static boolean structures = false;
	private static boolean siecle = false;

	private static boolean EXP_PROFESSEURF = false;
	private static boolean EXP_MATIERE = false;
	private static boolean EXP_ELEVE = false;
	private static boolean edt = false;

	private static Hashtable<String, RneImport> listRneImport = new Hashtable<String, RneImport>();
	private static String rne = null;

	private static Path pathFolder = null;

	public RneImport(String value) throws Exception {

		// verifie la syntaxe de rne
		// creer le path a partir du code rne
		setRne(value);
		setPathFolder();

		// recherche si le repertoire RNE EXISTE
		// si non ==> on créer le repertoire
		if (getRne(getRne()) == null) {
			CreateDirectories();
		}

		else {
			// si oui, on lit la liste des fichiers déja présent dans le répertoire et on
			// les marque comme chargés
			DirectoryContaint(getPathFolder());
			SetCharged();
		}

	}

	private static void SetCharged() {

		for (int i = 0; i < getFiles().size(); i++) {
			File file = getFiles().get(0);
			if (file.toString().equals("Communs.xml"))
				setCommuns(true);
			if (file.toString().equals("EleveAvecAdresse.xml"))
				setEleveAvecAdresse(true);
			if (file.toString().equals("Etablissements.xml"))
				setEtablissements(true);
			if (file.toString().equals("Geographique.xml"))
				setGeographique(true);
			if (file.toString().equals("Nomenclature.xml"))
				setNomenclature(true);
			if (file.toString().equals("Structures.xml"))
				setStructures(true);
			if (file.toString().equals("EXP_PROFESSEUR.xml"))
				setEXP_PROFESSEURF(true);
			if (file.toString().equals("EXP_MATIERE.xml"))
				setEXP_MATIERE(true);
			if (file.toString().equals("EXP_ELEVE.xml"))
				setEXP_ELEVE(true);

		}

	}

	public static void setRne(String rne) throws Exception {

		// test si la valeur est nulle ou vide
		if (rne == null || rne.equals(""))
			throw new Exception("Veuillez saisir un code rne");

		// test si elle correspond a l'expression reguliere
		boolean a = false;
		a = Pattern.matches("([0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z])", rne);
		if (a == false)
			throw new Exception("Code Rne invalide");

		RneImport.rne = rne;
	}

	/**
	 * 
	 * @return le code rne saisi par l'operateur<br/>
	 */
	public static String getRne() {

		return rne;
	}

	public static void setPathFolder() {

		pathFolder = Paths
				.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/" + getRne());

	}

	/**
	 * 
	 * @return Le répertoire d'accueil des fichiers Siecle et EDT<br/>
	 */
	public static Path getPathFolder() {

		return pathFolder;
	}

	public static void CreateDirectories() throws IOException {

		// verifie si le repertoire existe si non cree
		File file = new File(getPathFolder().toString());
		if (!file.exists()) {
			Files.createDirectories(getPathFolder());
			System.out.println("Repertoire créé " + getPathFolder().toString());
		} else {
			DirectoryContaint(getPathFolder());
		}
	}

	/**
	 * 
	 * @param value
	 *            rne saisi par l'operateur<br/>
	 * @return Le rne contenu dans la table mémoire<br/>
	 * @throws Exception
	 *             rne ne correspond pas a la regexp<br/>
	 */
	public static RneImport getRne(String value) throws Exception {
		// recherche dans le tableau si le RNEImport existe
		RneImport rne = listRneImport.get(value);
		if (rne == null) {
			rne = new RneImport(value);
			listRneImport.put(value, rne);
		}
		return rne;
	}

	public static void DirectoryContaint(Path path) throws IOException {

		DirectoryStream<Path> stream = Files.newDirectoryStream(path);
		try {
			Iterator<Path> iterator = stream.iterator();
			while (iterator.hasNext()) {
				Path p = iterator.next();
				addFile(p.toFile());
				System.out.println(p);
			}
		} finally {
			stream.close();
		}

	}

	/**
	 * 
	 * @return les files contenues dans le repertoire<br/>
	 */
	private static List<File> getFiles() {
		if (files == null)
			files = new ArrayList<File>();
		return files;
	}

	private static void addFile(File file) {
		getFiles().add(file);
	}

	public static boolean isCommuns() {
		return communs;
	}

	private static void setCommuns(boolean communs) {
		RneImport.communs = communs;
	}

	public static boolean isEleveAvecAdresse() {
		return eleveAvecAdresse;
	}

	private static void setEleveAvecAdresse(boolean eleveAvecAdresse) {
		RneImport.eleveAvecAdresse = eleveAvecAdresse;
	}

	public static boolean isEtablissements() {
		return etablissements;
	}

	private static void setEtablissements(boolean etablissements) {
		RneImport.etablissements = etablissements;
	}

	public static boolean isGeographique() {
		return geographique;
	}

	private static void setGeographique(boolean geographique) {
		RneImport.geographique = geographique;
	}

	public static boolean isNomenclature() {
		return nomenclature;
	}

	private static void setNomenclature(boolean nomenclature) {
		RneImport.nomenclature = nomenclature;
	}

	public static boolean isStructures() {
		return structures;
	}

	private static void setStructures(boolean structures) {
		RneImport.structures = structures;
	}

	public static boolean isSiecle() {

		if (isCommuns() && isEleveAvecAdresse() && isEtablissements() && isGeographique() && isNomenclature()
				&& isStructures())
			siecle = true;
		else
			siecle = false;
		return siecle;
	}

	public static boolean isEXP_PROFESSEURF() {
		return EXP_PROFESSEURF;
	}

	private static void setEXP_PROFESSEURF(boolean eXP_PROFESSEURF) {
		EXP_PROFESSEURF = eXP_PROFESSEURF;
	}

	public static boolean isEXP_MATIERE() {
		return EXP_MATIERE;
	}

	private static void setEXP_MATIERE(boolean eXP_MATIERE) {
		EXP_MATIERE = eXP_MATIERE;
	}

	public static boolean isEXP_ELEVE() {
		return EXP_ELEVE;
	}

	private static void setEXP_ELEVE(boolean eXP_ELEVE) {
		EXP_ELEVE = eXP_ELEVE;
	}

	public static boolean isEdt() {

		if (isEXP_ELEVE() && isEXP_MATIERE() && isEXP_PROFESSEURF())
			edt = true;
		else
			edt = false;

		return edt;
	}

}
