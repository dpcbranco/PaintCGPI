package xml;

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
}
