package fr.paquet.ihm.Import;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import fr.paquet.ihm.AlertListener;

@SuppressWarnings("serial")
public class WindowImport extends Window implements Button.ClickListener {

	private VerticalLayout mainLayout = null;
	private Button okButton = null;
	public OkButtonWindowImport okListener = null;

	public WindowImport(VerticalLayout layout, String okButton) {
		super();
		setOkButton(okButton);
		setMainLayout(layout);
		BuildWindow();
	}

	private void setOkButton(String button) {

		this.okButton = new Button(button);
	}

	private void setMainLayout(VerticalLayout layout) {
		this.mainLayout = layout;
	}

	private VerticalLayout getMainLayout() {
		return mainLayout;
	}

	private void BuildWindow() {

		setCaption("integration des fichiers dans base");
		setSizeUndefined();
		setWidth(600.0f, Unit.PIXELS);
		center();
		setModal(true);

		final FormLayout content = new FormLayout();
		content.setMargin(true);
		VerticalLayout layout = new VerticalLayout();

		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		getOkButton().addClickListener(this);
		hLayout.addComponent(getOkButton());
		hLayout.addComponent(getAnnulButton());

		layout.addComponent(getMainLayout());
		layout.addComponent(hLayout);
		content.addComponent(layout);
		setContent(content);

		setVisible(true);

	}

	private Button getOkButton() {

		return okButton;
	}

	private Button getAnnulButton() {

		Button button = new Button("Annuler");

		// listener
		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				close();

			}
		});

		return button;
	}

	@Override
	public void buttonClick(ClickEvent event) {

		Button btn = (Button) event.getSource();
		okListener.buttonClick(btn.getCaption());

		close();

	}

}
