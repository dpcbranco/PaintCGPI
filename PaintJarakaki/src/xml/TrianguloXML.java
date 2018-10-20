package xml;

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

}
