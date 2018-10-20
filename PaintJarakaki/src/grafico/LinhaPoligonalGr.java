package grafico;

import java.util.ArrayList;

import formas.LinhaPoligonal;
import formas.Ponto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class LinhaPoligonalGr extends LinhaPoligonal{
	Color cor;
	int borda;
	ArrayList<PontoGr> pontosLPoligonal = new ArrayList<>();
	
	public LinhaPoligonalGr(Ponto p1, Color cor, int borda) {
		super(p1);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Ponto p, Pane pane) {
		LinhaGr novaLinha = new LinhaGr(this.calcularNovaLinha(p), cor, borda);
		novaLinha.desenhar(pane);
		
		pontosLPoligonal.addAll(novaLinha.getPontos());
		
		for (PontoGr ponto : pontosLPoligonal) {
			ponto.obterElipse().setOnMouseClicked( 
				(ev)->{
					selecionar();
				}
			);
		}
	}

	private void selecionar() {
		for (PontoGr p : pontosLPoligonal) {
			Ellipse e = p.obterElipse();
			e.setStroke(Color.FUCHSIA);
		}
	}

	public Color getCor() {
		return this.cor;
	}
	
}
