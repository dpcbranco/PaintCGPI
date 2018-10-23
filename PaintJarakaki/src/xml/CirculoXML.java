package xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import formas.Ponto;
import grafico.CirculoGr;
import javafx.scene.paint.Color;

public class CirculoXML {

	static final int telaX = 1280;
	static final int telaY = 770;
	
	public static String salvarCirculo(CirculoGr c) {
		String circuloXML;
		Ponto centro = c.getCentro();
		int raio = (int) c.getRaio();
		Color cor = c.getCor();
		
		circuloXML = "<Circulo>"
				+ 		"<Ponto>"
				+ 			"<x>" + (centro.getX()/telaX) + "</x>"
				+ 			"<y>" + (centro.getY()/telaY) + "</y>"
				+ 		"</Ponto>"
				+ 		"<Raio>" + raio + "</Raio>"
				+ 		"<Cor>"
				+ 			"<R>" + (int)(cor.getRed()*255) + "</R>"
				+ 			"<G>" + (int)(cor.getGreen()*255) + "</G>"
				+ 			"<B>" + (int)(cor.getBlue()*255) + "</B>"
				+ 		"</Cor>"
				+ 	"</Circulo>";
		
		return circuloXML;
	}

	public static CirculoGr carregarCirculo(Node circulo) {
		NodeList atributosXML = circulo.getChildNodes();
		int raio = 0;
		Ponto centro = null;
		Color cor = new Color(0, 0, 0, 1); //Padrão: Preto
		
		for (int i = 0; i < atributosXML.getLength(); i++) {
			
			if (atributosXML.item(i).getNodeName() == "Ponto") {
				Element ePonto = (Element) atributosXML.item(i);
				
				int x = (int) (Double.parseDouble(ePonto.getElementsByTagName("x").item(0).getTextContent()) * telaX);
				int y = (int) (Double.parseDouble(ePonto.getElementsByTagName("y").item(0).getTextContent()) * telaY);
				
				centro = new Ponto(x, y);
			}
			
			else if (atributosXML.item(i).getNodeName() == "Raio") {
				raio = Integer.parseInt(atributosXML.item(i).getTextContent());
			}
			
			else if (atributosXML.item(i).getNodeName() == "Cor") {
				Element eCor = (Element) atributosXML.item(i);
				
				double r = Double.parseDouble(eCor.getElementsByTagName("R").item(0).getTextContent()) / 255;
				double g = Double.parseDouble(eCor.getElementsByTagName("G").item(0).getTextContent()) / 255;
				double b = Double.parseDouble(eCor.getElementsByTagName("B").item(0).getTextContent()) / 255;
				
				cor = new Color(r, g, b, 1);
			}
			
		}
		
		return new CirculoGr(centro, raio, cor, 5);
	}
}
