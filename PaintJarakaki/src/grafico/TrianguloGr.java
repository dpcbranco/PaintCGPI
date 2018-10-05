package grafico;

import formas.Linha;
import formas.Ponto;
import formas.Triangulo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TrianguloGr extends Triangulo implements FormaGr {
	
	Color cCor;
	int iBorda;


	public TrianguloGr(Ponto p1, Ponto p2, Ponto p3, Color cor, int borda) {
		super(p1, p2, p3);
		cCor = cor;
		iBorda = borda;
	}
	
	public void desenhar(Pane pane) {
		
		LinhaGr lgrReta1, lgrReta2, lgrReta3;
		
		lgrReta1 = new LinhaGr(cCor, iBorda);
		lgrReta2 = new LinhaGr(cCor, iBorda);
		lgrReta3 = new LinhaGr(cCor, iBorda);
		
		lgrReta1.setP1(this.p1);
		lgrReta1.setP2(this.p2);
		lgrReta1.desenhar(pane);

		lgrReta2.setP1(this.p1);
		lgrReta2.setP2(this.p3);
		lgrReta2.desenhar(pane);
		
		lgrReta3.setP1(this.p2);
		lgrReta3.setP2(this.p3);
		lgrReta3.desenhar(pane);
		
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
