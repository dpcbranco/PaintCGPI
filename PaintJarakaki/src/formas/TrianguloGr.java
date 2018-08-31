package formas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Ponto;
import math.Triangulo;

public class TrianguloGr extends Triangulo{
	
	Color cCor;
	int iBorda;

	public TrianguloGr(Ponto p1, Ponto p2, Ponto p3, Color cor, int borda) {
		super(p1, p2, p3);
		cCor = cor;
		iBorda = borda;
	}
	
	public void desenharTriangulo(GraphicsContext g) {
		new LinhaGr(this.p1, this.p2, cCor, iBorda).desenharLinha(g);
		new LinhaGr(this.p1, this.p3, cCor, iBorda).desenharLinha(g);
		new LinhaGr(this.p2, this.p3, cCor, iBorda).desenharLinha(g);
		
	}

}
