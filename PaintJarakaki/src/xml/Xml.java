package xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import formas.Formas;
import grafico.CirculoGr;
import grafico.LinhaGr;
import grafico.LinhaPoligonalGr;
import grafico.PoligonoGr;
import grafico.PontoGr;
import grafico.RetanguloGr;
import grafico.TrianguloGr;

public class Xml {
	File arqXml;
	BufferedWriter escritor;
	
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
}
