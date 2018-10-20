package xml;

import formas.Ponto;
import grafico.RetanguloGr;
import javafx.scene.paint.Color;

public class RetanguloXML {
	
	static final int telaX = 1280;
	static final int telaY = 770;
	
	public static String salvarRetangulo(RetanguloGr r) {
		String retanguloXML;
		Ponto p1 = r.getP1();
		Ponto p2 = r.getP2();
		Ponto p3 = r.getP3();
		Ponto p4 = r.getP4();
		Color cor = r.getCor();
		
		retanguloXML = "<Retangulo>"
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
				+ 	"<Ponto>"
				+ 		"<x>" + p4.getX()/telaX + "</x>"
				+ 		"<y>" + p4.getY()/telaY + "</y>"
				+ 	"</Ponto>"
				+ 	"<Cor>"
				+ 		"<R>" + (int)(cor.getRed() * 255) + "</R>"
				+ 		"<G>" + (int)(cor.getGreen() * 255) + "</G>"
				+ 		"<B>" + (int)(cor.getBlue() * 255) + "</B>"
				+ 	"</Cor>"
				+ "</Retangulo>";		
		
		return retanguloXML;
	}
}
