package grafico;

import java.util.ArrayList;

import formas.Linha;
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
	
	public LinhaPoligonalGr(ArrayList<Ponto> pontos, Color cor, int borda) {
		super(pontos);
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
	
	//Usado na leitura do XML - Quando todos os pontos do poligono estão definidos
	public void desenharCarregado (Pane pane) {
		ArrayList<Linha> linhasPoligono = this.calcularLPoligonal();
			
		for (Linha l : linhasPoligono) {
			LinhaGr novaLinha = new LinhaGr(l, cor, borda);
			novaLinha.desenhar(pane);
			
			for (PontoGr ponto : novaLinha.getPontos()) {
				ponto.obterElipse().setOnMouseClicked( 
					(ev)->{
						selecionar();
					}
				);
			}
				
			pontosLPoligonal.addAll(novaLinha.getPontos());
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
