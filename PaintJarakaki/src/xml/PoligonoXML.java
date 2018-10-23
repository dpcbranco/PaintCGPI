package xml;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import formas.Ponto;
import grafico.PoligonoGr;
import javafx.scene.paint.Color;

public class PoligonoXML {
	
	static final int telaX = 1280;
	static final int telaY = 770;
	
	public static String salvarPoligono(PoligonoGr p) {
		String poligonoXML = "<Poligono>";
		ArrayList<Ponto> pontos = p.getPontos();
		Color cor = p.getCor();
		
		for (Ponto ponto : pontos) {
			poligonoXML = 	poligonoXML 
							+ "<Ponto>"
							+	"<x>" + (ponto.getX()/telaX) + "</x>"
							+	"<y>" + (ponto.getY()/telaY) + "</y>"
							+ "</Ponto>";
		}
		
		poligonoXML = 	poligonoXML
						+ "<Cor>"
						+ 	"<R>" + (int)(cor.getRed()*255) + "</R>"
						+ 	"<G>" + (int)(cor.getGreen()*255) + "</G>"
						+ 	"<B>" + (int)(cor.getBlue()*255) + "</B>"
						+ "</Cor>"
						+ "</Poligono>";
				
		return poligonoXML;
	}

	public static PoligonoGr carregarPoligono(Node poligono) {
		
		//Transformação em Element para captura de atributos pelo nome
		NodeList pontosXML = poligono.getChildNodes();
		ArrayList<Ponto> pontosRet = new ArrayList<>();
		Color cor = new Color(0, 0, 0, 1); //Padrão: Preto
		
		for (int i = 0; i < pontosXML.getLength(); i++) {
				
			if (pontosXML.item(i).getNodeName() == "Ponto") {
				Element ePonto = (Element) pontosXML.item(i);
						
				int x = (int) (Double.parseDouble(ePonto.getElementsByTagName("x").item(0).getTextContent()) * telaX);
				int y = (int) (Double.parseDouble(ePonto.getElementsByTagName("y").item(0).getTextContent()) * telaY);
						
				pontosRet.add(new Ponto(x, y));
			}
					
			else if (pontosXML.item(i).getNodeName() == "Cor") {
				Element eCor = (Element) pontosXML.item(i);
						
				double r = Double.parseDouble(eCor.getElementsByTagName("R").item(0).getTextContent()) / 255;
				double g = Double.parseDouble(eCor.getElementsByTagName("G").item(0).getTextContent()) / 255;
				double b = Double.parseDouble(eCor.getElementsByTagName("B").item(0).getTextContent()) / 255;
						
				cor = new Color(r, g, b, 1);
			}
					
		}
				
		return new PoligonoGr(pontosRet, cor, 5);
	}
}
