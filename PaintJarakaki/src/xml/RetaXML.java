package xml;

import formas.Ponto;
import grafico.LinhaGr;
import javafx.scene.paint.Color;

public class RetaXML {
	static final int telaX = 1280;
	static final int telaY = 770;
	
	public static String salvarReta(LinhaGr l) {
		String retaXML;
		Ponto p1 = l.getP1();
		Ponto p2 = l.getP2();
		Color cor = l.getCor();
		
		retaXML = "<Reta>"
				+ 	"<Ponto>"
				+ 		"<x>" + p1.getX()/telaX + "</x>"
				+ 		"<y>" + p1.getY()/telaY + "</y>"
				+ 	"</Ponto>"
				+ 	"<Ponto>"
				+ 		"<x>" + p2.getX()/telaX + "</x>"
				+ 		"<y>" + p2.getY()/telaY + "</y>"
				+ 	"</Ponto>"
				+ 	"<Cor>"
				+ 		"<R>" + (int)(cor.getRed() * 255) + "</R>"
				+ 		"<G>" + (int)(cor.getGreen() * 255) + "</G>"
				+ 		"<B>" + (int)(cor.getBlue() * 255) + "</B>"
				+ 	"</Cor>"
				+ "</Reta>";		
		
		return retaXML;
	}
}
