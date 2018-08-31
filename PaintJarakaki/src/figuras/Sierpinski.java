package figuras;

import formas.TrianguloGr;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Sierpinski {
	
	int iIteracoes, iBorda;
	Color cCor;

	public Sierpinski(int iIteracoes, int iBorda, Color cCor) {
		this.iIteracoes = iIteracoes;
		this.iBorda = iBorda;
		this.cCor = cCor;
	}
	
	public void desenharSierpinski(GraphicsContext g) {
		
	}
	
	private void desenharIteracao(TrianguloGr t, int i) {
		
	}
}
