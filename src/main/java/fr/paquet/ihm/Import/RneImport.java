package fr.paquet.ihm.Import;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.vaadin.server.VaadinService;

import fr.paquet.io.siecle.RneIntegration;

public class RneImport {

	public interface RneChangedListener {
		public void rneChanged();
	}

	private static Hashtable<String, RneImport> listRneImport = new Hashtable<String, RneImport>();
	private static Path pathFolder = null;

	private String rne = null;

	private RneImport(String codeRne) throws Exception {
		setRne(codeRne);
	}

	private void setRne(String rne) throws Exception {

		// test si la valeur est nulle ou vide
		if (rne == null || rne.equals(""))
			throw new Exception("Veuillez saisir un code rne");

		// test si elle correspond a l'expression reguliere
		boolean a = false;
		a = Pattern.matches("([0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z])", rne);
		if (a == false)
			throw new Exception("Code Rne invalide");

		this.rne = rne;
	}

	/**
	 * 
	 * @return le code rne saisi par l'operateur<br/>
	 */
	public String getRne() {
		return rne;
	}

	/**
	 * 
	 * @return Le répertoire d'accueil des fichiers Siecle et EDT<br/>
	 */
	private static Path getPathFolder() {
		return Paths.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/fileFolder/");
	}

	/**
	 * 
	 * @return le chemin d'accés au repertoire d'import<br/>
	 * @throws Exception
	 *             rne ne correspond pas à la regexp<br/>
	 */
	public Path getPathFolderImport() throws Exception {
		Path path = Paths.get(getPathFolder().toString() + "/" + getRne() + "/");
		return path;
	}

	public void createDirectories() throws Exception {

		// verifie si le repertoire existe si non cree
		File file = new File(getPathFolder().toString() + "/" + getRne());
		if (!file.exists()) {
			Files.createDirectories(getPathFolderImport());
			System.out.println("Repertoire créé " + getPathFolderImport().toString());
		}
	}

	private static RneImport getRNEImportFromDirectory(String codeRNEE) throws Exception {
		RneImport rne = new RneImport(codeRNEE);
		// verifie si le repertoire existe si non cree
		File rneDirectory = new File(getPathFolder().toString() + "/" + codeRNEE);
		if (!rneDirectory.exists())
			rne.createDirectories();
		return rne;
	}

	/**
	 * 
	 * @param value
	 *            rne saisi par l'operateur<br/>
	 * @return Le rne contenu dans la table mémoire<br/>
	 * @throws Exception
	 *             rne ne correspond pas a la regexp<br/>
	 */
	public static RneImport getRneImport(String codeRNE) throws Exception {
		// recherche dans le la liste des RNEImport chargés si le RNEImport existe
		RneImport rne = listRneImport.get(codeRNE);

		// s'il n'existe pas
		if (rne == null) {
			// On le charge à partir du répertoire
			rne = getRNEImportFromDirectory(codeRNE);
		}
		return rne;
	}

	public ArrayList<XMLDocuments> getDirectoryContaint() throws Exception {
		ArrayList<XMLDocuments> directoryContaint = new ArrayList<XMLDocuments>();
		DirectoryStream<Path> stream = Files.newDirectoryStream(getPathFolderImport(), "*.xml");
		try {
			Iterator<Path> iterator = stream.iterator();
			while (iterator.hasNext()) {
				Path p = iterator.next();
				for (XMLDocuments doc : EnumSet.allOf(XMLDocuments.class)) {
					if (p.toFile().getName().toUpperCase().equals(doc.fileName().toUpperCase()))
						directoryContaint.add(doc);
				}
			}
		} finally {
			stream.close();
		}
		return directoryContaint;
	}

	public Boolean hasDocument(XMLDocuments doc) throws Exception {
		return getDirectoryContaint().contains(doc);
	}

	public boolean addDocument(File file) {
		XMLDocuments doc = XMLDocuments.getDocument(file.getName());
		return (doc != null);
	}

	public void deleteFile(String fileName) throws Exception {

		Path path = Paths.get(getPathFolderImport().toString() + fileName);

		try {

			Files.delete(path);

		} catch (NoSuchFileException nsfe) {
			nsfe.printStackTrace();
			System.err.println("Fichier ou repertoire " + path + " n'existe pas");

		} catch (DirectoryNotEmptyException dnee) {
			dnee.printStackTrace();
			System.err.println("Le repertoire " + path + " n'est pas vide");

		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.err.println("Impossible de supprimer " + path + " : " + ioe);

		}

	}

	// ** ajout du fichier dans la liste des fichiers si et seulement si le fichier
	// est autorisé
	public File createFile(String fileName) throws Exception {
		// recupéré le code document si autorisé
		XMLDocuments doc = XMLDocuments.getDocument(fileName);
		if (doc == null)
			throw (new Exception("Fichier non admis !!"));
		File file = getFile(fileName);
		file.createNewFile();
		System.out.println("Creation du fichier " + file.getAbsolutePath());
		fireChangeEvent();
		return file;
	}

	private void fireChangeEvent() {
		for (RneChangedListener listener : listeners) {
			listener.rneChanged();
		}
	}

	/**
	 * verifie le fichier telecharger, cad : vérifier que le code RNE du fichier est
	 * bien le code RNE du repertoire vérifier que la date d'export est la date
	 * d'export du RNE actuel renvoie vrai si les 2 conditions sont remplies, faux
	 * sinon.
	 * 
	 * @param fileName
	 */
	public boolean checkFile(String fileName) throws Exception {
		File file = getFile(fileName);
		String codeRNE = RneIntegration.getCodeRNE(file);
		return codeRNE.equals(getRne());
	}

	private File getFile(String fileName) throws Exception {
		return new File(getPathFolderImport().toString() + "/" + fileName);
	}

	ArrayList<RneChangedListener> listeners = null;

	public void addChangeListener(RneChangedListener documentCheckbox) {
		if (listeners == null)
			listeners = new ArrayList<RneChangedListener>();
		listeners.add(documentCheckbox);
	}

	public boolean isAllLoaded() throws Exception {
		for (XMLDocuments doc : EnumSet.allOf(XMLDocuments.class)) {
			if (!hasDocument(doc))
				return false;
		}
		return true;
	}
}
