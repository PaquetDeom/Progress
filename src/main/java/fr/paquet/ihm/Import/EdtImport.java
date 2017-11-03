package fr.paquet.ihm.Import;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class EdtImport extends VerticalLayout implements SucceededListener {

	private XMLImportView xmlView = null;

	private File CommunsFile = null;
	private File EleveAvecAdresseFile = null;
	private File EtablissementsFile = null;
	private File GeographiqueFile = null;
	private File NomenclatureFile = null;
	private File StructuresFile = null;

	public EdtImport(XMLImportView xml) {
		super();
		setXMLImportView(xml);

		// construction du layout
		setSizeFull();

		Label label = new Label("integration du fichier Communs.xml");
		Upload upload1 = new Upload(null, new Upload.Receiver() {

			public java.io.OutputStream receiveUpload(String filename, String mimeType) {

				File Communs = new File(getXmlView().getPathSiecleFolder() + "Communs.xml");

				try {
					Communs.createNewFile();
					System.out.println("Creation du fichier " + Communs.getAbsolutePath());
					return new FileOutputStream(Communs);

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		upload1.addSucceededListener(this);
		addComponent(label);
		addComponent(upload1);

		Layout emptyScreen = new VerticalLayout();

		addComponent(emptyScreen);
		setExpandRatio(emptyScreen, 1.0f);
	}

	private void setXMLImportView(XMLImportView xml) {
		this.xmlView = xml;

	}

	private XMLImportView getXmlView() {
		return xmlView;
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub

	}

}
