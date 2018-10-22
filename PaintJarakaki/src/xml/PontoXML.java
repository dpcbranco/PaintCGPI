package xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import grafico.PontoGr;
import javafx.scene.paint.Color;

public class PontoXML {
	
	static final int telaX = 1280;
	static final int telaY = 770;
	
	public static String salvarPonto(PontoGr p) {
		String pontoXML;
		double razaoX = (p.getX()/telaX);
		double razaoY = (p.getY()/telaY);
		Color cor = p.getCor();
		
		pontoXML = 	"<Ponto>"
				+ 		"<x>" + razaoX + "</x>"
				+ 		"<y>" + razaoY + "</y>"
				+ 		"<Cor>"
				+ 			"<R>" + (int) (cor.getRed() * 255) + "</R>"
				+ 			"<G>" + (int) (cor.getGreen()) * 255 + "</G>"
				+ 			"<B>" + (int) (cor.getBlue() * 255) + "</B>"
				+ 		"</Cor>"
				+ 	"</Ponto>" ;
		return pontoXML;
	}
	
	public static PontoGr carregarPonto(Node circulo) {
		
		//Transformação em Element para captura de atributos pelo nome
		Element ePonto = (Element) circulo;
		Element eCor = (Element) circulo.getLastChild(); 
		
		double x = Double.parseDouble(ePonto.getElementsByTagName("x").item(0).getTextContent()) * telaX;
		double y = Double.parseDouble(ePonto.getElementsByTagName("y").item(0).getTextContent()) * telaY;
		
		//RGB da cor
		double r = Double.parseDouble(eCor.getElementsByTagName("R").item(0).getTextContent()) / 255;
		double g = Double.parseDouble(eCor.getElementsByTagName("G").item(0).getTextContent()) / 255;
		double b = Double.parseDouble(eCor.getElementsByTagName("B").item(0).getTextContent()) / 255;
		
		Color cor = new Color(r, g, b, 1);
		
		return new PontoGr((int)x, (int)y, cor, 5);
	}
}
