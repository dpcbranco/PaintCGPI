package xml;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import formas.Ponto;
import grafico.TrianguloGr;
import javafx.scene.paint.Color;

public class TrianguloXML {
	static final int telaX = 1280;
	static final int telaY = 770;
	
	public static String salvarTriangulo(TrianguloGr t) {
		String trianguloXML;
		Ponto p1 = t.getP1();
		Ponto p2 = t.getP2();
		Ponto p3 = t.getP3();
		Color cor = t.getCor();
		
		trianguloXML = "<Triangulo>"
				+ 	"<Ponto>"
				+ 		"<x>" + p1.getX()/telaX + "</x>"
				+ 		"<y>" + p1.getY()/telaY + "</y>"
				+ 	"</Ponto>"
				+ 	"<Ponto>"
				+ 		"<x>" + p2.getX()/telaX + "</x>"
				+ 		"<y>" + p2.getY()/telaY + "</y>"
				+ 	"</Ponto>"
				+ 	"<Ponto>"
				+ 		"<x>" + p3.getX()/telaX + "</x>"
				+ 		"<y>" + p3.getY()/telaY + "</y>"
				+ 	"</Ponto>"
				+ 	"<Cor>"
				+ 		"<R>" + (int)(cor.getRed() * 255) + "</R>"
				+ 		"<G>" + (int)(cor.getGreen() * 255) + "</G>"
				+ 		"<B>" + (int)(cor.getBlue() * 255) + "</B>"
				+ 	"</Cor>"
				+ "</Triangulo>";		
		
		return trianguloXML;
	}

	public static TrianguloGr carregarTriangulo(Node triangulo) {
		
		//Transformação em Element para captura de atributos pelo nome
		NodeList pontosXML = triangulo.getChildNodes();
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
				
		return new TrianguloGr(pontosRet.get(0), pontosRet.get(1), pontosRet.get(2), cor, 5);
	}

}
