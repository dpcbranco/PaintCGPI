package xml;

import java.util.ArrayList;

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
}
