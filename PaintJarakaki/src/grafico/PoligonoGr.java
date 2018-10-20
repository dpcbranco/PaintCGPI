package grafico;

import java.util.ArrayList;

import formas.Poligono;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PoligonoGr extends Poligono {

	Color cor;
	int borda;
	ArrayList<PontoGr> pontosPoligono = new ArrayList<>();
	
	public PoligonoGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Ponto p, Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(pane);
		
		pontosPoligono.addAll(novaLinha.getPontos());
		
		for (PontoGr ponto : pontosPoligono) {
			ponto.obterElipse().setOnMouseClicked( 
				(ev)->{
					selecionar();
				}
			);
		}
	}

	private void selecionar() {
		for (PontoGr p : pontosPoligono) {
			Ellipse e = p.obterElipse();
			e.setStroke(Color.FUCHSIA);
		}
	}

	public void finalizarPoligono(Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(this.getP1()), cor, borda);
		novaLinha.desenhar(pane);
	}

	public Color getCor() {
		return this.cor;
	}
	
}
