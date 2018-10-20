package xml;

import java.util.ArrayList;

import formas.Ponto;
import grafico.LinhaPoligonalGr;
import javafx.scene.paint.Color;

public class LinhaPoligonalXML {
	static final int telaX = 1280;
	static final int telaY = 770;
	
	public static String salvarLinhaPoligonal(LinhaPoligonalGr lp) {
		String linhaPoligonalXML = "<LinhaPoligonal>";
		ArrayList<Ponto> pontos = lp.getPontos();
		Color cor = lp.getCor();
		
		for (Ponto ponto : pontos) {
			linhaPoligonalXML = 	linhaPoligonalXML 
							+ "<Ponto>"
							+	"<x>" + (ponto.getX()/telaX) + "</x>"
							+	"<y>" + (ponto.getY()/telaY) + "</y>"
							+ "</Ponto>";
		}
		
		linhaPoligonalXML = 	linhaPoligonalXML
						+ "<Cor>"
						+ 	"<R>" + (int)(cor.getRed()*255) + "</R>"
						+ 	"<G>" + (int)(cor.getGreen()*255) + "</G>"
						+ 	"<B>" + (int)(cor.getBlue()*255) + "</B>"
						+ "</Cor>"
						+ "</LinhaPoligonal>";
				
		return linhaPoligonalXML;
	}
}
