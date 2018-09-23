package formas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Linha;
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
		
		LinhaGr lgrReta1, lgrReta2, lgrReta3;
		
		lgrReta1 = new LinhaGr(this.p1, this.p2, cCor, iBorda);
		lgrReta2 = new LinhaGr(this.p1, this.p3, cCor, iBorda);
		lgrReta3 = new LinhaGr(this.p2, this.p3, cCor, iBorda);
		
		lgrReta1.desenharLinha(g);
		lgrReta2.desenharLinha(g);
		lgrReta3.desenharLinha(g);
		
	}
	
	
	public Ponto getPontoMedioP1P2() {
		Linha lreta = new Linha(this.p1, this.p2);		
		return lreta.getPontoMedio();
	}

	public Ponto getPontoMedioP1P3() {
		Linha lreta = new Linha(this.p1, this.p3);		
		return lreta.getPontoMedio();
	}
	
	public Ponto getPontoMedioP2P3() {
		Linha lreta = new Linha(this.p2, this.p3);		
		return lreta.getPontoMedio();
	}

}
