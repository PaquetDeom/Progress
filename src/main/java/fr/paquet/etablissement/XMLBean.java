package fr.paquet.etablissement;

import java.util.Date;

import org.w3c.dom.Element;

import com.ibm.icu.text.SimpleDateFormat;

public class XMLBean {
	private Element elt;

	public XMLBean(Element elt) {
		this.elt = elt;
	}

	protected String getStringFromXml(String tag) throws Exception {
		return elt.getElementsByTagName(tag).item(0).getTextContent();
	}

	protected Date getDateFromXml(String tag) throws Exception {
		return getDate(elt.getElementsByTagName(tag).item(0).getTextContent());
	}

	protected int getIdFromXml(String tag) {
		return getInt(elt.getAttribute(tag));
	}
	
	private int getInt(String integer) {
		return Integer.parseInt(integer);
	}

	protected Date getDate(String date) throws Exception {
		return new SimpleDateFormat("dd/MM/yyyy").parse(date);
	}

}
