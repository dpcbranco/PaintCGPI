package formas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Ponto;
import math.Triangulo;

public class TrianguloGr extends Triangulo{
	
	Color cCor;
	int iBorda;
	LinhaGr lgrReta1, lgrReta2, lgrReta3;

	public TrianguloGr(Ponto p1, Ponto p2, Ponto p3, Color cor, int borda) {
		super(p1, p2, p3);
		cCor = cor;
		iBorda = borda;
	}
	
	public void desenharTriangulo(GraphicsContext g) {
		
		lgrReta1 = new LinhaGr(this.p1, this.p2, cCor, iBorda);
		lgrReta2 = new LinhaGr(this.p1, this.p3, cCor, iBorda);
		lgrReta3 = new LinhaGr(this.p2, this.p3, cCor, iBorda);
		
		lgrReta1.desenharLinha(g);
		lgrReta2.desenharLinha(g);
		lgrReta3.desenharLinha(g);
		
	}
	
	//retorna lista de pontos médios das retas do triângulo
	public Ponto[] getPontosMedios(){
		Ponto[] pPontosMedios = new Ponto[3];
		
		pPontosMedios[0] = lgrReta1.getPontoMedio();
		pPontosMedios[1] = lgrReta2.getPontoMedio();
		pPontosMedios[2] = lgrReta3.getPontoMedio();
		
		return pPontosMedios;
	}
	
	public Ponto getPontoMedioP1P2() {
		return lgrReta1.getPontoMedio();
	}

	public Ponto getPontoMedioP1P3() {
		return lgrReta2.getPontoMedio();
	}
	
	public Ponto getPontoMedioP2P3() {
		return lgrReta3.getPontoMedio();
	}

}
