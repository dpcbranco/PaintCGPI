package grafico;

import java.util.ArrayList;

import formas.Linha;
import formas.Ponto;
import formas.Retangulo;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class RetanguloGr extends Retangulo implements FormaGr {

	Color cor;
	int borda;
	ArrayList<PontoGr> pontosRetangulo = new ArrayList<>();
	
	public RetanguloGr(Ponto p1, Ponto p2, Color cor, int borda) {
		super(p1, p2);
		this.cor = cor;
		this.borda = borda;
	}

	public void desenhar(Pane pane) {
		ArrayList<Linha> retasRetangulo = this.calcularRetangulo();
		LinhaGr retaGrafica;
		
		for (Linha reta : retasRetangulo) {
			retaGrafica = new LinhaGr(reta, cor, borda);	
			retaGrafica.desenhar(pane);
			pontosRetangulo.addAll(retaGrafica.getPontos());
		}
		
		for (PontoGr ponto : pontosRetangulo) {
			Ellipse e = ponto.obterElipse();
			e.setOnMouseClicked(  
				(ev)-> {
					this.selecionar();
				}
			);
		}
	}

	private void selecionar() {
		for (PontoGr p : pontosRetangulo) {
			Ellipse e = p.obterElipse();
			e.setFill(Color.FUCHSIA);
		}
	}

}
