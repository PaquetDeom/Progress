package fr.paquet.ihm.integration;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import fr.paquet.framework.ProgContainer;
import fr.paquet.framework.ProgFactory;
import fr.paquet.framework.ResourceScanner;
import fr.paquet.framework.authentication.User;
import fr.paquet.framework.ui.ProgView;

public class XMLIntegration extends VerticalLayout implements ProgView, SucceededListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File eleveFile = null;

	public XMLIntegration() {
		setSizeFull();
		Label label = new Label("integration de fichier élèves");
		Upload upload1 = new Upload(null, new Upload.Receiver() {
			public java.io.OutputStream receiveUpload(String filename, String mimeType) {
				String absPath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/";
				SecureRandom random = new SecureRandom();
				eleveFile = new File(absPath + "fileFolder/" + new BigInteger(130, random).toString(32) + filename);
				try {
					eleveFile.createNewFile();
					System.out.println("Creation du fichier " + eleveFile.getAbsolutePath());
					return new FileOutputStream(eleveFile);
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

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Integration XML";
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return "Integration XML";
	}

	private void showNotification(Notification notification) {
		// keep the notification visible a little while after moving the
		// mouse, or until clicked
		notification.setDelayMsec(10000);
		notification.show(Page.getCurrent());
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub

	}
}
