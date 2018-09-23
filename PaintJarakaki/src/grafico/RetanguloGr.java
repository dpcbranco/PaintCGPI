package grafico;

import java.util.ArrayList;

import formas.Linha;
import formas.Ponto;
import formas.Retangulo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RetanguloGr extends Retangulo implements FormaGr {

	Color cor;
	int borda;
	
	public RetanguloGr(Ponto p1, Ponto p2, Color cor, int borda) {
		super(p1, p2);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(GraphicsContext g) {
		ArrayList<Linha> retasRetangulo = this.calcularRetangulo();
		LinhaGr retaGrafica;
		
		for (Linha reta : retasRetangulo) {
			retaGrafica = new LinhaGr(reta, cor, borda);	
			retaGrafica.desenhar(g);
		}
	}

}
