package xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import formas.Formas;
import grafico.CirculoGr;
import grafico.LinhaGr;
import grafico.LinhaPoligonalGr;
import grafico.PoligonoGr;
import grafico.PontoGr;
import grafico.RetanguloGr;
import grafico.TrianguloGr;
import javafx.scene.layout.Pane;

public class Xml {
	File arqXml;
	BufferedWriter escritor;
	BufferedReader leitor;
	
	public Xml(File f) {
		this.arqXml = f;
	}
	
	public void escreverXml(ArrayList<Formas> formas) throws IOException {
		String xmlContent = "<Figura>";
		escritor = new BufferedWriter(new FileWriter(arqXml));
		
		for (Formas f : formas) {
			switch(f.getClass().getName()) {
			
				case "grafico.PontoGr":{
					xmlContent = xmlContent + PontoXML.salvarPonto((PontoGr) f);
					break;
				}
				
				case "grafico.LinhaGr":{
					xmlContent = xmlContent + RetaXML.salvarReta((LinhaGr) f);
					break;
				}
				
				case "grafico.RetanguloGr":{
					xmlContent = xmlContent + RetanguloXML.salvarRetangulo((RetanguloGr) f);
					break;
				}						
				
				case "grafico.TrianguloGr":{
					xmlContent = xmlContent + TrianguloXML.salvarTriangulo((TrianguloGr) f);
					break;
				}
				
				case "grafico.PoligonoGr":{
					xmlContent = xmlContent + PoligonoXML.salvarPoligono((PoligonoGr) f);
					break;
				}
				
				case "grafico.LinhaPoligonalGr":{
					xmlContent = xmlContent + LinhaPoligonalXML.salvarLinhaPoligonal((LinhaPoligonalGr) f);
					break;
				}
				
				case "grafico.CirculoGr":{
					xmlContent = xmlContent + CirculoXML.salvarCirculo((CirculoGr) f);
					break;
				}
				
				default:{
					System.out.println(f.getClass().getName());
				}
			}
		}
		
		xmlContent = xmlContent + "</Figura>";
		
		escritor.write(xmlContent);
		escritor.close();		
	}
	
	public ArrayList<Formas> lerXml(Pane pane) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(arqXml);
		NodeList xml, formasXML;
		ArrayList<Formas> formasGr = new ArrayList<>();
		
		doc.getDocumentElement().normalize();
		xml = doc.getElementsByTagName("Figura");
		
		if (xml.getLength() == 1) {
			formasXML = xml.item(0).getChildNodes();
			for (int i = 0; i < formasXML.getLength(); i++) {
				
				Node forma = formasXML.item(i);
				
				switch (forma.getNodeName()) {
					case "Ponto":{
						PontoGr ponto = PontoXML.carregarPonto(forma); 
						ponto.desenhar(pane);
						formasGr.add(ponto);
						break;
					}
				
				}
			}
		}
		
		return formasGr;
		
	}
}
