package xml;

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
}
