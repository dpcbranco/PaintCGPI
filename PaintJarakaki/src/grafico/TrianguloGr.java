package grafico;

import formas.Linha;
import formas.Ponto;
import formas.Triangulo;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TrianguloGr extends Triangulo implements FormaGr {
	
	Color cCor;
	int iBorda;


	public TrianguloGr(Ponto p1, Ponto p2, Ponto p3, Color cor, int borda) {
		super(p1, p2, p3);
		cCor = cor;
		iBorda = borda;
	}
	
	public void desenhar(GraphicsContext g) {
		
		LinhaGr lgrReta1, lgrReta2, lgrReta3;
		
		lgrReta1 = new LinhaGr(this.p1, this.p2, cCor, iBorda);
		lgrReta2 = new LinhaGr(this.p1, this.p3, cCor, iBorda);
		lgrReta3 = new LinhaGr(this.p2, this.p3, cCor, iBorda);
		
		lgrReta1.desenhar(g);
		lgrReta2.desenhar(g);
		lgrReta3.desenhar(g);
		
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
