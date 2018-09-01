package figuras;

import java.util.ArrayList;

import formas.PontoGr;
import formas.TrianguloGr;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Linha;

public class Sierpinski {
	
	int borda;
	Color clCor;
	ArrayList<TrianguloGr> alFilhos = new ArrayList<>();
	
	
	public void setBorda(int borda) {
		this.borda = borda;
	}


	public void setCor(Color clCor) {
		this.clCor = clCor;
	}

	public void desenharIteracao(GraphicsContext g) {
		TrianguloGr tgrNovo;
		ArrayList<TrianguloGr> alGalhos = new ArrayList<>();
		
		alGalhos.addAll(alFilhos);
		
		alFilhos.clear();
			
		if (alGalhos.isEmpty()) {
			PontoGr p1;
			PontoGr p2 = new PontoGr(240, 700, clCor, borda);
			PontoGr p3 = new PontoGr(1040, 700, clCor, borda);
			
			//Usado para calcular p1
			Linha lp2p3 = new Linha(p2, p3);
			
			
			p1 = new PontoGr(
					new Double(lp2p3.getPontoMedio().getX()).intValue(), 
					700 - new Double(Math.sqrt( Math.pow(lp2p3.getComprimento(), 2) - Math.pow(lp2p3.getComprimento() / 2, 2) )).intValue(), 
					clCor, 
					borda
				);
				
			tgrNovo = new TrianguloGr(p1, p2, p3, clCor, borda);
			tgrNovo.desenharTriangulo(g);
			
			alFilhos.add(tgrNovo);
		}
		
		else {
			for (TrianguloGr tgrFilho : alGalhos) {
				//desenha triangulo tendo p1 do pai como base
				tgrNovo = new TrianguloGr(tgrFilho.getP1(), tgrFilho.getPontoMedioP1P2(), tgrFilho.getPontoMedioP1P3(), clCor, borda);
				tgrNovo.desenharTriangulo(g);
				alFilhos.add(tgrNovo);
			
				//desenha triangulo tendo p2 do pai como base
				tgrNovo = new TrianguloGr(tgrFilho.getPontoMedioP1P2(), tgrFilho.getP2(), tgrFilho.getPontoMedioP2P3(), clCor, borda);
				tgrNovo.desenharTriangulo(g);
				alFilhos.add(tgrNovo);
			
				//desenha triangulo tendo p3 do pai como base
				tgrNovo = new TrianguloGr(tgrFilho.getPontoMedioP1P3(), tgrFilho.getPontoMedioP2P3(), tgrFilho.getP3(), clCor, borda);
				tgrNovo.desenharTriangulo(g);
				alFilhos.add(tgrNovo);
			
			}
		}	
	}
}
