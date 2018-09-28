package grafico;

import formas.LinhaPoligonal;
import formas.Ponto;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LinhaPoligonalGr extends LinhaPoligonal{
	Color cor;
	int borda;
	
	public LinhaPoligonalGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Ponto p, GraphicsContext g) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(g);
	}
	
}
