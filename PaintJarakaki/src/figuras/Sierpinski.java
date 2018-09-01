package figuras;

import java.util.ArrayList;

import formas.PontoGr;
import formas.TrianguloGr;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Linha;

public class Sierpinski {
	
	int iteracoes, borda;
	Color clCor;

	public Sierpinski(int iIteracoes, int borda, Color clCor) {
		this.iteracoes = iIteracoes;
		this.borda = borda;
		this.clCor = clCor;
	}
	
	public void desenharSierpinski(GraphicsContext g) {
		PontoGr p1;
		PontoGr p2 = new PontoGr(240, 700, clCor, borda);
		PontoGr p3 = new PontoGr(1040, 700, clCor, borda);
		
		TrianguloGr tgrPai;
		
		//Usado para calcular p1
		Linha lp2p3 = new Linha(p2, p3);
		
		
		p1 = new PontoGr(
				new Double(lp2p3.getPontoMedio().getX()).intValue(), 
				700 - new Double(Math.sqrt( Math.pow(lp2p3.getComprimento(), 2) - Math.pow(lp2p3.getComprimento() / 2, 2) )).intValue(), 
				clCor, 
				borda
			);
			
		tgrPai = new TrianguloGr(p1, p2, p3, clCor, borda);
		tgrPai.desenharTriangulo(g);
		
		desenharIteracao(tgrPai, iteracoes, g);
	}
	
	private void desenharIteracao(TrianguloGr t, int iIteracoes, GraphicsContext g) {
		ArrayList<TrianguloGr> alFilhos = new ArrayList<>();
		TrianguloGr tgrNovo;
		
		//desenha triangulo tendo p1 do pai como base
		tgrNovo = new TrianguloGr(t.getP1(), t.getPontoMedioP1P2(), t.getPontoMedioP1P3(), clCor, borda);
		tgrNovo.desenharTriangulo(g);
		alFilhos.add(tgrNovo);
		
		//desenha triangulo tendo p2 do pai como base
		tgrNovo = new TrianguloGr(t.getPontoMedioP1P2(), t.getP2(), t.getPontoMedioP2P3(), clCor, borda);
		tgrNovo.desenharTriangulo(g);
		alFilhos.add(tgrNovo);
		
		//desenha triangulo tendo p3 do pai como base
		tgrNovo = new TrianguloGr(t.getPontoMedioP1P3(), t.getPontoMedioP2P3(), t.getP3(), clCor, borda);
		tgrNovo.desenharTriangulo(g);
		alFilhos.add(tgrNovo);
		

		if (iIteracoes != 1) {
			for (TrianguloGr tgrFilho : alFilhos) {
				desenharIteracao(tgrFilho, iIteracoes-1, g);
			}
		}
		
	}
}
