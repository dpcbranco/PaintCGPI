package grafico;

import formas.Poligono;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PoligonoGr extends Poligono {

	Color cor;
	int borda;
	
	public PoligonoGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Ponto p, Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(pane);
	}

	public void finalizarPoligono(Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(this.getP1()), cor, borda);
		novaLinha.desenhar(pane);
	}
	
}
