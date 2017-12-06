package fr.paquet.io.siecle;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.paquet.io.RecursiveNodes;

public abstract class XMLFileIntegration {
	private Document doc=null;
	
	public XMLFileIntegration(Document doc) {
		this.setDoc(doc);
	}
	
	protected Document getDoc() {
		return doc;
	}
	protected void setDoc(Document doc) {
		this.doc = doc;
	}

	protected ArrayList<Element> getElements(String nodeName) {
		ArrayList<Element> list=new ArrayList<Element>();
		RecursiveNodes.getNodes(getDoc(), list, nodeName);
		return list;
	}
	
	public abstract void integre(String rne) throws Exception;

}
