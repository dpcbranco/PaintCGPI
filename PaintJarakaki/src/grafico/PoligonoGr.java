package grafico;

import formas.Poligono;
import formas.Ponto;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PoligonoGr extends Poligono {

	Color cor;
	int borda;
	
	public PoligonoGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Ponto p, GraphicsContext g) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(g);
	}

	public void finalizarPoligono(GraphicsContext g) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(this.getP1()), cor, borda);
		novaLinha.desenhar(g);
	}
	
}
