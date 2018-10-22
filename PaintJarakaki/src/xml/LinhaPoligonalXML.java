package xml;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	public static LinhaPoligonalGr carregarLPoligonal(Node lPoligonal) {

		//Transformação em Element para captura de atributos pelo nome
		NodeList pontosXML = lPoligonal.getChildNodes();
		ArrayList<Ponto> pontosRet = new ArrayList<>();
		Color cor = new Color(0, 0, 0, 1); //Padrão: Preto
		
		for (int i = 0; i < pontosXML.getLength(); i++) {
				
			if (pontosXML.item(i).getNodeName() == "Ponto") {
				Element ePonto = (Element) pontosXML.item(i);
						
				int x = (int) (Double.parseDouble(ePonto.getElementsByTagName("x").item(0).getTextContent()) * telaX);
				int y = (int) (Double.parseDouble(ePonto.getElementsByTagName("y").item(0).getTextContent()) * telaY);
						
				pontosRet.add(new Ponto(x, y));
			}
					
			else {
				Element eCor = (Element) pontosXML.item(i);
						
				double r = Double.parseDouble(eCor.getElementsByTagName("R").item(0).getTextContent()) / 255;
				double g = Double.parseDouble(eCor.getElementsByTagName("G").item(0).getTextContent()) / 255;
				double b = Double.parseDouble(eCor.getElementsByTagName("B").item(0).getTextContent()) / 255;
						
				cor = new Color(r, g, b, 1);
			}
					
		}
				
		return new LinhaPoligonalGr(pontosRet, cor, 5);
	}
}
